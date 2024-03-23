package ca.mcmaster.se2aa4.island.team217;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;

import static org.junit.jupiter.api.Assertions.*;

public class MissionControlTest {
    @Test
    public void testGetInstance() {
        Drone drone = Drone.getInstance(0, "example", MapRepresenter.getInstance());
        MapRepresenter map = MapRepresenter.getInstance();
        MissionControl instance1 = MissionControl.getInstance(drone, map);
        MissionControl instance2 = MissionControl.getInstance(drone, map);
        assertSame(instance1, instance2);
    }

    @Test
    public void testNextDecision() {
        Drone drone = Drone.getInstance(0, "example", MapRepresenter.getInstance());
        MapRepresenter map = MapRepresenter.getInstance();
        MissionControl instance = MissionControl.getInstance(drone, map);
        String decision = instance.nextDecision();
        assertNotNull(decision);
    }

}
