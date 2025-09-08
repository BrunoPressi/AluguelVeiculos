package com.bruno.pressi.aluguelveiculos.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDto {

    private long id;
    private String nome_completo;
    private String email;
    private String telefone;

}
