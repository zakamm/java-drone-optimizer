package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.*;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TurnToGroundTest {

        TurnToGround turn;
        MapInitializer map;

        @BeforeEach
        void initialize() {
                map = new MapInitializer(new Drone(1000, "N",
                                new MapRepresenter()),
                                new MapRepresenter());
                turn = new TurnToGround(map);
        }

        @Test
        void testReachedEndCase1() {
                assertEquals(false, turn.reachedEnd());
        }

        @Test
        void testReachedEndCase2() {
                turn.reachedEnd = true;
                assertEquals(true, turn.reachedEnd());
        }

        @Test
        void testGetNextPhase() {
                assertEquals(FlyToGround.class, turn.getNextPhase().getClass());

        }

        @Test
        void testIsFinal() {
                assertEquals(false, turn.isFinal());

        }

        @Test
        void testNextDecisionCase0() {
                map.directionToEcho = Heading.E;

                turn.nextDecision(new Drone(1000, "S",
                                new MapRepresenter()),
                                new MapRepresenter());

                assertEquals("left", turn.sideToTurn);

        }

        @Test
        void testNextDecisionCase1() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = Heading.E;

                turn.counter = 0;

                assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));

        }

        @Test
        void testNextDecisionCase2() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().leftSide();

                turn.sideToTurn = "left";

                turn.counter = 1;

                assertEquals(drone.fly(),
                                turn.nextDecision(drone,
                                                new MapRepresenter()));

        }

        @Test
        void testNextDecisionCase3() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().leftSide();

                turn.sideToTurn = "left";

                turn.counter = 2;

                assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));

        }

        @Test
        void testNextDecisionCase4() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().leftSide();

                turn.sideToTurn = "left";

                turn.counter = 3;

                assertEquals(drone.fly(),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));
        }

        @Test
        void testNextDecisionCase5() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().leftSide();

                turn.sideToTurn = "left";

                turn.counter = 4;

                assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));
        }

        @Test
        void testNextDecisionCase6() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().leftSide();

                turn.sideToTurn = "left";

                turn.counter = 5;

                assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));
        }

        @Test
        void testNextDecisionCase7() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().leftSide();

                turn.sideToTurn = "left";

                turn.counter = 6;

                assertEquals(drone.heading(drone.getCurrentHeading().leftSide()),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));
        }

        @Test
        void testNextDecisionCase8() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().rightSide();

                turn.sideToTurn = "right";

                turn.counter = 0;

                assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));
        }

        @Test
        void testNextDecisionCase9() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().rightSide();

                turn.sideToTurn = "right";

                turn.counter = 1;

                assertEquals(drone.fly(),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));
        }

        @Test
        void testNextDecisionCase10() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().rightSide();

                turn.sideToTurn = "right";

                turn.counter = 2;

                assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));
        }

        @Test
        void testNextDecisionCase11() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().rightSide();

                turn.sideToTurn = "right";

                turn.counter = 3;

                assertEquals(drone.fly(),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));

        }

        @Test
        void testNextDecisionCase12() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().rightSide();

                turn.sideToTurn = "right";

                turn.counter = 4;

                assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));

        }

        @Test
        void testNextDecisionCase13() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().rightSide();

                turn.sideToTurn = "right";

                turn.counter = 5;

                assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));

        }

        @Test
        void testNextDecisionCase14() {
                Drone drone = new Drone(1000, "S",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().rightSide();

                turn.sideToTurn = "right";

                turn.counter = 6;

                assertEquals(drone.heading(drone.getCurrentHeading().rightSide()),
                                turn.nextDecision(new Drone(1000, "S",
                                                new MapRepresenter()),
                                                new MapRepresenter()));

        }

        @Test
        void testNextDecisionCase15() {
                Drone drone = new Drone(1000, "N",
                                new MapRepresenter());

                map.directionToEcho = drone.getCurrentHeading().rightSide();

                turn.sideToTurn = "right";

                turn.counter = 7;

                assertNull(turn.nextDecision(new Drone(1000, "S",
                                new MapRepresenter()),
                                new MapRepresenter()));
                assertEquals(true, turn.reachedEnd);

        }

        @Test
        void testProcessResponse() {
                MapInitializer mapInitializer = new MapInitializer(new Drone(1000, "N", new MapRepresenter()), new MapRepresenter());
                TurnToGround instance = new TurnToGround(mapInitializer);
                String decision = instance.nextDecision(new Drone(1000, "N", new MapRepresenter()), new MapRepresenter());
                assertEquals("{\"action\": \"heading\", \"parameters\": { \"direction\": \"E\"}}", decision);
                assertEquals(1, instance.counter);
        }
        }
