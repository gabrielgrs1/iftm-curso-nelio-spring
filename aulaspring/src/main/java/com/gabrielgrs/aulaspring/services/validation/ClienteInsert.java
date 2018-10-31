package com.gabrielgrs.aulaspring.services.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ClienteInsertValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteInsert {

    String message() default "Erro de validacao";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
