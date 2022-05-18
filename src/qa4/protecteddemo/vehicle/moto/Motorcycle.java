package qa4.protecteddemo.vehicle.moto;

import qa4.protecteddemo.vehicle.GroundVehicle;

public class Motorcycle extends GroundVehicle {

    private int loudnessOfExhaust = 80; // in dB

    public Motorcycle(String brand, double power, String color, double cost) {
        super(brand, power, color, cost, 2);
    }

    public Motorcycle(String brand, double power, String color, double cost, int loudnessOfExhaust) {
        this(brand, power, color, cost);
        this.loudnessOfExhaust = loudnessOfExhaust;
    }

}
