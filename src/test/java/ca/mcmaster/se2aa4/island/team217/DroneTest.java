package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Drone;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DroneTest {

    MapRepresenter map = MapRepresenter.getInstance();

    @Test
    void testFly() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        String result = drone.fly();
        assertEquals("{\"action\": \"fly\"}", result);
    }
    @Test
    void testScan() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        String result = drone.scan();
        assertEquals("{\"action\": \"scan\"}", result);
    }

    @Test
    void testEcho() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        String result = drone.echo(Drone.Heading.N);
        assertEquals("{\"action\": \"echo\", \"parameters\": { \"direction\": \"N\"}}", result);
    }

    @Test
    void testHeading() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        String result = drone.heading(Drone.Heading.E);
        assertEquals("{\"action\": \"heading\", \"parameters\": { \"direction\": \"E\"}}", result);
    }

    @Test
    void testUpdateBatteryLevel() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        drone.updateBatteryLevel(100);
        assertEquals(900, drone.getBatteryLevel());
    }

    @Test
    void testInitializeCurrentLocation() {
        Drone drone = Drone.getInstance(1000, "N", MapRepresenter.getInstance());
        drone.initializeCurrentLocation(10, 10, true);
        assertEquals(10, drone.currentLocation.getRow());
        assertEquals(10, drone.currentLocation.getColumn());
    }
    // @Test
    // public void testUpdateBatteryLevel() {
    // Drone d = Drone.getInstance(7000, "E", map);

    // d.updateBatteryLevel(100);
    // assertEquals(6900, d.getBatteryLevel());

    // IllegalArgumentException exception =
    // assertThrows(IllegalArgumentException.class, () -> {
    // d.updateBatteryLevel(-10);
    // });

    // assertEquals("Cost cannot be negative", exception.getMessage());
    // assertEquals(6900, d.getBatteryLevel());
    // }

    // @Test
    // public void testDecisionTaken() {
    // Drone d = Drone.getInstance(7000, "E", map);

    // String result = d.decisionTaken("fly");
    // assertEquals("{\"action\": \"fly\"}", result);

    // IllegalArgumentException exception =
    // assertThrows(IllegalArgumentException.class, () -> {
    // d.decisionTaken("echo");
    // });
    // assertEquals("Invalid command", exception.getMessage());

    // result = d.decisionTaken("heading", "N");
    // Heading heading = Heading.N;
    // assertEquals("{\"action\": \"heading\", \"parameters\": { \"direction\":
    // \"N\"}}", result);
    // assertEquals("heading", d.getAction());
    // assertEquals(heading, d.getDirection());

    // IllegalArgumentException exception2 =
    // assertThrows(IllegalArgumentException.class, () -> {
    // d.decisionTaken("stop", "E");
    // });
    // assertEquals("Invalid command", exception2.getMessage());

    // IllegalArgumentException directionException =
    // assertThrows(IllegalArgumentException.class, () -> {
    // d.decisionTaken("heading", "null");
    // });
    // assertEquals("Invalid direction", directionException.getMessage());
    // }

    // @Test
    // public void testFly() {
    // Drone d = Drone.getInstance(7000, "E", map);
    // d.currentHeading = Heading.N;
    // d.currentLocation = new Point(1, 1);
    // String result = d.fly();
    // assertEquals("fly", d.getAction());
    // Point expected = new Point(0, 1);
    // assertEquals(expected.getRow(), d.currentLocation.getRow());
    // assertEquals(expected.getColumn(), d.currentLocation.getColumn());
    // }

    // @Test
    // public void testHeading(){
    // Drone d = Drone.getInstance(7000, "E", map);
    // d.currentLocation = new Point(10, 10);
    // //initial heading is E and we are turning left here
    // String result = d.heading(Heading.N);
    // Point expected = new Point(9, 11);
    // assertEquals(expected.getRow(), d.currentLocation.getRow());
    // assertEquals(expected.getColumn(), d.currentLocation.getColumn());
    // assertEquals(Heading.N, d.currentHeading);

    // //initial heading is N and we are turning right here
    // result = d.heading(Heading.E);
    // expected = new Point(8, 12);
    // assertEquals(expected.getRow(), d.currentLocation.getRow());
    // assertEquals(expected.getColumn(), d.currentLocation.getColumn());
    // assertEquals(Heading.E, d.currentHeading);

    // //initial heading is E and we are turning back here
    // IllegalArgumentException backwardException =
    // assertThrows(IllegalArgumentException.class, () -> {
    // d.heading(Heading.W);
    // });
    // assertEquals("Invalid heading", backwardException.getMessage());
    // assertEquals(Heading.E, d.currentHeading);
    // assertEquals(expected.getRow(), d.currentLocation.getRow());
    // assertEquals(expected.getColumn(), d.currentLocation.getColumn());

    // // initial heading is east and we try to heading forward
    // IllegalArgumentException forwardException =
    // assertThrows(IllegalArgumentException.class, () -> {
    // d.heading(Heading.E);
    // });
    // assertEquals("Invalid heading", forwardException.getMessage());
    // assertEquals(Heading.E, d.currentHeading);
    // assertEquals(expected.getRow(), d.currentLocation.getRow());
    // assertEquals(expected.getColumn(), d.currentLocation.getColumn());
    // }

    // @Test
    // public void testScan() {
    // Drone d = Drone.getInstance(7000, "E", map);
    // String result = d.scan();
    // assertEquals("scan", d.getAction());
    // }

    // @Test
    // public void testEcho() {
    // Drone d = Drone.getInstance(7000, "E", map);
    // String result = d.echo(Heading.N);
    // assertEquals("echo", d.getAction());
    // }

    // @Test
    // public void testStop() {
    // Drone d = Drone.getInstance(7000, "E", map);
    // String result = d.stop();
    // assertEquals("stop", d.getAction());
    // }

    // @Test
    // public void testTurnLeft() {
    // Drone d = Drone.getInstance(7000, "E", map);
    // d.currentLocation = new Point(0, 0);
    // String result = d.turnLeft();
    // assertEquals("heading", d.getAction());
    // assertEquals(Heading.N, d.currentHeading);
    // }

    // @Test
    // public void testTurnRight() {
    // Drone d = Drone.getInstance(7000, "E", map);
    // d.currentLocation = new Point(0, 0);
    // String result = d.turnRight();
    // assertEquals("heading", d.getAction());
    // assertEquals(Heading.S, d.currentHeading);
    // }

}
