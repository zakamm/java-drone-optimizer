package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class EchoCheck implements ResponsePhase {
    private final Logger logger = LogManager.getLogger();

    Boolean reachedEnd = false;
    Boolean isFinal = false;

    GridSearch gridSearch;

    Phase nextPhase;

    public EchoCheck(GridSearch gridSearch) {
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
        logger.info("EchoCheck");
        return drone.echo(drone.currentHeading);
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (responseStorage.getFound().equals("OUT_OF_RANGE") && !gridSearch.translated) {
            map.setAsScanned(drone, responseStorage.getRange());
            reachedEnd = true;
            gridSearch.outOfRangeCounter++;
            if (gridSearch.middle && gridSearch.outOfRangeCounter == 3) {
                isFinal = true;
            }else if (!gridSearch.middle && gridSearch.outOfRangeCounter == 2) {
                isFinal = true;
            }
            else{
                nextPhase = new TranslateDrone(gridSearch);
            }
        }else if (responseStorage.getFound().equals("OUT_OF_RANGE") && gridSearch.translated) {
            map.setAsScanned(drone, responseStorage.getRange());
            reachedEnd = true;
            gridSearch.translated = false;
            gridSearch.distanceToFly = responseStorage.getRange() - 5;
            nextPhase = new FlyNoScan(gridSearch);
        
        }else if (responseStorage.getFound().equals("GROUND")) {
            map.setAsScanned(drone, responseStorage.getRange());
            reachedEnd = true;
            gridSearch.translated = false;
            gridSearch.distanceToFly = responseStorage.getRange();
            if (gridSearch.distanceToFly == 0) {
                nextPhase = new ScanAndFly(gridSearch);
            } else {
                nextPhase = new FlyNoScan(gridSearch);
            }
        }
            
    }
}
