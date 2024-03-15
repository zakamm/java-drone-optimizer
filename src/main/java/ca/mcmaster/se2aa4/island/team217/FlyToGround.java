package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FlyToGround implements Phase {

    private final Logger logger = LogManager.getLogger();
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
        return null;
    }

    public Boolean isFinal() {
        return reachedEnd;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (mapInitializer.distanceToGround == 0) {
            reachedEnd = true;
            return drone.scan();
        } else {
            mapInitializer.distanceToGround--;
            return drone.fly();
        }
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

    }
}
