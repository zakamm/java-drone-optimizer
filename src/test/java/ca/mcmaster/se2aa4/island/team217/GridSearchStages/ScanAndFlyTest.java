package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

public class ScanAndFlyTest {

    MapRepresenter map;
    Drone drone;
    GridSearch gridSearch;
    ScanAndFly scanAndFly;
    ResponseStorage responseStorage;

    @BeforeEach
    public void setUp() {
        map = new MapRepresenter();
        map.columns = 53;
        map.rows = 53;
        map.initializeMap();
        drone = new Drone(500, "E", map);
        drone.initializeCurrentLocation(10, 20, true);
        gridSearch = new GridSearch(drone, map);
        scanAndFly = new ScanAndFly(gridSearch);
        responseStorage = new ResponseStorage();
    }

    @Test
    public void testReachedEnd() {
        assertFalse(scanAndFly.reachedEnd());
        scanAndFly.reachedEnd = true;
        assertTrue(scanAndFly.reachedEnd());
    }

    @Test
    public void testIsFinal() {
        assertFalse(scanAndFly.isFinal());
        scanAndFly.isFinal = true;
        assertTrue(scanAndFly.isFinal());
    }

    @Test
    public void testNextDecision() {
        
        assertEquals(drone.scan(), scanAndFly.nextDecision( drone, map));
        assertEquals(drone.fly(), scanAndFly.nextDecision( drone, map));
        scanAndFly.gridSearch.atEdge = true;
        assertEquals(drone.echo(drone.getCurrentHeading()), scanAndFly.nextDecision( drone, map));
    }

    @Test 
    public void testProcessResponse() {
        drone.decisionTaken("fly");
        scanAndFly.processResponse(responseStorage, drone, map);
        assertFalse(scanAndFly.flyCheck);
        assertEquals(scanAndFly.getNextPhase(), null);

        drone.decisionTaken("echo", "E");
        responseStorage.setFound("OUT_OF_RANGE");
        responseStorage.setRange(1);
        gridSearch.gridSearchDirection = Heading.E;
        gridSearch.generalDirection = Heading.N;
        scanAndFly.processResponse(responseStorage, drone, map);
        assertEquals(gridSearch.sideToTurn, "left");
        assertTrue(gridSearch.atEdge);
        assertTrue(scanAndFly.getNextPhase() instanceof FlyToPositionTurn);

        responseStorage.setFound("FOUND");
        responseStorage.setRange(1);
        scanAndFly.processResponse(responseStorage, drone, map);
        assertFalse(gridSearch.atEdge);
        assertTrue(scanAndFly.getNextPhase() instanceof FlyNoScan);
        assertEquals(gridSearch.distanceToFly, 2);

        drone.decisionTaken("scan");
        scanAndFly.processResponse(responseStorage, drone, map);
        assertEquals(gridSearch.atEdge, true);
    }

    @Test 
    public void testGetNextPhase() {
        assertEquals(scanAndFly.getNextPhase(), null);
    }

    @Test 
    public void testProcessResponseFoundClosestCreek() {
        //set up for site but no creeks
        responseStorage.setSite("site");
        List<String> temp = new ArrayList<String>();
        List<String> biomes = new ArrayList<String>();
        biomes.add("beach");
        responseStorage.setBiomes(biomes);
        temp.add("null");
        responseStorage.setCreeks(temp);
        map.storeScanResults(responseStorage, new NormalPoint(8, 30));

        //set up for no site but creeks
        responseStorage.setSite("null");
        List<String> temp2 = new ArrayList<String>();
        temp2.add("creek");
        responseStorage.setCreeks(temp2);
        map.storeScanResults(responseStorage, new NormalPoint(11, 34));

        drone.decisionTaken("scan");
        scanAndFly.processResponse(responseStorage, drone, map);
        assertFalse(scanAndFly.foundClosestCreek);

        //set all points in the map as scanned
        for (List<Point> pointRow : map.map) {
            for (Point p : pointRow) {
                NormalPoint normalPoint = (NormalPoint) p;
                normalPoint.setBeenScanned(true);
            }
        }

        scanAndFly.processResponse(responseStorage, drone, map);
        assertTrue(scanAndFly.foundClosestCreek);
        assertTrue(scanAndFly.reachedEnd());
        assertTrue(scanAndFly.isFinal());
    }

    @Test  
    public void testScannedGround(){
        List<String> biomes = new ArrayList<String>();
        biomes.add("beach");
        responseStorage.setBiomes(biomes);
        List<String> temp = new ArrayList<String>();
        temp.add("null");
        responseStorage.setCreeks(temp);
        responseStorage.setSite("null");
        map.storeScanResults(responseStorage, drone.getCurrentLocation());
        drone.decisionTaken("scan");
        scanAndFly.processResponse(responseStorage, drone, map);
        assertFalse(gridSearch.atEdge);

    }

}
