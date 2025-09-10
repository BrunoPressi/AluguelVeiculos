package com.bruno.pressi.aluguelveiculos.services;

import com.bruno.pressi.aluguelveiculos.entities.Cliente;
import com.bruno.pressi.aluguelveiculos.exceptions.ClienteNotFoundException;
import com.bruno.pressi.aluguelveiculos.exceptions.DuplicateClienteException;
import com.bruno.pressi.aluguelveiculos.repositories.ClienteRepository;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteResponseDto;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteUpdateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.mapper.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = false)
    public ClienteResponseDto saveCliente(ClienteCreateDto clienteCreateDto) {
        Cliente cliente = ObjectMapper.parseObject(clienteCreateDto, Cliente.class);

        try {
            clienteRepository.insert(cliente);
        }
        catch (org.springframework.dao.DuplicateKeyException e) {
            String msg = e.getMessage();
            String details = msg.substring(msg.indexOf("dup key: ") + 8, msg.indexOf("details") - 3).trim();
            throw new DuplicateClienteException("Cliente já registrado: " + details);
        }

        return ObjectMapper.parseObject(cliente, ClienteResponseDto.class);
    }

    @Transactional(readOnly = true)
    public ClienteResponseDto findById(String id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ClienteNotFoundException("Cliente não encontrado.")
        );

        return ObjectMapper.parseObject(cliente, ClienteResponseDto.class);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDto> findAll() {
        List<Cliente> clienteList = clienteRepository.findAll();

        return ObjectMapper.parseObjectList(clienteList, ClienteResponseDto.class);
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        findById(id);
        clienteRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public ClienteResponseDto updateById(String id, ClienteUpdateDto clienteUpdateDto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ClienteNotFoundException("Cliente não encontrado")
        );

        Cliente clienteNovo = ObjectMapper.parseObject(clienteUpdateDto, Cliente.class);
        cliente.setNomeCompleto(clienteNovo.getNomeCompleto());
        cliente.setEmail(clienteNovo.getEmail());
        cliente.setCpf(clienteNovo.getCpf());
        cliente.setNumeroCNH(clienteNovo.getNumeroCNH());
        cliente.setTelefone(clienteNovo.getTelefone());
        cliente.setEndereco(clienteNovo.getEndereco());

        clienteRepository.save(cliente);

        return ObjectMapper.parseObject(cliente, ClienteResponseDto.class);
    }
}
