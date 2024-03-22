package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GridSearch {
    
    private final Logger logger = LogManager.getLogger();
    
    Heading generalDirection;
    Heading gridSearchDirection;

    MapRepresenter map;
    Drone drone;

    // initial location and heading of the drone when grid search started
    Point initialLocation;
    Heading initialHeading;

    int distanceToFly;
    int outOfRangeCounter = 0;

    boolean middle;
    Boolean atEdge = false;
    Boolean translated = false;
    String sideToTurn = "";
    
    public GridSearch(Drone drone, MapRepresenter map){
        this.drone = drone;
        this.initialLocation = drone.getCurrentLocation();
        this.initialHeading = drone.getCurrentHeading();
        this.gridSearchDirection = initialHeading;
        this.map = map;
        this.middle = drone.getSpawnedFacingGround();
        initializeGeneralDirection();
    }

    public void initializeGeneralDirection() {
        if (initialLocation.getRow() < map.rows/2 && initialLocation.getColumn() < map.columns/2) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                generalDirection = Heading.E;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                generalDirection = Heading.S;
            }
        } else if (initialLocation.getColumn() >= map.columns/2 && initialLocation.getRow() < map.rows/2) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                generalDirection = Heading.W;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                generalDirection = Heading.S;
            }
        } else if (initialLocation.getRow() >= map.rows/2 && initialLocation.getColumn() < map.columns/2) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                generalDirection = Heading.E;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                generalDirection = Heading.N;
            }
        } else if (initialLocation.getColumn() >= map.columns/2 && initialLocation.getRow() >= map.rows/2) {
            if (initialHeading == Heading.N || initialHeading == Heading.S) {
                generalDirection = Heading.W;
            } else if (initialHeading == Heading.E || initialHeading == Heading.W) {
                generalDirection = Heading.N;
            }
        }
    }

    public Boolean foundClosestCreek(MapRepresenter map) {
        boolean foundClosestCreek = true;
        double radius = map.getClosestCreekDistance();

        for (List<Point> pointRow : map.map) {
            for (Point p : pointRow) {
                double distance = map.distanceBetweenTwoPoints(p, map.getSite());
                if (distance <= radius) {
                    NormalPoint normalPoint = (NormalPoint) p;
                    if (!normalPoint.getBeenScanned()){
                        // logger the biomes to make sure it is indeed ground
                        logger.info("NOT SCANNED");
                        logger.info("Distance: " + distance);
                        logger.info("Row: " + p.getRow());
                        logger.info("Column: " + p.getColumn());
                        logger.info("Radius: " + radius);
                        foundClosestCreek = false;
                        return foundClosestCreek;
                    }
                }

            }
        }
        return foundClosestCreek;
    }
}