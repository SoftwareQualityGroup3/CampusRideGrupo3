package pt.upt.quality.campusride;

public class Bike extends Vehicle {
    public Bike(String id) {
        super(id);
    }

    @Override
    public double calculatePrice(int minutes) {
        validateMinutes(minutes);
        return minutes * 0.10;
    }
}
