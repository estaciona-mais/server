package server.modelos;

import server.core.Config;
import server.core.Utils;

public abstract class Usuario {
    private String nome;
    private String email;
    private String senha;
    private final Config.PerfilUsuario perfil;

    protected Usuario(String nome, String email, String senha) throws IllegalArgumentException {
        Utils.conferirCredenciais(nome, email, senha);

        this.nome = nome;
        this.email = email;
        // TODO: senha deve ser processada com hash
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void updateNome(String novoNome) throws IllegalArgumentException {
        updateDado(novoNome, "Um nome", () -> this.nome = novoNome);
    }

    public void updateEmail(String novoEmail) throws IllegalArgumentException {
        updateDado(novoEmail, "Um e-mail", () -> this.email = novoEmail);
    }

    public void updateSenha(String novaSenha) throws IllegalArgumentException {
        // TODO: processamento da senha com hash
        updateDado(novaSenha, "Uma senha", () -> this.senha = novaSenha);
    }

    private void updateDado(String novoDado, String campo, Utils.Set callback) throws IllegalArgumentException {
        if (novoDado == null || novoDado.isBlank()) {
            throw new IllegalArgumentException(String.format("%s deve ser informado para a atualização.", campo));
        }
        callback.set();
    }
}
