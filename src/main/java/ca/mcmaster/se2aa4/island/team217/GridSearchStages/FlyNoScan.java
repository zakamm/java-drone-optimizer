package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

public class FlyNoScan implements Phase {

    Boolean reachedEnd = false;

    GridSearch gridSearch;

    public FlyNoScan(GridSearch gridSearch) {
        this.gridSearch = gridSearch;
    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        if (gridSearch.translated){
            gridSearch.translated = false;
            return new NormalTurn(gridSearch);
        }
        else{
            return new ScanAndFly(gridSearch);
        }
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(Drone drone, MapRepresenter map) {
        if (gridSearch.distanceToFly == 0) {
            reachedEnd = true;
            return null;
        } else {
            gridSearch.distanceToFly--;
            return drone.fly();
        }
    }
}