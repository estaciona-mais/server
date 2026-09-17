package com.estacionamais.server.controller;

import com.estacionamais.server.dto.ResumoPagamento;
import com.estacionamais.server.service.PagamentoService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public List<ResumoPagamento> listar() {
        return this.pagamentoService.listarTodos();
    }
}
