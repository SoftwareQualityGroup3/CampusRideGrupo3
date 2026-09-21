package pt.upt.quality.campusride;

import java.util.ArrayList;
import java.util.List;

public class Fleet {
    private final List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (findById(vehicle.getId()) != null) {
            throw new IllegalArgumentException("Duplicate vehicle id: " + vehicle.getId());
        }
        vehicles.add(vehicle);
    }

    public Vehicle findById(String id) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getId().equals(id)) {
                return vehicle;
            }
        }
        return null;
    }

    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }
}
