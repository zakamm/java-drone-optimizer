package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.CheckBehindDirection;
import ca.mcmaster.se2aa4.island.team217.FindMissingDimention;
import ca.mcmaster.se2aa4.island.team217.Phase;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class EchoThreeSides implements Phase {

    private final Logger logger = LogManager.getLogger();

    int counter = 0;
    int distanceToGround = 0;
    // used for intialization purposes
    public Integer topY;
    public Integer bottomY;
    public Integer leftX;
    public Integer rightX;

    Boolean foundLand = false;

    Boolean reachEnd = false;

    public Boolean reachedEnd() {
        return reachEnd;
    }

    public String nextDecision(Drone drone, ResponseStorage responseStorage, MapRepresenter map) {
        if (counter == 0 && !reachEnd) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                initializeMapDimensionHelper(drone.getDirection(), responseStorage.getRange());
            } else {
                distanceToGround = responseStorage.getRange();
                foundLand = true;
                // also indicate that intitial direction found ground
                logger.info("facing ground");
            }
            counter++;
            return drone.echo(drone.initialHeading.rightSide(drone.initialHeading));
        }

        else if (counter == 1) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                initializeMapDimensionHelper(drone.getDirection(), responseStorage.getRange());
            } else {
                distanceToGround = responseStorage.getRange();
                foundLand = true;
            }
            counter++;
            return drone.echo(drone.initialHeading.leftSide(drone.initialHeading));
        } else if (counter == 2) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                initializeMapDimensionHelper(drone.getDirection(), responseStorage.getRange());
            } else {
                distanceToGround = responseStorage.getRange();
                foundLand = true;
            }
            counter = 0;
        }

        return null;

    }

    // this method initializes the location of the drone based on 3 echoes, if they
    // dont echo ground
    private void initializeMapDimensionHelper(Heading heading, Integer range) {
        switch (heading) {
            case N:
                topY = range;
                break;
            case E:
                rightX = range;
                break;
            case S:
                bottomY = range;
                break;
            case W:
                leftX = range;
                break;
            default:
                break;
        }
    }

    public Phase getNextPhase() {
        if (foundLand) {
            return new FindMissingDimention();
        } else {
            return new CheckBehindDirection();
        }
    }

    public Boolean isFinal() {
        return true;
    }

}
