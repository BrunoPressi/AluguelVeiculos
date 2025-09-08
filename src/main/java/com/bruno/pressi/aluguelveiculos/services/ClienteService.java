package com.bruno.pressi.aluguelveiculos.services;

import com.bruno.pressi.aluguelveiculos.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

}
