package ca.mcmaster.se2aa4.island.team217.GridSearchStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;

public class GridSearchTest {

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
        responseStorage = new ResponseStorage();
    }

    @Test
    public void testConstructor(){
        assertEquals(gridSearch.middle, true);
        assertEquals(gridSearch.initialLocation.getRow(), 20);
        assertEquals(gridSearch.initialLocation.getColumn(), 10);
        assertEquals(gridSearch.initialHeading, Heading.E);
        assertEquals(gridSearch.gridSearchDirection, Heading.E);
        assertEquals(gridSearch.generalDirection, Heading.S);
    }

    @Test
    public void testInitializeGeneralDirection() {

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

        //set all points in the map as scanned
        for (List<Point> pointRow : map.map) {
            for (Point p : pointRow) {
                NormalPoint normalPoint = (NormalPoint) p;
                normalPoint.setBeenScanned(true);
            }
        }

        assertTrue(gridSearch.foundClosestCreek(map));

        //set one point as not scanned
        NormalPoint normalPoint = (NormalPoint) map.map.get(9).get(31);
        normalPoint.setBeenScanned(false);
        assertFalse(gridSearch.foundClosestCreek(map));

        //borderline case where the distance is equal to the radius of 5
        normalPoint.setBeenScanned(true);
        normalPoint = (NormalPoint) map.map.get(5).get(26);
        normalPoint.setBeenScanned(false);
        assertFalse(gridSearch.foundClosestCreek(map));
    }

}

