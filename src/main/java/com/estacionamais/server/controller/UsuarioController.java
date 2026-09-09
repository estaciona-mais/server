package com.estacionamais.server.controller;

import com.estacionamais.server.dto.FichaUsuario;
import com.estacionamais.server.service.UsuarioService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<FichaUsuario> listar() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public FichaUsuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id).orElse(null);
    }

    @GetMapping("/buscar")
    public List<FichaUsuario> buscar(@RequestParam(required = false) String termo) {
        return usuarioService.listarTodos().stream()
                .filter(u -> u.nome().toLowerCase().contains(termo.toLowerCase()))
                .toList();
    }
}
