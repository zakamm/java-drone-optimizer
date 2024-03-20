package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.NormalTurn;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;
import java.util.List;
import java.util.ArrayList;

public class FlyToPositionTurn implements Phase {
    private final Logger logger = LogManager.getLogger();

    Boolean reachedEnd = false;
    Boolean isFinal = false;

    // scan first then fly
    Boolean flyCheck = false;

    GridSearch gridSearch;

    Phase nextPhase;

    public FlyToPositionTurn(GridSearch gridSearch) {
        this.gridSearch = gridSearch;
    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return nextPhase;
    }

    public Boolean isFinal() {
        return isFinal;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        logger.info("WE ARE IN FLY TO TURINGIN POSITION");

        if (!flyCheck) {
            String sideToTurn = findSideToTurn();
            logger.info("ECHOING");
            flyCheck = true;
            if (sideToTurn.equals("left")) {
                logger.info("ECHOING LEFT, {}", drone.currentHeading.leftSide(drone.currentHeading));
                return drone.echo(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (sideToTurn.equals("right")) {
                logger.info("ECHOING RIGHT, {}", drone.currentHeading.rightSide(drone.currentHeading));
                return drone.echo(drone.currentHeading.rightSide(drone.currentHeading));
            }

        } else if (flyCheck) {
            logger.info("Range for ground {}", responseStorage.getRange());
            logger.info("reponseStorage FOUND {}", responseStorage.getFound());
            if (responseStorage.getFound().equals("OUT_OF_RANGE")
                    || (responseStorage.getFound().equals("GROUND") && responseStorage.getRange() > 2)) {
                logger.info("REACHED END");
                if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                    map.scannedRow(drone);
                }
                nextPhase = new NormalTurn(gridSearch);
                reachedEnd = true;
                return null;
            }
            flyCheck = false;
            logger.info("Flying");
            return drone.fly();
        }
        return null;
    }

    private String findSideToTurn() {
        String sideToTurn = "";

        if (gridSearch.gridSearchDirection == gridSearch.generalDirection.leftSide(gridSearch.generalDirection)) {
            sideToTurn = "right";
        } else if (gridSearch.gridSearchDirection == gridSearch.generalDirection
                .rightSide(gridSearch.generalDirection)) {
            sideToTurn = "left";
        }
        return sideToTurn;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        // if (drone.getAction().equals("echo")) {
        // if (responseStorage.getFound().equals("OUT_OF_RANGE")
        // || (responseStorage.getFound().equals("GROUND") && responseStorage.getRange()
        // > 2)) {
        // logger.info("SETTING NEXT PHASE");
        // }
        // }
    }

}
