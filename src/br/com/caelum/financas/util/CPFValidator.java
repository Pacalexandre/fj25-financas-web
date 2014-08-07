package br.com.caelum.financas.util;

import javax.validation.*;

public class CPFValidator implements ConstraintValidator<Cpf, String> {

	@Override
	public void initialize(Cpf arg0) {
		
	}

	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		return true;
	}
	


}
