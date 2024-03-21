package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class NormalTurn implements Phase {
    private final Logger logger = LogManager.getLogger();

    Boolean reachedEnd = false;

    GridSearch gridSearch;
    int counter;

    public NormalTurn(GridSearch gridSearch) {
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
        if (counter == 2) {
            counter = 0;
            gridSearch.gridSearchDirection = drone.currentHeading;
            gridSearch.atEdge = false;
            reachedEnd = true;
        } else {
            return normalTurnAroundGridSearch(gridSearch.sideToTurn, drone);
        }
        return null;
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

    // idea of an intializer method that calls some sort of method that is required
    // to instantiate this phase
}
