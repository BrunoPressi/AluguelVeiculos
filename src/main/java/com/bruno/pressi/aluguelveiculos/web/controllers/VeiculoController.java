package com.bruno.pressi.aluguelveiculos.web.controllers;

import com.bruno.pressi.aluguelveiculos.services.VeiculoService;
import com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO.VeiculoCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO.VeiculoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/veiculo")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<EntityModel<VeiculoResponseDto>> createVeiculo(@RequestBody VeiculoCreateDto veiculoCreateDto) {
        VeiculoResponseDto veiculoResponseDto = veiculoService.saveVeiculo(veiculoCreateDto);

        return ResponseEntity.created(null).body(EntityModel.of(veiculoResponseDto));
    }

}
