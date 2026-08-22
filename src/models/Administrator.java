package server.models;

public class Administrator extends User {
    public Administrator(String name, String email, String password) {
        super(name, email, password);
    }

    // public String getUser(User user) {}
    public String getUser(String userName) {}

    public String getReports() {}
}
