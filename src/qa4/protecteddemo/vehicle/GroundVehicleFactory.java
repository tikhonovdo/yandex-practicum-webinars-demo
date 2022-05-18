package qa4.protecteddemo.vehicle;

public interface GroundVehicleFactory<T extends GroundVehicle> {

    /**
     * Создает вариант по умлочанию
     * @param color
     * @return
     */
    T create(double power, String color, double cost);

    default GroundVehicle.Type getType() {
        return GroundVehicle.Type.CAR;
    }

}
