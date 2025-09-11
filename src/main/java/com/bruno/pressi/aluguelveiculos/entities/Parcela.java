package com.bruno.pressi.aluguelveiculos.entities;

import com.bruno.pressi.aluguelveiculos.entities.enums.ParcelaStatus;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.naming.Name;
import java.time.LocalDate;

public class Parcela {

    private double valor;

    @Field(name = "data_vencimento")
    private LocalDate dataVencimento;
    private ParcelaStatus status;

}
