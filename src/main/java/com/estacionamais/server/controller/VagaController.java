package com.estacionamais.server.controller;

import java.util.List;

import com.estacionamais.server.dto.OcupacaoVagas;
import com.estacionamais.server.dto.ResumoVaga;
import com.estacionamais.server.dto.VeiculoResponse;
import com.estacionamais.server.service.VagaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vagas")
public class VagaController {
    private final VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @GetMapping
    public List<ResumoVaga> listar() {
        return this.vagaService.listarTodos();
    }

    @GetMapping("/ocupacao")
    public List<OcupacaoVagas> conferirOcupacao() {
        return this.vagaService.conferirOcupacao();
    }

    @GetMapping("/estacionados")
    public List<VeiculoResponse> consultarEstacionados() {
        return this.vagaService.consultarEstacionados();
    }
}
