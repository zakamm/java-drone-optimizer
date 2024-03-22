package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.MapRepresentation.MapRepresenter;

public interface ResponsePhase extends Phase{

    void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map);

}
