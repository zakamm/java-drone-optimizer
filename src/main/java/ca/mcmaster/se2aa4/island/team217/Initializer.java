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

    private final Logger logger = LogManager.getLogger();

    Drone drone;
    MapRepresenter map;

    int counter = 0;
    int flyCounter = 0;
    Boolean initialThreeCheck = false;
    Boolean flyCheck = false;
    Boolean foundLand = false;

    Boolean facingGround = false;
    Boolean nextDimensionDetermined = false;

    // we want to make an initial turn to check backward direction, only if not
    // spawned facing ground
    Boolean initialTurn = false;
    Boolean foundMissingCoordinate = false;

    // used to determine how many dimensions we found from our initial echo and turn
    String rowsOrColumns;

    Heading directionToEcho;

    int distanceToGround;
    Heading headingAfterFirstTurn;
    Boolean spawnedFacingGround = false;

    // used for intialization purposes
    public Integer topY;
    public Integer bottomY;
    public Integer leftX;
    public Integer rightX;

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
    public String initializeMission(Heading initialHeading, ResponseStorage responseStorage) {

        // first we echo in 3 directions to determine where the drone is located
        if (initialThreeCheck == false) {
            return initialThreeCheck(responseStorage);
        }

        if (initialTurn == true && !facingGround && !nextDimensionDetermined) {
            return determineNextDimension(responseStorage);
        }

        if (!foundMissingCoordinate) {
            return findMissingCoordinate(responseStorage);
        }

        if (nextDimensionDetermined == true && rowsOrColumns.equals("both")) {
            logger.info("rows or columns: " + rowsOrColumns);
            // run a solid fly check here
            if (!flyCheck && !foundLand) {
                if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                    flyCheck = true;
                    return drone.fly();
                } else if (responseStorage.getFound().equals("GROUND")) {
                    distanceToGround = responseStorage.getRange();
                    foundLand = true;
                    headingAfterFirstTurn = drone.currentHeading;
                }
            } else if (flyCheck) {
                // echo in the direction again so we can see if there is ground in that
                // direction, only do this if the previous echo was out of range (flyCheck =
                // true)
                flyCheck = false;
                return drone.echo(directionToEcho);
            }

            if (facingGround == false) {
                logger.info(directionToEcho);
                return turnToGround(directionToEcho);
            }

            // once land is found, fly there
            if (foundLand && distanceToGround != 0) {
                distanceToGround--;
                return drone.fly();
            }
            if (distanceToGround == 0 && foundLand && !initialGroundScanned) {
                // once we found the ground, we need to scan it so that is shows up on the svg
                // map
                initialGroundScanned = true;
                map.initialized = true;
                return drone.scan();
            }
        }

        logger.info("stop");
        return drone.stop();
    }

    private String initialTurn(String rowsOrColumns) {
        if (drone.initialHeading == Heading.N || drone.initialHeading == Heading.S) {
            if (leftX > rightX) {
                return drone.heading(Heading.W);
            } else {
                return drone.heading(Heading.E);
            }
        } else if (drone.initialHeading == Heading.E || drone.initialHeading == Heading.W) {
            if (topY > bottomY) {
                return drone.heading(Heading.N);
            } else {
                return drone.heading(Heading.S);
            }
        }
        return null;

    }

    // if we spawned not facing ground, we echo backwards to determine that
    // dimension
    private String determineNextDimension(ResponseStorage responseStorage) {
        if (counter == 0) {
            counter++;
            return drone.echo(drone.initialHeading.backSide(drone.initialHeading));
        } else if (counter == 1) {
            initializeMapDimensions(drone.getDirection(),
                    responseStorage.getRange() - 1); 
            rowsOrColumns = rowsOrColumns();
            logger.info("rows or columns: " + rowsOrColumns);
            if (rowsOrColumns.equals("both")) {
                logger.info(topY + " " + bottomY + " " + leftX + " " + rightX);
                directionToEcho = directionToEcho(drone.currentHeading);
                map.initializeMap();
                drone.initializeCurrentLocation(leftX, topY, spawnedFacingGround);
                foundMissingCoordinate = true;
            }
            nextDimensionDetermined = true;
            counter = 0;
            return drone.echo(drone.currentHeading);
        }
        return null;
    }

    // echoe three times and then run initialturn based on the results
    public String initialThreeCheck(ResponseStorage responseStorage) {
        if (counter == 0) {
            if (responseStorage.getRange() == 0) {
                return drone.stop();
            }
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                initializeMapDimensions(drone.getDirection(), responseStorage.getRange());
            } else {
                distanceToGround = responseStorage.getRange();
                foundLand = true;
                // do not need to turn anymore since ground is in front of us
                initialTurn = true;
                // also indicate that intitial direction found ground
                facingGround = true;
                spawnedFacingGround = true;
                logger.info("facing ground");
            }
            counter++;
            return drone.echo(drone.initialHeading.rightSide(drone.initialHeading));
        }

        else if (counter == 1) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                initializeMapDimensions(drone.getDirection(), responseStorage.getRange());
            } else {
                distanceToGround = responseStorage.getRange();
                foundLand = true;
            }
            counter++;
            return drone.echo(drone.initialHeading.leftSide(drone.initialHeading));
        } else if (counter == 2) {
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                initializeMapDimensions(drone.getDirection(), responseStorage.getRange());
            } else {
                distanceToGround = responseStorage.getRange();
                foundLand = true;
            }
            counter = 0;
        }
        initialThreeCheck = true;
        rowsOrColumns = rowsOrColumns();
        if (rowsOrColumns.equals("none")) {
            return drone.stop();
        }
        if (!facingGround) {
            initialTurn = true;
            return initialTurn(rowsOrColumns);
        } else {
            return drone.echo(drone.currentHeading);
        }
    }

    // this method initializes the location of the drone based on 3 echoes, if they
    // dont echo ground
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

    public String rowsOrColumns() {
        if (topY != null && bottomY != null && leftX != null && rightX != null) {
            map.rows = topY + bottomY + 1;
            map.columns = leftX + rightX + 1;
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

    // if we spawned facing ground, we want to find the missing coordinate by flying
    // until we echo the edge of the map, this helps us determine the missing
    // coordinate
    public String findMissingCoordinate(ResponseStorage responseStorage) {
        if (!responseStorage.getFound().equals("OUT_OF_RANGE")) {
            if (counter == 0) {
                counter++;
                flyCounter++;
                return drone.fly();
            } else {
                counter = 0;
                return drone.echo(drone.currentHeading);
            }
        } else {
            initializeMapDimensions(drone.currentHeading, responseStorage.getRange());
            initializeMapDimensions(drone.currentHeading.backSide(drone.currentHeading), flyCounter);
            logger.info(flyCounter);
            logger.info("topY: " + topY + " bottomY: " + bottomY + " leftX: " + leftX + " rightX: " + rightX);
            rowsOrColumns = rowsOrColumns();
            map.initializeMap();
            drone.initializeCurrentLocation(leftX, topY, spawnedFacingGround);
            foundMissingCoordinate = true;
            map.initialized = true;
            return drone.scan();
        }
    }

    // determines the direction we have to echo based off drone location and heading
    // we want the drone to move in the initial direction and echo in the direction
    // that is furthest to the edge of the map
    public Heading directionToEcho(Heading initialHeading) {
        if (initialHeading == Heading.N || initialHeading == Heading.S) {
            if (leftX > rightX) {
                directionToEcho = Heading.W;
            } else if (rightX > leftX) {
                directionToEcho = Heading.E;
            } else if (leftX == rightX) {
                directionToEcho = Heading.E;
            }
        } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
            if (topY > bottomY) {
                directionToEcho = Heading.N;
            } else if (bottomY > topY) {
                directionToEcho = Heading.S;
            } else if (bottomY == topY) {
                directionToEcho = Heading.N;
            }
        }
        return directionToEcho;
    }

    // the methods below are used to turn perpendicularly
    public String turnToGround(Heading groundDirection) {
        switch (headingAfterFirstTurn) {
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
                    logger.info("WE ARE IN CASE 2");
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
        if (counter == 0) {
            counter++;
            return drone.fly();
        } else if (counter == 1) {
            counter++;
            distanceToGround -= 1;
            return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
        } else if (counter == 2) {
            counter++;
            distanceToGround -= 1;
            return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
        } else if (counter == 3) {
            counter = 0;
            facingGround = true;
            return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
        } else {
            return null;
        }
    }

    private String turnToGroundHelperCaseTwo() {
        logger.info("WE ARE IN CASE 2");

        if (counter == 0) {
            logger.info("Counter {}", counter);
            counter++;
            logger.info(drone.currentHeading);
            return drone.fly();
        } else if (counter == 1) {
            logger.info("Counter {}", counter);
            logger.info(drone.currentHeading);

            counter++;
            distanceToGround -= 1;
            return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
        } else if (counter == 2) {
            logger.info("Counter {}", counter);
            logger.info(drone.currentHeading);
            counter++;
            distanceToGround -= 1;
            return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
        } else if (counter == 3) {
            counter = 0;
            facingGround = true;
            return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
        } else {
            return null;
        }
    }

}