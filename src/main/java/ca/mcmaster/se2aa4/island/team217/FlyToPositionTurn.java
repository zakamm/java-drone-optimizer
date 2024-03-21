package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;
import java.util.List;
import java.util.ArrayList;

public class FlyToPositionTurn implements ResponsePhase {
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
        logger.info("WE ARE IN FLY TO TURNING POSITION");

        if (!flyCheck) {
            logger.info("ECHOING");
            flyCheck = true;
            return drone.echo(gridSearch.generalDirection);
        } else if (flyCheck) {
            flyCheck = false;
            logger.info("Flying");
            return drone.fly();
        }
        return null;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (drone.getAction().equals("echo")) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")
                    || (responseStorage.getFound().equals("GROUND") && responseStorage.getRange() > 2)) {
                logger.info("REACHED END");
                nextPhase = new NormalTurn(gridSearch);
                reachedEnd = true;
            }
        }
    
    }

}
