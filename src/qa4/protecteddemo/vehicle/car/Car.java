package qa4.protecteddemo.vehicle.car;

import qa4.protecteddemo.vehicle.GroundVehicle;

public class Car extends GroundVehicle {

    private CabinType cabinType;

    public Car(String brand, double power, double cost, String color, CabinType cabinType) {
        super(brand, power, color, cost, 4);
        this.cabinType = cabinType;
    }
}
