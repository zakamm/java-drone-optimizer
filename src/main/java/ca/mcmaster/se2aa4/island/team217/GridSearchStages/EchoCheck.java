package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

public class EchoCheck implements ResponsePhase {

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

    public String nextDecision(Drone drone, MapRepresenter map) {
        return drone.echo(drone.getCurrentHeading());
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (responseStorage.getFound().equals("OUT_OF_RANGE") && !gridSearch.translated) {
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading());
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading().backSide());
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
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading());
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading().backSide());
            reachedEnd = true;
            gridSearch.distanceToFly = responseStorage.getRange() - 5;
            nextPhase = new FlyNoScan(gridSearch);
        }else if (responseStorage.getFound().equals("GROUND")) {
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading());
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading().backSide());
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
