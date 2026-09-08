package com.estacionamais.server.model;

import com.estacionamais.server.core.Config;
import jakarta.persistence.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @OneToOne(fetch = FetchType.LAZY, optional = false)
    // @JoinColumn(name = "usuario_id", nullable = false)
    // private Usuario usuario;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plano_id", nullable = false)
    private Plano plano;

    @Column(nullable = false, length = 60)
    private String stripeCustomerId;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Config.StatusContrato statusContrato;

    public Cliente(Plano plano, String nome, String email, String senha, Config.PerfilUsuario perfil)
            throws IllegalArgumentException, NoSuchAlgorithmException {
        super(nome, email, senha, perfil);
        if (plano == null) {
            throw new IllegalArgumentException("Um plano deve ser informado para criar um cliente.");
        }
    }

    public Config.StatusContrato getStatusContrato() {
        return this.statusContrato;
    }

    public void atualizarStatusContrato(Config.StatusContrato novoStatus) {
        this.statusContrato = novoStatus;
    }

    public String[] getHistoricoAcesso() {}
}
