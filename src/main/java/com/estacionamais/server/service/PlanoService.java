package com.estacionamais.server.service;

import com.estacionamais.server.dto.AtualizacaoPlanoRequest;
import com.estacionamais.server.dto.ResumoPlano;
import com.estacionamais.server.model.entities.Plano;
import com.estacionamais.server.repository.PlanoRepository;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class PlanoService {
    private PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<ResumoPlano> listarTodos() {
        return this.planoRepository.findAll().stream().map(this::paraDto).toList();
    }

    private ResumoPlano paraDto(Plano plano) {
        return new ResumoPlano(
                plano.getId(), plano.getValor(), plano.getTitulo().toString());
    }

    public Optional<ResumoPlano> buscarPorTitulo(String titulo) {
        return listarTodos().stream()
                .filter(plano -> plano.titulo().toLowerCase().equals(titulo.toLowerCase()))
                .findFirst();
    }

    public void atualizarValorPlano(AtualizacaoPlanoRequest req) {
        Plano plano = this.planoRepository.findByTitulo(req.titulo()).orElseThrow();
        plano.setValor(req.novoValor());
        this.planoRepository.save(plano);
    }
}
