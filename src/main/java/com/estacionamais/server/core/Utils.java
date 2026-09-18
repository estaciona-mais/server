package com.estacionamais.server.core;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Utils {
    public static String hashSenha(String senhaOriginal) throws NoSuchAlgorithmException {
        byte[] bytes = MessageDigest.getInstance("SHA-256").digest(senhaOriginal.getBytes(StandardCharsets.UTF_8));
        StringBuilder novaSenha = new StringBuilder(2 * bytes.length);

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if (hex.length() == 1) {
                novaSenha.append('0');
            }
            novaSenha.append(hex);
        }

        return novaSenha.toString();
    }

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
