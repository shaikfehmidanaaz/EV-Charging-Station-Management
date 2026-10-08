import java.util.Scanner;

class ChargingPoint {
    private int pointNumber;
    private boolean occupied;
    private double chargingPower;

    public ChargingPoint(int pointNumber, double chargingPower) {
        this.pointNumber = pointNumber;
        this.chargingPower = chargingPower;
        this.occupied = false;
    }

    public boolean isAvailable() {
        return !occupied;
    }

    public void startCharging() {
        occupied = true;
    }

    public void stopCharging() {
        occupied = false;
    }

    public int getPointNumber() {
        return pointNumber;
    }

    public double getChargingPower() {
        return chargingPower;
    }
}

public class EVChargingStationManagement {

    private ChargingPoint[] chargingPoints;

    public EVChargingStationManagement(int numberOfPoints,
                                       double chargingPower) {
        chargingPoints = new ChargingPoint[numberOfPoints];

        for (int i = 0; i < numberOfPoints; i++) {
            chargingPoints[i] =
                    new ChargingPoint(i + 1, chargingPower);
        }
    }

    // Find an available charging point
    public int findAvailablePoint() {
        for (int i = 0; i < chargingPoints.length; i++) {
            if (chargingPoints[i].isAvailable()) {
                return i;
            }
        }

        return -1;
    }

    // Start charging
    public void startCharging() {
        int index = findAvailablePoint();

        if (index == -1) {
            System.out.println("No charging point is available.");
            return;
        }

        chargingPoints[index].startCharging();

        System.out.println(
            "EV connected to Charging Point "
            + chargingPoints[index].getPointNumber()
        );

        System.out.println(
            "Charging Power: "
            + chargingPoints[index].getChargingPower()
            + " kW"
        );
    }

    // Stop charging at a selected point
    public void stopCharging(int pointNumber) {

        if (pointNumber < 1 ||
            pointNumber > chargingPoints.length) {

            System.out.println("Invalid charging point.");
            return;
        }

        int index = pointNumber - 1;

        if (chargingPoints[index].isAvailable()) {
            System.out.println(
                "Charging Point " + pointNumber +
                " is already available."
            );
        } else {
            chargingPoints[index].stopCharging();

            System.out.println(
                "Charging stopped at Point "
                + pointNumber
            );
        }
    }

    // Display station status
    public void displayStatus() {

        System.out.println(
            "\n----- EV Charging Station Status -----"
        );

        for (ChargingPoint point : chargingPoints) {

            System.out.print(
                "Charging Point "
                + point.getPointNumber()
                + " : "
            );

            if (point.isAvailable()) {
                System.out.println("AVAILABLE");
            } else {
                System.out.println("OCCUPIED / CHARGING");
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Create station with 4 charging points
        // Each point has 22 kW charging capacity
        EVChargingStationManagement station =
                new EVChargingStationManagement(4, 22);

        System.out.println(
            "===== EV CHARGING STATION MANAGEMENT ====="
        );

        station.displayStatus();

        // Connect EVs
        System.out.println("\nConnecting EV 1...");
        station.startCharging();

        System.out.println("\nConnecting EV 2...");
        station.startCharging();

        System.out.println("\nConnecting EV 3...");
        station.startCharging();

        station.displayStatus();

        // Stop charging
        System.out.print(
            "\nEnter charging point to stop: "
        );

        int point = scanner.nextInt();

        station.stopCharging(point);

        station.displayStatus();

        scanner.close();
    }
}
