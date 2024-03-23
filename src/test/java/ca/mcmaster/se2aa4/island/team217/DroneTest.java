package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.Heading;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DroneTest {

    @Test
    void testGetInstance() {
        MapRepresenter mapRepresenter = MapRepresenter.getInstance();
        Drone drone = Drone.getInstance(1000, "N", mapRepresenter);
        assertNotNull(drone);
    }
    @Test
    void testFly() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        String result = drone.fly();
        assertEquals("{\"action\": \"fly\"}", result);
    }

    @Test
    void testInitializeCurrentLocation() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        drone.initializeCurrentLocation(10, 10, true);
        assertEquals(10, drone.getCurrentLocation().getRow());
        assertEquals(10, drone.getCurrentLocation().getColumn());
    }

    @Test
    void testHeading() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        String result = drone.heading(Heading.E);
        assertEquals("{\"action\": \"heading\", \"parameters\": { \"direction\": \"E\"}}", result);
}

    @Test
    void testEcho() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        String result = drone.echo(Heading.N);
        assertEquals("{\"action\": \"echo\", \"parameters\": { \"direction\": \"N\"}}", result);
    }

    @Test
    void testScan() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        String result = drone.scan();
        assertEquals("{\"action\": \"scan\"}", result);
    }

    @Test
    void testStop() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        String result = drone.stop();
        assertEquals("{\"action\": \"stop\"}", result);
    }

    @Test
    void testGetBatteryLevel() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        assertEquals(1000, drone.getBatteryLevel());
    }

    @Test
    void testGetAction() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        drone.fly();
        String result = drone.getAction(); 
        assertEquals("fly", result); 
}

    @Test
    void testGetSpawnedFacingGround() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        assertTrue(drone.getSpawnedFacingGround());
    }

    @Test
    void testUpdateBatteryLevel() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        drone.updateBatteryLevel(100);
        assertEquals(900, drone.getBatteryLevel());
    }

    @Test
    void testDecisionTaken() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        String result = drone.decisionTaken("fly");
        assertEquals("{\"action\": \"fly\"}", result);
    }
}