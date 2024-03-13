package ca.mcmaster.se2aa4.island.team217;

public interface Phase {
    Boolean reachedEnd();

    String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map);

    Phase getNextPhase();

    Boolean isFinal();

    void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map);
}
