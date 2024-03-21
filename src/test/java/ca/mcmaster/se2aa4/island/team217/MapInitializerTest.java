package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.MapInitializer;
import ca.mcmaster.se2aa4.island.team217.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

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

        map.setTopRows(0);
        map.setBottomRows(52);
        map.setLeftColumns(0);
        map.setRightColumns(52);

        map.initializeRowsAndColumns();

        assertEquals(map.getBottomRows() + map.getTopRows() + 1, MapRepresenter.getInstance().rows);
        assertEquals(map.getRightColumns() + map.getLeftColumns() + 1, MapRepresenter.getInstance().columns);

    }

    @Test
    void testDirectionToEcho() {
        MapInitializer map = new MapInitializer(Drone.getInstance(1000, "N",
                MapRepresenter.getInstance()),
                MapRepresenter.getInstance());

        Heading h = Heading.E;
        map.setTopRows(0);
        map.setBottomRows(52);
        map.setLeftColumns(0);
        map.setRightColumns(52);

        map.directionToEcho(h);

        assertEquals(Heading.E, h);
    }
}
