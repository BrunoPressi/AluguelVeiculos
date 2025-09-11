package com.bruno.pressi.aluguelveiculos.entities;

import com.bruno.pressi.aluguelveiculos.entities.enums.AluguelStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "alugueis")
public class Aluguel {

    @Id
    private String id;

    @Field(name = "data_inicio")
    private LocalDate dataInicio;

    @Field(name = "data_fim")
    private LocalDate dataFim;

    @Field(name = "valor_diaria")
    private double valorDiaria;

    @Field(name = "valor_total")
    private double valorTotal;
    private AluguelStatus status;

    @Field(name = "cliente_id")
    @DocumentReference(lazy = true)
    private Cliente cliente;

    @Field(name = "veiculo_id")
    @DocumentReference(lazy = true)
    private Veiculo veiculo;

    private Pagamento pagamento;

}
