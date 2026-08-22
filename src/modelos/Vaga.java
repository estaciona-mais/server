package server.modelos;

import core.Config;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

public class Vaga {
    private int numeroVaga;
    private Config.TipoVeiculo tipo;
    private LocalDateTime dataRegistro;
    public static int vagasTotais;
    public static int vagasDisponiveis;
    public static int vagasOcupadas;

    public Vaga(int numeroVaga, Config.TipoVeiculo tipo) {
        this.numeroVaga = numeroVaga;
        this.tipo = tipo;
        this.dataRegistro = LocalDateTime.now();
    }

    public void registrar(Veiculo veiculo, int numeroVaga, LocalDateTime tempo) {}

    public String conferir() {
        // long tempoOcupacao = LocalDateTime.now().toEpochSecond() - this.registerDate.toEpochSecond();
        String tempoOcupacao =
                Duration.between(Instant.from(this.dataRegistro), Instant.now()).toString();
        return String.format(
                "Sign: %s\nRegister date: %s\nSlot: %d\nElapsed: %s\n",
                this.placa, this.dataRegistro.toString(), this.numeroVaga, tempoOcupacao);
    }
}
