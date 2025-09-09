package com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO;

import com.bruno.pressi.aluguelveiculos.constraints.ValidCNH;
import com.bruno.pressi.aluguelveiculos.web.dto.EnderecoDTO.EnderecoCreateDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreateDto {

    @NotBlank(message = "Nome inválido, tente novamente.")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:\\s+[A-Za-zÀ-ÖØ-öø-ÿ]+)+$", message = "Nome inválido, tente novamente.")
    private String nomeCompleto;

    @NotBlank(message = "Email inválido, tente novamente.")
    @Email(message = "Email inválido, tente novamente.")
    private String email;

    @NotBlank(message = "CPF inválido, tente novamente.")
    @CPF(message = "CPF inválido, tente novamente.")
    private String cpf;

    @NotBlank(message = "O número da CNH está inválido, tente novamente.")
    @ValidCNH
    private String numeroCNH;

    @NotBlank(message = "Telefone inválido, tente novamente.")
    @Pattern(regexp = "^\\(?[1-9]{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$", message = "Telefone inválido, tente novamente.")
    private String telefone;

    @Valid
    private EnderecoCreateDto endereco;

}
