package server.models;

import server.core.Config;

public class Subscriber extends User {
    private boolean subscriber;

    public Subscriber(String name, String email, String password, boolean subscriber) {
        super(name, email, password);
        this.subscriber = subscriber;
    }

    public double checkPlanPrice() {
        return Config.SUBSCRIPTION_PRICE;
    }

    public boolean isSubscriber() {
        return this.subscriber;
    }

    public String[] getAccessHistory() {}
}
