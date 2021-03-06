package br.com.caelum.financas.mb;

import br.com.caelum.financas.modelo.Conta;
import javax.enterprise.context.RequestScoped;
import javax.inject.*;
import javax.persistence.*;

@Named
@RequestScoped
public class CacheBean {
	
	@Inject
	private EntityManager manager;
	
	private Integer id;
	private Conta conta;
	
	public void pesquisar() {
			this.conta = this.manager.find(Conta.class, id);
			this.conta = this.manager.find(Conta.class, id);
			this.conta = this.manager.find(Conta.class, id);
			this.conta = this.manager.find(Conta.class, id);
			System.out.println("Testando cache de primeiro nivel");
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