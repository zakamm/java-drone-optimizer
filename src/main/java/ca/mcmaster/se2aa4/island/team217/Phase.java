package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;

public interface Phase {
    Boolean reachedEnd();

    String nextDecision(Drone drone, MapRepresenter map);

    Phase getNextPhase();

    Boolean isFinal();
    
}
