package com.bruno.pressi.aluguelveiculos.web.dto.ParcelaDTO;

import com.bruno.pressi.aluguelveiculos.entities.enums.ParcelaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParcelaResponseDto {

    private double valor;
    private LocalDate dataVencimento;
    private ParcelaStatus status;

}
