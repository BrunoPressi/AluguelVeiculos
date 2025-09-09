package com.bruno.pressi.aluguelveiculos.web.dto.EnderecoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponseDto {

    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

}
