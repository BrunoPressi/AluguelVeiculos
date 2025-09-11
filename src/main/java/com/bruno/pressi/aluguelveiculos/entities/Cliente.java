package com.bruno.pressi.aluguelveiculos.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;

    @Field("nome_completo")
    private String nomeCompleto;

    @Indexed(unique = true)
    private String email;

    @Indexed(unique = true)
    private String cpf;

    @Field("numero_cnh")
    @Indexed(unique = true)
    private String numeroCNH;
    private String telefone;

    private Endereco endereco;

    @DocumentReference(lazy = true)
    private List<Aluguel> alugueis;

}
