package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.MapInitializer;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.Heading;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class MapInitializerTest {

    @Test
    void testInitializeMapDimensions() {
        MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance());
        Heading h = Heading.E;
        Integer range = 52;

        map.initializeMapDimensions(h, range);

        assertEquals(Heading.E, h);
        assertEquals(52, range);
    }

    @Test
    void testInitializeRowsAndColumns() {
        MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance());

        map.topRows = 0;
        map.bottomRows = 52;
        map.leftColumns = 0;
        map.rightColumns = 52;

        map.initializeRowsAndColumns();

        assertEquals(map.bottomRows + map.topRows + 1,
                MapRepresenter.getInstance().rows);
        assertEquals(map.rightColumns + map.leftColumns + 1,
                MapRepresenter.getInstance().columns);
    }

    @Test
    void testDirectionToEcho() {
        MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance());

        Heading h = Heading.E;
        map.topRows = 0;
        map.bottomRows = 52;
        map.leftColumns = 0;
        map.rightColumns = 52;

        map.directionToEcho(h);

        assertEquals(Heading.E, h);
    }

}
