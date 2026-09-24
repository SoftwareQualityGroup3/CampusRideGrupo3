package pt.upt.quality.campusride;

public class RentalService {
    private final Fleet fleet;

    public RentalService(Fleet fleet) {

        this.fleet = fleet;

    }
    public void rentVehicle(String id){
        Vehicle vehicle = findVehicleOrThrow(id);
        vehicle.rent();
    }
    public void returnVehicle(String id){
        Vehicle vehicle = findVehicleOrThrow(id);
        vehicle.returnVehicle();
    }

    public double estimatePrice(String id, int minutes){
        Vehicle vehicle = findVehicleOrThrow(id);
        return vehicle.calculatePrice(minutes);
    }

    private Vehicle findVehicleOrThrow(String id){
        Vehicle vehicle = fleet.findById(id);
        if(vehicle == null ){
            throw new IllegalArgumentException("Unknown vehicle id: " + id);
        }
        return vehicle;
    }
}
