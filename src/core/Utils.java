package server.core;

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
        String[][] campos = {{nome, "Nome"}, {email, "E-mail"}, {senha, "Senha"}};
        for (String[] grupo : campos) {
            String dado = grupo[0];
            String campo = grupo[1];
            if (grupo == null || dado.isBlank()) {
                throw new IllegalArgumentException(String.format("%s do usuário não pode estar vazio.", campo));
            }
        }
    }

    public interface Set {
        void set();
    }
}
