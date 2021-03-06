package br.com.caelum.financas.dao;

import java.math.*;
import java.util.*;

import javax.ejb.*;
import javax.inject.*;
import javax.persistence.*;
import javax.persistence.criteria.*;

import br.com.caelum.financas.modelo.*;

@Stateless
public class MovimentacaoDao {
	
	@Inject
	EntityManager manager;

	public void adiciona(Movimentacao movimentacao) {
		this.manager.joinTransaction();
		this.manager.persist(movimentacao);
	}

	public void altera(Movimentacao movimentacao) {
		this.manager.merge(movimentacao);
	}

	public Movimentacao busca(Integer id) {
		return this.manager.find(Movimentacao.class, id);
	}

	public List<Movimentacao> lista() {
		return this.manager.createQuery("select m from Movimentacao m",
				Movimentacao.class).getResultList();
	}
	
	public List<Movimentacao> listaComCategoria() {
		return this.manager.createQuery("select m from Movimentacao m left join fetch m.categorias",
				Movimentacao.class).getResultList();
	}

	public List<Movimentacao> listaTodasMovimentacoes(Conta conta) {
		String jpql = "select m from Movimentacao m where m.conta = :conta order by m.valor desc";
		TypedQuery<Movimentacao> query = this.manager.createQuery(jpql,
				Movimentacao.class);
		query.setParameter("conta", conta);
		return query.getResultList();
	}

	
	public List<Movimentacao> listaPorValorETipo(BigDecimal valor,
			TipoMovimentacao tipo) {
		String jpql = "select m from Movimentacao m where m.valor <= :valor and m.tipoMovimentacao = :tipo";
		TypedQuery<Movimentacao> query = this.manager.createQuery(jpql,
				Movimentacao.class);
		//query.setHint("org.hibernate.cacheable", "true");
		query.setParameter("valor", valor);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}

	public BigDecimal calculaTotalMovimentado(Conta conta, TipoMovimentacao tipo) {
		String jpql = "select sum(m.valor) from Movimentacao m where m.conta=:conta and m.tipoMovimentacao=:tipo";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("conta", conta);
		query.setParameter("tipo", tipo);
		return (BigDecimal) query.getSingleResult();
	}

	public void remove(Movimentacao movimentacao) {
		Movimentacao movimentacaoParaRemover = this.manager.find(
				Movimentacao.class, movimentacao.getId());
		this.manager.remove(movimentacaoParaRemover);
	}

	public List<ValorPorMesEAno> listaMesesComMovimentacoes(Conta conta,
			TipoMovimentacao tipo) {
		String jpql = "select new br.com.caelum.financas.modelo.ValorPorMesEAno(month(m.data),year(m.data),sum(m.valor)) "
				+ "from Movimentacao m "
				+ "where m.conta=:conta and m.tipoMovimentacao=:tipo "
				+ "group by year(m.data)|| month(m.data) "
				+ "order by sum(m.valor) desc";
		Query q = this.manager.createQuery(jpql);
		q.setParameter("conta", conta);
		q.setParameter("tipo", tipo);
		return q.getResultList();
	}
	
	public List<Movimentacao> pesquisa(Conta conta, TipoMovimentacao tipoMovimentacao,Integer mes){
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> criteria = builder.createQuery(Movimentacao.class);
		Root<Movimentacao> root= criteria.from(Movimentacao.class);
		Predicate conjuction = builder.conjunction();
		if (conta.getId() != null){
			conjuction = builder.and(conjuction,builder.equal(root.<Conta>get("conta"), conta));
		}
		if (mes != null && mes !=0) {
			Expression<Integer> expression = builder.function("mounth", Integer.class, root.<Calendar> get("data"));
			conjuction = builder.and(conjuction, builder.equal(expression,mes));
		}
		if (tipoMovimentacao != null){
			conjuction = builder.and(conjuction,builder.equal(root.<TipoMovimentacao> get("tipoMovimentacao"), tipoMovimentacao));
		}
		criteria.where(conjuction);
		return this.manager.createQuery(criteria).getResultList();
	}

}
