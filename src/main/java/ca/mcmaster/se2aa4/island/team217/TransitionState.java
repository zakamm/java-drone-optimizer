package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class TransitionState implements Phase {
    private final Logger logger = LogManager.getLogger();
    Boolean reachedEnd = false;
    Boolean needToTranslate;

    Boolean transitionToDoneSearch = false;
    Boolean transitionToTranslateDrone = false;

    GridSearchPhases griddy;

    public TransitionState(GridSearchPhases griddy, Boolean needToTranslate) {
        this.griddy = griddy;
        this.needToTranslate = needToTranslate;

    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        if (transitionToTranslateDrone) {
            return new TranslateDrone(griddy);
        } else if (transitionToDoneSearch) {
            return new DoneSearch(griddy);
        } else {
            return null;
        }
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
            needToTranslate = true;
            griddy.outOfRangeCounter++;
            logger.info("OUT OF RANGE COUNTER {}", griddy.outOfRangeCounter);
            if (!griddy.middle && griddy.outOfRangeCounter == 2) {
                transitionToDoneSearch = true;
                reachedEnd = true;
                return null;
            } else if (griddy.middle && griddy.outOfRangeCounter == 3) {
                transitionToDoneSearch = true;
                reachedEnd = true;
                return null;
            } else {
                if (griddy.gridSearchDirection == griddy.generalDirection.leftSide(griddy.generalDirection)) {
                    transitionToTranslateDrone = true;
                    reachedEnd = true;
                    griddy.sideToTranslate = "left";
                    return null;
                } else if (griddy.gridSearchDirection == griddy.generalDirection.rightSide(griddy.generalDirection)) {
                    transitionToTranslateDrone = true;
                    reachedEnd = true;
                    griddy.sideToTranslate = "right";
                    return null;
                }
            }
        }
        return null;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

    }
}
