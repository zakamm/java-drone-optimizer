package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;
import ca.mcmaster.se2aa4.island.team217.Drone;

public interface Phase {
    Boolean reachedEnd();

    String nextDecision(Drone drone, ResponseStorage responseStorage, MapRepresenter map);

    Phase getNextPhase();

    Boolean isFinal();
}
