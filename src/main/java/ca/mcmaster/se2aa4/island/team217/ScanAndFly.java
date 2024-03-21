package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Drone.Heading;
import java.util.List;
import java.util.ArrayList;

public class ScanAndFly implements ResponsePhase {
    private final Logger logger = LogManager.getLogger();

    Boolean reachedEnd = false;
    Boolean isFinal = false;

    // scan first then fly
    Boolean flyCheck = false;

    GridSearch gridSearch;

    Phase nextPhase;

    public ScanAndFly(GridSearch gridSearch) {
        this.gridSearch = gridSearch;

    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return nextPhase;
    }

    public Boolean isFinal() {
        return isFinal;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (gridSearch.atEdge) {
            reachedEnd = true;
            return drone.echo(drone.currentHeading);
        } else if (!flyCheck) {
            logger.info("Scanning");
            flyCheck = true;
            return drone.scan();
        } else if (flyCheck) {
            flyCheck = false;
            logger.info("Flying");
            return drone.fly();
        }
        return null;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (drone.getAction().equals("scan")) {
            if (!drone.currentLocation.getGround()) {
                gridSearch.atEdge = true;
            }
            if (map.site != null && !map.creeks.isEmpty()) {
                foundClosestCreek(map);
            }
        }
        if (drone.getAction().equals("echo")) {
            map.setAsScanned(drone, responseStorage.getRange());
            if (responseStorage.getFound().equals("OUT_OF_RANGE")) {
                gridSearch.atEdge = true;
                if (gridSearch.gridSearchDirection == gridSearch.generalDirection.leftSide(gridSearch.generalDirection)) {
                    gridSearch.sideToTurn = "right";
                } else if (gridSearch.gridSearchDirection == gridSearch.generalDirection
                        .rightSide(gridSearch.generalDirection)) {
                    gridSearch.sideToTurn = "left";
                }
                nextPhase = new FlyToPositionTurn(gridSearch);
            } else {
                gridSearch.distanceToFly = responseStorage.getRange() + 1;
                gridSearch.atEdge = false;
                nextPhase = new FlyNoScan(gridSearch);
            }
        }
    }

    public void foundClosestCreek(MapRepresenter map) {
        boolean foundClosestCreek = true;
        double radius = map.closestCreekDistance;
        outerloop: for (List<Point> pointRow : map.map) {
            for (Point p : pointRow) {
                double distance = map.distanceBetweenTwoPoints(p, map.site);
                if (distance <= radius) {
                    NormalPoint normalPoint = (NormalPoint) p;
                    if (!normalPoint.beenScanned){
                        // logger the biomes to make sure it is indeed ground
                        logger.info("NOT SCANNED");
                        logger.info("Distance: " + distance);
                        logger.info("Row: " + p.getRow());
                        logger.info("Column: " + p.getColumn());
                        logger.info("Radius: " + radius);
                        foundClosestCreek = false;
                        map.radius = 2 * distance;
                        break outerloop;
                    }
                }

            }
        }

        if (foundClosestCreek) {
            logger.info("Found closest creek");
            reachedEnd = true;
            isFinal = true;
        }
    }
}
