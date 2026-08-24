package server.modelos;

import core.Config;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

public class Vaga {
    private boolean disponivel;
    private Veiculo veiculo;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private final Config.TipoVeiculo tipo;
    // TODO: vamos registrar o número dentro da classe ou apenas pegar por índice na classe do estacionamento?
    private final int numeroVaga;
    private static int vagasDisponiveis;
    private static int vagasOcupadas;
    private static int numeroVagaAtual = 0;

    public Vaga(Config.TipoVeiculo tipo) throws IllegalArgumentException {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo do veículo deve ser informado.");
        }

        Vaga.vagasDisponiveis++;
        Vaga.numeroVagaAtual++;

        this.disponivel = true;
        this.numeroVaga = Vaga.numeroVagaAtual;
        this.tipo = tipo;
        this.veiculo = null;
        this.dataEntrada = null;
        this.dataSaida = null;
    }

    public int getNumeroVaga() {
        return this.numeroVaga;
    }

    public String conferir() {
        if (this.disponivel) {
            return String.format("Número: %d\nStatus: disponível", this.numeroVaga);
        }

        String tempoOcupacao =
                Duration.between(Instant.from(this.dataEntrada), Instant.now()).toString();
        return String.format(
                "Número: %d\nPlaca: %s\nHora de entrada: %s\nDuração: %s",
                this.numeroVaga, this.veiculo.placa, this.dataEntrada.toString(), tempoOcupacao);
    }

    public boolean conferirDisponibilidade() {
        return this.disponivel;
    }

    public void ocupar(Veiculo veiculo) {
        this.disponivel = false;
        this.veiculo = veiculo;
        this.dataEntrada = LocalDateTime.now();
        // TODO: nulificar a saída após a entrada de um novo veículo?
        // this.dataSaida = null;
        Vaga.vagasDisponiveis--;
    }

    public void liberar() {
        this.disponivel = true;
        this.dataSaida = LocalDateTime.now();
        this.veiculo = null;
        Vaga.vagasDisponiveis++;
    }
}
