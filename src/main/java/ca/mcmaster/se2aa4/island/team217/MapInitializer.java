package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class MapInitializer {

    private final Logger logger = LogManager.getLogger();

    Integer topRows;
    Integer bottomRows;
    Integer leftColumns;
    Integer rightColumns;
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
        switch (heading) {
            case N:
                topRows = range;
                break;
            case E:
                rightColumns = range;
                break;
            case S:
                bottomRows = range;
                break;
            case W:
                leftColumns = range;
                break;
            default:
                break;
        }
    }

    public void initializeRowsAndColumns() {
        if (topRows != null && bottomRows != null && leftColumns != null && rightColumns != null) {
            map.rows = topRows + bottomRows + 1;
            map.columns = leftColumns + rightColumns + 1;
        }
    }

    // determines the direction we have to echo based off drone location and heading
    // we want the drone to move in the initial direction and echo in the direction
    // that is furthest to the edge of the map
    public void directionToEcho(Heading currentHeading) {
        if (currentHeading == Heading.N || currentHeading == Heading.S) {
            if (leftColumns > rightColumns) {
                directionToEcho = Heading.W;
            } else if (rightColumns > leftColumns) {
                directionToEcho = Heading.E;
            } else if (leftColumns == rightColumns) {
                directionToEcho = Heading.E;
            }
        } else if (currentHeading == Heading.E || currentHeading == Heading.W) {
            if (topRows > bottomRows) {
                directionToEcho = Heading.N;
            } else if (bottomRows > topRows) {
                directionToEcho = Heading.S;
            } else if (bottomRows == topRows) {
                directionToEcho = Heading.N;
            }
        }
        logger.info("Direction to Echo: " + directionToEcho);
    }

}
