package com.estacionamais.server.controller;

import com.estacionamais.server.dto.movimentacao.*;
import com.estacionamais.server.service.MovimentacaoService;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimentacao")
public class MovimentacaoController {
    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping("/relatorio")
    public List<MovimentacaoResponse> listarRelatorio(@RequestParam(required = false) Integer limite) {
        return this.movimentacaoService.listarRelatorio(limite);
    }

    @PostMapping("/registros/cliente")
    public List<MovimentacaoResponse> listarHistoricoCliente(@RequestBody MovimentacaoClienteRequest req) {
        return this.movimentacaoService.listarHistoricoCliente(req);
    }

    @PostMapping("/registros/entrada")
    public HttpEntity<Void> registrarEntrada(@RequestBody RegistroMovimentacaoRequest req) {
        this.movimentacaoService.registrarEntrada(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/registros/saida")
    public HttpEntity<Void> registrarSaida(@RequestBody RegistroMovimentacaoRequest req) {
        this.movimentacaoService.registrarSaida(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
