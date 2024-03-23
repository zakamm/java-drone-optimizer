package ca.mcmaster.se2aa4.island.team217.FindingGroundStages;

import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.FindingGroundStages.MapInitializer;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.Heading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class MapInitializerTest {

    MapInitializer mapIni;

    @BeforeEach
    void initialize() {
        mapIni = new MapInitializer(new Drone(1000, "N",
                new MapRepresenter()),
                new MapRepresenter());
    }

    @Test
    void testInitializeMapDimensions() {
        Heading h = Heading.E;
        Integer range = 52;

        mapIni.initializeMapDimensions(h, range);

        assertEquals(Heading.E, h);
        assertEquals(52, range);
    }

    @Test
    void testInitializeRowsAndColumnsCase1() {
        mapIni.topRows = 0;
        mapIni.bottomRows = 52;
        mapIni.leftColumns = 0;
        mapIni.rightColumns = 52;

        assertEquals(0,
                mapIni.map.rows);
        assertEquals(0,
                mapIni.map.columns);
    }

    @Test
    void testInitializeRowsAndColumnsCase2() {
        mapIni.topRows = 0;
        mapIni.bottomRows = 52;
        mapIni.leftColumns = 0;
        mapIni.rightColumns = 52;

        mapIni.initializeRowsAndColumns();

        assertEquals(mapIni.bottomRows + mapIni.topRows + 1,
                mapIni.map.rows);
        assertEquals(mapIni.rightColumns + mapIni.leftColumns + 1,
                mapIni.map.columns);
    }

    @Test
    void testDirectionToEcho() {
        Heading h = Heading.E;
        mapIni.topRows = 0;
        mapIni.bottomRows = 52;
        mapIni.leftColumns = 0;
        mapIni.rightColumns = 52;

        mapIni.directionToEcho(h);

        assertEquals(Heading.E, h);
    }

}
