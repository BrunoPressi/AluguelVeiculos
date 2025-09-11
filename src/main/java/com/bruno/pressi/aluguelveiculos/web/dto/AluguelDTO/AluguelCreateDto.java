package com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO;

import com.bruno.pressi.aluguelveiculos.entities.Veiculo;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AluguelCreateDto {

    @NotBlank(message = "Id do veiculo inválido, tente novamente.")
    private String veiculoId;

    @FutureOrPresent(message = "A data de início não pode ser no passado.")
    private LocalDate dataInicio;

    @Future(message = "A data de início deve ser no futuro.")
    private LocalDate dataFim;

}
