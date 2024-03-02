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

public class MissionControl {

    private final Logger logger = LogManager.getLogger();
    Drone drone;
    MapRepresenter map;

    Boolean initialEchoed = false;
    Boolean gridSearch = false;
    Boolean searchedCoast = false;
    Boolean stop = false;
    List<String> nextDecision = new ArrayList<String>();
    HashMap<String, List<String>> responseStorage = new HashMap<String, List<String>>();
    Initializer initializer;
    GridSearcher gridSearcher;
    
    public MissionControl(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.map = map;
        this.initializer = new Initializer(drone, map);
        //this.gridSearcher = new GridSearcher(drone, map);
    }


    /*this method is where everything happens for this rescue mission. Interface between our objects and classes, and the explorer class
    We start by initializing the map and finding ground
    then we want to go through the coast line and find the creeks
    then we want to implement a grid search system to find the emergency sites
    We will make classes for all of these things and use nextDecision to implement them
    */
    public String nextDecision(){


        // first echo to determine where the drone is located
        if (initialEchoed == false){
            initialEchoed = true;
            return drone.echo(this.drone.initialHeading);
        }


        if (drone.getAction().equals("scan")){
            map.storeScanResults(responseStorage, drone.currentLocation);
        }

        // initializatoin and finding ground
        if (map.initialized == false){
            return initializer.initializeMission(this.drone.initialHeading, responseStorage);
        }

        // just testing out the point and biome stuff
        // logger.info(drone.currentLocation.getX());
        // logger.info(drone.currentLocation.getY());
        // logger.info(drone.currentLocation.getGround());
        // logger.info(drone.currentLocation.biomes.get(0));

        
        logger.info("MAP INITIALIZED");
        return drone.stop(); 



        // if (map.initialized == true && gridSearch == false){
        //     return gridSearcher.searchGrid(this.drone.initialHeading, responseStorage);
        // } 
    }

    public void storeResponse(String action, JSONObject previousResponse){
        // want to clear at the start of each iteration
        responseStorage.clear();


        // all actions will have cost and status
        List<String> temp = new ArrayList<String>();
        temp.add(Integer.toString(previousResponse.getInt("cost")));
        responseStorage.put("cost", temp);

        temp = new ArrayList<String>();
        temp.add(String.valueOf(previousResponse.getString("status")));
        responseStorage.put("status", temp);

        // ensure these are null if they are not part of response
        temp = new ArrayList<String>();
        temp.add("null");
        responseStorage.put("found", temp);
        temp = new ArrayList<String>();
        temp.add("null");
        responseStorage.put("range", temp);

        if (action.equals("echo")){
            temp = new ArrayList<String>();
            temp.add(String.valueOf(previousResponse.getJSONObject("extras").getInt("range")));
            responseStorage.put("range", temp);

            temp = new ArrayList<String>();
            temp.add(previousResponse.getJSONObject("extras").getString("found"));
            responseStorage.put("found", temp);

        }

        // store as lists with first item being null if empty
        else if (action.equals("scan")){
            temp = new ArrayList<String>();
            JSONArray creeksArray = previousResponse.getJSONObject("extras").getJSONArray("creeks");
            if (creeksArray.length() == 0){
                temp.add("null");
            }
            else{
                for (int i = 0; i < creeksArray.length(); i++) {
                    temp.add(creeksArray.getString(i));
                }
            }
            responseStorage.put("creeks", temp);


            temp = new ArrayList<String>();
            JSONArray biomesArray = previousResponse.getJSONObject("extras").getJSONArray("biomes");
            if (biomesArray.length() == 0){
                temp.add("null");
            }
            else{
                for (int i = 0; i < biomesArray.length(); i++) {
                    temp.add(biomesArray.getString(i));
                }
            }
            responseStorage.put("biomes", temp);



            temp = new ArrayList<String>();
            JSONArray sitesArray = previousResponse.getJSONObject("extras").getJSONArray("sites");
            if (sitesArray.length() == 0){
                temp.add("null");
            }
            else{
                for (int i = 0; i < sitesArray.length(); i++) {
                    temp.add(sitesArray.getString(i));
                }
            }
            responseStorage.put("sites", temp);
        }

        // for printing out the previous response for debugging
        // for (Map.Entry<String, List<String>> entry : responseStorage.entrySet()) {
        //     logger.info("Key: " + entry.getKey());
        //     for (String value : entry.getValue()) {
        //         logger.info("Value: " + value);
        //     }
        // }  
    }

    public void process_poi_data(PointOfInterest pointOfInterest){

    }

    public void rescue_mission(){

    }

}
