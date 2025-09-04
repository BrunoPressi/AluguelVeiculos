package com.bruno.pressi.aluguelveiculos.repositories;

import com.bruno.pressi.aluguelveiculos.entities.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    Optional<Cliente> findByEmail(String mail);
}
