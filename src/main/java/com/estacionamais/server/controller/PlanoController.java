package com.estacionamais.server.controller;

import com.estacionamais.server.dto.ResumoPlano;
import com.estacionamais.server.service.PlanoService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {
    private final PlanoService clienteService;

    public PlanoController(PlanoService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ResumoPlano> listar() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{titulo}")
    public ResumoPlano buscarPlanoPorTitulo(@PathVariable String titulo) {
        return clienteService.buscarPorTitulo(titulo).orElse(null);
    }
}
