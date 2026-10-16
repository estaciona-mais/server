package com.estacionamais.server.controller;

import com.estacionamais.server.dto.assinatura.*;
import com.estacionamais.server.service.AssinaturaService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/assinaturas")
public class AssinaturaController {
    private final AssinaturaService assinaturaService;

    public AssinaturaController(AssinaturaService pagamentoService) {
        this.assinaturaService = pagamentoService;
    }

    @GetMapping
    public List<ResumoAssinatura> listar() {
        return this.assinaturaService.listarTodas();
    }
}
