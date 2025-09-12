package com.bruno.pressi.aluguelveiculos.web.controllers;

import com.bruno.pressi.aluguelveiculos.services.AluguelService;
import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/aluguel")
@RequiredArgsConstructor
public class AluguelController {

    private final AluguelService aluguelService;

    @PostMapping("/{cliente_id}")
    public ResponseEntity<EntityModel<AluguelResponseDto>> createAluguel(@RequestBody @Valid AluguelCreateDto aluguelCreateDto, @PathVariable(name = "cliente_id") String clienteId) {
        AluguelResponseDto aluguelResponseDto = aluguelService.saveAluguel(aluguelCreateDto, clienteId);

        return ResponseEntity.created(null).body(EntityModel.of(aluguelResponseDto));
    }

}
