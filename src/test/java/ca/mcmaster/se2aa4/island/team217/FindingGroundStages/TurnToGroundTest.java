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
        TurnToGround turn = new TurnToGround(new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter()));

        assertEquals(false, turn.reachedEnd());

    }

    @Test
    void testReachedEndCase2() {
        TurnToGround turn = new TurnToGround(new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter()));

        turn.reachedEnd = true;

        assertEquals(true, turn.reachedEnd());

    }

    @Test
    void testGetNextPhase() {
        MapInitializer map = new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter());

        TurnToGround turn = new TurnToGround(map);

        assertEquals(FlyToGround.class, turn.getNextPhase().getClass());

    }

    @Test
    void testIsFinal() {
        TurnToGround turn = new TurnToGround(new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter()));

        assertEquals(false, turn.isFinal());

    }

    // @Test
    // void testNextDecisionCase1() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = Heading.E;

    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter());

    // assertEquals("left", turn.sideToTurn);

    // turn.counter = 0;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));

    // }

    // @Test
    // void testNextDecisionCase2() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 1;

    // assertEquals(drone.fly(),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));

    // }

    // @Test
    // void testNextDecisionCase3() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 2;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));

    // }

    // @Test
    // void testNextDecisionCase4() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 3;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));
    // }

    // @Test
    // void testNextDecisionCase5() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 4;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));
    // }

    // @Test
    // void testNextDecisionCase6() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 5;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));
    // }

    // @Test
    // void testNextDecisionCase7() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().leftSide();

    // turn.sideToTurn = "left";

    // turn.counter = 6;

    // assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));
    // }

    // @Test
    // void testNextDecisionCase8() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 0;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));
    // }

    // @Test
    // void testNextDecisionCase9() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 1;

    // assertEquals(drone.fly(),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));
    // }

    // @Test
    // void testNextDecisionCase10() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 2;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));
    // }

    // @Test
    // void testNextDecisionCase11() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 3;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));

    // }

    // @Test
    // void testNextDecisionCase12() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 4;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));

    // }

    // @Test
    // void testNextDecisionCase13() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 5;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));

    // }

    // @Test
    // void testNextDecisionCase14() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 6;

    // assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));

    // }

    // @Test
    // void testNextDecisionCase15() {
    // MapInitializer map = new MapInitializer(new Drone(1000, "N",
    // new MapRepresenter()),
    // new MapRepresenter());
    // TurnToGround turn = new TurnToGround(map);
    // Drone drone = new Drone(1000, "N",
    // new MapRepresenter());

    // map.directionToEcho = drone.getCurrentHeading().rightSide();

    // turn.sideToTurn = "right";

    // turn.counter = 7;

    // assertEquals(null,
    // turn.nextDecision(new ResponseStorage(), drone,
    // new MapRepresenter()));
    // assertEquals(true, turn.reachedEnd);

    // }

    @Test
    void testProcessResponse() {
        TurnToGround turn = new TurnToGround(new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter()));

    }

}
