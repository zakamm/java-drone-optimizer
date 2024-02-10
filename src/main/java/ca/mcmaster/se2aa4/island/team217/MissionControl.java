package ca.mcmaster.se2aa4.island.team217;

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
    HashMap<String, List<String>> responseStorage = new HashMap<String, List<String>>();
    
    MissionControl(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.map = map;
    }
    // i dont know what exactly these methods will do, but somebody put them in the google doc
    public void startMission(String action, JSONObject previousResponse){
        List<String> temp = new ArrayList<String>();
        Integer cost = previousResponse.getInt("cost");
        temp.add(Integer.toString(previousResponse.getInt("cost")));
        this.responseStorage.put("cost", temp);

        temp = new ArrayList<String>();
        temp.add(String.valueOf(previousResponse.getString("status")));
        this.responseStorage.put("status", temp);

        if (action.equals("echo")){
            temp = new ArrayList<String>();
            temp.add(String.valueOf(previousResponse.getJSONObject("extras").getInt("range")));
            this.responseStorage.put("range", temp);

            temp = new ArrayList<String>();
            temp.add(previousResponse.getJSONObject("extras").getString("found"));
            this.responseStorage.put("found", temp);

        }
        else if (action.equals("scan")){
            temp = new ArrayList<String>();
            JSONArray creeksArray = previousResponse.getJSONObject("extras").getJSONArray("creeks");
            for (int i = 0; i < creeksArray.length(); i++) {
                temp.add(creeksArray.getString(i));
            }
            this.responseStorage.put("creeks", temp);


            temp = new ArrayList<String>();
            JSONArray biomesArray = previousResponse.getJSONObject("extras").getJSONArray("biomes");
            for (int i = 0; i < biomesArray.length(); i++) {
                temp.add(biomesArray.getString(i));
            }
            this.responseStorage.put("biomes", temp);

            temp = new ArrayList<String>();
            JSONArray sitesArray = previousResponse.getJSONObject("extras").getJSONArray("sites");
            for (int i = 0; i < sitesArray.length(); i++) {
                temp.add(sitesArray.getString(i));
            }
            this.responseStorage.put("sites", temp);
        }

        for (Map.Entry<String, List<String>> entry : responseStorage.entrySet()) {
            // Print the key
            logger.info("Key: " + entry.getKey());
            for (String value : entry.getValue()) {
                // Print each value
                logger.info("Value: " + value);
            }
        }

        
    }

    public void process_poi_data(PointOfInterest pointOfInterest){

    }

    public void rescue_mission(){

    }

}
