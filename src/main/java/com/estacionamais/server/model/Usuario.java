package com.estacionamais.server.model;

import com.estacionamais.server.core.Utils;
import jakarta.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255, name = "senha_hash")
    private String senhaHash;

    // @Enumerated(value = EnumType.STRING)
    // @Column(nullable = false)
    // private final Config.PerfilUsuario perfil;

    public Usuario() {}

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    // TODO: atualizar os valores no banco
    public void atualizarNome(String novoNome) throws IllegalArgumentException {
        atualizarDado(novoNome, "Um nome", () -> this.nome = novoNome);
    }

    public void atualizarEmail(String novoEmail) throws IllegalArgumentException {
        atualizarDado(novoEmail, "Um e-mail", () -> this.email = novoEmail);
    }

    public void atualizarSenha(String novaSenha) throws IllegalArgumentException, NoSuchAlgorithmException {
        String hashedSenha = Utils.hashSenha(novaSenha);
        atualizarDado(novaSenha, "Uma senha", () -> this.senhaHash = hashedSenha);
    }

    private void atualizarDado(String novoDado, String campo, Utils.Set callback) throws IllegalArgumentException {
        if (novoDado == null || novoDado.isBlank()) {
            throw new IllegalArgumentException(String.format("%s deve ser informado para a atualização.", campo));
        }
        callback.set();
    }
}
