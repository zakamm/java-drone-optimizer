package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.PointOfInterest;

import main.java.ca.mcmaster.se2aa4.island.team217.Point;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapRepresenter {

    private final Logger logger = LogManager.getLogger();


    // used for map initialization
    int columns = 0;
    int rows = 0;



    public List<PointOfInterest> pois = new ArrayList<>();
    List<List<Point>> map = new ArrayList<>();
    public Boolean initialized = false;


    public MapRepresenter(){
        // initialize with these dimensions for now, will refactor this later
        for (int i = 0; i < 200; i++){
            List<Point> row = new ArrayList<>();
            for (int j = 0; j < 200; j++){
                Point point = new Point(i, j);
                row.add(point);
            }
            map.add(row);
        }
    }       

    public void storeScanResults(HashMap<String, List<String>> scanResults, Point currentLocation){

        if (!(scanResults.get("creeks").get(0).equals("null"))){
            // if there are creeks, add them to the POI list
            for (String creekIdentifier : scanResults.get("creeks")){
                PointOfInterest poi = new PointOfInterest(currentLocation.getX(), currentLocation.getY(), creekIdentifier, "creek");
                pois.add(poi);
            }
        }

        if (!(scanResults.get("sites").get(0).equals("null"))){
            // if there are sites, add them to the POI list
            for (String siteIdentifier : scanResults.get("sites")){
                PointOfInterest poi = new PointOfInterest(currentLocation.getX(), currentLocation.getY(), siteIdentifier, "creek");
                pois.add(poi);
            }
        }
        currentLocation.addBiomes(scanResults.get("biomes"), currentLocation);

    }  
    
    public void initializeMap(){
        // clear the map that we used for intialization purposes
        map.clear();

        // initialize the map with the given dimensions
        logger.info("Initializing map with dimensions: " + columns + "x" + rows);
        for (int i = 0; i < columns; i++){
            List<Point> row = new ArrayList<>();
            for (int j = 0; j < rows; j++){
                Point point = new Point(i, j);
                row.add(point);
            }
            map.add(row);
        }

    }

    // random things that dont do much for now
    // public PointOfInterest getPOI(int x, int y){

    //     return null;


    // }

    // // add poi method
    // public void addPOI(PointOfInterest poi){

    //     pois.add(poi);
    // }
}

