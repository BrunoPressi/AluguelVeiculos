package com.bruno.pressi.aluguelveiculos.repositories;

import com.bruno.pressi.aluguelveiculos.entities.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends MongoRepository<Veiculo, String> {
}
