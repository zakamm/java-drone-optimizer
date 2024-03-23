package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class FlyToPositionTurnTest {
    MapRepresenter map;
    Drone drone;
    GridSearch gridSearch;
    FlyToPositionTurn flyToPositionTurn;
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
        flyToPositionTurn = new FlyToPositionTurn(gridSearch);
        responseStorage = new ResponseStorage();
    }

    @Test 
    public void testGetNextPhase() {
        assertEquals(null, flyToPositionTurn.getNextPhase());
    }

    @Test
    public void testIsFinal() {
        assertFalse(flyToPositionTurn.isFinal());
    }

    @Test 
    public void testReachedEnd() {
        assertFalse(flyToPositionTurn.reachedEnd());
    }

    @Test
    public void testNextDecision() {
        flyToPositionTurn.flyCheck = false;
        gridSearch.generalDirection = Heading.N;
        assertEquals(drone.echo(Heading.N), flyToPositionTurn.nextDecision(drone, map));
        assertTrue(flyToPositionTurn.flyCheck);
        assertEquals(drone.fly(), flyToPositionTurn.nextDecision(drone, map));
    }

    @Test 
    public void testProcessResponseGround() {
        drone.decisionTaken("fly");
        flyToPositionTurn.processResponse(responseStorage, drone, map);
        assertFalse(flyToPositionTurn.reachedEnd());
        assertNull(flyToPositionTurn.getNextPhase());

        drone.decisionTaken("echo", "N");
        responseStorage.setFound("GROUND");
        responseStorage.setRange(2);
        flyToPositionTurn.processResponse(responseStorage, drone, map);
        assertFalse(flyToPositionTurn.reachedEnd());
        assertNull(flyToPositionTurn.getNextPhase());

        responseStorage.setRange(4);
        flyToPositionTurn.processResponse(responseStorage, drone, map);
        assertTrue(flyToPositionTurn.reachedEnd());
        assertTrue(flyToPositionTurn.getNextPhase() instanceof NormalTurn);
    }

    @Test 
    public void testProcessResponseOutOfRange() {
        drone.decisionTaken("echo", "N");
        responseStorage.setFound("OUT_OF_RANGE");
        responseStorage.setRange(4);
        flyToPositionTurn.processResponse(responseStorage, drone, map);
        assertTrue(flyToPositionTurn.reachedEnd());
        assertTrue(flyToPositionTurn.getNextPhase() instanceof NormalTurn);
    }
}
