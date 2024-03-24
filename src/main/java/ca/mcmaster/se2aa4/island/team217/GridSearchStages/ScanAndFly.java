package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;
import java.util.List;
import java.util.ArrayList;

public class ScanAndFly implements ResponsePhase {

    Boolean reachedEnd = false;
    Boolean isFinal = false;

    // scan first then fly
    Boolean flyCheck = false;
    Boolean foundClosestCreek = false;

    GridSearch gridSearch;

    Phase nextPhase;

    public ScanAndFly(GridSearch gridSearch) {
        this.gridSearch = gridSearch;

    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        //return null;
        return nextPhase;
    }

    public Boolean isFinal() {
        return isFinal;
    }

    public String nextDecision(Drone drone, MapRepresenter map) {
        if (gridSearch.atEdge) {
            reachedEnd = true;
            return drone.echo(drone.getCurrentHeading());
        } else if (!flyCheck) {
            flyCheck = true;
            return drone.scan();
        } else if (flyCheck) {
            flyCheck = false;
            return drone.fly();
        }
        return null;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (drone.getAction().equals("scan")) {
            if (!drone.getCurrentLocation().getGround()) {
                gridSearch.atEdge = true;
            }
            if (map.getSite() != null && !map.getCreeks().isEmpty()) {
                foundClosestCreek = gridSearch.foundClosestCreek(map);
                if (foundClosestCreek) {
                    reachedEnd = true;
                    isFinal = true;
                }
            }
        }
        if (drone.getAction().equals("echo")) {
            map.setAsScanned(drone, responseStorage.getRange(), drone.getCurrentHeading());
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                gridSearch.atEdge = true;
                if (gridSearch.gridSearchDirection == gridSearch.generalDirection.leftSide()) {
                    gridSearch.sideToTurn = "right";
                } else if (gridSearch.gridSearchDirection == gridSearch.generalDirection
                        .rightSide()) {
                    gridSearch.sideToTurn = "left";
                }
                nextPhase = new FlyToPositionTurn(gridSearch);
            } else {
                gridSearch.distanceToFly = responseStorage.getRange() + 1;
                gridSearch.atEdge = false;
                nextPhase = new FlyNoScan(gridSearch);
            }
        }
    }
}
