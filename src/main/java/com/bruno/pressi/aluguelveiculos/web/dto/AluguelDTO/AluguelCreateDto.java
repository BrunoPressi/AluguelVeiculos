package com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO;

import com.bruno.pressi.aluguelveiculos.entities.Veiculo;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Método de pagamento inválido, tente novamente.")
    @Pattern(regexp = "^(PIX|BOLETO|CARTAO_CREDITO|CARTAO_DEBITO)$", message = "Método de pagamento deve ser PIX, BOLETO, CARTAO_CREDITO OU CARTAO_DEBITO.")
    private String metodoPagamento;

    @NotNull(message = "Número de parcelas inválido, tente novamente.")
    @Min(value = 1, message = "O mínimo de parcelas é 1 (uma).")
    @Max(value = 12, message = "O máximo de parcelas é 12 (doze).")
    private int numeroParcelas;

}
