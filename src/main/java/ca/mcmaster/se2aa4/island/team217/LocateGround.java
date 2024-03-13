package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class LocateGround implements Phase {

    private final Logger logger = LogManager.getLogger();

    Boolean flyCheck = true;
    Boolean foundLand = false;
    Boolean reachEnd = false;

    Boolean tempState = true;

    MapInitializer mapInitializer;

    public LocateGround(MapInitializer mapInitializer) {
        this.mapInitializer = mapInitializer;

    }

    public Boolean reachedEnd() {
        return reachEnd;
    }

    public Phase getNextPhase() {
        return new TurnToGround(mapInitializer);
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        logger.info("LocateGround");
        if (tempState) {
            directionToEcho(drone.currentHeading);
            tempState = false;
        }

        if (!flyCheck && !foundLand) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                flyCheck = true;
                return drone.fly();
            } else if (responseStorage.getFound().equals("GROUND")) {
                mapInitializer.distanceToGround = responseStorage.getRange();
                foundLand = true;
                // headingAfterFirstTurn = drone.currentHeading;
            }
        } else if (flyCheck) {
            // echo in the direction again so we can see if there is ground in that
            // direction, only do this if the previous echo was out of range (flyCheck =
            // true)
            flyCheck = false;
            return drone.echo(mapInitializer.directionToEcho);
        }
        logger.info("WE FOUND THE LANDSSSSSSS");
        return drone.stop();
        // reachEnd = true;
        // return null
    }

    private void directionToEcho(Heading initialHeading) {
        if (initialHeading == Heading.N || initialHeading == Heading.S) {
            if (mapInitializer.leftX > mapInitializer.rightX) {
                mapInitializer.directionToEcho = Heading.W;
            } else if (mapInitializer.rightX > mapInitializer.leftX) {
                mapInitializer.directionToEcho = Heading.E;
            } else if (mapInitializer.leftX == mapInitializer.rightX) {
                mapInitializer.directionToEcho = Heading.E;
            }
        } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
            if (mapInitializer.topY > mapInitializer.bottomY) {
                mapInitializer.directionToEcho = Heading.N;
            } else if (mapInitializer.bottomY > mapInitializer.topY) {
                mapInitializer.directionToEcho = Heading.S;
            } else if (mapInitializer.bottomY == mapInitializer.topY) {
                mapInitializer.directionToEcho = Heading.N;
            }
        }
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

    }

}
