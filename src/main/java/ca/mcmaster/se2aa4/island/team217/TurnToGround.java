package ca.mcmaster.se2aa4.island.team217;

public class TurnToGround implements Phase {

    MapInitializer mapInitializer;

    public TurnToGround(MapInitializer mapInitializer) {
        this.mapInitializer = mapInitializer;

    }

    public Boolean reachedEnd() {
        return false;
    }

    public Phase getNextPhase() {
        return null;
    }

    public Boolean isFinal() {
        return false;
    }

    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        return null;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {

    }
}
