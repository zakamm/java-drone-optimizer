package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class FlyNoScanTest {
    MapRepresenter map;
    Drone drone;
    GridSearch gridSearch;
    FlyNoScan flyNoScan;

    @BeforeEach
    public void setUp() {
        map = new MapRepresenter();
        map.columns = 53;
        map.rows = 53;
        map.initializeMap();
        drone = new Drone(500, "E", map);
        drone.initializeCurrentLocation(10, 20, true);
        gridSearch = new GridSearch(drone, map);
        flyNoScan = new FlyNoScan(gridSearch);
    }

    @Test  
    public void testReachedEnd() {
        assertFalse(flyNoScan.reachedEnd());
        flyNoScan.reachedEnd = true;
        assertTrue(flyNoScan.reachedEnd());
    }

    @Test 
    public void testIsFinal() {
        assertFalse(flyNoScan.isFinal());
    }

    @Test 
    public void testGetNextPhase() {
        gridSearch.translated = false;
        assertTrue(flyNoScan.getNextPhase() instanceof ScanAndFly);
        gridSearch.translated = true;
        Phase nextPhase = flyNoScan.getNextPhase();
        assertFalse(gridSearch.translated);
        assertTrue(nextPhase instanceof NormalTurn); 
    }

    @Test
    public void testNextDecision() {
        gridSearch.distanceToFly = 0;
        assertNull(flyNoScan.nextDecision(drone, map));
        gridSearch.distanceToFly = 20;
        assertEquals(drone.fly(), flyNoScan.nextDecision(drone, map));
        assertEquals(19, gridSearch.distanceToFly);
    }
}