package ca.mcmaster.se2aa4.island.team217;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LocateGround implements Phase{

    private final Logger logger = LogManager.getLogger();

    MapInitializer mapInitializer;
    public LocateGround(MapInitializer mapInitializer){
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
        logger.info("LocateGround");
        return drone.stop();
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map){
        
    }
    
}
