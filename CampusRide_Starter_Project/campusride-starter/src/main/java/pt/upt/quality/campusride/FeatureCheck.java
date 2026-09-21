package pt.upt.quality.campusride;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class FeatureCheck {
    private static final double EPSILON = 0.0001;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: FeatureCheck <ebike|pricing|rental|report|all>");
            return;
        }

        try {
            switch (args[0]) {
                case "ebike" -> checkEBike();
                case "pricing" -> checkPricing();
                case "rental" -> checkRental();
                case "report" -> checkReport();
                case "all" -> {
                    checkEBike();
                    checkPricing();
                    checkRental();
                    checkReport();
                }
                default -> System.out.println("Unknown feature: " + args[0]);
            }
        } catch (Exception e) {
            System.out.println("CHECK STOPPED: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    private static void checkEBike() throws Exception {
        Class<?> clazz = Class.forName("pt.upt.quality.campusride.EBike");
        require(Bike.class.isAssignableFrom(clazz), "EBike must extend Bike");
        require(Electric.class.isAssignableFrom(clazz), "EBike must implement Electric");

        Constructor<?> ctor = clazz.getConstructor(String.class, int.class);
        Object object = ctor.newInstance("E20", 95);
        Electric electric = (Electric) object;
        electric.charge(20);
        require(electric.getBatteryLevel() == 100, "charging must be capped at 100");

        expectConstructorFailure(ctor, "E21", 120, "battery > 100");
        System.out.println("[OK] EBike hierarchy and battery rules");
    }

    private static void checkPricing() {
        Scooter scooter = new Scooter("S10", 80);
        require(close(scooter.calculatePrice(30), 5.50), "30 minutes must cost 5.50");
        require(close(scooter.calculatePrice(40), 7.50), "40 minutes must cost 7.50");
        expectFailure(() -> scooter.calculatePrice(-1), "negative minutes");
        System.out.println("[OK] Scooter long-ride pricing");
    }

    private static void checkRental() {
        Fleet fleet = sampleFleet();
        RentalService service = new RentalService(fleet);

        service.rentVehicle("B1");
        require(!fleet.findById("B1").isAvailable(), "rent must make B1 unavailable");
        service.returnVehicle("B1");
        require(fleet.findById("B1").isAvailable(), "return must make B1 available");
        require(close(service.estimatePrice("B1", 20), 2.00), "estimate must delegate to vehicle pricing");
        expectFailure(() -> service.rentVehicle("X99"), "unknown vehicle");
        System.out.println("[OK] Rental operations");
    }

    private static void checkReport() {
        Fleet fleet = sampleFleet();
        fleet.findById("B1").rent();
        FleetReport report = new FleetReport(fleet);

        List<String> ids = report.availableVehicleIds();
        require(ids.size() == 1 && ids.contains("S10"), "only S10 should be available");

        int minutes = 15;
        double expected = 0.0;
        for (Vehicle vehicle : fleet.getVehicles()) {
            expected += vehicle.calculatePrice(minutes);
        }
        require(close(report.estimateTotalPrice(minutes), expected), "report must use polymorphic calculatePrice");
        System.out.println("[OK] Fleet report");
    }

    private static Fleet sampleFleet() {
        Fleet fleet = new Fleet();
        fleet.addVehicle(new Bike("B1"));
        fleet.addVehicle(new Scooter("S10", 80));
        return fleet;
    }

    private static boolean close(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    private static void expectFailure(Runnable action, String label) {
        try {
            action.run();
            throw new IllegalStateException("Expected rejection for " + label);
        } catch (IllegalArgumentException expected) {
            // expected
        }
    }

    private static void expectConstructorFailure(Constructor<?> ctor,
                                                 String id,
                                                 int battery,
                                                 String label) throws Exception {
        try {
            ctor.newInstance(id, battery);
            throw new IllegalStateException("Expected rejection for " + label);
        } catch (InvocationTargetException e) {
            if (!(e.getCause() instanceof IllegalArgumentException)) {
                throw e;
            }
        }
    }
}
