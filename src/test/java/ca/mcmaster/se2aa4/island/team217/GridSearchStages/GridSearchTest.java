package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class GridSearchTest {

    @Test
    public void testConstructor(){
        Drone drone = Drone.getInstance(500, "N", MapRepresenter.getInstance());
        drone.initializeCurrentLocation(10, 20, true);
        GridSearch gridSearch = new GridSearch(drone, MapRepresenter.getInstance());
        assertEquals(gridSearch.middle, true);
        assertEquals(gridSearch.initialLocation.getRow(), 20);
        assertEquals(gridSearch.initialLocation.getColumn(), 10);
        assertEquals(gridSearch.initialHeading, Heading.N);
        assertEquals(gridSearch.gridSearchDirection, Heading.N);
        assertEquals(gridSearch.generalDirection, Heading.E);
    }

    @Test
    public void testInitializeGeneralDirection() {
        //setup
        Drone drone = Drone.getInstance(500, "E", MapRepresenter.getInstance());
        drone.initializeCurrentLocation(10, 20, true);
        GridSearch gridSearch = new GridSearch(drone, MapRepresenter.getInstance());
        gridSearch.map.columns = 53;
        gridSearch.map.rows = 53;

        gridSearch.initialLocation = new NormalPoint(10, 20);
        gridSearch.initialHeading = Heading.E;
        gridSearch.initializeGeneralDirection();
        assertEquals(gridSearch.generalDirection, Heading.S);

        gridSearch.initialLocation = new NormalPoint(10, 20);
        gridSearch.initialHeading = Heading.N;
        gridSearch.initializeGeneralDirection();
        assertEquals(gridSearch.generalDirection, Heading.E);

        gridSearch.initialLocation = new NormalPoint(26, 26);
        gridSearch.initialHeading = Heading.W;
        gridSearch.initializeGeneralDirection();
        assertEquals(gridSearch.generalDirection, Heading.N);

        gridSearch.initialLocation = new NormalPoint(53, 53);
        gridSearch.initialHeading = Heading.E;
        gridSearch.initializeGeneralDirection();
        assertEquals(gridSearch.generalDirection, Heading.N);

        gridSearch.initialLocation = new NormalPoint(40, 10);
        gridSearch.initialHeading = Heading.E;
        gridSearch.initializeGeneralDirection();
        assertEquals(gridSearch.generalDirection, Heading.N);

        gridSearch.initialLocation = new NormalPoint(48, 11);
        gridSearch.initialHeading = Heading.N;
        gridSearch.initializeGeneralDirection();
        assertEquals(gridSearch.generalDirection, Heading.E);

        gridSearch.initialLocation = new NormalPoint(8, 52);
        gridSearch.initialHeading = Heading.W;
        gridSearch.initializeGeneralDirection();
        assertEquals(gridSearch.generalDirection, Heading.S);

        gridSearch.initialLocation = new NormalPoint(25, 27);
        gridSearch.initialHeading = Heading.S;
        gridSearch.initializeGeneralDirection();
        assertEquals(gridSearch.generalDirection, Heading.W);
    }

    @Test 
    public void testFoundClosestCreek() {
        //setup
        // MapRepresenter map = MapRepresenter.getInstance();
        // map.columns = 53;
        // map.rows = 53;
        // map.initializeMap();
        // Drone drone = Drone.getInstance(500, "E", map);
        // drone.initializeCurrentLocation(10, 20, true);
        // GridSearch gridSearch = new GridSearch(drone, MapRepresenter.getInstance());
        // ResponseStorage responseStorage = ResponseStorage.getInstance();
        // responseStorage.clear();
        // List<String> testCreeks = new ArrayList<String>();
        // testCreeks.add("testCreek");
        // List<String> testBiomes = new ArrayList<String>();
        // testBiomes.add("BEACH");
        // testBiomes.add("MANGROVE");
        // responseStorage.setBiomes(testBiomes);
        // responseStorage.setCreeks(testCreeks);
        // map.storeScanResults(responseStorage, new NormalPoint(8, 30));
        // responseStorage.clear();
        // responseStorage.setBiomes(testBiomes);
        // responseStorage.setSite("testSite");
        // map.storeScanResults(responseStorage, new NormalPoint(13, 24));

        // assertEquals(map.getClosestCreekDistance(), 5.0);
        // assertFalse(gridSearch.foundClosestCreek(map));


        
        // //test
        // assertEquals(gridSearch.foundClosestCreek(MapRepresenter.getInstance()), true);
    }

}

