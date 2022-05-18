package qa4.protecteddemo.vehicle.moto;

import qa4.protecteddemo.vehicle.GroundVehicle;
import qa4.protecteddemo.vehicle.GroundVehicleFactory;

public interface MotorcycleFactory extends GroundVehicleFactory<Motorcycle> {

    Motorcycle create(double power, String color, double cost, int loudnessOfExhaust);

    default GroundVehicle.Type getType() {
        return GroundVehicle.Type.MOTO;
    }
}
