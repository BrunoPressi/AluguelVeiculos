package com.bruno.pressi.aluguelveiculos.entities;

import com.bruno.pressi.aluguelveiculos.entities.enums.VeiculoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "veiculos")
public class Veiculo {

    @Id
    private String id;
    private String modelo;
    private String marca;
    private String ano;
    private String cor;

    @Indexed(unique = true)
    private String placa;
    private VeiculoStatus status;

}
