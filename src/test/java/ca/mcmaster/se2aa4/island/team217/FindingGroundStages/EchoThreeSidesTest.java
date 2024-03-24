package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class EchoThreeSidesTest {

        MapInitializer map;
        EchoThreeSides echoT;

        @BeforeEach
        void initialize() {
                map = new MapInitializer(new Drone(1000, "N",
                                new MapRepresenter()),
                                new MapRepresenter());
                echoT = new EchoThreeSides(map);
        }

        @Test
        void testReachedEnd() {

                assertEquals(false, echoT.reachedEnd());

                for (int i = 0; i < 3; i++) {
                        echoT.nextDecision(new Drone(1000,
                                        "N",
                                        new MapRepresenter()), new MapRepresenter());
                }

                assertEquals(true, echoT.reachedEnd());

        }

        @Test
        void testGetNextPhaseCase1() {
                map.spawnedFacingGround = true;

                assertEquals(FindMissingDimension.class, echoT.getNextPhase().getClass());

        }

        @Test
        void testGetNextPhaseCase2() {
                map.spawnedFacingGround = false;

                assertEquals(LocateGround.class, echoT.getNextPhase().getClass());

        }

        @Test
        void testIsFinal() {
                assertEquals(false, echoT.isFinal());

        }

        @Test
        void testNextDecisionCase1() {
                Drone drone = new Drone(1000, "N",
                                new MapRepresenter());

                assertEquals(drone.echo(
                                drone.initialHeading),
                                echoT.nextDecision(drone,
                                                new MapRepresenter()));

        }

        @Test
        void testNextDecisionCase2() {
                Drone drone = new Drone(1000, "N",
                                new MapRepresenter());

                echoT.counter = 1;
                assertEquals(drone.echo(drone.initialHeading
                                .rightSide()),
                                echoT.nextDecision(drone,
                                                new MapRepresenter()));
        }

        @Test
        void testNextDecisionCase3() {
                Drone drone = new Drone(1000, "N",
                                new MapRepresenter());

                echoT.counter = 2;
                assertEquals(drone.echo(drone.initialHeading
                                .leftSide()),
                                echoT.nextDecision(drone,
                                                new MapRepresenter()));
        }

        @Test
        void testProcessResponse() {
                        MapInitializer mapInitializer = new MapInitializer(new Drone(1000, "N", new MapRepresenter()), new MapRepresenter());
                        EchoThreeSides instance = new EchoThreeSides(mapInitializer);
                        ResponseStorage responseStorage = new ResponseStorage();
                        responseStorage.setFound("OUT_OF_RANGE");
                        responseStorage.setRange(0);
                        Drone drone = new Drone(1000, "N", new MapRepresenter());
                        MapRepresenter map = new MapRepresenter();
                        instance.processResponse(responseStorage, drone, map);
                        assertEquals(0, mapInitializer.distanceToGround);
                }
        }
