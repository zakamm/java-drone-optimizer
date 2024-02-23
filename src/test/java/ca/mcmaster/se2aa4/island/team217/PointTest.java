package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Point;
import ca.mcmaster.se2aa4.island.team217.PointOfInterest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class PointTest {

    @Test
    public void testGetX() {
        Point p = new Point(1, 2);
        assertEquals(1, p.getX());
    }

    @Test
    public void testGetY() {
        Point p = new Point(1, 2);
        assertEquals(2, p.getY());
    }

    @Test
    public void testZeroValues() {
        Point p = new Point(0, 0);
        assertEquals(0, p.getX());
        assertEquals(0, p.getY());
    }

    @Test
    public void testNegativeValues() {
        Point p = new Point(-1, 2);
        assertEquals(-1, p.getX());
    }

    public void testSetGround() {
        Point p = new Point(1, 2);
        assertFalse(p.getGround());
        p.setGround();
        assertTrue(p.getGround());
    }

    public void testSetPOI() {
        Point p = new Point(1, 2);
        assertFalse(p.getPOI());
    }

}
