package com.estacionamais.server.controller;

import com.estacionamais.server.dto.*;
import com.estacionamais.server.dto.veiculo.*;
import com.estacionamais.server.model.enums.CorVeiculo;
import com.estacionamais.server.model.enums.TipoVeiculo;
import com.estacionamais.server.service.VeiculoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @GetMapping
    public List<VeiculoResponse> listar() {
        return this.veiculoService.listarTodos();
    }

    @GetMapping("/cores")
    public List<CorVeiculo> listarCores() {
        return this.veiculoService.listarCores();
    }

    @GetMapping("/tipos")
    public List<TipoVeiculo> listarTipos() {
        return this.veiculoService.listarTipos();
    }

    @PostMapping
    public CadastroResponse cadastrar(@RequestBody @Valid VeiculoRequest veiculoRequest) {
        return this.veiculoService.cadastrar(veiculoRequest);
    }
}
