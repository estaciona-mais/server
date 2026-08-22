package server.models;

public class Operator extends User {
    public Operator(String name, String email, String password) {
        super(name, email, password);
    }

    public void registerEntrance() {}

    public void registerDeparture() {}

    public void registerPayment() {}

    public boolean checkVacancy(int vacancyNumber) {}
}
