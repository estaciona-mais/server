package com.estacionamais.server.controller;

import com.estacionamais.server.dto.ResumoPlano;
import com.estacionamais.server.service.PlanoService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planos")
public class PlanoController {
    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @GetMapping
    public List<ResumoPlano> listar() {
        return planoService.listarTodos();
    }

    @GetMapping("/{titulo}")
    public ResumoPlano buscarPlanoPorTitulo(@PathVariable String titulo) {
        return planoService.buscarPorTitulo(titulo).orElse(null);
    }
}
