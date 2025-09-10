package com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO;

import com.bruno.pressi.aluguelveiculos.web.dto.EnderecoDTO.EnderecoResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDto {

    private String id;
    private String nomeCompleto;
    private String email;
    private String cpf;
    private String numeroCNH;
    private String telefone;

    private EnderecoResponseDto endereco;

}
