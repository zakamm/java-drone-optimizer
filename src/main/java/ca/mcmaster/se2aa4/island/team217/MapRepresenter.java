package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MapRepresenter {

    private final Logger logger = LogManager.getLogger();

    // used for map initialization
    int columns = 0;
    int rows = 0;

    public List<PointWithCreeks> creeks = new ArrayList<>();
    PointWithCreeks closestCreek;
    public PointWithSite site;
    List<List<Point>> map = new ArrayList<>();
    public Boolean initialized = false;

    public MapRepresenter() {
        // initialize with these dimensions for now, will refactor this later
        for (int i = 0; i < 200; i++) {
            List<Point> row = new ArrayList<>();
            for (int j = 0; j < 200; j++) {
                Point point = new NormalPoint(i, j);
                row.add(point);
            }
            map.add(row);
        }
    }

    public void storeScanResults(ResponseStorage scanResults, Point currentLocation) {

        if (!(scanResults.getCreeks().get(0).equals("null"))) {
            // if there are creeks, add them to the POI list
            PointWithCreeks pointWithCreeks = new PointWithCreeks(currentLocation);
            pointWithCreeks.storeScanResults(scanResults);
            creeks.add(pointWithCreeks);
            //map.get(currentLocation.getX()).set(currentLocation.getY(), pointWithCreeks);
        }

        if (!(scanResults.getSite().equals("null"))) {
            PointWithSite pointWithSite = new PointWithSite(currentLocation);
            pointWithSite.storeScanResults(scanResults);
            site = pointWithSite;
        }
        else {
            currentLocation.storeScanResults(scanResults);
        }
    }

    public void initializeMap() {
        // clear the map that we used for intialization purposes
        map.clear();

        // initialize the map with the given dimensions
        logger.info("Initializing map with dimensions: " + columns + "x" + rows);
        for (int i = 0; i < columns; i++) {
            List<Point> row = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                Point point = new NormalPoint(i, j);
                row.add(point);
            }
            map.add(row);
        }

    }

    public double computeMinDistance(){
        if (site == null){
            return 0;
        }
        closestCreek = creeks.get(0);
        double minDistance = 1000000;
        for (PointWithCreeks creek : creeks){
            double distance = Math.sqrt(Math.pow((creek.getX() - site.getX()), 2) + Math.pow((creek.getY() - site.getY()), 2));
            if (distance < minDistance){
                closestCreek = creek;
                minDistance = distance;
            }
        }
        return minDistance;
    }
}
