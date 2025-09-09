package com.bruno.pressi.aluguelveiculos.web.controllers;

import com.bruno.pressi.aluguelveiculos.entities.Cliente;
import com.bruno.pressi.aluguelveiculos.services.ClienteService;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController()
@RequestMapping("/api/v1/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<ClienteResponseDto> createCliente(@RequestBody @Valid ClienteCreateDto clienteCreateDto) {
        ClienteResponseDto clienteResponseDto = clienteService.saveCliente(clienteCreateDto);
        Link selfLink = linkTo(methodOn(ClienteController.class).findClienteById(clienteResponseDto.getId())).withSelfRel();
        Link listLink = linkTo(methodOn(ClienteController.class).findAllClientes()).withRel("list");

        return EntityModel.of(clienteResponseDto, selfLink, listLink);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EntityModel<ClienteResponseDto> findClienteById(@PathVariable(name = "id") String id) {
        ClienteResponseDto clienteResponseDto = clienteService.findById(id);
        Link listLink = linkTo(methodOn(ClienteController.class).findAllClientes()).withRel("list");

        return EntityModel.of(clienteResponseDto, listLink);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<EntityModel<ClienteResponseDto>> findAllClientes() {
        List<ClienteResponseDto> clientes = clienteService.findAll();

        List<EntityModel<ClienteResponseDto>> clientesModel = clientes.stream()
                .map(cliente -> EntityModel.of(cliente,
                    linkTo(methodOn(ClienteController.class).findClienteById(cliente.getId())).withSelfRel())).toList();

        return CollectionModel.of(clientesModel);
    }

}
