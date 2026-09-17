package com.estacionamais.server.model.entities;

import com.estacionamais.server.model.enums.TipoVeiculo;
import jakarta.persistence.*;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;

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
    private OffsetDateTime dataEntrada;

    @Column(nullable = false)
    private OffsetDateTime dataSaida;

    // private static int vagasDisponiveis;
    // private static int vagasOcupadas = 0;

    public Vaga() {}

    public Long getId() {
        return this.id;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public Cliente getCliente() {
        return this.getVeiculo().getCliente();
    }

    public String getPlaca() {
        return this.getVeiculo().getPlaca();
    }

    public Integer getNumero() {
        return this.numero;
    }

    public TipoVeiculo getTipo() {
        return this.getVeiculo().getTipo();
    }

    public OffsetDateTime getDataEntrada() {
        return this.dataEntrada;
    }

    public OffsetDateTime getDataSaida() {
        return this.dataSaida;
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
        this.dataEntrada = OffsetDateTime.now();
        // TODO: nulificar a saída após a entrada de um novo veículo?
        // this.dataSaida = null;
        // Vaga.vagasDisponiveis--;
    }

    public void liberar() {
        this.disponivel = true;
        this.dataSaida = OffsetDateTime.now();
        // this.veiculo = null;
        // Vaga.vagasDisponiveis++;
    }
}
