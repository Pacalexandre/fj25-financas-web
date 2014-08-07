package br.com.caelum.financas.util;

import java.lang.annotation.*;

import javax.validation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumeroEAgenciaValidator.class)
public @interface NumeroEAgencia {
	String message() default "Nao foi digitado corretamente Agencia";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
