package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    ResponseStorage responseStorage = ResponseStorage.getInstance();
    MapInitializer mapInitializer;
    GridSearcher gridSearcher;

    Phase current;

    // used for singleton pattern implementation
    private static MissionControl uniqueInstance = null;

    private MissionControl(Drone drone, MapRepresenter map) {
        this.drone = drone;
        this.map = map;
        this.mapInitializer = new MapInitializer(drone, map);
        this.gridSearcher = new GridSearcher(drone, map);
        this.current = new EchoThreeSides(mapInitializer);
    }

    public static MissionControl getInstance(Drone drone, MapRepresenter map) {
        if (uniqueInstance == null) {
            uniqueInstance = new MissionControl(drone, map);
        }
        return uniqueInstance;
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

        logger.info("Battery Level: " + drone.getBatteryLevel());

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
            }
            this.current = current.getNextPhase();
        }
        return drone.stop();
    }

    public void storeResponse(String action, JSONObject previousResponse) {
        responseStorage.storeResponse(action, previousResponse);
    }
}