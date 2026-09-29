package com.estacionamais.server.model.entities;

import com.estacionamais.server.core.Utils;
import com.estacionamais.server.model.enums.*;
import jakarta.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;

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

    @Column(nullable = false, length = 255)
    private String senha;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Papel papel = Papel.CLIENTE;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Provider provider = Provider.LOCAL;

    @Column(length = 120)
    private String providerId;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senhaHash) {
        this.senha = senhaHash;
    }

    public Papel getPapel() {
        return papel;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    // TODO: atualizar os valores no banco
    public void atualizarNome(String novoNome) throws IllegalArgumentException {
        Utils.atualizarDado(novoNome, "Um nome", () -> this.nome = novoNome);
    }

    public void atualizarEmail(String novoEmail) throws IllegalArgumentException {
        Utils.atualizarDado(novoEmail, "Um e-mail", () -> this.email = novoEmail);
    }

    public void atualizarSenha(String novaSenha) throws IllegalArgumentException, NoSuchAlgorithmException {
        String hashedSenha = Utils.hashSenha(novaSenha);
        Utils.atualizarDado(novaSenha, "Uma senha", () -> this.senha = hashedSenha);
    }
}
