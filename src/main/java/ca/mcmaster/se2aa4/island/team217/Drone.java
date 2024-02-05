
package ca.mcmaster.se2aa4.island.team217;

public class Drone {
    Integer batteryLevel;
    PointOfInterest currentLocation; // may need to change this to Point instead of POI
    Heading currentHeading = Heading.EAST;
    // Scanner will be a subclass of Drone
    public enum Heading {
        NORTH, EAST, SOUTH, WEST
    }

    Drone(Integer batteryLevel, PointOfInterest currentLocation){
        this.batteryLevel = batteryLevel;
        this.currentLocation = currentLocation;
    }

    public void fly(PointOfInterest poi){
        // move forward
    }

    public Heading heading(Heading currentHeading){
        return null;
    }

    public String echo(){
        return null;
    }

    public boolean stop(Integer batteryLevel){
        return false;
    }
}
