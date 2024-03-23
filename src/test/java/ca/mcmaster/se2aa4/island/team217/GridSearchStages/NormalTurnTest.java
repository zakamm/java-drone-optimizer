package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class NormalTurnTest {
    MapRepresenter map;
    Drone drone;
    GridSearch gridSearch;
    NormalTurn normalTurn;

    @BeforeEach
    public void setUp() {
        map = new MapRepresenter();
        map.columns = 53;
        map.rows = 53;
        map.initializeMap();
        drone = new Drone(500, "E", map);
        drone.initializeCurrentLocation(10, 20, true);
        gridSearch = new GridSearch(drone, map);
        normalTurn = new NormalTurn(gridSearch);
    }

    @Test
    public void testReachedEnd() {
        assertFalse(normalTurn.reachedEnd());
        normalTurn.reachedEnd = true;
        assertTrue(normalTurn.reachedEnd());
    }

    @Test
    public void testIsFinal() {
        assertFalse(normalTurn.isFinal());
    }

    @Test 
    public void getNextPhase() {
        assertTrue(normalTurn.getNextPhase() instanceof EchoCheck);
    }

    @Test
    public void testTurnLeft() {
        gridSearch.sideToTurn = "left";
        normalTurn.counter = 0;
        assertEquals(Heading.E, drone.getCurrentHeading());
        String decision = normalTurn.nextDecision(drone, map);
        assertEquals(Heading.N, drone.getCurrentHeading());
        
        decision = normalTurn.nextDecision(drone, map);
        assertEquals(Heading.W, drone.getCurrentHeading());
        assertEquals(normalTurn.counter, 2);

        decision = normalTurn.nextDecision(drone, map);
        assertEquals(gridSearch.gridSearchDirection, Heading.W);
        assertEquals(gridSearch.atEdge, false);
        assertEquals(normalTurn.reachedEnd, true);
    }

    @Test
    public void testTurnRight() {
        gridSearch.sideToTurn = "right";
        normalTurn.counter = 0;
        assertEquals(Heading.E, drone.getCurrentHeading());
        String decision = normalTurn.nextDecision(drone, map);
        assertEquals(Heading.S, drone.getCurrentHeading());

        decision = normalTurn.nextDecision(drone, map);
        assertEquals(drone.getCurrentHeading(), Heading.W);
        assertEquals(normalTurn.counter, 2);

        decision = normalTurn.nextDecision(drone, map);
        assertEquals(gridSearch.gridSearchDirection, Heading.W);
        assertEquals(gridSearch.atEdge, false);
        assertEquals(normalTurn.reachedEnd, true);
    }
}