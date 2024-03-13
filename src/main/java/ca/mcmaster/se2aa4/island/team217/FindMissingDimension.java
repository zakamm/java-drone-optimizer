package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindMissingDimension implements Phase {

    private final Logger logger = LogManager.getLogger();
    int counter = 0;
    int flyCounter = 0;

    Boolean reachEnd = false;

    MapInitializer mapInitializer;

    public FindMissingDimension(MapInitializer mapInitializer) {
        this.mapInitializer = mapInitializer;
    }

    public Boolean reachedEnd() {
        return false;
    }

    public Phase getNextPhase() {
        return null;
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (!responseStorage.getFound().equals("OUT_OF_RANGE")) {
            if (counter == 0) {
                counter++;
                flyCounter++;
                return drone.fly();
            } else {
                counter = 0;
                return drone.echo(drone.currentHeading);
            }
        } else {
            initializeMapDimensionHelper(drone.currentHeading, responseStorage.getRange());
            initializeMapDimensionHelper(drone.currentHeading.backSide(drone.currentHeading), flyCounter);
            // logger.info(flyCounter);
            // logger.info("topY: " + topY + " bottomY: " + bottomY + " leftX: " + leftX + "
            // rightX: " + rightX);
            logger.info("INITIALIZING THE MAPPINGS BABY");
            map.initializeMap();
            logger.info("MAPPPY 1");
            drone.initializeCurrentLocation(mapInitializer.leftX, mapInitializer.topY, mapInitializer.facingGround);
            logger.info("MAPPPY 2");
            map.initialized = true;

            logger.info("WE DID IT BOIII");
            return drone.stop();
            // return null;

            // if (counter == 0) {
            // counter++;
            // return drone.scan();

            // } else {
            // counter = 0;
            // reachEnd = true;
            // logger.info("WE DID IT BOIII");
            // return drone.stop();
            // // return null;

            // }
        }
    }

    private void initializeMapDimensionHelper(Heading heading, Integer range) {
        switch (heading) {
            case N:
                mapInitializer.topY = range;
                break;
            case E:
                mapInitializer.rightX = range;
                break;
            case S:
                mapInitializer.bottomY = range;
                break;
            case W:
                mapInitializer.leftX = range;
                break;
            default:
                break;
        }
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (!(responseStorage.getCost() == null)) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                mapInitializer.initializeMapDimensions(drone.getDirection(), responseStorage.getRange());

            } else {
                mapInitializer.distanceToGround = responseStorage.getRange();
                mapInitializer.facingGround = true;
            }
        }

    }

}
