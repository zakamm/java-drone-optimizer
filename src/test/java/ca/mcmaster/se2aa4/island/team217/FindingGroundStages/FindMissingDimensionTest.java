package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team217.GridSearchStages.ScanAndFly;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class FindMissingDimensionTest {

        FindMissingDimension find;
        MapInitializer map;

        @BeforeEach
        void initialize() {
                map = new MapInitializer(new Drone(1000, "N",
                                new MapRepresenter()),
                                new MapRepresenter());
                find = new FindMissingDimension(map);
        }

        @Test
        void testReachedEndCase1() {

                find.nextDecision(new ResponseStorage(), new Drone(1000, "N",
                                new MapRepresenter()), new MapRepresenter());

                assertEquals(false, find.reachedEnd());

        }

        @Test
        void testReachedEndCase2() {

                find.foundDimension = true;

                find.nextDecision(new ResponseStorage(), new Drone(1000, "N",
                                new MapRepresenter()), new MapRepresenter());

                assertEquals(true, find.reachedEnd());

        }

        @Test
        void testGetNextPhase() {
                assertEquals(ScanAndFly.class, find.getNextPhase().getClass());

        }

        @Test
        void testIsFinal() {
                assertEquals(false, find.isFinal());

        }

        @Test
        void testNextDecisionCase1() {
                Drone drone = new Drone(1000, "N",
                                new MapRepresenter());

                find.foundDimension = true;

                assertEquals(drone.scan(),
                                find.nextDecision(new ResponseStorage(), drone,
                                                new MapRepresenter()));

                assertEquals(true, find.reachedEnd);

        }

        @Test
        void testNextDecisionCase2() {
                Drone drone = new Drone(1000, "N",
                                new MapRepresenter());

                find.foundDimension = false;

                find.counter = 0;

                assertEquals(drone.fly(),
                                find.nextDecision(new ResponseStorage(), drone,
                                                new MapRepresenter()));
        }

        @Test
        void testNextDecisionCase3() {
                Drone drone = new Drone(1000, "N",
                                new MapRepresenter());

                find.foundDimension = false;
                find.counter = 1;

                assertEquals(drone.echo(drone
                                .getCurrentHeading()),
                                find.nextDecision(new ResponseStorage(), drone,
                                                new MapRepresenter()));

        }

        @Test
        void testProcessResponse() {

        }
}
