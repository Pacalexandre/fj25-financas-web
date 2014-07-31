package br.com.caelum.financas.dao;

import java.math.*;
import java.util.List;

import javax.ejb.*;
import javax.persistence.*;

import br.com.caelum.financas.modelo.*;

@Stateless
public class MovimentacaoDao {
	@PersistenceContext
	EntityManager manager;

	public void adiciona(Movimentacao movimentacao) {
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

	public List<Movimentacao> listaTodasMovimentacoes(Conta conta) {
		String jpql = "select m from Movimentacao m where m.conta = :conta order by m.valor desc";
		TypedQuery<Movimentacao> query = this.manager.createQuery(jpql,Movimentacao.class);
		query.setParameter("conta", conta);
		return query.getResultList();
	}
	
	public List<Movimentacao> listaPorValorETipo(BigDecimal valor, TipoMovimentacao tipo){
		String jpql = "select m from Movimentacao m where m.valor <= :valor and m.tipoMovimentacao = :tipo";
		TypedQuery<Movimentacao> query = this.manager.createQuery(jpql , Movimentacao.class);
		query.setParameter("valor", valor);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}
	
	public BigDecimal calculaTotalMovimentado(Conta conta,TipoMovimentacao tipo){
		String jpql="select sum(m.valor) from Movimentacao m where m.conta=:conta and m.tipoMovimentacao=:tipo";
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

}
