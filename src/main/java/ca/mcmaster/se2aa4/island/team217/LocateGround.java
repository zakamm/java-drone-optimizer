package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocateGround implements ResponsePhase {

    private final Logger logger = LogManager.getLogger();

    Boolean flyCheck = true;
    Boolean foundLand = false;
    Boolean reachEnd = false;

    Boolean tempState = true;

    MapInitializer mapInitializer;

    int counter = 0;
    boolean reachedEnd = false;

    public LocateGround(MapInitializer mapInitializer) {
        this.mapInitializer = mapInitializer;

    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return new TurnToGround(mapInitializer);
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (counter == 0) {
            counter++;
            return drone.fly();
        } else if (counter == 1) {
            counter = 0;
            return drone.echo(mapInitializer.directionToEcho);
        } else {
            return null;
        }
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        logger.info(mapInitializer.distanceToGround);
        if (responseStorage.getFound().equals("GROUND")) {
            reachedEnd = true;
            mapInitializer.distanceToGround = responseStorage.getRange() + 1;
        }
        if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
            map.setAsScanned(drone, responseStorage.getRange(), mapInitializer.directionToEcho);
        }
    }

}
