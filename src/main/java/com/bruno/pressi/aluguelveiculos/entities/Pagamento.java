package com.bruno.pressi.aluguelveiculos.entities;

import com.bruno.pressi.aluguelveiculos.entities.enums.MetodoPagamento;
import com.bruno.pressi.aluguelveiculos.entities.enums.ParcelaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {

    private double total;
    private MetodoPagamento metodo;
    private double desconto;

    @Field(name = "numero_parcelas")
    private int numeroParcelas;

    private List<Parcela> parcelas;
}
