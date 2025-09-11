package com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO;

import com.bruno.pressi.aluguelveiculos.entities.enums.AluguelStatus;
import com.bruno.pressi.aluguelveiculos.entities.enums.VeiculoStatus;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteResponseDto;
import com.bruno.pressi.aluguelveiculos.web.dto.PagamentoDTO.PagamentoResponseDto;
import com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO.VeiculoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AluguelResponseDto {

    private String id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private double valorDiaria;
    private double valorTotal;
    private AluguelStatus status;

    private ClienteResponseDto cliente;
    private VeiculoResponseDto veiculo;
    private PagamentoResponseDto pagamento;

}
