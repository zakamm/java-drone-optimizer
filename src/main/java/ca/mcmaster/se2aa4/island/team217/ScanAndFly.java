package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class ScanAndFly implements ResponsePhase {
    private final Logger logger = LogManager.getLogger();

    Boolean reachedEnd = false;

   //scan first then fly
    Boolean flyCheck = false;

    GridSearch gridSearch;

    Phase nextPhase;

    public ScanAndFly(GridSearch gridSearch) {
        this.gridSearch = gridSearch;

    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return nextPhase;
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (gridSearch.atEdge) {
            reachedEnd = true;
            return drone.echo(drone.currentHeading);
        } else if (!flyCheck) {
            logger.info("Scanning");
            flyCheck = true;
            return drone.scan();
        } else if (flyCheck) {
            flyCheck = false;
            logger.info("Flying");
            return drone.fly();
        }
        return null;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (drone.getAction().equals("scan")){    
            if (!drone.currentLocation.getGround()) {
                gridSearch.atEdge = true;
            }
        }
        if (drone.getAction().equals("echo")) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                gridSearch.atEdge = true;
                nextPhase = new NormalTurn(gridSearch);
            } else {
                gridSearch.distanceToFly = responseStorage.getRange();
                gridSearch.atEdge = false;
                if (gridSearch.distanceToFly == 0) {
                    nextPhase = new ScanAndFly(gridSearch);
                } else {
                    nextPhase = new FlyNoScan(gridSearch);
                }
            }
        }
    }
}
