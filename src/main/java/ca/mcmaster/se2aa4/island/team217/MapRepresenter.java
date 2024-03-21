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
    double closestCreekDistance = 0.0;

    // used for singleton pattern implementation
    private static MapRepresenter uniqueInstance = null;

    private MapRepresenter() {
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

    public static MapRepresenter getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MapRepresenter();
        }
        return uniqueInstance;
    }

    public void storeScanResults(ResponseStorage scanResults, Point currentLocation) {

        if (!(scanResults.getCreeks().get(0).equals("null"))) {
            // if there are creeks, add them to the POI list
            PointWithCreeks pointWithCreeks = new PointWithCreeks(currentLocation);
            pointWithCreeks.storeScanResults(scanResults);
            creeks.add(pointWithCreeks);
            updateClosestCreek();
        }

        if (!(scanResults.getSite().equals("null"))) {
            PointWithSite pointWithSite = new PointWithSite(currentLocation);
            pointWithSite.storeScanResults(scanResults);
            site = pointWithSite;
            updateClosestCreek();
        } else {
            currentLocation.storeScanResults(scanResults);
        }
    }

    public void initializeMap() {
        // clear the map that we used for intialization purposes
        map.clear();

        // initialize the map with the given dimensions
        logger.info("Initializing map with dimensions: " + columns + "x" + rows);
        for (int i = 0; i < rows; i++) {
            List<Point> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                Point point = new NormalPoint(i, j);
                row.add(point);
            }
            map.add(row);
        }
        logger.info("Number of rows" + map.size());
        logger.info("Number of columns" + map.get(0).size());

    }

    public double distanceBetweenTwoPoints(Point point1, Point point2) {
        return Math.sqrt(Math.pow((point1.getRow() - point2.getRow()), 2)
                + Math.pow((point1.getColumn() - point2.getColumn()), 2));
    }

    public double computeMinDistance() {
        if (site == null) {
            return 0;
        }
        closestCreek = creeks.get(0);
        double minDistance = 1000000;
        double tolerance = 0.05;
        for (PointWithCreeks creek : creeks) {
            double distance = distanceBetweenTwoPoints(creek, site);
            logger.info("Distance: " + distance);
            logger.info("creek: " + creek.getRow() + " " + creek.getColumn());
            if (distance < minDistance) {
                logger.info("Distance: " + distance);
                closestCreek = creek;
                logger.info("Closest creek: " + closestCreek.getRow() + " " + closestCreek.getColumn());
                minDistance = distance;
            }
        }
        return minDistance;
    }

    public void updateClosestCreek() {
        closestCreekDistance = uniqueInstance.computeMinDistance();
    }

    public void setAsScanned(Drone drone, int distance, Heading heading) {
        Point currentLocation = drone.currentLocation;
        switch (heading){
            case Heading.N:
                if (heading == drone.currentHeading.backSide(drone.currentHeading)){
                    distance = 0;
                }
                else{
                    distance = currentLocation.getRow() - distance;
                }
                for (int i = currentLocation.getRow(); i >= distance; i--){
                    NormalPoint normalPoint = (NormalPoint) map.get(i).get(currentLocation.getColumn());
                    normalPoint.beenScanned = true;
                }
                break;
            case Heading.E:
                if (heading == drone.currentHeading.backSide(drone.currentHeading)){
                    distance = this.columns;
                }
                else{
                    distance = currentLocation.getColumn() + distance + 1;
                }
                for (int i = currentLocation.getColumn(); i < distance; i++){
                    NormalPoint normalPoint = (NormalPoint) map.get(currentLocation.getRow()).get(i);
                    normalPoint.beenScanned = true;
                }
                break;
            case Heading.S:
                if (heading == drone.currentHeading.backSide(drone.currentHeading)){
                    distance = this.rows;
                }
                else{
                    distance = currentLocation.getRow() + distance + 1;
                }
                for (int i = currentLocation.getRow(); i < distance; i++){
                    NormalPoint normalPoint = (NormalPoint) map.get(i).get(currentLocation.getColumn());
                    normalPoint.beenScanned = true;
                }
                break;
            case Heading.W:
                if (heading == drone.currentHeading.backSide(drone.currentHeading)){
                    distance = 0;
                }
                else{
                    distance = currentLocation.getColumn() - distance;
                }
                for (int i = currentLocation.getColumn(); i >= distance; i--){
                    NormalPoint normalPoint = (NormalPoint) map.get(currentLocation.getRow()).get(i);
                    normalPoint.beenScanned = true;
                }
                break;
        }
        NormalPoint normalPoint = (NormalPoint) currentLocation;
        normalPoint.beenScanned = true;
    }
}
