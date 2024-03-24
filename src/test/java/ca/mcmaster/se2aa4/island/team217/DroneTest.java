package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.Heading;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DroneTest {

    @Test
    void testFly() {
        MapRepresenter mapRepresenter = new MapRepresenter();
        Drone drone = new Drone(1000, "N", mapRepresenter);
        mapRepresenter.columns = 53;
        mapRepresenter.rows = 53;
        mapRepresenter.initializeMap();
        drone.initializeCurrentLocation(10, 10, true);
        String result = drone.fly();
        assertEquals("{\"action\": \"fly\"}", result);
        assertEquals(9, drone.getCurrentLocation().getRow());
        assertEquals(10, drone.getCurrentLocation().getColumn());

        drone = new Drone(1000, "E", mapRepresenter);
        drone.initializeCurrentLocation(51, 10, true);
        result = drone.fly();
        assertEquals(52, drone.getCurrentLocation().getColumn());
        assertEquals(10, drone.getCurrentLocation().getRow());

        try {
            drone.fly();
        } catch (IndexOutOfBoundsException e) {
            // Handle the exception
            System.out.println("IndexOutOfBoundsException occurred: " + e.getMessage());
            assertEquals("Index 53 out of bounds for length 53", e.getMessage());
        }
        assertEquals(52, drone.getCurrentLocation().getColumn());
        assertEquals(10, drone.getCurrentLocation().getRow());
    }

    @Test
    void testInitializeCurrentLocation() {
        MapRepresenter mapRepresenter = new MapRepresenter();
        mapRepresenter.columns = 53;
        mapRepresenter.rows = 53;
        mapRepresenter.initializeMap();
        Drone drone = new Drone(1000, "N", mapRepresenter);
        drone.initializeCurrentLocation(10, 18, true);
        assertEquals(18, drone.getCurrentLocation().getRow());
        assertEquals(10, drone.getCurrentLocation().getColumn());
        assertTrue(drone.getSpawnedFacingGround());

        MapRepresenter mapRepresenter2 = new MapRepresenter();
        Drone drone2 = new Drone(1000, "N", mapRepresenter2);
        drone2.initializeCurrentLocation(20, 50, false);
        assertEquals(50, drone2.getCurrentLocation().getRow());
        assertEquals(20, drone2.getCurrentLocation().getColumn());
        assertFalse(drone2.getSpawnedFacingGround());
    }

    @Test
    void testHeading() {
        MapRepresenter mapRepresenter = new MapRepresenter();
        Drone drone = new Drone(1000, "N", mapRepresenter);
        mapRepresenter.columns = 53;
        mapRepresenter.rows = 53;
        mapRepresenter.initializeMap();
        drone.initializeCurrentLocation(10, 10, true);
        String result = drone.heading(Heading.E);
        assertEquals("{\"action\": \"heading\", \"parameters\": { \"direction\": \"E\"}}", result);
        assertEquals(Heading.E, drone.getDirection());
        assertEquals("heading", drone.getAction());
        assertEquals(drone.getCurrentLocation().getRow(), 9);
        assertEquals(drone.getCurrentLocation().getColumn(), 11);
        assertEquals(drone.getCurrentHeading(), Heading.E);

        drone.initializeCurrentLocation(52, 20, true);
        try{
            drone.heading(Heading.N);
        } catch (IndexOutOfBoundsException e) {
            // Handle the exception
            System.out.println("IndexOutOfBoundsException occurred: " + e.getMessage());
            assertEquals("Index 53 out of bounds for length 53", e.getMessage());
        
        }

        Drone d = new Drone(1000, "E", mapRepresenter);
        d.initializeCurrentLocation(10, 10, true);
        IllegalArgumentException backwardException = assertThrows(IllegalArgumentException.class, () -> {
            d.heading(Heading.W); 
        });
        assertEquals("Invalid heading", backwardException.getMessage());
        assertEquals(Heading.E, d.getCurrentHeading());

        // initial heading is east and we try to heading forward
        IllegalArgumentException forwardException = assertThrows(IllegalArgumentException.class, () -> {
            d.heading(Heading.E); 
        });
        assertEquals("Invalid heading", forwardException.getMessage());
        assertEquals(Heading.E, d.getCurrentHeading());
}

    @Test
    void testEcho() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        String result = drone.echo(Heading.N);
        assertEquals("{\"action\": \"echo\", \"parameters\": { \"direction\": \"N\"}}", result);
    }

    @Test
    void testScan() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        String result = drone.scan();
        assertEquals("{\"action\": \"scan\"}", result);
    }

    @Test
    void testStop() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        String result = drone.stop();
        assertEquals("{\"action\": \"stop\"}", result);
    }

    @Test
    void testGetBatteryLevel() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        assertEquals(1000, drone.getBatteryLevel());
    }

    @Test
    void testGetAction() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        drone.fly();
        String result = drone.getAction(); 
        assertEquals("fly", result); 
}

    @Test
    void testGetSpawnedFacingGround() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        assertFalse(drone.getSpawnedFacingGround());
        drone.initializeCurrentLocation(10, 10, true);
        assertTrue(drone.getSpawnedFacingGround());
    }

    @Test
    void testUpdateBatteryLevel() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        drone.updateBatteryLevel(100);
        assertEquals(900, drone.getBatteryLevel());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            drone.updateBatteryLevel(-10); 
        });

        assertEquals("Cost cannot be negative", exception.getMessage());
        assertEquals(900, drone.getBatteryLevel());
    }

    @Test
    void testDecisionTaken() {
        Drone d = new Drone(7000, "E", new MapRepresenter());

        String result = d.decisionTaken("fly");
        assertEquals("{\"action\": \"fly\"}", result);

        IllegalArgumentException echoException = assertThrows(IllegalArgumentException.class, () -> {
            d.decisionTaken("echo"); 
        });
        assertEquals("Invalid command", echoException.getMessage());

        result = d.decisionTaken("heading", "N");
        assertEquals("{\"action\": \"heading\", \"parameters\": { \"direction\": \"N\"}}", result);
        assertEquals("heading", d.getAction());
        assertEquals(Heading.N, d.getDirection());

        IllegalArgumentException stopException = assertThrows(IllegalArgumentException.class, () -> {
            d.decisionTaken("stop", "E"); 
        });
        assertEquals("Invalid command", stopException.getMessage());

        IllegalArgumentException directionException = assertThrows(IllegalArgumentException.class, () -> {
            d.decisionTaken("heading", "null"); 
        });
        assertEquals("Invalid direction", directionException.getMessage());
    }
}