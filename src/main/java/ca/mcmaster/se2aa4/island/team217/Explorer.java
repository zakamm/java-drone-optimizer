package ca.mcmaster.se2aa4.island.team217;

import java.io.StringReader;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;

public class Explorer implements IExplorerRaid {

    enum Heading {
        N, E, S, W
    }

    Heading currentHeading = Heading.E;

    private String action; // the action to be taken

    private final Logger logger = LogManager.getLogger();

    private JSONObject checker = new JSONObject(); // to store values to check for iteration
    private JSONObject newResponse = new JSONObject(); // to store values to check for iteration

    private Integer distToGround = 0;
    private Boolean foundLand = false;
    private Boolean boilerPlate = true;
    private Boolean secBoiler = true;
    private Boolean flyCheck = false;
    private Boolean end = false;
    private Boolean boilerPlateMission = true;

    private String startHead;
    private Integer startT;
    private Integer startB;
    private Integer startR;
    private Integer startL;
    private Integer count = 1;
    private String echo1;
    private String echo2;
    private String loc = "";
    private Integer bat;
    private Boolean negBoiler = true;
    private String groundCaught;

    Drone drone;
    MapRepresenter map;
    MissionControl missionControl = new MissionControl(drone, map);

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));
        String direction = info.getString("heading");
        startHead = direction;
        switch (direction) {
            case "N":
                currentHeading = Heading.N;
                startHead = "N";
                break;
            case "E":
                currentHeading = Heading.E;
                startHead = "E";
                break;
            case "S":
                currentHeading = Heading.S;
                startHead = "S";
                break;
            case "W":
                currentHeading = Heading.W;
                startHead = "W";
                break;
            default:
                break;
        }
        Integer batteryLevel = info.getInt("budget");
        bat = batteryLevel;
        drone = new Drone(batteryLevel, direction);
        map = new MapRepresenter();
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        JSONObject parameters = new JSONObject();

        if (negBoiler) {
            decision.put("action", "scan");
            decision.put("action", "stop");
            negBoiler = false;
            logger.info("** Decision: {}", decision.toString());
            return decision.toString();
        }

        if (boilerPlate && !foundLand) {
            switch (startHead) {
                case "E":
                    if (count > 1 && count != 5 && count != 7
                            && checker.getJSONObject("check").getString("found") == "GROUND") {
                        foundLand = true;
                        distToGround = checker.getJSONObject("check").getInt("range");
                        break;
                    }
                    if (count == 1) {
                        parameters.put("direction", "S");
                        decision.put("action", "echo");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 2) {
                        startB = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "E");
                        decision.put("action", "echo");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 3) {
                        startR = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "N");
                        decision.put("action", "echo");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 4) {
                        startT = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "S");
                        decision.put("action", "heading");
                        // action = "echo";
                        decision.put("parameters", parameters);
                        count++;

                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 5) {
                        parameters.put("direction", "W");
                        decision.put("action", "echo");
                        // action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 6) {
                        startL = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "E");
                        decision.put("action", "heading");
                        // action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else {
                        break;
                    }
                case "S":
                    if (count > 1 && count != 5 && count != 7
                            && checker.getJSONObject("check").getString("found") == "GROUND") {
                        foundLand = true;
                        distToGround = checker.getJSONObject("check").getInt("range");
                        break;
                    }
                    if (count == 1) {
                        parameters.put("direction", "S");
                        decision.put("action", "echo");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 2) {
                        startB = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "E");
                        decision.put("action", "echo");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 3) {
                        startR = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "W");
                        decision.put("action", "echo");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 4) {
                        startL = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "E");
                        decision.put("action", "heading");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 5) {
                        parameters.put("direction", "N");
                        decision.put("action", "echo");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 6) {
                        startT = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "S");
                        decision.put("action", "heading");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else {
                        break;
                    }
                case "W":
                    if (count > 1 && count != 5 && count != 7
                            && checker.getJSONObject("check").getString("found") == "GROUND") {
                        foundLand = true;
                        distToGround = checker.getJSONObject("check").getInt("range");
                        break;
                    }
                    if (count == 1) {
                        parameters.put("direction", "S");
                        decision.put("action", "echo");
                        groundCaught = "S";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 2) {
                        startB = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "N");
                        decision.put("action", "echo");
                        groundCaught = "N";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 3) {
                        startT = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "W");
                        decision.put("action", "echo");
                        groundCaught = "W";

                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 4) {
                        startL = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "S");
                        decision.put("action", "heading");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 5) {
                        parameters.put("direction", "E");
                        decision.put("action", "echo");
                        groundCaught = "E";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 6) {
                        startR = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "W");
                        decision.put("action", "heading");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else {
                        break;
                    }
                case "N":
                    if (count > 1 && count != 5 && count != 7
                            && checker.getJSONObject("check").getString("found").equals("GROUND")) {
                        foundLand = true;
                        distToGround = checker.getJSONObject("check").getInt("range");

                        parameters.put("direction", groundCaught);
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    }
                    if (count == 1) {
                        parameters.put("direction", "N");
                        decision.put("action", "echo");
                        groundCaught = "N";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 2) {
                        startT = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "E");
                        groundCaught = "E";
                        decision.put("action", "echo");
                        action = "echo";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 3) {
                        startR = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "W");
                        decision.put("action", "echo");
                        groundCaught = "W";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 4) {
                        startL = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "E");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 5) {
                        parameters.put("direction", "S");
                        decision.put("action", "echo");
                        groundCaught = "S";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else if (count == 6) {
                        startB = checker.getJSONObject("check").getInt("range");
                        parameters.put("direction", "N");
                        decision.put("action", "heading");
                        action = "heading";
                        decision.put("parameters", parameters);
                        count++;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    } else {
                        break;
                    }
                default:
                    break;
            }

            boilerPlate = false; // Set boilerPlate to false
        }

        if (startR != null && startL != null && startT != null && startB != null && startR > startL
                && startT < startB) { // top left of map
            // echo1 = "S";
            // echo2 = "E";
            loc = "TL";
            if (startHead != "E") {
                switch (currentHeading.name()) {
                    case "N":
                        parameters.put("direction", "E");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.E;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    case "W":
                        parameters.put("direction", "N");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.N;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    case "S":
                        parameters.put("direction", "E");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.E;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    default:
                        break;
                }
            }
        } else if (startR != null && startL != null && startT != null && startB != null
                && startL > startR && startT < startB) { // top right of map
            echo1 = "S";
            echo2 = "W";
            loc = "TR";
            if (startHead != "W") {
                switch (currentHeading.name()) {
                    case "N":
                        parameters.put("direction", "W");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.W;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    case "E":
                        parameters.put("direction", "N");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.N;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    case "S":
                        parameters.put("direction", "W");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.W;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    default:
                        break;
                }
            }
        } else if (startR != null && startL != null && startT != null && startB != null
                && startR > startL && startT > startB) { // bottom left of map
            echo1 = "N";
            echo2 = "E";
            loc = "BL";

            if (startHead != "E") {
                switch (currentHeading.name()) {
                    case "N":
                        parameters.put("direction", "E");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.E;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    case "W":
                        parameters.put("direction", "N");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.N;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    case "S":
                        parameters.put("direction", "E");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.E;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    default:
                        break;
                }
            }
        } else if (startR != null && startL != null && startT != null && startB != null
                && startR < startL && startT > startB) { // bottom right of map
            echo1 = "N";
            echo2 = "W";
            loc = "BR";
            if (startHead != "W") {
                switch (currentHeading.name()) {
                    case "N":
                        parameters.put("direction", "W");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.W;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    case "E":
                        parameters.put("direction", "N");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.N;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    case "S":
                        parameters.put("direction", "W");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        currentHeading = Heading.W;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    default:
                        break;
                }
            }
        }

        if (!foundLand && secBoiler && (loc == "TR" || loc == "TL")) {
            parameters.put("direction", "S");
            decision.put("action", "echo");
            decision.put("parameters", parameters);
            secBoiler = false;
            logger.info("** Decision: {}", decision.toString());
            return decision.toString();
        } else if (!foundLand && secBoiler && (loc == "BR" || loc == "BL")) {
            parameters.put("direction", "N");
            decision.put("action", "echo");
            decision.put("parameters", parameters);
            secBoiler = false;
            logger.info("** Decision: {}", decision.toString());
            return decision.toString();
        }

        if (loc == "TR" || loc == "TL") {
            if (!flyCheck && !foundLand) {
                switch (checker.getJSONObject("check").getString("found")) {
                    case "OUT_OF_RANGE":
                        if (checker.getJSONObject("check").getInt("range") == 0 && currentHeading != Heading.N) {
                            parameters.put("direction", "N");
                            decision.put("action", "heading");
                            decision.put("parameters", parameters);
                            currentHeading = Heading.N;
                            logger.info("** Decision: {}", decision.toString());
                            return decision.toString();
                        }
                        if (loc == "TL" && currentHeading == Heading.N) {
                            parameters.put("direction", "W");
                            decision.put("action", "heading");
                            decision.put("parameters", parameters);
                            currentHeading = Heading.W;
                            loc = "TR";
                            logger.info("** Decision: {}", decision.toString());
                            return decision.toString();
                        } else if (loc == "TR" && currentHeading == Heading.N) {
                            parameters.put("direction", "E");
                            decision.put("action", "heading");
                            decision.put("parameters", parameters);
                            currentHeading = Heading.E;
                            loc = "TL";
                            logger.info("** Decision: {}", decision.toString());
                            return decision.toString();
                        }
                        if (bat < 100) {
                            decision.put("action", "stop");

                            logger.info("** Decision: {}", decision.toString());
                            return decision.toString();
                        }
                        decision.put("action", "fly");
                        action = "fly";
                        flyCheck = true;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    case "GROUND":
                        distToGround = checker.getJSONObject("check").getInt("range");
                        foundLand = true;

                        // change heading to south here so we can fly towards ground
                        parameters.put("direction", "S");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        action = "heading";
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    default:
                        break;
                }
            } else if (flyCheck) {
                // echo south again so we can see if there is ground in that direction, only do
                // this if the previous echo was out of range (flyCheck = true)
                parameters.put("direction", "S");
                decision.put("action", "echo");
                decision.put("parameters", parameters);
                action = "echo";
                flyCheck = false;// cechl
            }
        } else if (loc == "BL" || loc == "BR") {
            if (!flyCheck && !foundLand) {
                switch (checker.getJSONObject("check").getString("found")) {
                    case "OUT_OF_RANGE":
                        if (checker.getJSONObject("check").getInt("range") == 0 && currentHeading != Heading.N) {
                            parameters.put("direction", "N");
                            decision.put("action", "heading");
                            decision.put("parameters", parameters);
                            currentHeading = Heading.N;
                            logger.info("** Decision: {}", decision.toString());
                            return decision.toString();
                        }
                        if (loc == "BL" && currentHeading == Heading.N) {
                            parameters.put("direction", "W");
                            decision.put("action", "heading");
                            decision.put("parameters", parameters);
                            currentHeading = Heading.W;
                            loc = "BR";
                            logger.info("** Decision: {}", decision.toString());
                            return decision.toString();
                        } else if (loc == "BR" && currentHeading == Heading.N) {
                            parameters.put("direction", "E");
                            decision.put("action", "heading");
                            decision.put("parameters", parameters);
                            currentHeading = Heading.E;
                            loc = "BL";
                            logger.info("** Decision: {}", decision.toString());
                            return decision.toString();
                        }
                        if (bat < 100) {
                            decision.put("action", "stop");

                            logger.info("** Decision: {}", decision.toString());
                            return decision.toString();
                        }
                        decision.put("action", "fly");
                        action = "fly";
                        flyCheck = true;
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    case "GROUND":
                        distToGround = checker.getJSONObject("check").getInt("range");
                        foundLand = true;

                        // change heading to south here so we can fly towards ground
                        parameters.put("direction", "N");
                        decision.put("action", "heading");
                        decision.put("parameters", parameters);
                        action = "heading";
                        logger.info("** Decision: {}", decision.toString());
                        return decision.toString();
                    default:
                        break;
                }
            } else if (flyCheck) {
                // echo south again so we can see if there is ground in that direction, only do
                // this if the previous echo was out of range (flyCheck = true)
                parameters.put("direction", "S");
                decision.put("action", "echo");
                decision.put("parameters", parameters);
                action = "echo";
                flyCheck = false;// cechl
            }
        }

        if (foundLand && distToGround != 0) {
            // fly south until we reach the ground
            decision.put("action", "fly");
            action = "fly";
            distToGround--;
        }
        if (distToGround == 0 && foundLand && !end) {
            // once we found the ground, we need to scan it so that is shows up on the svg
            // map
            // parameters.put("direction", "S");
            decision.put("action", "scan");
            action = "scan";
            // decision.put("parameters", parameters);
            end = true;
            logger.info("** Decision: {}", decision.toString());
            return decision.toString();
        }

        if (end) {
            // Get the JSONArray from the JSONObject
            JSONArray biomesArray = checker.getJSONObject("check").getJSONArray("biomes");

            // Initialize a String array with the same length as the JSONArray
            String[] biomes = new String[biomesArray.length()];

            // Iterate through the JSONArray and extract each element as a String
            for (int i = 0; i < biomesArray.length(); i++) {
                biomes[i] = biomesArray.getString(i);
            }

            return decision.toString();

        }

        if (end) {
            // ground has been found so return to base
            decision.put("action", "stop");
            action = "stop";

        }

        logger.info("** Decision: {}", decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        // if (boilerPlateMission) {
        // logger.info("Mission has started");
        // logger.info("Mission has started");
        // boilerPlateMission = false;
        // }
        // missionControl.startMission(action, response);

        logger.info("** Response received:\n" + response.toString(2));
        Integer cost = response.getInt("cost");
        bat -= cost;
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        checker.put("check", response.getJSONObject("extras"));
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

    // write code to keep count of iterations and battery level

    public static void main(String[] args) {
        Explorer e = new Explorer();
        e.initialize("{\"budget\":1000,\"heading\":\"N\"}");
        e.takeDecision();
        e.acknowledgeResults("{\"cost\":1,\"status\":\"success\",\"extras\":{\"range\":1}}");
        e.deliverFinalReport();
    }

}
