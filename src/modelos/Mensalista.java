package server.modelos;

import server.core.Config;

public class Mensalista extends Usuario {
    private boolean inscrito;

    public Mensalista(String nome, String email, String senha, boolean inscrito) {
        super(nome, email, senha);
        this.inscrito = inscrito;
    }

    public double conferirPrecoPlano() {
        return Config.PRECO_INSCRICAO;
    }

    public boolean estaInscrito() {
        return this.inscrito;
    }

    public String[] getHistoricoAcesso() {}
}
