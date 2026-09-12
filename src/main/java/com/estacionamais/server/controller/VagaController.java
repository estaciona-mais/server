package com.estacionamais.server.controller;

import com.estacionamais.server.dto.ResumoVaga;
import com.estacionamais.server.service.VagaService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/vagas")
public class VagaController {
    private final VagaService pagamentoService;

    public VagaController(VagaService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public List<ResumoVaga> listar() {
        return this.pagamentoService.listarTodos();
    }
}
