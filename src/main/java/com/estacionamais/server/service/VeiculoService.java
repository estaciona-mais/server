package com.estacionamais.server.service;

import com.estacionamais.server.dto.ResumoVeiculo;
import com.estacionamais.server.model.entities.Veiculo;
import com.estacionamais.server.repository.VeiculoRepository;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {
    private VeiculoRepository planoRepository;

    public VeiculoService(VeiculoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<ResumoVeiculo> listarTodos() {
        return this.planoRepository.findAll().stream().map(this::paraDto).toList();
    }

    private ResumoVeiculo paraDto(Veiculo veiculo) {
        return new ResumoVeiculo(
                veiculo.getId(),
                veiculo.getNomeCliente(),
                veiculo.getPlaca(),
                veiculo.getFabricante(),
                veiculo.getModelo(),
                veiculo.getAno(),
                veiculo.getCor(),
                veiculo.getTipo(),
                veiculo.getObservacao());
    }

    public Optional<ResumoVeiculo> buscarPorPlaca(String placa) {
        return listarTodos().stream()
                .filter(veiculo -> veiculo.placa().toUpperCase().equals(placa.toUpperCase()))
                .findFirst();
    }
}
