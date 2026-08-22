package server.core;

public interface Config {
    int MAX_CAPACITY = 50;
    double SUBSCRIPTION_PRICE = 40;

    enum UserProfile {
        Administrator,
        Operator,
        Subscriber
    }

    enum VehicleColor {
        Black,
        White,
        Gray,
        Silver,
        Beige,
        Brown,
        Red,
        Orange,
        NeonOrange,
        Gold,
        Yellow,
        NeonYellow,
        Green,
        Cyan,
        Blue,
        Purple,
        Pink,
        NeonPink,
        Fantasy // mixed colors
    }

    // TODO: keep as an enum or create individual classes to contain categories of each type? (suv, sports, limousine,
    // etc)
    enum VehicleType {
        Car,
        Motorcycle,
    }

    enum CarCategory {
        Hatchback,
        Sedan,
        SUV,
        Pickup,
        Sports,
        Minivan
    }

    enum MotorcycleCategory {
        Scooter,
        Commuter,
        Touring,
        Sports,
        Bobber,
        Adventure
    }
}
