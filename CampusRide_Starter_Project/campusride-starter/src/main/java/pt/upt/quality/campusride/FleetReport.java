package pt.upt.quality.campusride;

import java.util.List;

public class FleetReport {
    private final Fleet fleet;

    public FleetReport(Fleet fleet) {
        this.fleet = fleet;
    }

    public List<String> availableVehicleIds() {
        throw new UnsupportedOperationException("availableVehicleIds not implemented");
    }

    public double estimateTotalPrice(int minutes) {
        throw new UnsupportedOperationException("estimateTotalPrice not implemented");
    }
}
