package br.com.caelum.financas.util;

import java.lang.annotation.*;

import javax.validation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CPFValidator.class)
public @interface Cpf {
	String message() default "CPF invalido";

}
