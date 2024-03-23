package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.Heading;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DroneTest {

    @Test
    void testGetInstance() {
        MapRepresenter mapRepresenter = new MapRepresenter();
        Drone drone = new Drone(1000, "N", mapRepresenter);
        assertNotNull(drone);
    }
    @Test
    void testFly() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        String result = drone.fly();
        assertEquals("{\"action\": \"fly\"}", result);
    }

    @Test
    void testInitializeCurrentLocation() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        drone.initializeCurrentLocation(10, 10, true);
        assertEquals(10, drone.getCurrentLocation().getRow());
        assertEquals(10, drone.getCurrentLocation().getColumn());
    }

    @Test
    void testHeading() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        String result = drone.heading(Heading.E);
        assertEquals("{\"action\": \"heading\", \"parameters\": { \"direction\": \"E\"}}", result);
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
    }

    @Test
    void testDecisionTaken() {
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        String result = drone.decisionTaken("fly");
        assertEquals("{\"action\": \"fly\"}", result);
    }
}