package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team217.GridSearchStages.ScanAndFly;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;

import static org.junit.jupiter.api.Assertions.*;

public class FindMissingDimensionTest {
        @Test
        void testReachedEndCase1() {
                FindMissingDimension find = new FindMissingDimension(new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

                find.nextDecision(ResponseStorage.getInstance(), Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()), MapRepresenter.getInstance());

                assertEquals(false, find.reachedEnd());

        }

        @Test
        void testReachedEndCase2() {
                FindMissingDimension find = new FindMissingDimension(new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

                find.foundDimension = true;

                find.nextDecision(ResponseStorage.getInstance(), Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()), MapRepresenter.getInstance());

                assertEquals(true, find.reachedEnd());

        }

        @Test
        void testGetNextPhase() {

                FindMissingDimension find = new FindMissingDimension(new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

                assertEquals(ScanAndFly.class, find.getNextPhase().getClass());

        }

        @Test
        void testIsFinal() {
                FindMissingDimension find = new FindMissingDimension(new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

                assertEquals(false, find.isFinal());

        }

        @Test
        void testNextDecisionCase1() {
                FindMissingDimension find = new FindMissingDimension(new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

                Drone drone = Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance());

                find.foundDimension = true;

                assertEquals(drone.scan(),
                                find.nextDecision(ResponseStorage.getInstance(), drone,
                                                MapRepresenter.getInstance()));

                assertEquals(true, find.reachedEnd);

        }

        @Test
        void testNextDecisionCase2() {
                FindMissingDimension find = new FindMissingDimension(new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

                Drone drone = Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance());

                find.foundDimension = false;

                find.counter = 0;

                assertEquals(drone.fly(),
                                find.nextDecision(ResponseStorage.getInstance(), drone,
                                                MapRepresenter.getInstance()));
        }

        @Test
        void testNextDecisionCase3() {
                FindMissingDimension find = new FindMissingDimension(new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

                Drone drone = Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance());

                find.foundDimension = false;
                find.counter = 1;

                assertEquals(drone.echo(drone
                                .getCurrentHeading()),
                                find.nextDecision(ResponseStorage.getInstance(), drone,
                                                MapRepresenter.getInstance()));

        }

        @Test
        void testProcessResponse() {
                FindMissingDimension find = new FindMissingDimension(new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

        }
}
