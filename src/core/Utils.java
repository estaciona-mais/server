package server.core;

public final class Utils {
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
