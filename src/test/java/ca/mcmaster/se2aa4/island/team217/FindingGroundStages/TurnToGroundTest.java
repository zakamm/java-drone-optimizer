package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.Heading;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.MapInitializer;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.TurnToGround;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.FlyToGround;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnToGroundTest {

    @Test
    void testReachedEndCase1() {
        TurnToGround turn = new TurnToGround(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

        assertEquals(false, turn.reachedEnd());

    }

    @Test
    void testReachedEndCase2() {
        TurnToGround turn = new TurnToGround(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

        turn.reachedEnd = true;

        assertEquals(true, turn.reachedEnd());

    }

    @Test
    void testGetNextPhase() {
        MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance());

        TurnToGround turn = new TurnToGround(map);

        assertEquals(FlyToGround.class, turn.getNextPhase().getClass());

    }

    @Test
    void testIsFinal() {
        TurnToGround turn = new TurnToGround(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

        assertEquals(false, turn.isFinal());

    }

    // @Test
    // void testNextDecisionCase1() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = Heading.E;

    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance());

    // assertEquals("left", turn.sideToTurn);

    // turn.counter = 0;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));

    // }

    // @Test
    // void testNextDecisionCase2() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 1;

    // assertEquals(drone.fly(),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));

    // }

    // @Test
    // void testNextDecisionCase3() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 2;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));

    // }

    // @Test
    // void testNextDecisionCase4() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 3;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));
    // }

    // @Test
    // void testNextDecisionCase5() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 4;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));
    // }

    // @Test
    // void testNextDecisionCase6() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 5;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));
    // }

    // @Test
    // void testNextDecisionCase7() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 6;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));
    // }

    // @Test
    // void testNextDecisionCase8() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 0;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));
    // }

    // @Test
    // void testNextDecisionCase9() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 1;

    // assertEquals(drone.fly(),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));
    // }

    // @Test
    // void testNextDecisionCase10() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 2;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));
    // }

    // @Test
    // void testNextDecisionCase11() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 3;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));

    // }

    // @Test
    // void testNextDecisionCase12() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 4;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));

    // }

    // @Test
    // void testNextDecisionCase13() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 5;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));

    // }

    // @Test
    // void testNextDecisionCase14() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 6;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));

    // }

    // @Test
    // void testNextDecisionCase15() {
    // MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance()),
    // MapRepresenter.getInstance());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = Drone.getInstance(1000, "N",
    // MapRepresenter.getInstance());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 7;

    // assertEquals(null,
    // turn.nextDecision(ResponseStorage.getInstance(), drone,
    // MapRepresenter.getInstance()));
    // assertEquals(true, turn.reachedEnd);

    // }

    @Test
    void testProcessResponse() {
        TurnToGround turn = new TurnToGround(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

    }

}
