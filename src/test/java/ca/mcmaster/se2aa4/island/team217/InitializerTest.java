package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Explorer;
import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;
import ca.mcmaster.se2aa4.island.team217.Initializer;
import ca.mcmaster.se2aa4.island.team217.MapRepresenter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

// public class InitializerTest {

//     Integer batteryLevel = 7000;
//     Heading initialHeading = Heading.E;
//     Drone drone = new Drone(batteryLevel, String.valueOf(initialHeading));
//     MapRepresenter map = new MapRepresenter();
//     HashMap<String, List<String>> responseStorage = new HashMap<String, List<String>>();

//     @Test
//     public void DirectionToEchoTest() {
//         Initializer initializer = new Initializer(drone, map);

//         // test result if both directions are different
//         initializer.rightX = 8;
//         initializer.leftX = 5;
//         initializer.bottomY = 20;
//         initializer.topY = 5;
//         assertEquals("S", initializer.directionToEcho(initialHeading));
//         assertEquals("E", initializer.directionToEcho(initialHeading.rightSide(initialHeading)));

//         // test result if both directions are same
//         initializer.rightX = 5;
//         initializer.leftX = 5;
//         initializer.bottomY = 5;
//         initializer.topY = 5;
//         assertEquals("N", initializer.directionToEcho(initialHeading));
//         assertEquals("E", initializer.directionToEcho(initialHeading.rightSide(initialHeading)));
//     }

//     @Test
//     public void initializeMapDimensionsTest() {
//         Initializer initializer = new Initializer(drone, map);

//         initializer.initializeMapDimensions(initialHeading, "5");
//         assertEquals(5, initializer.rightX);

//         initializer.initializeMapDimensions(initialHeading.rightSide(initialHeading), "0");
//         assertEquals(0, initializer.bottomY);
//     }

//     @Test
//     public void initializeMissionTest(){
//         Initializer initializer = new Initializer(drone, map);
//         List<String> range = new ArrayList<String>();
//         List<String> found = new ArrayList<String>();

//         // first echo is 0
//         range.add("0");
//         responseStorage.put("range", range);
//         found.add("OUT_OF_RANGE");
//         responseStorage.put("found", found);
//         String result = initializer.initializeMission(initialHeading, responseStorage);
//         assertEquals(drone.stop(), result);

//         // initial threecheck does not find ground

//         found.add("GROUND");
//         responseStorage.put("found", found);

//     }
// }
