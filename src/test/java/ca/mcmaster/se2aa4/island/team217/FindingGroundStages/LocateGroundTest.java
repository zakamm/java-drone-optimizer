package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LocateGroundTest {

    MapInitializer map;
    LocateGround loc;

    @BeforeEach
    void initialize() {
        map = new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter());
        loc = new LocateGround(map);
    }

    @Test
    void testReachedEndCase1() {
        assertEquals(false, loc.reachedEnd());

    }

    @Test
    void testReachedEndCase2() {
        loc.reachedEnd = true;

        assertEquals(true, loc.reachedEnd());

    }

    @Test
    void testGetNextPhase() {
        assertEquals(TurnToGround.class, loc.getNextPhase().getClass());
    }

    @Test
    void testIsFinal() {
        assertEquals(false, loc.isFinal());
    }

    @Test
    void testNextDecisionCase1() {
        Drone drone = new Drone(1000, "N",
                new MapRepresenter());

        assertEquals(drone.fly(),
                loc.nextDecision(drone,
                        new MapRepresenter()));
    }

    @Test
    void testNextDecisionCase2() {
        Drone drone = new Drone(1000, "N",
                new MapRepresenter());

        loc.counter++;
        map.directionToEcho = Heading.S;

        assertEquals(drone.echo(
                        map.directionToEcho),
                loc.nextDecision(drone,
                        new MapRepresenter()));

    }

    @Test
    void testProcessResponse() {
        MapInitializer mapInitializer = new MapInitializer(new Drone(1000, "N", new MapRepresenter()), new MapRepresenter());
        LocateGround instance = new LocateGround(mapInitializer);
        ResponseStorage responseStorage = new ResponseStorage();
        responseStorage.setFound("GROUND");
        responseStorage.setRange(5);
        Drone drone = new Drone(1000, "N", new MapRepresenter());
        MapRepresenter map = new MapRepresenter();
        instance.processResponse(responseStorage, drone, map);
        assertTrue(instance.reachedEnd());
    }
}
