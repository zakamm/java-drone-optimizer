package ca.mcmaster.se2aa4.island.team217;

//import 3.4;

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

    Boolean initialEchoed = false;

    Boolean gridSearch = false;

    Boolean searchedCoast = false;
    Boolean stop = false;
    // List<String> nextDecision = new ArrayList<String>();
    ResponseStorage responseStorage = new ResponseStorage();
    // HashMap<String, List<String>> responseStorage = new HashMap<String,
    // List<String>>();

    MapInitializer mapInitializer;

    GridSearcher gridSearcher;

    Phase current;

    public MissionControl(Drone drone, MapRepresenter map) {
        this.drone = drone;
        this.map = map;
        this.mapInitializer = new MapInitializer(drone, map);
        this.gridSearcher = new GridSearcher(drone, map);
        this.current = new EchoThreeSides(mapInitializer);
    }

    /*
     * this method is where everything happens for this rescue mission. Interface
     * between our objects and classes, and the explorer class
     * We start by initializing the map and finding ground
     * then we want to go through the coast line and find the creeks
     * then we want to implement a grid search system to find the emergency sites
     * We will make classes for all of these things and use nextDecision to
     * implement them
     */
    public String nextDecision() {

        logger.info("Battery Level: "+ drone.getBatteryLevel());

        if (drone.getBatteryLevel() < 50) {
            return drone.stop();
        }

        if (responseStorage.getCost() != null) {
            if (drone.getAction().equals("scan")) {
                map.storeScanResults(responseStorage, drone.currentLocation);
            }
            if (current instanceof ResponsePhase) {
                ((ResponsePhase) current).processResponse(responseStorage, drone, map);
            }
        }

        while (!current.isFinal()) {
            while (!current.reachedEnd()) {
                String decision = current.nextDecision(responseStorage, drone, map);
                if (!(decision == null)) {
                    return decision;
                }
                /// handler.process(decision); questionable
            }
            this.current = current.getNextPhase();
        }
        return drone.stop();
    }

    // refactor: Use GoF - Decorative pattern to store, biomes with a wrapper of
    // points of interest
    public void storeResponse(String action, JSONObject previousResponse) {
        // want to clear at the start of each iteration, sets all values to null
        responseStorage.clear();

        // all actions will have cost
        List<String> temp = new ArrayList<String>();
        responseStorage.setCost(previousResponse.getInt("cost"));

        if (action.equals("echo")) {
            responseStorage.setRange(previousResponse.getJSONObject("extras").getInt("range"));
            responseStorage.setFound(previousResponse.getJSONObject("extras").getString("found"));
        }

        // store as lists with first item being null if empty
        else if (action.equals("scan")) {
            temp = new ArrayList<String>();
            JSONArray creeksArray = previousResponse.getJSONObject("extras").getJSONArray("creeks");
            if (creeksArray.length() == 0) {
                temp.add("null");
            } else {
                for (int i = 0; i < creeksArray.length(); i++) {
                    temp.add(creeksArray.getString(i));
                }
            }
            responseStorage.setCreeks(temp);

            temp = new ArrayList<String>();
            JSONArray biomesArray = previousResponse.getJSONObject("extras").getJSONArray("biomes");
            if (biomesArray.length() == 0) {
                temp.add("null");
            } else {
                for (int i = 0; i < biomesArray.length(); i++) {
                    temp.add(biomesArray.getString(i));
                }
            }
            responseStorage.setBiomes(temp);

            // assuming there is only one site, we only store the first value
            temp = new ArrayList<String>();
            JSONArray sitesArray = previousResponse.getJSONObject("extras").getJSONArray("sites");
            if (sitesArray.length() == 0) {
                temp.add("null");
            } else {
                temp.add(sitesArray.getString(0));
            }
            responseStorage.setSite(temp.get(0));
        }
    }
}