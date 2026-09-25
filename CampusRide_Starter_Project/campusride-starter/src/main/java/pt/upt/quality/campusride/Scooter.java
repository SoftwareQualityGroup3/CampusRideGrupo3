package pt.upt.quality.campusride;

public class Scooter extends Vehicle implements Electric {
    private int batteryLevel;

    public Scooter(String id, int batteryLevel) {
        super(id);
        validateBattery(batteryLevel);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public int getBatteryLevel() {
        return batteryLevel;
    }

    @Override
    public void charge(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Charge amount cannot be negative");
        }
        batteryLevel = Math.min(100, batteryLevel + amount);
    }

    @Override
    public double calculatePrice(int minutes) {
        validateMinutes(minutes);
        double price = 1.00;
        if (minutes <= 30) {
            price += 0.15 * minutes;
        }
        else {
            price += 30 * 0.15;
            price += (minutes - 30) * 0.20;
        }
        return price;
    }

    private void validateBattery(int batteryLevel) {
        if (batteryLevel < 0 || batteryLevel > 100) {
            throw new IllegalArgumentException("Battery must be between 0 and 100");
        }
    }
}
