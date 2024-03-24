package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

public class TranslateDrone implements Phase {
    
    Boolean reachedEnd = false;
    Integer turnCounter = 0;

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

    public String nextDecision(Drone drone, MapRepresenter map) {

        if (turnCounter == 5) {
            turnCounter = 0;
            gridSearch.atEdge = false;
            gridSearch.generalDirection = gridSearch.generalDirection.backSide();
            reachedEnd = true;
            gridSearch.translated = true;
        }
        else{
            if (gridSearch.gridSearchDirection == gridSearch.generalDirection.leftSide()) {
                sideToTranslate = "left";
            } else if (gridSearch.gridSearchDirection == gridSearch.generalDirection.rightSide()) {
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

        if (sideToTranslate.equals("left")) {
            if (turnCounter == 0) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (turnCounter == 1) {
                turnCounter++;
                return drone.fly();
            } else if (turnCounter == 2) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (turnCounter == 3) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            } else if (turnCounter == 4) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().leftSide());
            }
        } else if (sideToTranslate.equals("right")) {
            if (turnCounter == 0) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (turnCounter == 1) {
                turnCounter++;
                return drone.fly();
            } else if (turnCounter == 2) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (turnCounter == 3) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            } else if (turnCounter == 4) {
                turnCounter++;
                return drone.heading(drone.getCurrentHeading().rightSide());
            }
        }
        return null;

    }
}
