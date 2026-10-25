package com.estacionamais.server.controller;

import com.estacionamais.server.dto.usuario.*;
import com.estacionamais.server.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public FichaUsuario cadastrar(@RequestBody @Valid UsuarioCadastro usuarioCadastro) {
        return usuarioService.cadastrar(usuarioCadastro);
    }
}
