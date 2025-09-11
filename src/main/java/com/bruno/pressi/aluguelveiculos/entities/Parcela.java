package com.bruno.pressi.aluguelveiculos.entities;

import com.bruno.pressi.aluguelveiculos.entities.enums.ParcelaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.naming.Name;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parcela {

    private double valor;

    @Field(name = "data_vencimento")
    private LocalDate dataVencimento;
    private ParcelaStatus status;

}
