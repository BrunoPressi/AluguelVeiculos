package com.bruno.pressi.aluguelveiculos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private String cep;
    private String cidade;
    private String estado;
    private String rua;
    private String bairro;

}
