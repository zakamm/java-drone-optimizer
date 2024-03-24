package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;
import ca.mcmaster.se2aa4.island.team217.GridSearchStages.*;

public class FlyToGround implements Phase {

    int counter = 0;

    boolean reachedEnd = false;

    MapInitializer mapInitializer;

    public FlyToGround(MapInitializer mapInitializer) {
        this.mapInitializer = mapInitializer;
    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return new ScanAndFly(new GridSearch(mapInitializer.drone, mapInitializer.map));
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(Drone drone, MapRepresenter map) {
        if (mapInitializer.distanceToGround == 1) {
            reachedEnd = true;
            return drone.scan();
        } else {
            mapInitializer.distanceToGround--;
            return drone.fly();
        }
    }
}
