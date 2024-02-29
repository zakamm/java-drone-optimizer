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
    public List<PointOfInterest> pois = new ArrayList<>();
    List<List<Point>> map = new ArrayList<>();
    public Boolean initialized = false;

    public MapRepresenter() { // what goes in the constructor needs to be determined

    }

    public void storeScanResults(HashMap<String, List<String>> scanResults, Point currentLocation) {
        // store the scan results in the map

        if (!(scanResults.get("creeks").get(0).equals("null"))) {
            // if there are creeks, add them to the POI list
            for (String creekIdentifier : scanResults.get("creeks")) {
                PointOfInterest poi = new PointOfInterest(currentLocation.getX(), currentLocation.getY(),
                        creekIdentifier, "creek");
                pois.add(poi);
            }
        }
        if (!(scanResults.get("sites").get(0).equals("null"))) {
            // if there are sites, add them to the POI list
            for (String siteIdentifier : scanResults.get("sites")) {
                PointOfInterest poi = new PointOfInterest(currentLocation.getX(), currentLocation.getY(),
                        siteIdentifier, "creek");
                pois.add(poi);
            }
        }

    }

    // random things that dont do much for now
    // public PointOfInterest getPOI(int x, int y){
    // return null;
    // }

    // // add poi method
    // public void addPOI(PointOfInterest poi){
    // pois.add(poi);
    // }
}