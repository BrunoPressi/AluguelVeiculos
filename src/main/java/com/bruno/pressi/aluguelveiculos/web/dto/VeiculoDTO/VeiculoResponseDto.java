package com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO;

import com.bruno.pressi.aluguelveiculos.entities.enums.VeiculoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoResponseDto {

    private String id;
    private String modelo;
    private String marca;
    private String ano;
    private String cor;
    private String placa;
    private VeiculoStatus status;

}
