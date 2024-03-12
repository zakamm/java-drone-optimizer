package ca.mcmaster.se2aa4.island.team217;

public interface Phase {
    Boolean reachedEnd();

    String nextDecision();

    Phase getPhase();

    Boolean isFinal();
}
