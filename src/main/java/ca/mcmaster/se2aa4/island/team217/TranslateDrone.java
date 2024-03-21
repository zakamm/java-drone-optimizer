package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class TranslateDrone implements Phase {
    private final Logger logger = LogManager.getLogger();
    Boolean reachedEnd = false;
    Integer turnCounter = 0;
    Integer distanceOutOfBounds;

    GridSearch gridSearch;
    String sideToTranslate;

    public TranslateDrone(GridSearch gridSearch) {
        this.gridSearch = gridSearch;
    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return new EchoCheck(gridSearch);
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

        if (turnCounter == 5) {
            turnCounter = 0;
            gridSearch.atEdge = false;
            gridSearch.generalDirection = gridSearch.generalDirection.backSide(gridSearch.generalDirection);
            reachedEnd = true;
            gridSearch.translated = true;
        }
        else{
            if (gridSearch.gridSearchDirection == gridSearch.generalDirection.leftSide(gridSearch.generalDirection)) {
                sideToTranslate = "left";
            } else if (gridSearch.gridSearchDirection == gridSearch.generalDirection.rightSide(gridSearch.generalDirection)) {
                sideToTranslate = "right";
            }
            return translateOver(sideToTranslate, drone);
        }
        return null;
    }

    // this method translates the drone over one spot to the left or right when we
    // reach the end of the island so we can grid search in the opposite general
    // direction
    // it only needs two squares above and to the side to perform the maneuver
    private String translateOver(String sideToTranslate, Drone drone) {
        logger.info("Translating the Drone over to the {} side", sideToTranslate);

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
}
