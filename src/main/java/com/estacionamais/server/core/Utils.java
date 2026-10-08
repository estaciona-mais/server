package com.estacionamais.server.core;

public final class Utils {
    public static void conferirCredenciais(String nome, String email, String senha) throws IllegalArgumentException {
        String[][] campos = { { nome, "Nome" }, { email, "E-mail" }, { senha, "Senha" } };
        for (String[] grupo : campos) {
            String dado = grupo[0];
            String campo = grupo[1];
            if (grupo == null || dado.isBlank()) {
                throw new IllegalArgumentException(String.format("%s do usuário não pode estar vazio.", campo));
            }
        }
    }

    @FunctionalInterface
    public interface Set {
        void set();
    }

    static public void atualizarDado(String novoDado, String campo, Utils.Set callback)
            throws IllegalArgumentException {
        if (novoDado == null || novoDado.isBlank()) {
            throw new IllegalArgumentException(String.format("%s deve ser informado para a atualização.", campo));
        }
        if (callback != null) {
            callback.set();
        }
    }

    static public void conferirCamposString(String[][] campos) throws IllegalArgumentException {
        for (String[] campo : campos) {
            conferirCampoString(campo);
        }
    }

    static public void conferirCampoString(String[] campo) throws IllegalArgumentException {
        String arg = campo[0];
        String mensagemErro = campo[1];

        if (arg == null || arg.isBlank())
            throw new IllegalArgumentException(mensagemErro);
    }
}
