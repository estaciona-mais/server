package com.estacionamais.server.controller;

import com.estacionamais.server.dto.ClienteRequest;
import com.estacionamais.server.dto.ClienteResponse;
import com.estacionamais.server.dto.DadosCliente;
import com.estacionamais.server.service.ClienteService;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<DadosCliente> listar() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public DadosCliente buscarClientePorTitulo(@PathVariable Long id) {
        return clienteService.buscarPorId(id).orElse(null);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody @Valid ClienteRequest cliente) {
        ClienteResponse criado = clienteService.cadastrar(cliente);
        return ResponseEntity.ok(criado);
    }
}
