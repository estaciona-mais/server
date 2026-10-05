package com.estacionamais.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.estacionamais.server.core.Config;
import com.estacionamais.server.dto.OcupacaoVagas;
import com.estacionamais.server.dto.ResumoVaga;
import com.estacionamais.server.dto.VeiculoResponse;
import com.estacionamais.server.model.entities.Vaga;
import com.estacionamais.server.model.enums.TipoVeiculo;
import com.estacionamais.server.repository.MovimentacaoRepository;
import com.estacionamais.server.repository.VagaRepository;
import com.estacionamais.server.repository.VeiculoRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VagaService {
    private VagaRepository vagaRepository;
    private VeiculoService veiculoService;
    private MovimentacaoRepository movimentacaoRepository;

    public VagaService(VagaRepository vagaRepository, VeiculoService veiculoService,
            MovimentacaoRepository movimentacaoRepository) {
        this.vagaRepository = vagaRepository;
        this.veiculoService = veiculoService;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public List<ResumoVaga> listarTodos() {
        return this.vagaRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(this::paraDto)
                .toList();
    }

    private ResumoVaga paraDto(Vaga vaga) {
        return new ResumoVaga(
                vaga.getId(),
                vaga.getNumero(),
                vaga.getZona(),
                vaga.getTipo(),
                vaga.getDisponivel());
    }

    public Optional<ResumoVaga> buscarPorNumero(Integer numero) {
        return listarTodos().stream().filter(vaga -> vaga.numero() == numero).findFirst();
    }

    public List<OcupacaoVagas> conferirOcupacao() {
        List<TipoVeiculo> tipos = List.of(TipoVeiculo.CARRO, TipoVeiculo.MOTOCICLETA);
        List<Integer> capacidades = List.of(Config.CAPACIDADE_VAGAS_CARRO, Config.CAPACIDADE_VAGAS_MOTOCICLETA);

        List<ResumoVaga> vagas = this.listarTodos();
        List<List<ResumoVaga>> veiculos = tipos.stream()
                .map(tipo -> vagas.stream().filter(vaga -> vaga.tipo().equals(tipo)).toList()).toList();

        List<OcupacaoVagas> ocupacoes = new ArrayList<>();
        for (int i = 0; i < tipos.size(); i++) {
            TipoVeiculo tipo = tipos.get(i);
            List<ResumoVaga> items = veiculos.get(i);
            int capacidade = capacidades.get(i);
            int ocupadas = items.stream().filter(itm -> !itm.disponivel()).toList().size();
            ocupacoes.add(new OcupacaoVagas(
                    tipo,
                    capacidade,
                    capacidade - ocupadas,
                    ocupadas));
        }
        return ocupacoes;
    }

    public List<VeiculoResponse> consultarEstacionados() {
        return this.movimentacaoRepository.findAll().stream()
                .filter(m -> m.getDataSaida() == null || m.getDataEntrada().isAfter(m.getDataSaida()))
                .map(m -> this.veiculoService.paraDto(m.getVeiculo())).toList();
    }
}
