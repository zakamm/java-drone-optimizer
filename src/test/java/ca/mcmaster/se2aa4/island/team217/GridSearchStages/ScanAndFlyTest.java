package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeEach;

public class ScanAndFlyTest {

    MapRepresenter map;
    Drone drone;
    GridSearch gridSearch;
    ScanAndFly scanAndFly;
    ResponseStorage responseStorage;

    @BeforeEach
    public void setUp() {
        map = MapRepresenter.getInstance();
        map.columns = 53;
        map.rows = 53;
        map.initializeMap();
        drone = Drone.getInstance(500, "E", map);
        drone.initializeCurrentLocation(10, 20, true);
        gridSearch = new GridSearch(drone, MapRepresenter.getInstance());
        scanAndFly = new ScanAndFly(gridSearch);
        responseStorage = ResponseStorage.getInstance();
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
        
        assertEquals(drone.scan(), scanAndFly.nextDecision(responseStorage, drone, map));
        assertEquals(drone.fly(), scanAndFly.nextDecision(responseStorage, drone, map));
        scanAndFly.gridSearch.atEdge = true;
        assertEquals(drone.echo(drone.getCurrentHeading()), scanAndFly.nextDecision(responseStorage, drone, map));
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
        assertEquals(gridSearch.atEdge, true);
        



    }

}
