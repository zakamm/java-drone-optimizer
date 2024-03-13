package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FindMissingDimension implements Phase{

    private final Logger logger = LogManager.getLogger();

    int counter;
    int flyCounter;
    boolean reachedEnd = false;
    boolean foundDimension = false;

    MapInitializer mapInitializer;

    public FindMissingDimension(MapInitializer mapInitializer){
        this.mapInitializer = mapInitializer;
    }
        
    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return null;
    }

    public Boolean isFinal() {
        return false;
    }
    
    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (!foundDimension){
            if (counter == 0) {
                counter++;
                flyCounter++;
                return drone.fly();
            } else {
                counter = 0;
                return drone.echo(drone.currentHeading);
            }
        }
        else{
            reachedEnd = true;
            return drone.scan();
        }
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map){
        if (drone.getAction().equals("echo")) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")){
                mapInitializer.initializeMapDimensions(drone.currentHeading, responseStorage.getRange());
                mapInitializer.initializeMapDimensions(drone.currentHeading.backSide(drone.currentHeading), flyCounter);
                String rowsOrColumns = mapInitializer.rowsOrColumns();
                map.initializeMap();
                drone.initializeCurrentLocation(mapInitializer.leftX, mapInitializer.topY, mapInitializer.spawnedFacingGround);
                foundDimension = true;
            }
        }
    }
}
