package server.modelos;

import server.core.Config;

public abstract class Usuario {
    private String nome;
    private String email;
    // TODO: senha deve ser processada com hash
    private String senha;
    private final Config.PerfilUsuario perfil;

    protected Usuario(String nome, String email, String senha) throws IllegalArgumentException {
        String[][] campos = {{nome, "Nome"}, {email, "E-mail"}, {senha, "Senha"}};
        for (String[] campo : campos) {
            if (campo == null || campo[0].isBlank()) {
                throw new IllegalArgumentException(String.format("%s do usuário não pode estar vazio.", campo[1]));
            }
        }

        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }
}
