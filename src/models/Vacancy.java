package server.models;

import core.Config;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

public class Vancancy {
    private int slotNumber;
    private Config.VehicleType type;
    private LocalDateTime registerDate;
    public static int totalVacancies;
    public static int availableVacancies;
    public static int takenVacancies;

    public Vacancy(int slotNumber, Config.VehicleType type) {
        this.slotNumber = slotNumber;
        this.type = type;
        this.registerDate = LocalDateTime.now();
    }

    public void register(Vehicle vehicle, int vacancySlot, Date time) {}

    // TODO: print or return String?
    public void check() {
        // long elapsed = LocalDateTime.now().toEpochSecond() - this.registerDate.toEpochSecond();
        String elapsed =
                Duration.between(Instant.from(this.registerDate), Instant.now()).toString();
        System.out.printf(
                "Sign: %s\nRegister date: %s\nSlot: %d\nElapsed: %s\n",
                this.sign, this.registerDate.toString(), this.slotNumber, elapsed);
    }
}
