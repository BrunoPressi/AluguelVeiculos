package com.bruno.pressi.aluguelveiculos.web.controllers;

import com.bruno.pressi.aluguelveiculos.services.AluguelService;
import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelResponseDto;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Link listLink = linkTo(methodOn(AluguelController.class).findAllAlugueis()).withRel("list");

        return ResponseEntity.created(selfLink.toUri()).body(EntityModel.of(aluguelResponseDto, selfLink, listLink));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<AluguelResponseDto>> findAluguelById(@PathVariable(name = "id") String id) {
        AluguelResponseDto aluguelResponseDto = aluguelService.findById(id);

        Link listLink = linkTo(methodOn(AluguelController.class).findAllAlugueis()).withRel("list");

        return ResponseEntity.ok(EntityModel.of(aluguelResponseDto, listLink));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<AluguelResponseDto>>> findAllAlugueis() {
        List<AluguelResponseDto> aluguelResponseDtoList = aluguelService.findAll();

        List<EntityModel<AluguelResponseDto>> aluguelModel = aluguelResponseDtoList.stream()
                .map(aluguel -> EntityModel.of(aluguel,
                        linkTo(methodOn(AluguelController.class).findAluguelById(aluguel.getId())).withSelfRel()
                ))
                .toList();

        return ResponseEntity.ok(CollectionModel.of(aluguelModel));
    }

    @GetMapping("/cliente/{cliente_id}")
    public ResponseEntity<CollectionModel<EntityModel<AluguelResponseDto>>> findAlugueisByClienteId(@PathVariable(name = "cliente_id") String clienteId) {
        List<AluguelResponseDto> aluguelResponseDtoList = aluguelService.findByClienteId(clienteId);

        List<EntityModel<AluguelResponseDto>> aluguelModel = aluguelResponseDtoList.stream()
                .map(aluguel -> EntityModel.of(aluguel,
                        linkTo(methodOn(AluguelController.class).findAluguelById(aluguel.getId())).withSelfRel()
                        ))
                .toList();

        return ResponseEntity.ok(CollectionModel.of(aluguelModel));
    }

}
