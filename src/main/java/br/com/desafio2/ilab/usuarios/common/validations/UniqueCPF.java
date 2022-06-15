package br.com.desafio2.ilab.usuarios.common.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCPFValidator.class)
public @interface UniqueCPF {

    String message() default "Este CPF já está em uso.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}