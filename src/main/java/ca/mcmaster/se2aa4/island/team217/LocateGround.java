package ca.mcmaster.se2aa4.island.team217;

public class LocateGround implements Phase {
    public Boolean reachedEnd() {
        return true;
    }

    public String nextDecision(Drone drone, ResponseStorage responseStorage, MapRepresenter map) {
        return null;
    }

    public Phase getNextPhase() {
        return null;
    }

    public Boolean isFinal() {
        return true;
    }
}
