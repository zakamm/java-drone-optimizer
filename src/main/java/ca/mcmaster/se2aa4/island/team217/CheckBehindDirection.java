package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class CheckBehindDirection implements Phase {

    private final Logger logger = LogManager.getLogger();

    int counter = 0;
    MapInitializer mapInitializer;

    boolean reachedEnd = false;

    public CheckBehindDirection(MapInitializer mapInitializer) {
        this.mapInitializer = mapInitializer;
    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return new LocateGround(mapInitializer);
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (counter == 0) {
            counter++;
            return initialTurn(drone);
        } else if (counter == 1) {
            counter++;
            return drone.echo(drone.initialHeading.backSide(drone.initialHeading));
        } else {

            // this is questionable 
            logger.info("Reached end of CheckBehindDirection");
            mapInitializer.initializeMapDimensions(drone.getDirection(), responseStorage.getRange() - 1);
            String rowsOrColumns = mapInitializer.rowsOrColumns();
            logger.info("rows or columns: " + rowsOrColumns);
            if (rowsOrColumns.equals("both")) {
                // directionToEcho = directionToEcho(drone.currentHeading);
                map.initializeMap();
                drone.initializeCurrentLocation(mapInitializer.leftX, mapInitializer.topY,
                        mapInitializer.spawnedFacingGround);
            }
            reachedEnd = true;
            mapInitializer.directionToEcho(drone.currentHeading);
            return null;
        }
    }

    private String initialTurn(Drone drone) {
        if (drone.initialHeading == Heading.N || drone.initialHeading == Heading.S) {
            if (mapInitializer.leftX > mapInitializer.rightX) {
                return drone.heading(Heading.W);
            } else {
                return drone.heading(Heading.E);
            }
        } else if (drone.initialHeading == Heading.E || drone.initialHeading == Heading.W) {
            if (mapInitializer.topY > mapInitializer.bottomY) {
                return drone.heading(Heading.N);
            } else {
                return drone.heading(Heading.S);
            }
        }
        return null;
    }
}
