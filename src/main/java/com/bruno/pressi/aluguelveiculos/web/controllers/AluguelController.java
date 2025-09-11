package com.bruno.pressi.aluguelveiculos.web.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aluguel")
@RequiredArgsConstructor
public class AluguelController {

    private final AluguelController aluguelController;

}
