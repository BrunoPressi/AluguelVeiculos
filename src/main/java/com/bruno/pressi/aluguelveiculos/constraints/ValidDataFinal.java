package com.bruno.pressi.aluguelveiculos.constraints;

import com.bruno.pressi.aluguelveiculos.constraints.validators.ValidatorCNH;
import com.bruno.pressi.aluguelveiculos.constraints.validators.ValidatorDataFinal;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatorDataFinal.class) // classe que vai validar
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDataFinal {

    String message() default "A data final deve ser depois que a data de início.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
