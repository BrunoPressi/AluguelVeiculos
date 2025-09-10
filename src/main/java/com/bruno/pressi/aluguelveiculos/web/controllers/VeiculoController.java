package com.bruno.pressi.aluguelveiculos.web.controllers;

import com.bruno.pressi.aluguelveiculos.services.VeiculoService;
import com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO.VeiculoCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO.VeiculoResponseDto;
import com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO.VeiculoUpdateDto;
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
@RequestMapping("/api/v1/veiculo")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<EntityModel<VeiculoResponseDto>> createVeiculo(@RequestBody @Valid VeiculoCreateDto veiculoCreateDto) {
        VeiculoResponseDto veiculoResponseDto = veiculoService.saveVeiculo(veiculoCreateDto);

        Link selfLink = linkTo(methodOn(VeiculoController.class).findVeiculoById(veiculoResponseDto.getId())).withSelfRel();
        Link listLink = linkTo(methodOn(VeiculoController.class).findAllVeiculos()).withRel("list");
        Link deleteLink = linkTo(methodOn(VeiculoController.class).deleteVeiculoById(veiculoResponseDto.getId())).withRel("delete").withType("DELETE");
        Link updateLink = linkTo(methodOn(VeiculoController.class).updateVeiculoById(veiculoResponseDto.getId(), null)).withRel("update").withType("PUT");

        return ResponseEntity.created(selfLink.toUri()).body(EntityModel.of(veiculoResponseDto, selfLink, listLink, deleteLink, updateLink));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<VeiculoResponseDto>> findVeiculoById(@PathVariable(name = "id") String id) {
        VeiculoResponseDto veiculoResponseDto = veiculoService.findById(id);

        Link listLink = linkTo(methodOn(VeiculoController.class).findAllVeiculos()).withRel("list");
        Link deleteLink = linkTo(methodOn(VeiculoController.class).deleteVeiculoById(veiculoResponseDto.getId())).withRel("delete").withType("DELETE");
        Link updateLink = linkTo(methodOn(VeiculoController.class).updateVeiculoById(veiculoResponseDto.getId(), null)).withRel("update").withType("PUT");


        return ResponseEntity.ok(EntityModel.of(veiculoResponseDto, listLink, deleteLink, updateLink));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<VeiculoResponseDto>>> findAllVeiculos() {
        List<VeiculoResponseDto> veiculoResponseDtoList = veiculoService.findAll();

        List<EntityModel<VeiculoResponseDto>> veiculos = veiculoResponseDtoList.stream().map(
                (veiculo) -> EntityModel.of(veiculo,
                    linkTo(methodOn(VeiculoController.class).findVeiculoById(veiculo.getId())).withSelfRel(),
                    linkTo(methodOn(VeiculoController.class).deleteVeiculoById(veiculo.getId())).withRel("delete").withType("DELETE"),
                    linkTo(methodOn(VeiculoController.class).updateVeiculoById(veiculo.getId(), null)).withRel("update").withType("PUT")
        )).toList();

        return ResponseEntity.ok(CollectionModel.of(veiculos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculoById(@PathVariable(name = "id") String id) {
        veiculoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<VeiculoResponseDto>> updateVeiculoById(@PathVariable(name = "id") String id, @RequestBody @Valid VeiculoUpdateDto veiculoUpdateDto) {
        VeiculoResponseDto veiculoResponseDto = veiculoService.updateById(id, veiculoUpdateDto);

        Link selfLink = linkTo(methodOn(VeiculoController.class).findVeiculoById(veiculoResponseDto.getId())).withSelfRel();
        Link listLink = linkTo(methodOn(VeiculoController.class).findAllVeiculos()).withRel("list");
        Link deleteLink = linkTo(methodOn(VeiculoController.class).deleteVeiculoById(veiculoResponseDto.getId())).withRel("delete").withType("DELETE");

        return ResponseEntity.ok(EntityModel.of(veiculoResponseDto, selfLink, listLink, deleteLink));
    }

}
