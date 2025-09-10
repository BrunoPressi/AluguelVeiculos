package com.bruno.pressi.aluguelveiculos.web.controllers;

import com.bruno.pressi.aluguelveiculos.services.ClienteService;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteResponseDto;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteUpdateDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
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
    public ResponseEntity<EntityModel<ClienteResponseDto>> createCliente(@RequestBody @Valid ClienteCreateDto clienteCreateDto) {
        ClienteResponseDto clienteResponseDto = clienteService.saveCliente(clienteCreateDto);

        Link selfLink = linkTo(methodOn(ClienteController.class).findClienteById(clienteResponseDto.getId())).withSelfRel();
        Link listLink = linkTo(methodOn(ClienteController.class).findAllClientes()).withRel("list");
        Link deleteLink = linkTo(methodOn(ClienteController.class).deleteClienteById(clienteResponseDto.getId())).withRel("delete").withType("delete");
        Link updateLink = linkTo(methodOn(ClienteController.class).updateClienteById(clienteResponseDto.getId(), null)).withRel("update").withType("update");

        return ResponseEntity.created(selfLink.toUri()).body(EntityModel.of(clienteResponseDto, selfLink, listLink, deleteLink, updateLink));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ClienteResponseDto>> findClienteById(@PathVariable(name = "id") String id) {
        ClienteResponseDto clienteResponseDto = clienteService.findById(id);

        Link listLink = linkTo(methodOn(ClienteController.class).findAllClientes()).withRel("list");
        Link deleteLink = linkTo(methodOn(ClienteController.class).deleteClienteById(clienteResponseDto.getId())).withRel("delete").withType("delete");
        Link updateLink = linkTo(methodOn(ClienteController.class).updateClienteById(clienteResponseDto.getId(), null)).withRel("update").withType("update");

        return ResponseEntity.ok(EntityModel.of(clienteResponseDto, listLink, deleteLink, updateLink));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ClienteResponseDto>>> findAllClientes() {
        List<ClienteResponseDto> clientes = clienteService.findAll();

        List<EntityModel<ClienteResponseDto>> clientesModel = clientes.stream()
                .map(cliente -> EntityModel.of(cliente,
                    linkTo(methodOn(ClienteController.class).findClienteById(cliente.getId())).withSelfRel(),
                    linkTo(methodOn(ClienteController.class).deleteClienteById(cliente.getId())).withRel("delete").withType("delete"),
                    linkTo(methodOn(ClienteController.class).updateClienteById(cliente.getId(), null)).withRel("update").withType("update")

                ))
                .toList();

        return ResponseEntity.ok(CollectionModel.of(clientesModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable(name = "id") String id) {
        clienteService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ClienteResponseDto>> updateClienteById(@PathVariable(name = "id") String id, @RequestBody ClienteUpdateDto clienteUpdateDto) {
        ClienteResponseDto clienteResponseDto = clienteService.updateById(id, clienteUpdateDto);

        Link selfLink = linkTo(methodOn(ClienteController.class).findClienteById(clienteResponseDto.getId())).withSelfRel();
        Link listLink = linkTo(methodOn(ClienteController.class).findAllClientes()).withRel("list");
        Link deleteLink = linkTo(methodOn(ClienteController.class).deleteClienteById(clienteResponseDto.getId())).withRel("delete").withType("delete");

        return ResponseEntity.ok(EntityModel.of(clienteResponseDto, selfLink, listLink, deleteLink));
    }

}
