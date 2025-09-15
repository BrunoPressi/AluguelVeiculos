package com.bruno.pressi.aluguelveiculos.constraints.validators;

import com.bruno.pressi.aluguelveiculos.constraints.ValidDataFinal;
import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelCreateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.time.LocalDate;

public class ValidatorDataFinal implements ConstraintValidator<ValidDataFinal, AluguelCreateDto> {

    @Override
    public void initialize(ValidDataFinal constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AluguelCreateDto aluguelCreateDto, ConstraintValidatorContext constraintValidatorContext) {

        LocalDate dataInicio = aluguelCreateDto.getDataInicio();
        LocalDate dataFinal = aluguelCreateDto.getDataFim();

        return dataFinal.isAfter(dataInicio);
    }
}
