package com.bruno.pressi.aluguelveiculos.repositories;

import com.bruno.pressi.aluguelveiculos.entities.Aluguel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepository extends MongoRepository<Aluguel, String> {
}
