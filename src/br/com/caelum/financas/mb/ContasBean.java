package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.financas.dao.*;
import br.com.caelum.financas.modelo.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.*;
import javax.validation.*;

@Named
@SessionScoped
public class ContasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContaDao dao;
	
	@Inject
	private Validator validador;

	private Conta conta = new Conta();
	private List<Conta> contas;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	
	public void grava() {	
		Set<ConstraintViolation<Conta>> error = validador.validate(this.conta);
		if (!error.isEmpty()){
			return;
		}
		
		if (this.conta.getId() == null){
			dao.adiciona(conta);
		} else {
			dao.altera(conta);
		}
		this.contas=dao.lista();
		limpaFormularioDoJSF();
	}

	public List<Conta> getContas() {
		if (this.contas == null) {
			this.contas = dao.lista();	
		}
		return this.contas;
	}

	public void remove() {
		dao.remove(conta);
		this.contas=dao.lista();
		limpaFormularioDoJSF();
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.conta = new Conta();
	}
}
