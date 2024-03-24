package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.Heading;
import ca.mcmaster.se2aa4.island.team217.MapRepresentation.*;

public class Drone {

    private final Logger logger = LogManager.getLogger();

    private Integer batteryLevel;
    private Point currentLocation;
    private Boolean spawnedFacingGround = false;
    private Heading currentHeading;
    public final Heading initialHeading;

    public MapRepresenter mapRepresenter;

    public String action;
    private Heading direction;

    public Drone(Integer batteryLevel, String initialHeading, MapRepresenter map) {
        this.batteryLevel = batteryLevel;
        this.currentHeading = Heading.valueOf(initialHeading);
        this.initialHeading = Heading.valueOf(initialHeading);
        // initialize it at (100, 100) for now
        this.currentLocation = new NormalPoint(100, 100);
        this.mapRepresenter = map;
    }

    // this method also updates the current location of the drone
    public String fly() {
        try {
            switch (currentHeading) {
                case N:
                    currentLocation = mapRepresenter.map.get(currentLocation.getRow() - 1)
                        .get(currentLocation.getColumn());
                    break;
                case E:
                    currentLocation = mapRepresenter.map.get(currentLocation.getRow())
                            .get(currentLocation.getColumn() + 1);
                    break;
                case S:
                    currentLocation = mapRepresenter.map.get(currentLocation.getRow() + 1)
                            .get(currentLocation.getColumn());
                    break;
                case W:
                    currentLocation = mapRepresenter.map.get(currentLocation.getRow())
                            .get(currentLocation.getColumn() - 1);
                    break;
                default:
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            logger.info("Out of bounds");
            return decisionTaken("stop");
        }

        logger.info(currentLocation.getRow() + " " + currentLocation.getColumn());

        return decisionTaken("fly");
    }

    public void initializeCurrentLocation(Integer leftColumns, Integer topRows, Boolean spawnedFacingGround) {
        int rows;
        int columns;
        this.spawnedFacingGround = spawnedFacingGround;

        // we didnt change heading and so leftColumns and topRows are the same as the
        // current
        // location
        if (spawnedFacingGround) {
            rows = topRows;
            columns = leftColumns;
        }
        // since we changed heading, leftColumns and topRows are off by a bit, the 100
        // we
        // intialized currentLocation at (100, 100)
        else {
            rows = topRows + currentLocation.getRow() - 100;
            columns = leftColumns + currentLocation.getColumn() - 100;
        }
        logger.info("Current location, X" + currentLocation.getRow() + " Y" + currentLocation.getColumn());
        logger.info(rows);
        logger.info(columns);
        currentLocation = mapRepresenter.map.get(rows).get(columns);
    }

    // this method also updates current location based on current heading and next
    // heading
    public String heading(Heading heading) {
        if (heading == currentHeading || heading == currentHeading.backSide()) {
            throw new IllegalArgumentException("Invalid heading");
        }
        try {
            if (heading == currentHeading.leftSide()) {
                switch (currentHeading) {
                    case N:
                        currentLocation = mapRepresenter.map.get(currentLocation.getRow() - 1)
                                .get(currentLocation.getColumn() - 1);
                        break;
                    case E:
                        currentLocation = mapRepresenter.map.get(currentLocation.getRow() - 1)
                                .get(currentLocation.getColumn() + 1);
                        break;
                    case S:
                        currentLocation = mapRepresenter.map.get(currentLocation.getRow() + 1)
                                .get(currentLocation.getColumn() + 1);
                        break;
                    case W:
                        currentLocation = mapRepresenter.map.get(currentLocation.getRow() + 1)
                                .get(currentLocation.getColumn() - 1);
                        break;
                    default:
                        return null;
                }
            }

            if (heading == currentHeading.rightSide()) {
                switch (currentHeading) {
                    case N:
                        currentLocation = mapRepresenter.map.get(currentLocation.getRow() - 1)
                                .get(currentLocation.getColumn() + 1);
                        break;
                    case E:
                        currentLocation = mapRepresenter.map.get(currentLocation.getRow() + 1)
                                .get(currentLocation.getColumn() + 1);
                        break;
                    case S:
                        currentLocation = mapRepresenter.map.get(currentLocation.getRow() + 1)
                                .get(currentLocation.getColumn() - 1);
                        break;
                    case W:
                        currentLocation = mapRepresenter.map.get(currentLocation.getRow() - 1)
                                .get(currentLocation.getColumn() - 1);
                        break;
                    default:
                        return null;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            logger.info("Out of bounds");
            return decisionTaken("stop");
        }
        logger.info(currentLocation.getRow() + " " + currentLocation.getColumn());
        return decisionTaken("heading", heading.toString());
    }

    public String echo(Heading heading) {
        return decisionTaken("echo", heading.toString());
    }

    public String scan() {
        return decisionTaken("scan");
    }

    public String stop() {
        return decisionTaken("stop");
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public String getAction() {
        return action;
    }

    public Heading getDirection() {
        return direction;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public Heading getCurrentHeading() {
        return currentHeading;
    }

    public void setCurrentHeading(Heading currentHeading) {
        this.currentHeading = currentHeading;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Boolean getSpawnedFacingGround() {
        return spawnedFacingGround;
    }

    public void updateBatteryLevel(Integer cost) {
        if (cost < 0) {
            throw new IllegalArgumentException("Cost cannot be negative");
        }
        batteryLevel -= cost;
    }

    // these helper methods store the parameters of the next decision in the
    // variables action and direction and provide a string that will be returned to
    // takeDecision
    public String decisionTaken(String command) {

        // ensures the commands are valid
        if (!command.equals("fly") && !command.equals("scan") && !command.equals("stop")) {
            throw new IllegalArgumentException("Invalid command");
        }
        action = command;
        String nextDecision = "{\"action\": \"" + command + "\"}";
        return nextDecision;
    }

    public String decisionTaken(String command, String direction) {

        // need to make sure that the commands are valid
        if (!command.equals("echo") && !command.equals("heading")) {
            throw new IllegalArgumentException("Invalid command");
        }
        // ensures the direction is valid
        if (!direction.equals("N") && !direction.equals("E") && !direction.equals("S") && !direction.equals("W")) {
            throw new IllegalArgumentException("Invalid direction");
        }

        // if the command is heading, then the currentDirection is the new heading
        if (command.equals("heading")) {
            this.currentHeading = Heading.valueOf(direction);
        }

        // store the parameters of the next decision
        action = command;
        this.direction = Heading.valueOf(direction);

        String nextDecision = "{\"action\": \"" + command + "\", \"parameters\": { \"direction\": \"" + direction
                + "\"}}";
        return nextDecision;
    }

}