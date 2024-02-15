package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;

public class MissionControl {
    private final Logger logger = LogManager.getLogger();
    Drone drone;
    MapRepresenter map;
    JSONObject decision = new JSONObject();
    Boolean initialGroundEchoed = false;
    Boolean initialGroundScanned = false;
    Boolean searchedCoast = false;
    
    MissionControl(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.map = map;
    }

    public String nextDecision(String action, JSONObject previousResponse){
        decision.clear();
        HashMap<String, List<String>> response = storeResponse(action, previousResponse);
        if (map.initialized == false){
            map.initialized = true;
            initializeMission(drone.initialHeading);
        }
        return decision.toString();
    
    }
    
    // this method echos all 4 directions to determine where in the map the drone is located and where the nearest ground is
    public void initializeMission(Heading initialHeading){
        decisionTaken("echo", String.valueOf(initialHeading));

    }


    public HashMap<String, List<String>> storeResponse(String action, JSONObject previousResponse){
        HashMap<String, List<String>> responseStorage = new HashMap<String, List<String>>();
        List<String> temp = new ArrayList<String>();
        Integer cost = previousResponse.getInt("cost");
        temp.add(Integer.toString(previousResponse.getInt("cost")));
        responseStorage.put("cost", temp);

        temp = new ArrayList<String>();
        temp.add(String.valueOf(previousResponse.getString("status")));
        responseStorage.put("status", temp);

        if (action.equals("echo")){
            temp = new ArrayList<String>();
            temp.add(String.valueOf(previousResponse.getJSONObject("extras").getInt("range")));
            responseStorage.put("range", temp);

            temp = new ArrayList<String>();
            temp.add(previousResponse.getJSONObject("extras").getString("found"));
            responseStorage.put("found", temp);

        }
        else if (action.equals("scan")){
            temp = new ArrayList<String>();
            JSONArray creeksArray = previousResponse.getJSONObject("extras").getJSONArray("creeks");
            for (int i = 0; i < creeksArray.length(); i++) {
                temp.add(creeksArray.getString(i));
            }
            responseStorage.put("creeks", temp);


            temp = new ArrayList<String>();
            JSONArray biomesArray = previousResponse.getJSONObject("extras").getJSONArray("biomes");
            for (int i = 0; i < biomesArray.length(); i++) {
                temp.add(biomesArray.getString(i));
            }
            responseStorage.put("biomes", temp);

            temp = new ArrayList<String>();
            JSONArray sitesArray = previousResponse.getJSONObject("extras").getJSONArray("sites");
            for (int i = 0; i < sitesArray.length(); i++) {
                temp.add(sitesArray.getString(i));
            }
            responseStorage.put("sites", temp);
        }

        for (Map.Entry<String, List<String>> entry : responseStorage.entrySet()) {
            // Print the key
            logger.info("Key: " + entry.getKey());
            for (String value : entry.getValue()) {
                // Print each value
                logger.info("Value: " + value);
            }
        }
        return responseStorage;   
    }

    // based off the given command, this method will return the appropriate commands and parameters to takeDecision as a jsonObject
    public void decisionTaken(String command){
        String nextDecision;
        if (command.equals("fly")){
            decision.put("action", "echo");
        }
        else if (command.equals("scan")){
            decision.put("action", "scan");
        }
        else if (command.equals("stop")){
            decision.put("action", "stop");
        }
        else{
            logger.info("Invalid command");
            System.exit(0);
        }
        nextDecision = "{action: " + command + "}";
    }

    public void decisionTaken(String command, String direction){
        JSONObject parameters = new JSONObject();

        if (command.equals("echo")){
            parameters.put("direction", direction);
            decision.put("action", "echo");
            decision.put("parameters", parameters);
        }
        else if (command.equals("heading")){
            parameters.put("direction", direction);
            decision.put("action", "heading");
            decision.put("parameters", parameters);
        }
        else{
            logger.info("Invalid command");
            System.exit(0);
        }
    }

    public JSONObject getDecision(){
        return decision;
    }

    public void process_poi_data(PointOfInterest pointOfInterest){

    }

    public void rescue_mission(){

    }

}
