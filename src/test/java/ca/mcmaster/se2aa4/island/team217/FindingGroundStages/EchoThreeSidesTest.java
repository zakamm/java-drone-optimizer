package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.FindMissingDimension;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.LocateGround;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class EchoThreeSidesTest {

    @Test
    void testReachedEnd() {
        EchoThreeSides echoT = new EchoThreeSides(new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter()));

        assertEquals(false, echoT.reachedEnd());

        for (int i = 0; i < 3; i++) {
            echoT.nextDecision(new ResponseStorage(), new Drone(1000,
                    "N",
                    new MapRepresenter()), new MapRepresenter());
        }

        assertEquals(true, echoT.reachedEnd());

    }

    @Test
    void testGetNextPhaseCase1() {
        MapInitializer map = new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter());

        EchoThreeSides echoT = new EchoThreeSides(map);

        map.spawnedFacingGround = true;

        assertEquals(FindMissingDimension.class, echoT.getNextPhase().getClass());

    }

    @Test
    void testGetNextPhaseCase2() {
        MapInitializer map = new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter());

        EchoThreeSides echoT = new EchoThreeSides(map);

        map.spawnedFacingGround = false;

        assertEquals(LocateGround.class, echoT.getNextPhase().getClass());

    }

    @Test
    void testIsFinal() {
        EchoThreeSides echoT = new EchoThreeSides(new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter()));

        assertEquals(false, echoT.isFinal());

    }

    @Test
    void testNextDecisionCase1() {
        EchoThreeSides echoT = new EchoThreeSides(new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter()));
        Drone drone = new Drone(1000, "N",
                new MapRepresenter());

        assertEquals(drone.echo(
                drone.initialHeading),
                echoT.nextDecision(new ResponseStorage(), drone,
                        new MapRepresenter()));

    }

    @Test
    void testNextDecisionCase2() {
        EchoThreeSides echoT = new EchoThreeSides(new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter()));
        Drone drone = new Drone(1000, "N",
                new MapRepresenter());

        echoT.counter = 1;
        assertEquals(drone.echo(drone.initialHeading
                .rightSide()),
                echoT.nextDecision(new ResponseStorage(), drone,
                        new MapRepresenter()));
    }

    @Test
    void testNextDecisionCase3() {
        EchoThreeSides echoT = new EchoThreeSides(new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter()));
        Drone drone = new Drone(1000, "N",
                new MapRepresenter());

        echoT.counter = 2;
        assertEquals(drone.echo(drone.initialHeading
                .leftSide()),
                echoT.nextDecision(new ResponseStorage(), drone,
                        new MapRepresenter()));
    }

    @Test
    void testProcessResponse() {
        EchoThreeSides echoT = new EchoThreeSides(new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter()));

    }
}
