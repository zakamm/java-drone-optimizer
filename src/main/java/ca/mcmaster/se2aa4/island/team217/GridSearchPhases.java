package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class GridSearchPhases {
    private final Logger logger = LogManager.getLogger();

    Boolean middle;
    Boolean echoed = false;
    Boolean atEdge = false;
    Heading generalDirection;
    Heading gridSearchDirection;
    Integer outOfRangeCounter = 0;
    String sideToTranslate = "";

}
