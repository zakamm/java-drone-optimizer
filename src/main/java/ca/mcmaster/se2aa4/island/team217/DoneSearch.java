package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class DoneSearch implements Phase {
    private final Logger logger = LogManager.getLogger();
    Boolean reachedEnd = false;

    GridSearchPhases griddy;

    public DoneSearch(GridSearchPhases griddy) {
        this.griddy = griddy;

    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return null;
    }

    public Boolean isFinal() {
        return reachedEnd;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        reachedEnd = true;
        return drone.stop();
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

    }
}
