package qa4.protecteddemo.vehicle.car;

import qa4.protecteddemo.vehicle.GroundVehicleFactory;

public interface CarFactory extends GroundVehicleFactory<Car> {

    Car create(double power, String color, double cost, CabinType cabinType);

}
