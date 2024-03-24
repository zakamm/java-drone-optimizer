package ca.mcmaster.se2aa4.island.team217.MapRepresentation;

import ca.mcmaster.se2aa4.island.team217.ResponseStorage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class MapRepresenterTest {

    @Test
    public void testDistanceBetweenTwoPoints() {
        MapRepresenter instance = new MapRepresenter();
        Point point1 = new NormalPoint(0, 0);
        Point point2 = new NormalPoint(3, 4);
        double distance = instance.distanceBetweenTwoPoints(point1, point2);
        assertEquals(5.0, distance);
    }
    @Test
    public void testComputeMinDistance() {
        MapRepresenter mapRepresenter = new MapRepresenter();
        PointWithCreeks creek1 = new PointWithCreeks(new NormalPoint(0, 0));
        PointWithCreeks creek2 = new PointWithCreeks(new NormalPoint(3, 4));
        PointWithSite site = new PointWithSite(new NormalPoint(6, 8));
        mapRepresenter.getCreeks().add(creek1);
        mapRepresenter.getCreeks().add(creek2);
        mapRepresenter.site = site;
        double minDistance = mapRepresenter.computeMinDistance();
        assertEquals(5.0, minDistance, 0.01);
    }
    @Test
    public void testGetters() {
        MapRepresenter mapRepresenter = new MapRepresenter();
        PointWithCreeks creek = new PointWithCreeks(new NormalPoint(0, 0));
        PointWithSite site = new PointWithSite(new NormalPoint(3, 4));
        mapRepresenter.getCreeks().add(creek);
        mapRepresenter.site = site;
        mapRepresenter.closestCreek = creek;
        mapRepresenter.closestCreekDistance = 5.0;
        assertEquals(creek, mapRepresenter.getClosestCreek());
        assertEquals(site, mapRepresenter.getSite());
        assertEquals(5.0, mapRepresenter.getClosestCreekDistance());
    }
    @Test
    public void testUpdateClosestCreek() {
        MapRepresenter mapRepresenter = new MapRepresenter();
        PointWithCreeks creek = new PointWithCreeks(new NormalPoint(0, 0));
        PointWithSite site = new PointWithSite(new NormalPoint(3, 4));
        mapRepresenter.getCreeks().add(creek);
        mapRepresenter.site = site;
        mapRepresenter.updateClosestCreek();
        assertEquals(creek, mapRepresenter.getClosestCreek());
    }
    @Test
    public void testInitializeMap() {
        MapRepresenter mapRepresenter = new MapRepresenter();
        mapRepresenter.columns = 5;
        mapRepresenter.rows = 5;
        mapRepresenter.initializeMap();
        assertEquals(5, mapRepresenter.map.size());
    }
}

