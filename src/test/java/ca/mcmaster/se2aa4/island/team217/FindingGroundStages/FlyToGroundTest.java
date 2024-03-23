package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.GridSearchStages.ScanAndFly;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.FlyToGround;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlyToGroundTest {
        @Test
        void testReachedEndCase1() {

                FlyToGround fly = new FlyToGround(new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

                assertEquals(false, fly.reachedEnd());

        }

        @Test
        void testReachedEndCase2() {
                MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance());

                FlyToGround fly = new FlyToGround(map);

                map.distanceToGround = 1;

                fly.nextDecision(ResponseStorage.getInstance(), Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()), MapRepresenter.getInstance());

                assertEquals(true, fly.reachedEnd());
        }

        @Test
        void testGetNextPhase() {
                MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance());

                FlyToGround fly = new FlyToGround(map);

                assertEquals(ScanAndFly.class, fly.getNextPhase().getClass());

        }

        @Test
        void testIsFinal() {
                FlyToGround fly = new FlyToGround(new MapInitializer(Drone.getInstance(1000,
                                "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

                assertEquals(false, fly.isFinal());

        }

        @Test
        void testNextDecisionCase1() {
                MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance());
                FlyToGround fly = new FlyToGround(map);
                Drone drone = Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance());

                map.distanceToGround = 1;

                assertEquals(drone.scan(),
                                fly.nextDecision(ResponseStorage.getInstance(), drone,
                                                MapRepresenter.getInstance()));
        }

        @Test
        void testNextDecisionCase2() {
                MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance());
                FlyToGround fly = new FlyToGround(map);
                Drone drone = Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance());

                map.distanceToGround = 2;

                assertEquals(drone.fly(),
                                fly.nextDecision(ResponseStorage.getInstance(), drone,
                                                MapRepresenter.getInstance()));

        }

        @Test
        void testProcessResponse() {
                FlyToGround echoT = new FlyToGround(new MapInitializer(Drone.getInstance(1000, "N",
                                MapRepresenter.getInstance()),
                                MapRepresenter.getInstance()));

        }

}
