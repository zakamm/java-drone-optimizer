package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class TranslateDroneTest {
    MapRepresenter map;
    Drone drone;
    GridSearch gridSearch;
    TranslateDrone translateDrone;

    @BeforeEach
    public void setUp() {
        map = new MapRepresenter();
        map.columns = 53;
        map.rows = 53;
        map.initializeMap();
        drone = new Drone(500, "E", map);
        drone.initializeCurrentLocation(10, 20, true);
        gridSearch = new GridSearch(drone, map);
        translateDrone = new TranslateDrone(gridSearch);
    }

    @Test
    public void testReachedEnd() {
        assertFalse(translateDrone.reachedEnd());
        translateDrone.reachedEnd = true;
        assertTrue(translateDrone.reachedEnd());
    }

    @Test 
    public void getNextPhase() {
        assertTrue(translateDrone.getNextPhase() instanceof EchoCheck);
    }

    @Test 
    public void testIsFinal() {
        assertFalse(translateDrone.isFinal());
    }

    @Test 
    public void testSideToTranslate() {
        gridSearch.gridSearchDirection = Heading.W;
        gridSearch.generalDirection = Heading.N;
        translateDrone.turnCounter = 0;
        translateDrone.nextDecision(drone, map);
        assertEquals("left", translateDrone.sideToTranslate);
        gridSearch.generalDirection = Heading.S;
        translateDrone.nextDecision(drone, map);
        assertEquals("right", translateDrone.sideToTranslate);
    }

    @Test 
    public void testTranslateLeft(){
        gridSearch.gridSearchDirection = Heading.E;
        gridSearch.generalDirection = Heading.S;
        translateDrone.turnCounter = 0;

        translateDrone.nextDecision(drone, map);
        assertEquals(Heading.N, drone.getCurrentHeading());

        translateDrone.nextDecision(drone, map);
        assertEquals(2, translateDrone.turnCounter);
        assertEquals(Heading.N, drone.getCurrentHeading());

        translateDrone.nextDecision(drone, map);
        assertEquals(Heading.W, drone.getCurrentHeading());
        translateDrone.nextDecision(drone, map);
        assertEquals(Heading.S, drone.getCurrentHeading());
        translateDrone.nextDecision(drone, map);
        assertEquals(Heading.E, drone.getCurrentHeading());
        assertEquals(5, translateDrone.turnCounter);

        translateDrone.nextDecision(drone, map);
        assertTrue(translateDrone.reachedEnd());
        assertTrue(gridSearch.translated);
        assertEquals(Heading.N, gridSearch.generalDirection);
        assertFalse(gridSearch.atEdge);
    }

    @Test 
    public void testTranslateRight(){
        gridSearch.gridSearchDirection = Heading.E;
        gridSearch.generalDirection = Heading.N;
        translateDrone.turnCounter = 0;

        translateDrone.nextDecision(drone, map);
        assertEquals(Heading.S, drone.getCurrentHeading());

        translateDrone.nextDecision(drone, map);
        assertEquals(2, translateDrone.turnCounter);
        assertEquals(Heading.S, drone.getCurrentHeading());

        translateDrone.nextDecision(drone, map);
        assertEquals(Heading.W, drone.getCurrentHeading());
        translateDrone.nextDecision(drone, map);
        assertEquals(Heading.N, drone.getCurrentHeading());
        translateDrone.nextDecision(drone, map);
        assertEquals(Heading.E, drone.getCurrentHeading());
        assertEquals(5, translateDrone.turnCounter);

        translateDrone.nextDecision(drone, map);
        assertTrue(translateDrone.reachedEnd());
        assertTrue(gridSearch.translated);
        assertEquals(Heading.S, gridSearch.generalDirection);
        assertFalse(gridSearch.atEdge);
    }
}
