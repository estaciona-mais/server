package com.estacionamais.server.model;

import com.estacionamais.server.core.Config;
import jakarta.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "vaga")
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private Boolean disponivel;

    @Column(nullable = false)
    private LocalDateTime dataEntrada;

    @Column(nullable = false)
    private LocalDateTime dataSaida;

    private static int vagasDisponiveis;
    private static int vagasOcupadas = 0;

    public Vaga(Config.TipoVeiculo tipo) throws IllegalArgumentException {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo do veículo deve ser informado.");
        }

        Vaga.vagasDisponiveis--;
        Vaga.vagasOcupadas++;
        Vaga.vagasDisponiveis = switch (tipo) {
            case Carro -> Config.CAPACIDADE_VAGAS_CARRO;
            case Motocicleta -> Config.CAPACIDADE_VAGAS_MOTOCICLETA;
        };

        this.disponivel = true;
        this.veiculo = null;
        this.dataEntrada = null;
        this.dataSaida = null;
    }

    public int getNumero() {
        return this.numero;
    }

    public String conferirStatus() {
        if (this.disponivel) {
            return String.format("Número: %d\nStatus: disponível", this.numero);
        }

        String tempoOcupacao =
                Duration.between(Instant.from(this.dataEntrada), Instant.now()).toString();
        return String.format(
                "Número: %d\nPlaca: %s\nHora de entrada: %s\nDuração: %s",
                this.numero, this.veiculo.getPlaca(), this.dataEntrada.toString(), tempoOcupacao);
    }

    public boolean conferirDisponibilidade() {
        return this.disponivel;
    }

    public void ocupar(Veiculo veiculo) {
        this.disponivel = false;
        // this.veiculo = veiculo;
        this.dataEntrada = LocalDateTime.now();
        // TODO: nulificar a saída após a entrada de um novo veículo?
        // this.dataSaida = null;
        Vaga.vagasDisponiveis--;
    }

    public void liberar() {
        this.disponivel = true;
        this.dataSaida = LocalDateTime.now();
        // this.veiculo = null;
        Vaga.vagasDisponiveis++;
    }
}
