package ca.mcmaster.se2aa4.island.team217;


public class LocateGround implements Phase{
    
    private boolean reachedEnd = false;
    private boolean groundLocated = false;
    private Initializer initializer;
    MapInitializer mapInitializer;
    

    public Boolean reachedEnd() {
        return reachedEnd();
    }

    public Phase getNextPhase() {
        if (reachedEnd) {
            return new TurnToGround();
        } else {
            return null;
        }
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        if (groundLocated) {
            reachedEnd = true;
            return drone.stop();
        } else {
            return drone.echo(initializer.directionToEcho(drone.currentHeading));
        }
    }
    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map){
        if ("GROUND".equals(responseStorage.getFound())) {
            groundLocated = true;
        }
    }
}