package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class MapInitializer {

    private final Logger logger = LogManager.getLogger();

    Integer topY;
    Integer bottomY;
    Integer leftX;
    Integer rightX;
    Boolean facingGround;

    Heading directionToEcho;

    Boolean spawnedFacingGround = false;

    int distanceToGround = 0;

    MapRepresenter map;
    Drone drone;

    Boolean middle;
    Boolean echoed = false;
    Heading generalDirection;
    Integer outOfRangeCounter = 0;

    public MapInitializer(Drone drone, MapRepresenter map) {
        this.drone = drone;
        this.map = map;
    }

    public void initializeMapDimensions(Heading heading, Integer range) {
        logger.info("Range" + range);
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

    public String rowsOrColumns() {
        if (topY != null && bottomY != null && leftX != null && rightX != null) {
            map.rows = topY + bottomY + 1;
            map.columns = leftX + rightX + 1;
            logger.info("Rows" + map.rows);
            logger.info("columns" + map.columns);
            return "both";
        }
        if (topY != null && bottomY != null) {
            map.rows = topY + bottomY + 1;
            return "rows";
        } else if (leftX != null && rightX != null) {
            map.columns = leftX + rightX + 1;
            return "columns";
        } else {
            return "none";
        }
    }

    // determines the direction we have to echo based off drone location and heading
    // we want the drone to move in the initial direction and echo in the direction
    // that is furthest to the edge of the map
    public void directionToEcho(Heading currentHeading) {
        if (currentHeading == Heading.N || currentHeading == Heading.S) {
            if (leftX > rightX) {
                directionToEcho = Heading.W;
            } else if (rightX > leftX) {
                directionToEcho = Heading.E;
            } else if (leftX == rightX) {
                directionToEcho = Heading.E;
            }
        } else if (currentHeading == Heading.E || currentHeading == Heading.W) {
            if (topY > bottomY) {
                directionToEcho = Heading.N;
            } else if (bottomY > topY) {
                directionToEcho = Heading.S;
            } else if (bottomY == topY) {
                directionToEcho = Heading.N;
            }
        }
    }

}
