package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FindMissingDimensionTest {
        // @Test
        // void testReachedEnd() {
        // FindMissingDimension find = new FindMissingDimension(new
        // MapInitializer(Drone.getInstance(1000, "N",
        // MapRepresenter.getInstance()),
        // MapRepresenter.getInstance()));

        // find.nextDecision(ResponseStorage.getInstance(), Drone.getInstance(1000, "N",
        // MapRepresenter.getInstance()), MapRepresenter.getInstance());

        // assertEquals(false, echoT.reachedEnd());

        // find.foundDimension = true;

        // find.nextDecision(ResponseStorage.getInstance(), Drone.getInstance(1000, "N",
        // MapRepresenter.getInstance()), MapRepresenter.getInstance());

        // assertEquals(true, echoT.reachedEnd());

        // }

        // @Test
        // void testGetNextPhase() {

        // FindMissingDimension echoT = new FindMissingDimension(new
        // MapInitializer(Drone.getInstance(1000, "N",
        // MapRepresenter.getInstance()),
        // MapRepresenter.getInstance()));

        // assertEquals(new ScanAndFly(new GridSearch(mapInitializer.drone,
        // mapInitializer.map)), echoT.getNextPhase());

        // }

        // @Test
        // void testIsFinal() {
        // EchoThreeSides echoT = new EchoThreeSides(new
        // MapInitializer(Drone.getInstance(1000, "N",
        // MapRepresenter.getInstance()),
        // MapRepresenter.getInstance()));

        // assertEquals(false, echoT.isFinal());

        // }

        // @Test
        // void testNextDecision() {
        // FindMissingDimension find = new FindMissingDimension(new
        // MapInitializer(Drone.getInstance(1000, "N",
        // MapRepresenter.getInstance()),
        // MapRepresenter.getInstance()));

        // Drone drone = Drone.getInstance(1000, "N",
        // MapRepresenter.getInstance());

        // find.foundDimension = true;

        // assertEquals(drone.scan(),
        // find.nextDecision(ResponseStorage.getInstance(), drone,
        // MapRepresenter.getInstance()));

        // assertEquals(true, find.reachEnd);

        // find.foundDimension = false;
        // find.counter = 1;

        // assertEquals(drone.echo(drone
        // .getCurrentHeading()),
        // echoT.nextDecision(ResponseStorage.getInstance(), drone,
        // MapRepresenter.getInstance()));

        // find.counter = 0;

        // assertEquals(drone.fly(),
        // echoT.nextDecision(ResponseStorage.getInstance(), drone,
        // MapRepresenter.getInstance()));
        // }

        // @Test
        // void testProcessResponse() {
        // EchoThreeSides echoT = new EchoThreeSides(new
        // MapInitializer(Drone.getInstance(1000, "N",
        // MapRepresenter.getInstance()),
        // MapRepresenter.getInstance()));

        // }
}
