package ca.mcmaster.se2aa4.island.team217.MapRepresentation;

import ca.mcmaster.se2aa4.island.team217.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

public class MapRepresenterTest {

    MapRepresenter mapRepresenter;
    ResponseStorage responseStorage;

    @BeforeEach 
    public void setUp() {
        mapRepresenter = new MapRepresenter();
        responseStorage = new ResponseStorage();
    }

    @Test
    public void testDistanceBetweenTwoPoints() {
        MapRepresenter instance = new MapRepresenter();
        Point point1 = new NormalPoint(0, 0);
        Point point2 = new NormalPoint(3, 4);
        double distance = instance.distanceBetweenTwoPoints(point1, point2);
        assertEquals(5.0, distance);

        point1 = new NormalPoint(0, 0);
        point2 = new NormalPoint(0, 0);
        distance = instance.distanceBetweenTwoPoints(point1, point2);
        assertEquals(0.0, distance);
    }

    @Test
    public void testInitializeMap() {
        MapRepresenter mapRepresenter = new MapRepresenter();
        mapRepresenter.columns = 5;
        mapRepresenter.rows = 5;
        mapRepresenter.initializeMap();
        assertEquals(5, mapRepresenter.map.size());
        assertEquals(5, mapRepresenter.map.get(0).size());

        mapRepresenter.columns = 0;
        mapRepresenter.rows = 0;
        mapRepresenter.initializeMap();
        assertEquals(0, mapRepresenter.map.size());
    }

    @Test  
    public void testGetSite(){
        responseStorage.setSite("site");
        List<String> temp = new ArrayList<String>();
        List<String> biomes = new ArrayList<String>();
        biomes.add("beach");
        responseStorage.setBiomes(biomes);
        temp.add("null");
        responseStorage.setCreeks(temp);
        mapRepresenter.storeScanResults(responseStorage, new NormalPoint(8, 30));
        
        PointWithSite site = mapRepresenter.getSite();
        assertEquals(site.getIdentifier(), "site");
        assertEquals(site.getRow(), 8);
        assertEquals(site.getColumn(), 30);
        assertTrue(site.getBeenScanned());
    }

    @Test
    public void testGettingAndUpdatingCreeks(){
        responseStorage.setSite("null");
        List<String> temp = new ArrayList<String>();
        List<String> biomes = new ArrayList<String>();
        biomes.add("beach");
        responseStorage.setBiomes(biomes);
        temp.add("creek1");
        responseStorage.setCreeks(temp);
        mapRepresenter.storeScanResults(responseStorage, new NormalPoint(8, 30));

        assertEquals(mapRepresenter.getCreeks().size(), 1);
        PointWithCreeks creek = mapRepresenter.getCreeks().get(0);
        assertEquals(creek.getIdentifiers().get(0), "creek1");
        assertEquals(creek.getRow(), 8);
        assertEquals(creek.getColumn(), 30);

        temp = new ArrayList<String>();
        temp.add("creek2");
        responseStorage.setCreeks(temp);
        responseStorage.setSite("site");
        mapRepresenter.storeScanResults(responseStorage, new NormalPoint(9, 35));

        assertEquals(mapRepresenter.getCreeks().size(), 2);
        PointWithCreeks closestCreek = mapRepresenter.getClosestCreek();
        assertEquals(closestCreek.getIdentifiers().get(0), "creek2");
        assertEquals(closestCreek.getRow(), 9);
        assertEquals(closestCreek.getColumn(), 35);

        temp = new ArrayList<String>();
        temp.add("creek3");
        responseStorage.setCreeks(temp);
        responseStorage.setSite("null");
        mapRepresenter.storeScanResults(responseStorage, new NormalPoint(15, 44));

        assertEquals(mapRepresenter.getCreeks().size(), 3);
        closestCreek = mapRepresenter.getClosestCreek();
        assertEquals(closestCreek.getIdentifiers().get(0), "creek2");
        assertEquals(closestCreek.getRow(), 9);
        assertEquals(closestCreek.getColumn(), 35);
    }

    @Test 
    public void testSetAsScanned(){
        mapRepresenter.columns = 53;
        mapRepresenter.rows = 53;
        mapRepresenter.initializeMap();
        Drone drone = new Drone(1000, "N", mapRepresenter);
        drone.initializeCurrentLocation(25, 25, true);
        mapRepresenter.setAsScanned(drone, 5, Heading.N);
        for (int i = 25; i >= 20; i--){
            NormalPoint normalPoint = (NormalPoint) mapRepresenter.map.get(i).get(25);
            assertTrue(normalPoint.getBeenScanned());
        }
        mapRepresenter.setAsScanned(drone, 5, Heading.S);
        for (int i = 25; i < mapRepresenter.rows; i++){
            NormalPoint normalPoint = (NormalPoint) mapRepresenter.map.get(i).get(25);
            assertTrue(normalPoint.getBeenScanned());
        }

        drone = new Drone(1000, "E", mapRepresenter);
        drone.initializeCurrentLocation(40, 8, true);
        mapRepresenter.setAsScanned(drone, 12, Heading.E);
        for (int i = 40; i < mapRepresenter.columns; i++){
            NormalPoint normalPoint = (NormalPoint) mapRepresenter.map.get(8).get(i);
            assertTrue(normalPoint.getBeenScanned());
        }
        mapRepresenter.setAsScanned(drone, 13, Heading.W);
        for (int i = 40; i >= 27; i--){
            NormalPoint normalPoint = (NormalPoint) mapRepresenter.map.get(8).get(i);
            assertTrue(normalPoint.getBeenScanned());
        }
    }
}

