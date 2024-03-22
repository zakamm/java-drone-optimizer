package ca.mcmaster.se2aa4.island.team217;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.MapInitializerTest;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.FlyToGround;

import static org.junit.jupiter.api.Assertions.*;

public class FlyToGroundTest {

    @Test
    void testReachedEnd() {
        MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance());

        FlyToGround fly = new FlyToGround(map);

        assertEquals(false, echoT.reachedEnd());

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

        FlyToGround echoT = new FlyToGround(map);

        assertEquals(new ScanAndFly(new GridSearch(mapInitializer.drone, mapInitializer.map)), fly.getNextPhase());

    }

    @Test
    void testIsFinal() {
        FlyToGround fly = new FlyToGround(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

        assertEquals(false, fly.isFinal());

    }

    @Test
    void testNextDecision() {
        MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance());
        EchoThreeSides echoT = new EchoThreeSides(map);
        Drone drone = Drone.getInstance(1000, "N",
                MapRepresenter.getInstance());

        map.distanceToGround = 1;

        assertEquals(drone.scan(),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

        map.distanceToGround = 2;

        assertEquals(drone.fly(),
                echoT.nextDecision(ResponseStorage.getInstance(), drone, MapRepresenter.getInstance()));

    }

    @Test
    void testProcessResponse() {
        FlyToGround echoT = new FlyToGround(new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance()));

    }

}
