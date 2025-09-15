package com.bruno.pressi.aluguelveiculos.services;

import com.bruno.pressi.aluguelveiculos.entities.*;
import com.bruno.pressi.aluguelveiculos.entities.enums.AluguelStatus;
import com.bruno.pressi.aluguelveiculos.entities.enums.MetodoPagamento;
import com.bruno.pressi.aluguelveiculos.entities.enums.ParcelaStatus;
import com.bruno.pressi.aluguelveiculos.entities.enums.VeiculoStatus;
import com.bruno.pressi.aluguelveiculos.exceptions.EntityNotFoundException;
import com.bruno.pressi.aluguelveiculos.exceptions.VeiculoIndisponivelException;
import com.bruno.pressi.aluguelveiculos.repositories.AluguelRepository;
import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelCreateDto;
import com.bruno.pressi.aluguelveiculos.web.dto.AluguelDTO.AluguelResponseDto;
import com.bruno.pressi.aluguelveiculos.web.dto.ClienteDTO.ClienteResponseDto;
import com.bruno.pressi.aluguelveiculos.web.dto.VeiculoDTO.VeiculoResponseDto;
import com.bruno.pressi.aluguelveiculos.web.dto.mapper.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AluguelService {

    private final AluguelRepository aluguelRepository;
    private final VeiculoService veiculoService;
    private final ClienteService clienteService;

    private static final double VALOR_DIARIA = 150.00;
    private static final int DESCONTO = 0;

    @Transactional(readOnly = false)
    public AluguelResponseDto saveAluguel(@Valid AluguelCreateDto aluguelCreateDto, String clienteId) {
        Aluguel aluguel = processAluguel(aluguelCreateDto, clienteId);
        aluguelRepository.insert(aluguel);

        return ObjectMapper.parseObject(aluguel, AluguelResponseDto.class);
    }

    private final Aluguel processAluguel(AluguelCreateDto aluguelCreateDto, String clienteId) {
        Aluguel aluguel = new Aluguel();

        LocalDate dataInicio = aluguelCreateDto.getDataInicio();
        LocalDate dataFim = aluguelCreateDto.getDataFim();
        int dias = Period.between(aluguelCreateDto.getDataInicio(), aluguelCreateDto.getDataFim()).getDays();
        double valorTotal = dias * VALOR_DIARIA;
        AluguelStatus aluguelStatus = AluguelStatus.ATIVO;

        Veiculo veiculo = verifyVeiculoExistsAndAvailable(aluguelCreateDto.getVeiculoId());
        Cliente cliente = verifyClienteExists(clienteId);

        aluguel.setDataInicio(dataInicio);
        aluguel.setDataFim(dataFim);
        aluguel.setValorDiaria(VALOR_DIARIA);
        aluguel.setValorTotal(valorTotal);
        aluguel.setStatus(aluguelStatus);
        aluguel.setCliente(cliente);
        aluguel.setVeiculo(veiculo);

        Pagamento pagamento = processPagamento(MetodoPagamento.valueOf(aluguelCreateDto.getMetodoPagamento()), valorTotal, aluguelCreateDto.getNumeroParcelas());
        aluguel.setPagamento(pagamento);

        List<Parcela> parcelas = processParcelas(pagamento.getNumeroParcelas(), pagamento.getTotal(), aluguel.getDataInicio());
        pagamento.setParcelas(parcelas);

        veiculoService.updateStatus(veiculo);
        return  aluguel;
    }

    private final Pagamento processPagamento(MetodoPagamento metodoPagamento, double valorTotal, int numeroParcelas) {
        Pagamento pagamento = new Pagamento();

        pagamento.setMetodo(metodoPagamento);
        pagamento.setNumeroParcelas(numeroParcelas);
        pagamento.setDesconto(DESCONTO);
        pagamento.setTotal(valorTotal - DESCONTO);

        return  pagamento;
    }

    private final List<Parcela> processParcelas(int numeroParcelas, double valorPagamento, LocalDate dataInicio) {
        List<Parcela> parcelas = new ArrayList<>();
        for (int i = 1; i <= numeroParcelas; i++) {
            Parcela parcela = new Parcela();

            double valorParcela = valorPagamento / numeroParcelas;
            LocalDate dataVencimento = dataInicio.plusMonths(i);
            ParcelaStatus parcelaStatus = ParcelaStatus.PENDENTE;

            parcela.setValor(valorParcela);
            parcela.setDataVencimento(dataVencimento);
            parcela.setStatus(parcelaStatus);

            parcelas.add(parcela);
        }

        return  parcelas;
    }

    @Transactional(readOnly = true)
    private final Veiculo verifyVeiculoExistsAndAvailable(String veiculoId) {
        VeiculoResponseDto veiculoResponseDto = veiculoService.findById(veiculoId);
        Veiculo veiculo = ObjectMapper.parseObject(veiculoResponseDto, Veiculo.class);

        if (veiculo.getStatus().equals(VeiculoStatus.INDISPONIVEL)) throw new VeiculoIndisponivelException("Este veículo não está disponível.");

        return veiculo;
    }

    @Transactional(readOnly = true)
    private final Cliente verifyClienteExists(String clienteId) {
        ClienteResponseDto clienteResponseDto = clienteService.findById(clienteId);
        return ObjectMapper.parseObject(clienteResponseDto, Cliente.class);
    }

    @Transactional(readOnly = true)
    public AluguelResponseDto findById(String id) {
        Aluguel aluguel = aluguelRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Aluguel não encontrado.")
        );

        return ObjectMapper.parseObject(aluguel, AluguelResponseDto.class);
    }

    @Transactional(readOnly = true)
    public List<AluguelResponseDto> findAll() {
        List<Aluguel> aluguelList = aluguelRepository.findAll();

        return ObjectMapper.parseObjectList(aluguelList, AluguelResponseDto.class);
    }

    @Transactional(readOnly = true)
    public List<AluguelResponseDto> findByClienteId(String clienteId) {
        List<Aluguel> aluguelList = aluguelRepository.findByClienteId(clienteId).orElseThrow(
                () -> new EntityNotFoundException("Cliente não encontrado.")
        );

        return ObjectMapper.parseObjectList(aluguelList, AluguelResponseDto.class);
    }
}
