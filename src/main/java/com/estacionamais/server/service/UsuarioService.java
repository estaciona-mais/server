package com.estacionamais.server.service;

import com.estacionamais.server.core.Config;
import com.estacionamais.server.dto.FichaUsuario;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    public List<FichaUsuario> listarTodos() {
        return List.of(
                new FichaUsuario(1L, "Igor Borges", "i.b@mail.com", "abc123", Config.PerfilUsuario.Administrador),
                new FichaUsuario(2L, "Josimar Pereira", "j.p@mail.com", "def456", Config.PerfilUsuario.Administrador),
                new FichaUsuario(3L, "Jonas Meireles", "jonas@mail.com", "abab", Config.PerfilUsuario.Operador),
                new FichaUsuario(4L, "Clara Bittencourt", "clara@mail.com", "cllc", Config.PerfilUsuario.Cliente),
                new FichaUsuario(5L, "Otávio Nunes", "otavio@mail.com", "otto", Config.PerfilUsuario.Cliente),
                new FichaUsuario(6L, "Helena Prado", "helena@mail.com", "hele", Config.PerfilUsuario.Cliente),
                new FichaUsuario(7L, "Marcos Vieira", "varcos@ail.com", "marc", Config.PerfilUsuario.Cliente),
                new FichaUsuario(8L, "Sofia Andrade", "sofia@mail.com", "sfia", Config.PerfilUsuario.Cliente));
    }

    public Optional<FichaUsuario> buscarPorId(Long id) {
        return listarTodos().stream().filter(usuario -> usuario.id().equals(id)).findFirst();
    }
}
