package pt.upt.quality.campusride;

import java.util.List;
import java.util.stream.Collectors;

public class FleetReport {
    private final Fleet fleet;

    public FleetReport(Fleet fleet) {
        this.fleet = fleet;
    }

    public List<String> availableVehicleIds() {
        return fleet.getVehicles().stream()
                .filter(Vehicle::isAvailable)
                .map(Vehicle::getId)
                .collect(Collectors.toList());
    }

    public double estimateTotalPrice(int minutes) {
        return fleet.getVehicles().stream()
                .mapToDouble(vehicle -> vehicle.calculatePrice(minutes))
                .sum();
    }
}