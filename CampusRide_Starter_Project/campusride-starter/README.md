# CampusRide - Starter Project

Starter project for the Version Control Systems / GitHub team workshop in Software Quality.

The code continues the CampusRide examples used in previous classes (`Vehicle`, `Bike`, `Scooter`, `Electric`).

## Requirements
- Java 17+
- Maven 3.8+

## Build
```bash
mvn clean package
```

## Run the baseline
```bash
java -cp target/classes pt.upt.quality.campusride.App
```

## Run one feature check
```bash
java -cp target/classes pt.upt.quality.campusride.FeatureCheck ebike
java -cp target/classes pt.upt.quality.campusride.FeatureCheck pricing
java -cp target/classes pt.upt.quality.campusride.FeatureCheck rental
java -cp target/classes pt.upt.quality.campusride.FeatureCheck report
```

The starter intentionally contains incomplete team features. Work only on the Issue assigned to you and integrate changes through branches and Pull Requests.
