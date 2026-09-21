package pt.upt.quality.campusride;

public class RentalService {
    private final Fleet fleet;

    public RentalService(Fleet fleet) {
        this.fleet = fleet;
    }

    public void rentVehicle(String id) {
        throw new UnsupportedOperationException("rentVehicle not implemented");
    }

    public void returnVehicle(String id) {
        throw new UnsupportedOperationException("returnVehicle not implemented");
    }

    public double estimatePrice(String id, int minutes) {
        throw new UnsupportedOperationException("estimatePrice not implemented");
    }
}
