package com.bruno.pressi.aluguelveiculos.web.controllers;

import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/api/v1/aluguel")
@RequiredArgsConstructor
public class AluguelController {

    private final AluguelController aluguelController;

    @PostMapping("/{cliente_id}")
    private ResponseEntity<EntityModel<AluguelResponseDto>> createAluguel(@RequestBody @Valid AluguelCreateDto aluguelCreateDto, @PathVariable(name = "cliente_id") String clienteId) {

    }


}
