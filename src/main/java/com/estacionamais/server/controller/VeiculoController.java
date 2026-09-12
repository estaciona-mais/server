package com.estacionamais.server.controller;

import com.estacionamais.server.dto.ResumoVeiculo;
import com.estacionamais.server.service.VeiculoService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    private final VeiculoService pagamentoService;

    public VeiculoController(VeiculoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public List<ResumoVeiculo> listar() {
        return this.pagamentoService.listarTodos();
    }
}
