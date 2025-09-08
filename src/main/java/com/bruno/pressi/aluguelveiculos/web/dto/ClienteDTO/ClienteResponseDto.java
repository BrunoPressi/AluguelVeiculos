package com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO;

import com.bruno.pressi.aluguelveiculos.web.dto.EnderecoDTO.EnderecoResponseDto;
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

    private EnderecoResponseDto endereco;

}
