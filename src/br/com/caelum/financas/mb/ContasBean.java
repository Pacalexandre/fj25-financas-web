package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.financas.dao.*;
import br.com.caelum.financas.modelo.Conta;

import javax.enterprise.context.SessionScoped;
import javax.inject.*;

@Named
@SessionScoped
public class ContasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContaDao dao;

	private Conta conta = new Conta();
	private List<Conta> contas;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public void grava() {
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
