package ca.mcmaster.se2aa4.island.team217;

//import 3.4;

import ca.mcmaster.se2aa4.island.team217.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;
import ca.mcmaster.se2aa4.island.team217.Initializer;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;

public class GridSearcher {
    private final Logger logger = LogManager.getLogger();

    Drone drone;
    MapRepresenter map;

    int counter = 0;
    Boolean echoed = false;
    Boolean flyCheck = false;
    Boolean atEdge = false;
    Heading gridSearchDirection = Heading.S;

    public GridSearcher(Drone drone, MapRepresenter map) {
        this.drone = drone;
        this.map = map;
    }

    public String searchGrid(HashMap<String, List<String>> responseStorage) {
        if (echoed) {
            logger.info("WE IN THE ECHOEED");
            logger.info("THIS IS THE STORAGE {} ", responseStorage.get("found").get(0));
            // return drone.stop();
            if (counter == 0 &&
                    responseStorage.get("found").get(0).equals("OUT_OF_RANGE")) {
                logger.info("WE SETTING atEdge!!!");
                atEdge = true;
            }
            // implement turning around method
            if (atEdge) {
                if (counter == 6) {
                    counter = 0;
                    gridSearchDirection = drone.currentHeading;
                    // if (gridSearchDirection.equals(Heading.S)) {
                    // gridSearchDirection = Heading.N;
                    // } else {
                    // gridSearchDirection = Heading.S;
                    // }
                    flyCheck = false;
                    atEdge = false;
                    return drone.scan();
                    // return drone.stop();
                } else {
                    logger.info("COUNTERRRR {} , ", counter);

                    return turnAroundGridSearch();
                }
            }
            if (echoed && !atEdge && counter == 0) {
                logger.info("DOING A QUICK FLY");
                counter++;
                return drone.fly();
            } else {
                logger.info("DOING A QUICK SCAN");
                echoed = false;
                counter = 0;
                return drone.scan();
            }
        }
        if (!drone.currentLocation.getGround()) {
            echoed = true ? echoed == false : false;
            logger.info("WE BOUTTA ECHOOOOOOOOOOOOOOOOOOOOOO");
            // return drone.stop();
            logger.info("CURRENT HEADING {}", drone.currentHeading);
            return drone.echo(drone.currentHeading);
        } else if (!flyCheck) {
            flyCheck = true;
            echoed = false;
            return drone.scan();
        } else if (flyCheck) {
            flyCheck = false;
            echoed = false;
            return drone.fly();
        } else {
            return drone.stop();
        }

    }

    private String turnAroundGridSearch() {
        logger.info("WE TURNING AROUNDDD");
        logger.info(gridSearchDirection);
        if (gridSearchDirection.equals(Heading.S) || gridSearchDirection.equals(Heading.W)) {
            if (counter == 0) {
                counter++;
                return drone.fly();
            } else if (counter == 1) {
                counter++;
                logger.info("COUNTER IS A 1 WE TURNIGN LEFTT");
                logger.info(drone.currentHeading.leftSide(drone.currentHeading));
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (counter == 2) {
                counter++;
                return drone.fly();
            } else if (counter == 3) {
                counter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (counter == 4) {
                counter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (counter == 5) {
                counter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            }
        } else if (gridSearchDirection.equals(Heading.N) || gridSearchDirection.equals(Heading.E)) {
            if (counter == 0) {
                counter++;
                return drone.fly();
            } else if (counter == 1) {
                counter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (counter == 2) {
                counter++;
                return drone.fly();
            } else if (counter == 3) {
                counter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (counter == 4) {
                counter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (counter == 5) {
                counter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            }
        }
        return null;
        // switch (gridSearchDirection) {
        // case S:
        // if (counter == 0) {
        // counter++;
        // return drone.fly();
        // } else if (counter == 1) {
        // counter++;
        // logger.info("COUNTER IS A 1 WE TURNIGN LEFTT");
        // logger.info(drone.currentHeading.leftSide(drone.currentHeading));
        // return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
        // } else if (counter == 2) {
        // counter++;
        // return drone.fly();
        // } else if (counter == 3) {
        // counter++;
        // return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
        // } else if (counter == 4) {
        // counter++;
        // return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
        // } else if (counter == 5) {
        // counter++;
        // return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
        // }
        // case N:
        // if (counter == 0) {
        // counter++;
        // return drone.fly();
        // } else if (counter == 1) {
        // counter++;
        // return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
        // } else if (counter == 2) {
        // counter++;
        // return drone.fly();
        // } else if (counter == 3) {
        // counter++;
        // return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
        // } else if (counter == 4) {
        // counter++;
        // return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
        // } else if (counter == 5) {
        // counter++;
        // return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
        // }
        // default:
        // return null;
        // }

    }
}
