package ca.mcmaster.se2aa4.island.team217;
import main.java.ca.mcmaster.se2aa4.island.team217.Point;

public class Drone {
    Integer batteryLevel;
    Point currentLocation; // may need to change this to Point instead of POI
    Heading currentHeading;
    Boolean status; 

    // Scanner will be a subclass of Drone
    public enum Heading {
        N, E, S, W
    }

    Drone(Integer batteryLevel, String currentHeading) {
        this.batteryLevel = batteryLevel;
        this.currentHeading = Heading.valueOf(currentHeading);
        System.out.println("Drone is created");
    }

    public void fly(Point poi) {
        // move forward
    }

    public Heading heading(Heading currentHeading) {
        return null;
    }

    public String echo() {
        return null;
    }

    public boolean stop(Integer batteryLevel) {
        return false;
    }
}
