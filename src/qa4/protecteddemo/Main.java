package qa4.protecteddemo;

import qa4.protecteddemo.vehicle.GroundVehicle;
import qa4.protecteddemo.vehicle.GroundVehicleFactory;
import qa4.protecteddemo.vehicle.car.DefaultCarFactory;
import qa4.protecteddemo.vehicle.moto.DefaultMotorcycleFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<GroundVehicleFactory<?>> vehicleFactories = createFactories();
        for (GroundVehicleFactory<?> factory : vehicleFactories) {
            int power = 70;
            if (factory.getType() == GroundVehicle.Type.MOTO) {
                power = 100;
            }
            GroundVehicle vehicle = factory.create(power, "blue", 10_000.0);
            System.out.println(vehicle.toString());
        }
    }

    private static List<GroundVehicleFactory<?>> createFactories() {
        List<GroundVehicleFactory<?>> factories = new ArrayList<>();
        factories.add(new DefaultCarFactory("Ford"));
        factories.add(new DefaultCarFactory("Ferrari"));
        factories.add(new DefaultCarFactory("Lada"));
        factories.add(new DefaultMotorcycleFactory("Harley Davidson"));
        factories.add(new DefaultMotorcycleFactory("ИЖ"));
        factories.add(new DefaultMotorcycleFactory("Suzuki"));
        return factories;
    }

}