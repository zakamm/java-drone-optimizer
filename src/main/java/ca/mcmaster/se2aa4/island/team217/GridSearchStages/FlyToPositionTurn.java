package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;
import java.util.List;
import java.util.ArrayList;

public class FlyToPositionTurn implements ResponsePhase {

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

    public String nextDecision(Drone drone, MapRepresenter map) {
        if (!flyCheck) {
            flyCheck = true;
            return drone.echo(gridSearch.generalDirection);
        } else if (flyCheck) {
            flyCheck = false;
            return drone.fly();
        }
        return null;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (drone.getAction().equals("echo")) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")
                    || (responseStorage.getFound().equals("GROUND") && responseStorage.getRange() > 3)) {
                nextPhase = new NormalTurn(gridSearch);
                reachedEnd = true;
            }
        }

    }

}
