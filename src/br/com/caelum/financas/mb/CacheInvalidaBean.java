package br.com.caelum.financas.mb;

import br.com.caelum.financas.modelo.Conta;
import javax.enterprise.context.RequestScoped;
import javax.inject.*;
import javax.persistence.*;

@Named
@RequestScoped
public class CacheInvalidaBean {
	@Inject
	private Cache cache;
	
	private Integer id;
	private Conta conta;
	

	public void invalidar() {
		cache.evict(Conta.class,id);
		System.out.println("Invalidando o cache programaticamente");
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Conta getConta() {
		return conta;
	}
}
