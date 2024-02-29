package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;
import ca.mcmaster.se2aa4.island.team217.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.List;

// an object of this class is responsible for initializing the drone location and finding ground 
public class Initializer {

    // private final java.util.logging.Logger logger = LogManager.getLogger();
    private final Logger logger = LogManager.getLogger();

    Drone drone;
    MapRepresenter map;

    int counter = 0;
    int counter2 = 0;
    int counter3 = 0;
    Boolean initialThreeCheck = false;
    Boolean flyCheck = false;
    Boolean flyCheck1 = false;
    Boolean foundLand = false;
    boolean facingGround = false;
    String directionToEcho;
    int distanceToGround;

    // used for intialization purposes
    public int topY;
    public int bottomY;
    public int leftX;
    public int rightX;

    Boolean initialGroundScanned = false;

    public Initializer(Drone drone, MapRepresenter map) {
        this.drone = drone;
        this.map = map;
    }

    /*
     * this method finds ground, flies there and scans it
     * This method works on the assumption that when the drone enters the map, it is
     * going to be on edges of the map (since entrypoint is closest location to
     * base)
     * Also, we assume the drone will be facing a realistic direction (drone
     * entering top left should not be facing north, etc.)
     * We also assume the island is more or less centered in the map
     */
    public String initializeMission(Heading initialHeading, HashMap<String, List<String>> responseStorage) {
        logger.info("CURRENT HEAD, {}", drone.currentHeading);
        // first we echo in 3 directions to determine where the drone is located
        if ((responseStorage.get("found").get(0).equals("GROUND")
                || responseStorage.get("found").get(0).equals("OUT_OF_RANGE")) && initialThreeCheck == false) {
            if (responseStorage.get("found").get(0).equals("GROUND")) {
                distanceToGround = Integer.parseInt(responseStorage.get("range").get(0));
                foundLand = true;
            }
            if (counter == 0) {
                if (responseStorage.get("range").get(0).equals("0")) {
                    return drone.stop();
                }
                initializeLocation(drone.initialHeading, responseStorage.get("range").get(0));
                counter++;
                return drone.echo(drone.initialHeading.rightSide(drone.initialHeading));
            } else if (counter == 1) {
                initializeLocation(drone.initialHeading.rightSide(drone.initialHeading),
                        responseStorage.get("range").get(0));
                counter++;
                return drone.echo(drone.initialHeading.leftSide(drone.initialHeading));
            } else if (counter == 2) {
                initializeLocation(drone.initialHeading.leftSide(drone.initialHeading),
                        responseStorage.get("range").get(0));
                counter++;
            }
            directionToEcho = directionToEcho(drone.initialHeading);
            // only get here if all directions don't echo ground
            logger.info("directionToEcho: " + directionToEcho);
            initialThreeCheck = true;
        }
        // this statement is entered if we echoed ground in one of the three directions
        // above
        // if (initialThreeCheck == false &&
        // responseStorage.get("found").get(0).equals("GROUND")){
        // initialThreeCheck = true;
        // distanceToGround = Integer.parseInt(responseStorage.get("range").get(0));
        // foundLand = true;
        // }

        // if (drone.currentHeading.equals(drone.getDirection())) {
        // facingGround = true;
        // }

        // if (initialThreeCheck == true && foundLand == true && !facingGround){
        // facingGround = true;
        // return drone.heading(Heading.valueOf(directionToEcho));
        // }

        // this statement is entered if initialThreeCheck is true, meaning we did not
        // find ground in our intial three direction check
        // we fly in the initial direction while echoing directiontoecho until we find
        // ground
        if (!flyCheck && !foundLand) {
            if (responseStorage.get("found").get(0).equals("OUT_OF_RANGE")) {
                flyCheck = true;
                return drone.fly();
            } else if (responseStorage.get("found").get(0).equals("GROUND")) {
                distanceToGround = Integer.parseInt(responseStorage.get("range").get(0));
                foundLand = true;
                return drone.scan();
            }
        } else if (flyCheck && !foundLand) {
            // echo in the direction again so we can see if there is ground in that
            // direction, only do this if the previous echo was out of range (flyCheck =
            // true)
            flyCheck = false;
            return drone.echo(Heading.valueOf(directionToEcho));
        }

        if (initialThreeCheck && foundLand && !facingGround) {
            if (counter2 == 4) {
                facingGround = true;
            } else {
                return turnToGround(Heading.valueOf(drone.getDirection()));
            }
        }

        // once land is found, fly there
        if (!flyCheck1 && distanceToGround != 0) {
            flyCheck1 = true;
            return drone.scan();
        } else if (flyCheck1 && foundLand && distanceToGround != 0) {
            distanceToGround--;
            flyCheck1 = false;
            return drone.fly();
        }
        if (distanceToGround == 0 && foundLand && !initialGroundScanned) {
            // once we found the ground, we need to scan it so that is shows up on the svg
            // map
            initialGroundScanned = true;
            map.initialized = true;
            return drone.scan();
        }

        // this is here for now, but will be removed when we work out the final
        // nextDecision logic
        // if (initialGroundScanned == true){
        // map.initialized = true;
        // return drone.stop();
        // }

        logger.info("stop");
        return drone.stop();

    }

    public String turnToGround(Heading groundDirection) {
        switch (drone.currentHeading) {
            case N:
                if (groundDirection.equals(Heading.W)) {
                    return turnToGroundHelperCaseOne();
                } else {
                    return turnToGroundHelperCaseTwo();
                }
            case E:
                if (groundDirection.equals(Heading.N)) {
                    return turnToGroundHelperCaseOne();
                } else {
                    return turnToGroundHelperCaseTwo();
                }
            case S:
                if (groundDirection.equals(Heading.E)) {
                    return turnToGroundHelperCaseOne();
                } else {
                    return turnToGroundHelperCaseTwo();
                }
            case W:
                if (groundDirection.equals(Heading.S)) {
                    return turnToGroundHelperCaseOne();
                } else {
                    return turnToGroundHelperCaseTwo();
                }
            default:
                return null;
        }

    }

    private String turnToGroundHelperCaseOne() {
        if (counter2 == 0) {
            counter2++;
            return drone.fly();
        } else if (counter2 == 1) {
            counter2++;
            distanceToGround -= 1;
            return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
        } else if (counter2 == 2) {
            counter2++;
            distanceToGround -= 1;
            return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
        } else if (counter2 == 3) {
            counter2++;
            return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
        } else {
            return null;
        }
    }

    private String turnToGroundHelperCaseTwo() {
        logger.info("WE ARE IN CASE 2");

        if (counter2 == 0) {
            logger.info("Counter {}", counter2);
            counter2++;
            logger.info(drone.currentHeading);
            return drone.fly();
        } else if (counter2 == 1) {
            logger.info("Counter {}", counter2);
            logger.info(drone.currentHeading);

            counter2++;
            distanceToGround -= 1;
            return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
        } else if (counter2 == 2) {
            logger.info("Counter {}", counter2);
            logger.info(drone.currentHeading);
            counter2++;
            distanceToGround -= 1;
            return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
        } else if (counter2 == 3) {
            counter2++;
            return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
        } else {
            return null;
        }
    }

    // // testing minimized grid search algo
    // public String gridSearch(HashMap responseStorage) {
    // flyCheck = false;
    // if (responseStorage.get("found").get(0).equals("OUT_OF_RANGE") &&
    // responseStorage.get("range").get(0) <= 2) {
    // // implement turning around method
    // if (counter3 == 0) {

    // }

    // } else if (!flyCheck) {
    // flyCheck = true;
    // return drone.scan();
    // } else if (flyCheck) {
    // flyCheck = false;
    // return drone.fly();
    // }

    // }

    // private String turnAroundGridSearch() {

    // }

    // this method initializes the location of the drone based on 3 echoes, may get
    // skipped if all three echoes dont happen
    public void initializeLocation(Heading heading, String range) {
        switch (heading) {
            case N:
                topY = Integer.parseInt(range);
                break;
            case E:
                rightX = Integer.parseInt(range);
                break;
            case S:
                bottomY = Integer.parseInt(range);
                break;
            case W:
                leftX = Integer.parseInt(range);
                break;
            default:
                break;
        }
    }

    // determines the direction we have to echo based off drone location and heading
    // we want the drone to move in the initial direction and echo in the direction
    // that is furthest to the edge of the map
    public String directionToEcho(Heading initialHeading) {
        String directionToEcho = "";
        switch (initialHeading) {
            case N:
                if (rightX > leftX) {
                    directionToEcho = "E";
                } else if (leftX > rightX) {
                    directionToEcho = "W";
                } else if (leftX == rightX) {
                    directionToEcho = "E";
                }
                break;
            case S:
                if (leftX > rightX) {
                    directionToEcho = "W";
                } else if (rightX > leftX) {
                    directionToEcho = "E";
                } else if (leftX == rightX) {
                    directionToEcho = "E";
                }
                break;
            case W:
                if (topY > bottomY) {
                    directionToEcho = "N";
                } else if (bottomY > topY) {
                    directionToEcho = "S";
                } else if (bottomY == topY) {
                    directionToEcho = "N";
                }
                break;
            case E:
                if (bottomY > topY) {
                    directionToEcho = "S";
                } else if (topY > bottomY) {
                    directionToEcho = "N";
                } else if (bottomY == topY) {
                    directionToEcho = "N";
                }
                break;
            default:
                break;
        }
        return directionToEcho;
    }

}
