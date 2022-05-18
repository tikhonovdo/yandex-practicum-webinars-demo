package qa4.protecteddemo.vehicle.moto;

public class DefaultMotorcycleFactory implements MotorcycleFactory {

    private final String brandName;
    private int defaultLoudness = 80;

    public DefaultMotorcycleFactory(String brandName) {
        this.brandName = brandName;
    }

    public DefaultMotorcycleFactory(String brandName, int defaultLoudness) {
        this.brandName = brandName;
        this.defaultLoudness = defaultLoudness;
    }

    @Override
    public Motorcycle create(double power, String color, double cost, int loudnessOfExhaust) {
        return new Motorcycle(brandName, power, color, cost, loudnessOfExhaust);
    }

    @Override
    public Motorcycle create(double power, String color, double cost) {
        return create(power, color, cost, defaultLoudness);
    }

    public String getBrandName() {
        return brandName;
    }

    public void setDefaultLoudness(int defaultLoudness) {
        this.defaultLoudness = defaultLoudness;
    }

    public int getDefaultLoudness() {
        return defaultLoudness;
    }
}
