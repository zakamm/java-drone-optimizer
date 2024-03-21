package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlyNoScan implements Phase {
    private final Logger logger = LogManager.getLogger();

    Boolean reachedEnd = false;

    Boolean skipFirstTile = true;

    GridSearch gridSearch;

    public FlyNoScan(GridSearch gridSearch) {
        this.gridSearch = gridSearch;
    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        if (gridSearch.translated) {
            return new NormalTurn(gridSearch);
        } else {
            return new ScanAndFly(gridSearch);
        }
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        logger.info("FlyingNoScan");
        if (gridSearch.distanceToFly == 0) {
            reachedEnd = true;
            return null;
        // } else if (skipFirstTile) {
        //     skipFirstTile = false;
        //     return drone.scan();
        } else {
            gridSearch.distanceToFly--;
            return drone.fly();
        }
    }
}