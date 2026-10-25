package com.estacionamais.server.controller;

import com.estacionamais.server.dto.cliente.*;
import com.estacionamais.server.service.ClienteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public DadosCliente cadastrar(@RequestBody @Valid ClienteRequest cliente) {
        return this.clienteService.cadastrar(cliente);
    }

    @PostMapping("/google")
    public DadosCliente cadastrarGoogle(@RequestBody @Valid ClienteRequest cliente) {
        return this.clienteService.cadastrarGoogle(cliente);
    }

    @PostMapping("/consulta")
    public DadosCliente consultarCliente(@RequestBody ConsultaClienteRequest req) {
        return this.clienteService.buscarCliente(req).orElse(null);
    }
}
