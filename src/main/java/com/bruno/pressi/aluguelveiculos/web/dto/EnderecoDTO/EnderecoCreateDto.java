package com.bruno.pressi.aluguelveiculos.web.dto.EnderecoDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoCreateDto {

    @NotBlank(message = "CEP inválido, tente novamente.")
    @Pattern(regexp = "^(?=.{2,100}$)[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[ '-][A-Za-zÀ-ÖØ-öø-ÿ]{2,})*$", message = "CEP inválido, tente novamente.")
    private String cep;

    @NotBlank(message = "Nome da rua inválida, tente novamente.")
    @Pattern(regexp = "Rua inválida, tente novamente.")
    private String rua;

    @NotBlank(message = "Nome do bairro inválido, tente novamente.")
    @Pattern(regexp = "^(?=.{2,100}$)[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[ '-][A-Za-zÀ-ÖØ-öø-ÿ]{2,})*$", message = "Bairro inválido, tente novamente.")
    private String bairro;

    @NotBlank(message = "Nome da cidade inválida, tente novamente.")
    @Pattern(regexp = "^(?=.{2,100}$)[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[ '-][A-Za-zÀ-ÖØ-öø-ÿ]{2,})*$", message = "Nome da cidade inválida, tente novamente.")
    private String cidade;

    @NotBlank(message = "Sigla do estado inválida, tente novamente.")
    @Pattern(regexp = "^(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)$", message = "Sigla do estado inválida, tente novamente.")
    private String estado;

}
