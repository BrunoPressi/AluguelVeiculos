package com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO;

import com.bruno.pressi.aluguelveiculos.entities.enums.VeiculoStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoCreateDto {

    @NotBlank(message = "Modelo inválido, tente novamente.")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9 ]{2,50}$", message = "Modelo inválido, tente novamente.")
    private String modelo;

    @NotBlank(message = "Modelo inválido, tente novamente.")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ ]{2,30}$", message = "Marca inválida, tente novamente.")
    private String marca;

    @Pattern(regexp = "^(18[8-9][6-9]|19\\d{2}|20\\d{2}|2100)$", message = "Ano inválido, tente novamente.")
    private String ano;

    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\- ]{3,20}$", message = "Cor inválida, tente novamente.")
    private String cor;

    @Pattern(regexp = "^[A-Z]{3}-?\\d{4}$|^[A-Z]{3}\\d[A-Z]\\d{2}$", message = "Placa inválida. Use o formato ABC-1234 ou ABC1D23")
    private String placa;

    @Pattern(regexp = "^(DISPONIVEL|INDISPONIVEL)$", message = "Status deve ser DISPONIVEL ou INDISPONIVEL")
    private VeiculoStatus status;

}
