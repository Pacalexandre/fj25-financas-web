package br.com.caelum.financas.util;

import javax.inject.*;
import javax.persistence.*;
import javax.validation.*;

import br.com.caelum.financas.modelo.*;


public class NumeroEAgenciaValidator implements ConstraintValidator<NumeroEAgencia, Conta> {

	@Inject
	private EntityManager manager;
	
	@Override
	public void initialize(NumeroEAgencia arg0) {
		
	}

	@Override
	public boolean isValid(Conta arg0, ConstraintValidatorContext arg1) {
		if (arg0 == null) {
		 return true;
	}
		manager.find(Conta.class, arg0.getId());
		
		
		boolean agenciaEhVazia = (arg0.getAgencia() == null || arg0.getAgencia().trim().isEmpty());
		boolean numeroEhVazio = (arg0.getNumero() == null || arg0.getNumero().trim().isEmpty());
		return !(agenciaEhVazia ^ numeroEhVazio);
	}
}
