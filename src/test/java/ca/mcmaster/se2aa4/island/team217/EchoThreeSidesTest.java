package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.MapInitializer;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.EchoThreeSides;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class EchoThreeSidesTest {

    @Test
    void testReachedEnd() {
        EchoThreeSides echoT = new EchoThreeSides(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

        assertEquals(false, echoT.reachedEnd());

        for (int i = 0; i < 3; i++) {
            echoT.nextDecision(ResponseStorage.getInstance(), Drone.getInstance(1000, "N",
                    MapRepresenter.getInstance()), MapRepresenter.getInstance());
        }

        assertEquals(true, echoT.reachedEnd());

    }

    @Test
    void testGetNextPhase() {
        EchoThreeSides echoT = new EchoThreeSides(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

        assertEquals(false, echoT.isFinal());

    }

    @Test
    void testIsFinal() {
        EchoThreeSides echoT = new EchoThreeSides(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));
    }

    @Test
    void testNextDecision() {
        EchoThreeSides echoT = new EchoThreeSides(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));
        Drone drone = Drone.getInstance(1000, "N",
                MapRepresenter.getInstance());

        Integer counter = 0;
        if (counter == 0) {
            assertEquals(drone.echo(
                    drone.initialHeading),
                    echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));
            counter++;
        }
        if (counter == 1) {
            assertEquals(drone.echo(drone.initialHeading
                    .rightSide()),
                    echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));
            counter++;
        }
        if (counter == 2) {
            assertEquals(drone.echo(drone.initialHeading
                    .leftSide()),
                    echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));
            counter++;
        }
    }

    @Test
    void testProcessResponse() {

    }
}
