package com.bruno.pressi.aluguelveiculos.web.dto.PagamentoDTO;

import com.bruno.pressi.aluguelveiculos.entities.Parcela;
import com.bruno.pressi.aluguelveiculos.entities.enums.MetodoPagamento;
import com.bruno.pressi.aluguelveiculos.web.dto.ParcelaDTO.ParcelaResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoResponseDto {

    private double total;
    private MetodoPagamento metodo;
    private double desconto;
    private int numeroParcelas;

    private List<ParcelaResponseDto> parcelas;

}
