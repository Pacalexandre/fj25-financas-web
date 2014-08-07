package br.com.caelum.financas.dao;

import java.util.List;

import javax.ejb.*;
import javax.inject.*;
import javax.persistence.*;

import br.com.caelum.financas.modelo.Conta;

@Stateless
public class ContaDao {

	@Inject
	//@PersistenceContext
	EntityManager manager;

	public void adiciona(Conta conta) {
		this.manager.persist(conta);
	}

	public void altera(Conta conta) {
		this.manager.merge(conta);
	}

	public Conta busca(Integer id) {
		return this.manager.find(Conta.class, id);
	}

	public List<Conta> lista() {
		return this.manager.createQuery("select c from Conta c", Conta.class)
				.getResultList();
	}

	
	public void remove(Conta conta) {
		Conta contaParaRemover = this.manager.find(Conta.class, conta.getId());
		this.manager.remove(contaParaRemover);
	}
	
	public int trocaNomeDoBancoEmLote(String antigoNomeBanco, String novoNomeBanco){
		
		String jpql = "Update Conta c set c.banco = :novoNome"+
		" where c.banco = :antigoNome";
		Query query = manager.createQuery(jpql);
		query.setParameter("antigoNome", antigoNomeBanco);
		query.setParameter("novoNome", novoNomeBanco);		
		return query.executeUpdate();
	}

}
