package qa4.protecteddemo.vehicle.car;

public class DefaultCarFactory implements CarFactory {

    private final String brandName;

    private CabinType defaultCabinType = CabinType.SEDAN;

    public DefaultCarFactory(String brandName) {
        this.brandName = brandName;
    }

    public DefaultCarFactory(String brandName, CabinType defaultCabinType) {
        this.brandName = brandName;
        this.defaultCabinType = defaultCabinType;
    }

    @Override
    public Car create(double power, String color, double cost, CabinType cabinType) {
        return new Car(brandName, power, cost, color, cabinType);
    }

    @Override
    public Car create(double power, String color, double cost) {
        return create(power, color, cost, CabinType.SEDAN);
    }

    public String getBrandName() {
        return brandName;
    }

    public void setDefaultCabinType(CabinType defaultCabinType) {
        this.defaultCabinType = defaultCabinType;
    }

    public CabinType getDefaultCabinType() {
        return defaultCabinType;
    }
}
