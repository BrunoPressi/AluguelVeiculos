package com.bruno.pressi.aluguelveiculos.web.controllers;

import com.bruno.pressi.aluguelveiculos.services.AluguelService;
import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/aluguel")
@RequiredArgsConstructor
public class AluguelController {

    private final AluguelService aluguelService;

    @PostMapping("/{cliente_id}")
    public ResponseEntity<EntityModel<AluguelResponseDto>> createAluguel(@RequestBody @Valid AluguelCreateDto aluguelCreateDto, @PathVariable(name = "cliente_id") String clienteId) {
        AluguelResponseDto aluguelResponseDto = aluguelService.saveAluguel(aluguelCreateDto, clienteId);

        Link selfLink = linkTo(methodOn(AluguelController.class).findAluguelById(aluguelResponseDto.getId())).withSelfRel();

        return ResponseEntity.created(selfLink.toUri()).body(EntityModel.of(aluguelResponseDto, selfLink));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<AluguelResponseDto>> findAluguelById(@PathVariable(name = "id") String id) {
        AluguelResponseDto aluguelResponseDto = aluguelService.findById(id);

        return ResponseEntity.ok(EntityModel.of(aluguelResponseDto));
    }

}
