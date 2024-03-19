package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EchoThreeSides implements ResponsePhase {

    private final Logger logger = LogManager.getLogger();

    int counter = 0;
    boolean reachedEnd = false;

    MapInitializer mapInitializer;

    public EchoThreeSides(MapInitializer mapInitializer) {
        this.mapInitializer = mapInitializer;
    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        // want to know if we are found land or not
        // if we have found land, we want to go to the FindMissingDimension phase
        // else we go to the checkbehinddirection phase
        if (mapInitializer.spawnedFacingGround){
            return new FindMissingDimension( mapInitializer);
        }
        else{
            return new LocateGround(mapInitializer);
        }
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        logger.info(drone.getBatteryLevel());
        if (counter == 0) {
            counter++;
            return drone.echo(drone.initialHeading);
        } else if (counter == 1) {
            counter++;
            return drone.echo(drone.initialHeading.rightSide(drone.initialHeading));
        } else if (counter == 2) {
            counter++;
            reachedEnd = true;
            return drone.echo(drone.initialHeading.leftSide(drone.initialHeading));
        } else {
            return null;
        }
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        // we want to process the response from the echo
        if (!(responseStorage.getCost() == null)) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                mapInitializer.initializeMapDimensions(drone.getDirection(), responseStorage.getRange());
            } else {
                mapInitializer.distanceToGround = responseStorage.getRange();
                mapInitializer.spawnedFacingGround = true;
            }
            if (reachedEnd && !mapInitializer.spawnedFacingGround) {
                mapInitializer.initializeMapDimensions(drone.currentHeading.backSide(drone.currentHeading), 0);
                drone.initializeCurrentLocation(mapInitializer.leftColumns, mapInitializer.topRows, mapInitializer.spawnedFacingGround);
                mapInitializer.directionToEcho(drone.currentHeading);
            }
        }

    }
}
