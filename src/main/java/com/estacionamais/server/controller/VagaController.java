package com.estacionamais.server.controller;

import com.estacionamais.server.dto.vaga.*;
import com.estacionamais.server.dto.veiculo.*;
import com.estacionamais.server.service.VagaService;
import java.util.List;
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
