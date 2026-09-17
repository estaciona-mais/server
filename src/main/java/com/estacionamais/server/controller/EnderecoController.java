package com.estacionamais.server.controller;

import com.estacionamais.server.dto.ResumoEndereco;
import com.estacionamais.server.service.EnderecoService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
    private final EnderecoService pagamentoService;

    public EnderecoController(EnderecoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public List<ResumoEndereco> listar() {
        return this.pagamentoService.listarTodos();
    }
}
