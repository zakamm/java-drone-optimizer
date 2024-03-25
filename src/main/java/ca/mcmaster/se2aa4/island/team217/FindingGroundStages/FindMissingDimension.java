package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;
import ca.mcmaster.se2aa4.island.team217.GridSearchStages.*;


// if we spawn facing ground, fly all the way to the other side of the island and then find the missing dimension
public class FindMissingDimension implements ResponsePhase {

    int counter = 0;
    int flyCounter = 0;
    boolean reachedEnd = false;
    boolean foundDimension = false;

    MapInitializer mapInitializer;

    public FindMissingDimension(MapInitializer mapInitializer) {
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
        // Fly to opposite side of the island and then find the missing dimension, end
        // by scanning the ground
        if (!foundDimension) {
            if (counter == 0) {
                counter++;
                flyCounter++;
                return drone.fly();
            } else {
                counter = 0;
                return drone.echo(drone.getCurrentHeading());
            }
        } else {
            reachedEnd = true;
            return drone.scan();
        }
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (drone.getAction().equals("echo")) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                mapInitializer.initializeMapDimensions(drone.getCurrentHeading(), responseStorage.getRange());
                mapInitializer.initializeMapDimensions(drone.getCurrentHeading().backSide(), flyCounter);
                mapInitializer.initializeRowsAndColumns();
                map.initializeMap();
                drone.initializeCurrentLocation(mapInitializer.leftColumns, mapInitializer.topRows,
                        mapInitializer.spawnedFacingGround);
                foundDimension = true;
            }
        }
    }
}