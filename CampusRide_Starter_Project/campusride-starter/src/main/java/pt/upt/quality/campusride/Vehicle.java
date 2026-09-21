package pt.upt.quality.campusride;

public abstract class Vehicle {
    private final String id;
    private boolean available = true;

    protected Vehicle(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Vehicle id cannot be blank");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void rent() {
        if (!available) {
            throw new IllegalStateException("Vehicle is already rented");
        }
        available = false;
    }

    public void returnVehicle() {
        available = true;
    }

    protected void validateMinutes(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes cannot be negative");
        }
    }

    public abstract double calculatePrice(int minutes);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + id + ", available=" + available + "}";
    }
}
