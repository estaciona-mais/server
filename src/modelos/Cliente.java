package server.modelos;

import server.core.Config;

public class Cliente extends Usuario {
    private int diasInscricao;

    public Cliente(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public double conferirPrecoPlano() {
        return Config.PRECO_INSCRICAO;
    }

    public String[] getHistoricoAcesso() {}
}
