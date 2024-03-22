package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.MapInitializer;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.TurnToGround;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.EchoThreeSides;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class TurnToGroundTest {
    @Test
    void testReachedEnd() {
        TurnToGround turn = new TurnToGround(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

        assertEquals(false, turn.reachedEnd());

        turn.reachedEnd = true;

        assertEquals(true, turn.reachedEnd());

    }

    @Test
    void testGetNextPhase() {
        MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance());

        TurnToGround turn = new TurnToGround(map);

        assertEquals(new FlyToGround(map), turn.getNextPhase());

    }

    @Test
    void testIsFinal() {
        TurnToGround turn = new TurnToGround(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

        assertEquals(false, turn.isFinal());

    }

    @Test
    void testNextDecision() {
        TurnToGround turn = new TurnToGround(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));
        Drone drone = Drone.getInstance(1000, "N",
                MapRepresenter.getInstance());

        map.directionToEcho = drone.getCurrentHeading().leftSide();

        turn.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance());

        assertEquals("left",turn.sideToTurn);

        turn.counter = 0;

        assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

        turn.counter = 1;

        assertEquals(drone.fly(),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

        turn.counter = 2;

        assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));
        
        turn.counter = 3;

        assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));
                
        turn.counter = 4;

        assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

        turn.counter = 5;

        assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

        turn.counter = 6;

        assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

        map.directionToEcho = drone.getCurrentHeading().rightSide();

        turn.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance())

        assertEquals("right", turn.sideToTurn);

        turn.counter = 0;

        assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

        turn.counter = 1;

        assertEquals(drone.fly(),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

        turn.counter = 2;

        assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));
        
        turn.counter = 3;

        assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));
                
        turn.counter = 4;

        assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

        turn.counter = 5;

        assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

        turn.counter = 6;

        assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));


        turn.counter = 7;

        assertEquals(null,
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));
        assertEqual(true, turn.reachedEnd);

        
    }

    @Test
    void testProcessResponse() {
        TurnToGround turn = new TurnToGround(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

    }
}
