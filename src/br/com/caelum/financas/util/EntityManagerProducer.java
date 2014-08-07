package br.com.caelum.financas.util;

import javax.enterprise.context.*;
import javax.enterprise.inject.*;
import javax.inject.*;
import javax.persistence.*;

import br.com.caelum.financas.modelo.*;


@ApplicationScoped
public class EntityManagerProducer {

	@PersistenceUnit
	EntityManagerFactory factory;
	
	@Produces
	@ApplicationScoped
	public Cache getCache(){
		return factory.getCache();
	}

	@Produces
	@RequestScoped
	private EntityManager getEntityManager() throws Exception {
		return factory.createEntityManager();
	}

	public void close(@Disposes EntityManager manager) throws Exception {
		manager.close();
	}

}
