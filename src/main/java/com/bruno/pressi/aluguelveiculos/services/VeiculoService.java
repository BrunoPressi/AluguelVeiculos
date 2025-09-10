package com.bruno.pressi.aluguelveiculos.services;

import com.bruno.pressi.aluguelveiculos.repositories.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

}
