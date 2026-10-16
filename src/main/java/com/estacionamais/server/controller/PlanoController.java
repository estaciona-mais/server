package com.estacionamais.server.controller;

import com.estacionamais.server.dto.AtualizacaoPlanoRequest;
import com.estacionamais.server.dto.PlanoRequest;
import com.estacionamais.server.dto.ResumoPlano;
import com.estacionamais.server.service.PlanoService;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/plano")
    public ResumoPlano buscarPlano(@RequestBody PlanoRequest req) {
        return planoService.buscarPorTitulo(req.titulo()).orElse(null);
    }

    @PatchMapping("/atualizacao/valor")
    public HttpEntity<Void> atualizarValorPlano(@RequestBody AtualizacaoPlanoRequest req) {
        this.planoService.atualizarValorPlano(req);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
