package com.bruno.pressi.aluguelveiculos.services;

import com.bruno.pressi.aluguelveiculos.repositories.AluguelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AluguelService {

    private final AluguelRepository aluguelRepository;

}
