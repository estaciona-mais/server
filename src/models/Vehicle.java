package server.models;

import core.Config;

public abstract class Vehicle {
    protected String plate;
    protected String model;
    protected Config.VehicleColor color;
    protected Config.VehicleType type;
    protected static int vehicleCount;

    protected Vehicle(String plate, String model, Config.VehicleColor color, Config.VehicleType type)
            throws IllegalArgumentException {
        Vehicle.checkArguments(plate, model, color, type);

        this.plate = plate;
        this.model = model;
        this.color = color;
        this.type = type;
        Vehicle.vehicleCount++;
    }

    public String getPlate() {
        return this.plate;
    }

    public String getModel() {
        return this.model;
    }

    static void checkArguments(String plate, String model, Config.VehicleColor color, Config.VehicleType type)
            throws IllegalArgumentException {
        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("Empty vehicle sign.");
        } else if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Empty vehicle model.");
        } else if (color == null) {
            throw new IllegalArgumentException("Vehicle color must be specified.");
        } else if (type == null) {
            throw new IllegalArgumentException("Vehicle type must be specified.");
        }
    }

    static void signUpVehicle(String plate, String model, Config.VehicleColor color, Config.VehicleType type)
            throws IllegalArgumentException {
        Vehicle.checkArguments(plate, model, color, type);

        // TODO: save vehicle on database
        Vehicle.vehicleCount++;
    }

    static void removeVehicle(String plate) throws IllegalArgumentException {
        if (plate == null || plate.isBlank()) {
            throw new IllegalArgumentException("Vehicle sign must be informed for removal.");
        }

        // TODO: remove vehicle from database
        Vehicle.vehicleCount--;
    }
}
