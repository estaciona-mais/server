package com.estacionamais.server.service;

import com.estacionamais.server.dto.ResumoVaga;
import com.estacionamais.server.model.entities.Vaga;
import com.estacionamais.server.repository.VagaRepository;
import java.util.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VagaService {
    private VagaRepository planoRepository;

    public VagaService(VagaRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<ResumoVaga> listarTodos() {
        return this.planoRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).stream()
                .map(this::paraDto)
                .toList();
    }

    private ResumoVaga paraDto(Vaga veiculo) {
        return new ResumoVaga(
                veiculo.getId(),
                veiculo.getNumero(),
                veiculo.getZona(),
                veiculo.getDisponivel());
    }

    public Optional<ResumoVaga> buscarPorNumero(Integer numero) {
        return listarTodos().stream().filter(vaga -> vaga.numero() == numero).findFirst();
    }
}
