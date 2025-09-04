package com.bruno.pressi.aluguelveiculos.persistenceTests;

import com.bruno.pressi.aluguelveiculos.entities.Cliente;
import com.bruno.pressi.aluguelveiculos.entities.Endereco;
import com.bruno.pressi.aluguelveiculos.repositories.ClienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.util.Assert;

import java.util.Optional;

@DataMongoTest
@ActiveProfiles("test")
public class ClientePT {

    @Autowired
    private ClienteRepository clienteRepository;

    @AfterEach
    public void exit() {
        clienteRepository.deleteAll();
    }

    @Test
    public void saveCliente() {
        Cliente cliente = new Cliente();
        cliente.setNomeCompleto("John Doe");
        cliente.setEmail("john@email.com");
        cliente.setCpf("274.940.360-08");
        cliente.setNumeroCNH(10232810103L);
        cliente.setTelefone("(92) 98823-7852");

        Endereco endereco = new Endereco();
        endereco.setCep("49095-620");
        endereco.setRua("Rua Oziel Dória");
        endereco.setBairro("Jabotiana");
        endereco.setCidade("Aracaju");
        endereco.setEstado("SE");

        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);

        Optional<Cliente> clienteDB = clienteRepository.findByEmail("john@email.com");
        Assertions.assertEquals(clienteDB.get().getNomeCompleto(), cliente.getNomeCompleto());
        Assertions.assertEquals(clienteDB.get().getCpf(), cliente.getCpf());
    }
}
