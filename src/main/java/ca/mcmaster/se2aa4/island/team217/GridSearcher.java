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
    int turnCounter = 0;
    int distanceOutOfBounds = 0;

    Boolean echoed = false;
    Boolean flyCheck = false;
    Boolean atEdge = false;
    Boolean initializeGridSearch = false;
    Boolean middle;
    int outOfRangeCounter = 0;
    Point initialLocation;
    Heading initialHeading; 
    String sideToTranslate = "";
    String sideToTurn = "";
    Boolean needToTranslate = false;
    Boolean needToFly = false;

    Heading generalDirection;
    Heading gridSearchDirection;

    public GridSearcher(Drone drone, MapRepresenter map) {
        this.drone = drone;
        this.map = map;
        gridSearchDirection = this.drone.initialHeading;
    }

    /*
     * scans through the whole island and will store all important POI's in the map
     * instanstiation
     */
    public String searchGrid(ResponseStorage responseStorage) {

        // initialize the grid search
        if (initializeGridSearch == false) {
            initializeGridSearch = true;
            initialLocation = drone.currentLocation;
            initialHeading = drone.currentHeading;

            //  need to refactor this in the future
            middle = drone.spawnedFacingGround;
            initializeGeneralDirection();
        }

        logger.info("BATTERY {}", drone.getBatteryLevel());

        // This is a state in which the drone is above only water as a biome, but we
        // dont know if we are in the middle of the island or past it so, it checks that
        if (echoed) {
            logger.info("WE IN THE ECHOEED");
            logger.info("THIS IS THE STORAGE {} ", responseStorage.getFound());
            // The drone will have echoed and so now it will check to see if the echo is out
            // of range or if it needs to keep searching, this is done through activating
            // this next state of "atEdge"
            if (counter == 0 &&
                responseStorage.getFound().equals("OUT_OF_RANGE")) 
            {
                logger.info("WE SETTING atEdge!!!");
                atEdge = true;
            }
            // implement turning around method
            if (atEdge) {
                // The drone will continue to call the turnAroundGridSearch method until it has
                // completed its rotation and then will reset all elements for the next turn in
                // the future
                if (counter == 2) {
                    counter = 0;
                    gridSearchDirection = drone.currentHeading;
                    atEdge = false;
                    return drone.scan();
                } else {
                    if (gridSearchDirection == generalDirection.leftSide(generalDirection)){
                        sideToTurn = "right";
                    }
                    else if (gridSearchDirection == generalDirection.rightSide(generalDirection)){
                        sideToTurn = "left";
                    }
                    logger.info("COUNTERRRR {} , ", counter);
                    return normalTurnAroundGridSearch(sideToTurn);
                }
            }


            // Once we have turned around, and echoed, if we are out of range, we determine if we are done or not
            if (echoed && !atEdge && counter == 1 && !needToTranslate) {
                if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                    needToTranslate = true;
                    outOfRangeCounter++;
                    logger.info("OUT OF RANGE COUNTER {}", outOfRangeCounter);
                    if (!middle && outOfRangeCounter == 2) {
                        logger.info("DONE");
                        return drone.stop();
                    }
                    else if (middle && outOfRangeCounter == 3) {
                        logger.info("DONE");
                        return drone.stop();
                    }
                    else{
                        if (gridSearchDirection == generalDirection.leftSide(generalDirection)){
                            sideToTranslate = "left";
                        }
                        else if (gridSearchDirection == generalDirection.rightSide(generalDirection)){
                            sideToTranslate = "right";
                        }
                    }
                }
            }
            // this moves the drone horizontally to the left or right by one so that we can grid search again in the opposite general direction
            if (needToTranslate) {
                if (turnCounter == 5) {
                    if(responseStorage.getFound().equals(null) && !needToFly) {
                        return drone.echo(drone.currentHeading);
                    }
                    else if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                        distanceOutOfBounds = responseStorage.getRange();
                        needToFly = true;
                    }
                    if (needToFly && distanceOutOfBounds >= 3) {
                        distanceOutOfBounds--;
                        return drone.fly();
                    }
                    needToFly = false;
                    needToTranslate = false;
                    turnCounter = 0;
                    gridSearchDirection = drone.currentHeading;
                    generalDirection = generalDirection.backSide(generalDirection);
                    atEdge = false;
                    return drone.scan();
                } else {
                    logger.info("TURNCOUNTERRRR {} , ", turnCounter);
                    return translateOver(sideToTranslate);
                }
            }

            // echo after the turn is complete to determine if we are out of range
            if (echoed && !atEdge && counter == 0) {
                logger.info("ECHO AFTER THE TURN TO DETERMINE IF WE ARE DONE");
                counter++;
                return drone.echo(drone.currentHeading);
            } else {
                echoed = false;
                counter = 0;
                flyCheck = false;
                return drone.fly();
            }

        }
        // If we are only on OCEAN as a biome, the drone will echo and set echoed to be
        // true, otherwise it will cycle between scanning and then flying forward
        if (!drone.currentLocation.getGround()) {
            echoed = !echoed;
            logger.info("WE BOUTTA ECHOOOOOOOOOOOOOOOOOOOOOO");
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
            // a pretty useless stop
            return drone.stop();
        }

    }

    private String normalTurnAroundGridSearch(String sideToTurn){
        if (sideToTurn.equals("left")) {
            if (counter == 0) {
                counter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            }
            else if (counter == 1){
                counter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            }
        } else if (sideToTurn.equals("right")) {
            if (counter == 0) {
                counter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (counter == 1){
                counter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            }
        }
        return null;
    }
    
    // this method translates the drone over one spot to the left or right when we reach the end of the island so we can grid search in the opposite general direction
    // it only needs two squares above and to the side to perform the maneuver
    private String translateOver(String sideToTranslate) {
        logger.info("WE TURNING AROUNDDD");
        logger.info(gridSearchDirection);
        if (sideToTranslate.equals("left")) {
            // Only needs one spot above it turn
            if (turnCounter == 0) {
                turnCounter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (turnCounter == 1) {
                turnCounter++;
                return drone.fly();
            } else if (turnCounter == 2) {
                turnCounter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (turnCounter == 3) {
                turnCounter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            } else if (turnCounter == 4) {
                turnCounter++;
                return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
            }
        } else if (sideToTranslate.equals("right")) {
            // needs one spot above it to turn
            if (turnCounter == 0) {
                turnCounter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (turnCounter == 1) {
                turnCounter++;
                return drone.fly();
            } else if (turnCounter == 2) {
                turnCounter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (turnCounter == 3) {
                turnCounter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            } else if (turnCounter == 4) {
                turnCounter++;
                return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
            }
        }
        return null;

    }

    // this method initializes the general direction of the drone based on where it is located on the island and the initial heading when it touches ground
    public void initializeGeneralDirection() {
        if (initialLocation.getX() < 26 && initialLocation.getY() < 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S){
                generalDirection = Heading.E;
            }
            else if (initialHeading == Heading.E || initialHeading == Heading.W){
                generalDirection = Heading.S;
            }
        }
        else if (initialLocation.getY() >= 26 && initialLocation.getX() < 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S){
                generalDirection = Heading.W;
            }
            else if (initialHeading == Heading.E || initialHeading == Heading.W){
                generalDirection = Heading.S;
            }
        } else if (initialLocation.getX() >= 26 && initialLocation.getY() < 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S){
                generalDirection = Heading.E;
            }
            else if (initialHeading == Heading.E || initialHeading == Heading.W){
                generalDirection = Heading.N;
            }
        } else if (initialLocation.getY() >= 26 && initialLocation.getX() >= 26) {
            if (initialHeading == Heading.N || initialHeading == Heading.S){
                generalDirection = Heading.W;
            }
            else if (initialHeading == Heading.E || initialHeading == Heading.W){
                generalDirection = Heading.N;
            }
        }
    }
}
