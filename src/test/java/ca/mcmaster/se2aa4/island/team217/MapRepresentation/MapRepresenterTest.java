package ca.mcmaster.se2aa4.island.team217.MapRepresentation;

import ca.mcmaster.se2aa4.island.team217.ResponseStorage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class MapRepresenterTest {


    @Test
    public void testGetInstance() {
        MapRepresenter instance1 = MapRepresenter.getInstance();
        MapRepresenter instance2 = MapRepresenter.getInstance();
        assertSame(instance1, instance2);
    }


    @Test
    public void testDistanceBetweenTwoPoints() {
        MapRepresenter instance = MapRepresenter.getInstance();
        Point point1 = new NormalPoint(0, 0);
        Point point2 = new NormalPoint(3, 4);
        double distance = instance.distanceBetweenTwoPoints(point1, point2);
        assertEquals(5.0, distance);
    }



}

