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

    Heading directionToEcho;

    Boolean spawnedFacingGround = false;

    int distanceToGround = 0;

    MapRepresenter map;

    public MapInitializer(MapRepresenter map) {
        this.map = map;
    }

    public void initializeMapDimensions(Heading heading, Integer range) {
        logger.info("Range"+ range);
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
            logger.info("columns"+  map.columns);
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


}
