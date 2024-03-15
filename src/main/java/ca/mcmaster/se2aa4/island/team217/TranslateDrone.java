package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.GridSearchPhases;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class TranslateDrone implements Phase {
    private final Logger logger = LogManager.getLogger();
    Boolean reachedEnd = false;
    Integer turnCounter = 0;
    Integer distanceOutOfBounds;

    GridSearchPhases griddy;

    public TranslateDrone(GridSearchPhases griddy) {
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
        if (turnCounter == 5) {
            if (responseStorage.getFound().equals(null)) { // took out !needToFly
                return drone.echo(drone.currentHeading);
            } else if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                distanceOutOfBounds = responseStorage.getRange();
                // needToFly = true;
            }
            if (distanceOutOfBounds >= 3) { // took out needToFly
                distanceOutOfBounds--;
                return drone.fly();
            }
            // needToFly = false;
            turnCounter = 0;
            griddy.gridSearchDirection = drone.currentHeading;
            griddy.generalDirection = griddy.generalDirection.backSide(griddy.generalDirection);
            griddy.atEdge = false;

            reachedEnd = true;
            return drone.scan();
        } else {
            logger.info("TURNCOUNTERRRR {} , ", turnCounter);
            return translateOver(griddy.sideToTranslate, drone);
        }

    }

    // this method translates the drone over one spot to the left or right when we
    // reach the end of the island so we can grid search in the opposite general
    // direction
    // it only needs two squares above and to the side to perform the maneuver
    private String translateOver(String sideToTranslate, Drone drone) {
        logger.info("WE TURNING AROUNDDD");
        logger.info(griddy.gridSearchDirection);
        if (sideToTranslate.equals("left")) {
            // Only needs one spot above it turn
            if (turnCounter == 0) {
                turnCounter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (turnCounter == 1) {
                turnCounter++;
                return drone.fly();
            } else if (turnCounter == 2) {
                turnCounter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (turnCounter == 3) {
                turnCounter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (turnCounter == 4) {
                turnCounter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            }
        } else if (sideToTranslate.equals("right")) {
            // needs one spot above it to turn
            if (turnCounter == 0) {
                turnCounter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (turnCounter == 1) {
                turnCounter++;
                return drone.fly();
            } else if (turnCounter == 2) {
                turnCounter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (turnCounter == 3) {
                turnCounter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (turnCounter == 4) {
                turnCounter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            }
        }
        return null;

    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

    }
}
