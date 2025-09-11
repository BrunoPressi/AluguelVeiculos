package com.bruno.pressi.aluguelveiculos.services;

import com.bruno.pressi.aluguelveiculos.entities.Veiculo;
import com.bruno.pressi.aluguelveiculos.entities.enums.VeiculoStatus;
import com.bruno.pressi.aluguelveiculos.exceptions.DuplicateEntityException;
import com.bruno.pressi.aluguelveiculos.exceptions.EntityNotFoundException;
import com.bruno.pressi.aluguelveiculos.repositories.VeiculoRepository;
import com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO.VeiculoCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO.VeiculoResponseDto;
import com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO.VeiculoUpdateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.mapper.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Transactional(readOnly = false)
    public VeiculoResponseDto saveVeiculo(VeiculoCreateDto veiculoCreateDto) {
        Veiculo veiculo = ObjectMapper.parseObject(veiculoCreateDto, Veiculo.class);

        try {
            veiculoRepository.insert(veiculo);
        }
        catch (DuplicateKeyException e) {
            String msg = e.getMessage();
            String details = msg.substring(msg.indexOf("dup key: ") + 8, msg.indexOf("details") - 3).trim();
            throw new DuplicateEntityException("Veiculo já existe: " + details);
        }

        return ObjectMapper.parseObject(veiculo, VeiculoResponseDto.class);
    }

    @Transactional(readOnly = true)
    public VeiculoResponseDto findById(String id) {
        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Veiculo não encontrado.")
        );

        return ObjectMapper.parseObject(veiculo, VeiculoResponseDto.class);
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponseDto> findAll() {
        List<Veiculo> veiculosList = veiculoRepository.findAll();

        return ObjectMapper.parseObjectList(veiculosList, VeiculoResponseDto.class);
    }

    @Transactional(readOnly = false)
    public void deleteById(String id) {
        findById(id);

        veiculoRepository.deleteById(id);
    }

    @Transactional(readOnly = false)
    public VeiculoResponseDto updateById(String id, VeiculoUpdateDto veiculoUpdateDto) {
        Veiculo veiculo = veiculoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Veiculo não encontrado")
        );

        Veiculo veiculoNovo = ObjectMapper.parseObject(veiculoUpdateDto, Veiculo.class);
        veiculo.setModelo(veiculoNovo.getModelo());
        veiculo.setMarca(veiculoNovo.getMarca());
        veiculo.setAno(veiculoNovo.getAno());
        veiculo.setCor(veiculoNovo.getCor());
        veiculo.setPlaca(veiculoNovo.getPlaca());
        veiculo.setStatus(veiculoNovo.getStatus());

        veiculoRepository.save(veiculo);

        return ObjectMapper.parseObject(veiculo, VeiculoResponseDto.class);
    }

    @Transactional(readOnly = false)
    public void updateStatus(Veiculo veiculo) {
        veiculo.setStatus(VeiculoStatus.INDISPONIVEL);
        veiculoRepository.save(veiculo);
    }
}
