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

    Boolean initializeDirection = false;

    public GridSearcher(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.map = map;
    }

    public String searchGrid(Heading currentHeading, HashMap<String, List<String>> responseStorage){
        // initialize direction so its north or south
        if (initializeDirection == false){
            return initializeDirection(currentHeading);
        }

        return null;
        
    }

    public String initializeDirection(Heading currentHeading){
        // if we are facing east or west want to face north or south for a vertical grid search
        // but we need to ensure that when we change heading we are still on ground
        return null;
    }
}
