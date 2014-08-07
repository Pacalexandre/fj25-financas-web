package br.com.caelum.financas.dao;

import java.util.*;

import javax.ejb.*;
import javax.inject.*;
import javax.persistence.*;

import br.com.caelum.financas.modelo.*;

@Stateless
public class CategoriaDao {

	@Inject
	//@PersistenceContext
	private EntityManager manager;

	public Categoria procura(Integer id) {
		return manager.find(Categoria.class, id);
	}

	public List<Categoria> lista() {
		String jpql = "from Categoria";
		TypedQuery<Categoria> q = manager.createQuery(jpql, Categoria.class);
		return q.getResultList();

	}

}
