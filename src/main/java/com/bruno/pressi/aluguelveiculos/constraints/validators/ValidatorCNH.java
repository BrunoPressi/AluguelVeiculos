package com.bruno.pressi.aluguelveiculos.constraints.validators;

import com.bruno.pressi.aluguelveiculos.constraints.ValidCNH;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidatorCNH implements ConstraintValidator<ValidCNH, String> {

    @Override
    public void initialize(ValidCNH constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cnh, ConstraintValidatorContext constraintValidatorContext) {
        if (cnh.chars().distinct().count() == 1) {
            return false;
        }

        if (cnh == null || cnh.length() != 11) {
            return false; // CNH inválida
        }

        int[] numeros = cnh.chars().map(c -> c - '0').toArray();

        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += numeros[i] * (9 - i);
        }
        int dv1 = soma1 % 11;
        dv1 = (dv1 >= 10) ? 0 : dv1;

        int soma2 = 0;
        for (int i = 0; i < 9; i++) {
            soma2 += numeros[i] * (i + 1);
        }
        int dv2 = soma2 % 11;
        dv2 = (dv2 >= 10) ? 0 : dv2;

        return numeros[9] == dv1 && numeros[10] == dv2;
    }
}
