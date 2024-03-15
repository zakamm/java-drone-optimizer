package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class PossibleTurn implements Phase {
    private final Logger logger = LogManager.getLogger();
    Boolean transitionToScanAndFly = false;
    Boolean transitionToTransitionState = false;
    Boolean transitionToTranslateDrone = false;
    Boolean reachedEnd = false;
    Boolean flyCheck = false;
    Boolean boilerPlate;

    Boolean needToTranslate = false;
    Integer counter = 0;
    String sideToTurn = "";

    Heading gridSearchDirection;

    GridSearchPhases griddy;

    public PossibleTurn(GridSearchPhases griddy) {
        this.griddy = griddy;
    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        if (transitionToScanAndFly) {
            return new ScanAndFly(griddy);
        } else if (transitionToTransitionState) {
            return new TransitionState(griddy, needToTranslate);
        } else if (transitionToTranslateDrone) {
            return new TranslateDrone(griddy);
        } else {
            return null;
        }
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        logger.info("WE IN THE ECHOEED");
        logger.info("THIS IS THE STORAGE {} ", responseStorage.getFound());
        // The drone will have echoed and so now it will check to see if the echo is out
        // of range or if it needs to keep searching, this is done through activating
        // this next state of "atEdge"
        if (counter == 0 &&
                responseStorage.getFound().equals("OUT_OF_RANGE")) {
            logger.info("WE SETTING atEdge!!!");
            griddy.atEdge = true;
            boilerPlate = false;
        } else if (boilerPlate) {
            reachedEnd = true;
            transitionToScanAndFly = true;
            return drone.scan();
        }

        if (griddy.atEdge) {
            // The drone will continue to call the turnAroundGridSearch method until it has
            // completed its rotation and then will reset all elements for the next turn in
            // the future
            if (counter == 2) {
                counter = 0;
                gridSearchDirection = drone.currentHeading;
                griddy.atEdge = false;
                return drone.scan();
            } else {
                if (gridSearchDirection == griddy.generalDirection.leftSide(griddy.generalDirection)) {
                    sideToTurn = "right";
                } else if (gridSearchDirection == griddy.generalDirection
                        .rightSide(griddy.generalDirection)) {
                    sideToTurn = "left";
                }
                logger.info("COUNTERRRR {} , ", counter);
                return normalTurnAroundGridSearch(sideToTurn, drone);
            }
        }

        // Once we have turned around, and echoed, if we are out of range, we determine
        // if we are done or not
        if (griddy.echoed && !griddy.atEdge && counter == 1 && !needToTranslate) {
            transitionToTransitionState = true;
            reachedEnd = true;
            return null;
        }
        // this moves the drone horizontally to the left or right by one so that we can
        // grid search again in the opposite general direction
        if (needToTranslate) {
            transitionToTranslateDrone = true;
            reachedEnd = true;
            return null;
        }

        // echo after the turn is complete to determine if we are out of range
        if (griddy.echoed && !griddy.atEdge && counter == 0) {
            logger.info("ECHO AFTER THE TURN TO DETERMINE IF WE ARE DONE");
            counter++;
            return drone.echo(drone.currentHeading);
        } else {
            griddy.echoed = false;
            counter = 0;
            flyCheck = false;
            transitionToScanAndFly = true;
            reachedEnd = true;
            boilerPlate = true;
            return drone.fly();

        }

    }

    private String normalTurnAroundGridSearch(String sideToTurn, Drone drone) {
        if (sideToTurn.equals("left")) {
            if (counter == 0) {
                counter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (counter == 1) {
                counter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            }
        } else if (sideToTurn.equals("right")) {
            if (counter == 0) {
                counter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (counter == 1) {
                counter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            }
        }
        return null;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

    }
}
