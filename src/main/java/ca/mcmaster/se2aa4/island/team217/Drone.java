package ca.mcmaster.se2aa4.island.team217;
import main.java.ca.mcmaster.se2aa4.island.team217.Point;

public class Drone {
    Integer batteryLevel;
    Point currentLocation; // may need to change this to Point instead of POI
    Heading currentHeading;
    Heading initialHeading;
    //Boolean status; 

    // Scanner will be a subclass of Drone
    public enum Heading {
        N, E, S, W;
        public Heading turnLeft() {
            switch (this) {
                case N:
                    return W;
                case E:
                    return N;
                case S:
                    return E;
                case W:
                    return S;
                default:
                    return null;
            }
        }
        public Heading turnRight() {
            switch (this) {
                case N:
                    return E;
                case E:
                    return S;
                case S:
                    return W;
                case W:
                    return N;
                default:
                    return null;
            }
        }

        public Heading turnBack() {
            switch (this) {
                case N:
                    return S;
                case E:
                    return W;
                case S:
                    return N;
                case W:
                    return E;
                default:
                    return null;
            }
        }
    }

    Drone(Integer batteryLevel, String initialHeading) {
        this.batteryLevel = batteryLevel;
        this.currentHeading = Heading.valueOf(initialHeading);
        this.initialHeading = Heading.valueOf(initialHeading);
        System.out.println("Drone is created");
    }

}
