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
        rentalService.rentVehicle("B1");
        System.out.println("B1 available after rent = "
                + fleet.findById("B1").isAvailable());
        rentalService.returnVehicle("B1");
        System.out.println("TEAM FEATURES NOT YET INTEGRATED");
    }
}
