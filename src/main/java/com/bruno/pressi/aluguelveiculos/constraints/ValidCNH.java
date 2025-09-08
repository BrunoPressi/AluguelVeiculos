package com.bruno.pressi.aluguelveiculos.constraints;

import com.bruno.pressi.aluguelveiculos.constraints.validators.ValidatorCNH;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatorCNH.class) // classe que vai validar
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCNH {

    String message() default "Número de CNH inválido. Tente novamente";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
