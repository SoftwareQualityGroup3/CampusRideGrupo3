package pt.upt.quality.campusride;

public class App {
    public static void main(String[] args) {
        Fleet fleet = new Fleet();
        fleet.addVehicle(new Bike("B1"));
        fleet.addVehicle(new Scooter("S10", 80));

        RentalService rentalService = new RentalService(fleet);
        FleetReport report = new FleetReport(fleet);

        System.out.println("=== CampusRide ===");
        fleet.getVehicles().forEach(System.out::println);
        runTeamFeatures(fleet, rentalService, report);
    }

    private static void runTeamFeatures(Fleet fleet,
                                        RentalService rentalService,
                                        FleetReport report) {
        System.out.println("Available vehicles = " + report.availableVehicleIds());

        rentalService.rentVehicle("B1");
        System.out.println("B1 available after rent = "
                + fleet.findById("B1").isAvailable());

        rentalService.returnVehicle("B1");

        EBike eBike = new EBike("E20", 95);
        eBike.charge(20);
        System.out.println("E20 battery = " + eBike.getBatteryLevel());

        System.out.printf("S10 / 40 min = %.2f%n",
                fleet.findById("S10").calculatePrice(40));
    }
}