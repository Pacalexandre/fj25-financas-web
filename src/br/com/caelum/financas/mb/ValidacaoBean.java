package br.com.caelum.financas.mb;

import java.util.*;

import javax.enterprise.context.*;
import javax.faces.application.*;
import javax.faces.context.*;
import javax.inject.*;
import javax.validation.*;

import br.com.caelum.financas.modelo.*;

@Named
@RequestScoped
public class ValidacaoBean {

	private Conta conta = new Conta();
	@Inject
	private Validator validador;
	

	public void validar() {
		Set<ConstraintViolation<Conta>> erros = validador.validate(conta);
		for (ConstraintViolation<Conta> erro : erros){
			geraMensagemJsf(erro);
		}
		
		
		
	}
	
	public Conta getConta() {
		return conta;
	}

	/**
	 * Esse metodo disponibiliza uma mensagem para a tela JSF.
	 */
	private void geraMensagemJsf(ConstraintViolation<Conta> erro) {
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(erro.getPropertyPath().toString() + "  " + erro.getMessage()));
	}
	

	
}
