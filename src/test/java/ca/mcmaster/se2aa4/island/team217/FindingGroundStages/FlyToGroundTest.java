package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.GridSearchStages.ScanAndFly;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlyToGroundTest {

        MapInitializer map;
        FlyToGround fly;

        @BeforeEach
        void initialize() {
                map = new MapInitializer(new Drone(1000, "N",
                                new MapRepresenter()),
                                new MapRepresenter());
                fly = new FlyToGround(map);
        }

        @Test
        void testReachedEndCase1() {
                assertEquals(false, fly.reachedEnd());

        }

        @Test
        void testReachedEndCase2() {

                map.distanceToGround = 1;

                fly.nextDecision(new Drone(1000, "N",
                                new MapRepresenter()), new MapRepresenter());

                assertEquals(true, fly.reachedEnd());
        }

        @Test
        void testGetNextPhase() {
                assertEquals(ScanAndFly.class, fly.getNextPhase().getClass());
        }

        @Test
        void testIsFinal() {
                assertEquals(false, fly.isFinal());
        }

        @Test
        void testNextDecisionCase1() {
                Drone drone = new Drone(1000, "N",
                                new MapRepresenter());

                map.distanceToGround = 1;

                assertEquals(drone.scan(),
                                fly.nextDecision(drone,
                                                new MapRepresenter()));
        }

        @Test
        void testNextDecisionCase2() {
                Drone drone = new Drone(1000, "N",
                                new MapRepresenter());

                map.distanceToGround = 2;

                assertEquals(drone.fly(),
                                fly.nextDecision(drone,
                                                new MapRepresenter()));

        }

}
