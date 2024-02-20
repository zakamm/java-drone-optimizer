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
    Boolean initialThreeCheck = false;
    Boolean flyCheck = false;
    Boolean foundLand = false;
    String directionToEcho;
    int distanceToGround;

    // used for intialization purposes
    public int topY;
    public int bottomY;
    public int leftX;
    public int rightX;

    Boolean initialGroundScanned = false;

    Initializer(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.map = map;
    }

    /* this method finds ground, flies there and scans it
    This method works on the assumption that when the drone enters the map, it is going to be on edges of the map (since entrypoint is closest location to base)
    Also, we assume the drone will be facing a realistic direction (drone entering top left should not be facing north, etc.)
    We also assume the island is more or less centered in the map
    */
    public String initializeMission(Heading initialHeading, HashMap<String, List<String>> responseStorage){

        // first we echo in 3 directions to determine where the drone is located
        if (!(responseStorage.get("found").get(0).equals("GROUND")) && initialThreeCheck == false){
            if (counter == 0){
                initializeLocation(drone.initialHeading, responseStorage.get("range").get(0));
                counter++;
                return drone.echo(drone.initialHeading.rightSide(drone.initialHeading));
            }
            else if (counter == 1){
                initializeLocation(drone.initialHeading.rightSide(drone.initialHeading), responseStorage.get("range").get(0));
                counter++;
                return drone.echo(drone.initialHeading.leftSide(drone.initialHeading));
            }
            else if (counter == 2){
                initializeLocation(drone.initialHeading.leftSide(drone.initialHeading), responseStorage.get("range").get(0));
                counter++;    
            }
            directionToEcho = directionToEcho(drone.initialHeading);
            // only get here if all directions don't echo ground
            logger.info("directionToEcho: " + directionToEcho);
            initialThreeCheck = true;
        }
        // this statement is entered if we echoed ground in one of the three directions above
        if (initialThreeCheck == false && responseStorage.get("found").get(0).equals("GROUND")){
            initialThreeCheck = true;
            distanceToGround = Integer.parseInt(responseStorage.get("range").get(0));
            foundLand = true;
        }

        // this statement is entered if initialThreeCheck is true, meaning we did not find ground in our intial three direction check
        // we fly in the initial direction while echoing directiontoecho until we find ground
        if (!flyCheck && !foundLand) {
            if (responseStorage.get("found").get(0).equals("OUT_OF_RANGE")){
                flyCheck = true;
                return drone.fly();
            }
            else if (responseStorage.get("found").get(0).equals("GROUND")){
                distanceToGround = Integer.parseInt(responseStorage.get("range").get(0));
                foundLand = true;
                return drone.heading(Heading.valueOf(directionToEcho));
            }
        } else if (flyCheck) {
            //echo in the direction again so we can see if there is ground in that direction, only do this if the previous echo was out of range (flyCheck = true)
            flyCheck = false;
            return drone.echo(Heading.valueOf(directionToEcho));
        }

        // once land is found, fly there
        if (foundLand && distanceToGround != 0) {
            distanceToGround--;
            return drone.fly();
        }
        if (distanceToGround == 0 && foundLand && !initialGroundScanned) {
            // once we found the ground, we need to scan it so that is shows up on the svg map
            initialGroundScanned = true;
            map.initialized = true;
            return drone.scan();
        }

        // this is here for now, but will be removed when we work out the final nextDecision logic
        // if (initialGroundScanned == true){
        //     map.initialized = true;
        //     return drone.stop();
        // }

        logger.info("stop");
        return drone.stop();
        

    }

    // this method initializes the location of the drone based on 3 echoes, may get skipped if all three echoes dont happen
    public void initializeLocation(Heading heading, String range){
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
    // we want the drone to move in the initial direction and echo in the direction that is furthest to the edge of the map
    public String directionToEcho(Heading initialHeading){
        logger.info("Initial heading is {}", initialHeading);
        String directionToEcho = "";
        switch (initialHeading){
            case N:
                if (rightX != 0 && leftX == 0){
                    directionToEcho = "E";
                }
                else if (rightX == 0 && leftX != 0){
                    directionToEcho = "W";
                }
                else if (rightX > leftX){
                    directionToEcho = "E";
                }
                else if (leftX > rightX){
                    directionToEcho = "W";
                }
                break;
            case S:
                if (leftX != 0 && rightX == 0){
                    directionToEcho = "W";
                }
                else if (leftX == 0 && rightX != 0){
                    directionToEcho = "E";
                }
                else if (leftX > rightX){
                    directionToEcho = "W";
                }
                else if (rightX > leftX){
                    directionToEcho = "E";
                }
                break;
            case W:
                if (topY != 0 && bottomY == 0){
                    directionToEcho = "N";
                }
                else if (topY == 0 && bottomY != 0){
                    directionToEcho = "S";
                }
                else if (topY > bottomY){
                    directionToEcho = "N";
                }
                else if (bottomY > topY){
                    directionToEcho = "S";
                }
                break;
            case E: 
                logger.info("Bottom Y is {}", bottomY);
                logger.info("Top Y is {}", topY);
                if (bottomY != 0 && topY == 0){
                    directionToEcho = "S";
                }
                else if (bottomY == 0 && topY != 0){
                    directionToEcho = "N";
                }
                else if (bottomY > topY){
                    directionToEcho = "S";
                }
                else if (topY > bottomY){
                    directionToEcho = "N";
                }
                break;
            default:
                break;
        }
        return directionToEcho;
    }

}
