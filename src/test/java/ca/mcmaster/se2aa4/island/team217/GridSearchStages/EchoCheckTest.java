package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.GridSearchStages.EchoCheck;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class EchoCheckTest {
    MapRepresenter map;
    Drone drone;
    GridSearch gridSearch;
    EchoCheck echoCheck;
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
        echoCheck = new EchoCheck(gridSearch);
        responseStorage = new ResponseStorage();
    }

    @Test
    public void testGetNextPhase() {
        assertEquals(null, echoCheck.getNextPhase());
    }

    @Test 
    public void testNotTranslatedOutOfRange(){
        responseStorage.setFound("OUT_OF_RANGE");
        responseStorage.setRange(1);
        gridSearch.translated = false;

        echoCheck.processResponse(responseStorage, drone, map);
        assertTrue(echoCheck.reachedEnd());
        assertEquals(1, gridSearch.outOfRangeCounter);
        assertFalse(echoCheck.isFinal());
        assertTrue(echoCheck.getNextPhase() instanceof TranslateDrone);

        //test finished if not middle
        gridSearch.middle = false;
        echoCheck.processResponse(responseStorage, drone, map);
        assertEquals(2, gridSearch.outOfRangeCounter);
        assertTrue(echoCheck.reachedEnd());
        assertTrue(echoCheck.isFinal());

        //test finished if in middle
        gridSearch.middle = true;
        echoCheck.processResponse(responseStorage, drone, map);
        assertEquals(3, gridSearch.outOfRangeCounter);
        assertTrue(echoCheck.isFinal());
    }

    @Test  
    public void testTranslatedOutOfRange(){
        gridSearch.translated = true;
        responseStorage.setFound("OUT_OF_RANGE");
        responseStorage.setRange(10);
        echoCheck.processResponse(responseStorage, drone, map);
        assertTrue(echoCheck.reachedEnd());
        assertEquals(0, gridSearch.outOfRangeCounter);
        assertEquals(5, gridSearch.distanceToFly);
        assertTrue(echoCheck.getNextPhase() instanceof FlyNoScan);
    }

    @Test 
    public void testFoundGround(){
        responseStorage.setFound("GROUND");
        responseStorage.setRange(10);
        echoCheck.processResponse(responseStorage, drone, map);
        assertTrue(echoCheck.reachedEnd());
        assertFalse(gridSearch.translated);
        assertEquals(10, gridSearch.distanceToFly);
        assertTrue(echoCheck.getNextPhase() instanceof FlyNoScan);
        
        responseStorage.setRange(0);
        echoCheck.processResponse(responseStorage, drone, map);
        assertTrue(echoCheck.reachedEnd());
        assertFalse(gridSearch.translated);
        assertEquals(0, gridSearch.distanceToFly);
        assertTrue(echoCheck.getNextPhase() instanceof ScanAndFly);
    }

    @Test  
    public void testGetNextDecision(){
        assertEquals(drone.echo(drone.getCurrentHeading()), echoCheck.nextDecision( drone, map));
    }


}