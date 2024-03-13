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

    MapRepresenter map;

    public MapInitializer(MapRepresenter map) {
        this.map = map;
    }

    public void initializeMapDimensions(Heading heading, Integer range) {
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


}
