package ca.mcmaster.se2aa4.island.team217;


public class FindMissingDimension implements Phase{

    int distanceToGround;

    MapInitializer mapInitializer;

    public FindMissingDimension(int distanceToGround, MapInitializer mapInitializer){
        this.distanceToGround = distanceToGround;
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

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map){
        
    }
}
