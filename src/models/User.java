package server.models;

import server.core.Config;

public abstract class User {
    private String name;
    private String email;
    // TODO: password must be hashed
    private String password;
    private final Config.UserProfile profile;

    protected User(String name, String email, String password) throws IllegalArgumentException {
        String[][] fields = {{name, "name"}, {email, "e-mail"}, {password, "password"}};
        for (String[] field : fields) {
            if (field == null || field[0].isBlank()) {
                throw new IllegalArgumentException(String.format("User %s cannot be empty.", field[1]));
            }
        }

        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
}
