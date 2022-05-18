package qa4.protecteddemo.vehicle;

/**
 * Класс описания наземного транспортного средства
 */
public abstract class GroundVehicle {
    protected final String brand;
    protected final double power;
    protected final int wheelsCount;

    private final double cost;

    private String color;

    // без protected нельзя будет держать Car и Motorcycle в своих пакетах
    protected GroundVehicle(String brand, double power, String color, double cost, int wheelsCount) {
        this.brand = brand;
        this.power = power;
        this.color = color;
        this.cost = cost;
        this.wheelsCount = wheelsCount;
    }

    public enum Type {
        CAR,
        MOTO
    }

    @Override
    public String toString() {
        return "GroundVehicle{" +
                "brand='" + brand + '\'' +
                ", power=" + power +
                ", wheelsCount=" + wheelsCount +
                ", cost=" + cost +
                ", color='" + color + '\'' +
                '}';
    }
}
