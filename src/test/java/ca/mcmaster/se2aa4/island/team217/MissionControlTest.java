package ca.mcmaster.se2aa4.island.team217;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;

import static org.junit.jupiter.api.Assertions.*;

public class MissionControlTest {

    @Test
    public void testNextDecision() {
        Drone drone = new Drone(0, "E", new MapRepresenter());
        MapRepresenter map = new MapRepresenter();
        MissionControl instance = new MissionControl(drone, map);
        String decision = instance.nextDecision();
        assertNotNull(decision);
    }

}
