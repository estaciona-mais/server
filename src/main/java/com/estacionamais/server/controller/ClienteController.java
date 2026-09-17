package com.estacionamais.server.controller;

import com.estacionamais.server.dto.DadosCliente;
import com.estacionamais.server.service.ClienteService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService planoService;

    public ClienteController(ClienteService planoService) {
        this.planoService = planoService;
    }

    @GetMapping
    public List<DadosCliente> listar() {
        return planoService.listarTodos();
    }

    @GetMapping("/{id}")
    public DadosCliente buscarClientePorTitulo(@PathVariable Long id) {
        return planoService.buscarPorId(id).orElse(null);
    }
}
