package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class GridSearch {
    
    
    Heading generalDirection;
    Heading gridSearchDirection;

    MapRepresenter map;
    Drone drone;
    Point initialLocation;
    Heading initialHeading;
    int distanceToFly;
    int outOfRangeCounter = 0;

    boolean middle;
    Boolean atEdge = false;
    Boolean translated = false;
    String sideToTurn = "";
    
    public GridSearch(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.initialLocation = drone.currentLocation;
        this.initialHeading = drone.currentHeading;
        this.gridSearchDirection = initialHeading;
        this.map = map;
        this.middle = drone.spawnedFacingGround;
        initializeGeneralDirection();
    }

    public void initializeGeneralDirection() {
        if (initialLocation.getRow() < 26 && initialLocation.getColumn() < 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                generalDirection = Heading.E;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                generalDirection = Heading.S;
            }
        } else if (initialLocation.getColumn() >= 26 && initialLocation.getRow() < 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                generalDirection = Heading.W;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                generalDirection = Heading.S;
            }
        } else if (initialLocation.getRow() >= 26 && initialLocation.getColumn() < 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                generalDirection = Heading.E;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                generalDirection = Heading.N;
            }
        } else if (initialLocation.getColumn() >= 26 && initialLocation.getRow() >= 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                generalDirection = Heading.W;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                generalDirection = Heading.N;
            }
        }
    }
}