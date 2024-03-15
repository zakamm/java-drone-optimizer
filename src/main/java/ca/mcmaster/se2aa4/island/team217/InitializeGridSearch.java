package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.GridSearchPhases;
import ca.mcmaster.se2aa4.island.team217.GridSearchPhases;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class InitializeGridSearch implements Phase {
    private final Logger logger = LogManager.getLogger();

    Boolean reachedEnd = false;

    Heading initialHeading;
    Point initialLocation;

    GridSearchPhases griddy;

    public InitializeGridSearch(GridSearchPhases griddy) {
        this.griddy = griddy;
    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return new ScanAndFly(griddy);
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        initialLocation = drone.currentLocation;
        initialHeading = drone.currentHeading;

        // need to refactor this in the future
        griddy.middle = drone.spawnedFacingGround;
        initializeGeneralDirection();
        reachedEnd = true;
        logger.info("GRID SEARCH INITIALIZED");
        return null;
    }

    // this method initializes the general direction of the drone based on where it
    // is located on the island and the initial heading when it touches ground
    public void initializeGeneralDirection() {
        if (initialLocation.getX() < 26 && initialLocation.getY() < 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                griddy.generalDirection = Heading.E;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                griddy.generalDirection = Heading.S;
            }
        } else if (initialLocation.getY() >= 26 && initialLocation.getX() < 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                griddy.generalDirection = Heading.W;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                griddy.generalDirection = Heading.S;
            }
        } else if (initialLocation.getX() >= 26 && initialLocation.getY() < 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                griddy.generalDirection = Heading.E;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                griddy.generalDirection = Heading.N;
            }
        } else if (initialLocation.getY() >= 26 && initialLocation.getX() >= 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                griddy.generalDirection = Heading.W;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                griddy.generalDirection = Heading.N;
            }
        }
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

    }
}
