package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ca.mcmaster.se2aa4.island.team217.GridSearchPhases;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;

public class ScanAndFly implements Phase {
    private final Logger logger = LogManager.getLogger();

    Boolean reachedEnd = false;

    Boolean flyCheck = true;

    GridSearchPhases griddy;

    public ScanAndFly(GridSearchPhases griddy) {
        this.griddy = griddy;

    }

    public Boolean reachedEnd() {
        return reachedEnd;
    }

    public Phase getNextPhase() {
        return new PossibleTurn(griddy);
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

        if (!drone.currentLocation.getGround()) {
            griddy.echoed = !griddy.echoed;
            logger.info("WE BOUTTA ECHOOOOOOOOOOOOOOOOOOOOOO");
            logger.info("CURRENT HEADING {}", drone.currentHeading);
            if (griddy.echoed) {
                reachedEnd = true;
                logger.info("Possible TURN INBOUNDDD");
                return drone.echo(drone.currentHeading);
            }
            return null;
        } else if (!flyCheck) {
            flyCheck = true;
            griddy.echoed = false;
            return drone.scan();
        } else if (flyCheck) {
            flyCheck = false;
            griddy.echoed = false;
            return drone.fly();
        }
        return null;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

    }
}
