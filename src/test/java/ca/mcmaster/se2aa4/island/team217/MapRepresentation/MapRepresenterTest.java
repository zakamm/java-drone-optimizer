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
    
}

