package ca.mcmaster.se2aa4.island.team217;

<<<<<<< HEAD
public class CheckBehindDirection implements Phase{
    public Boolean reachedEnd() {
        return false;
    }

=======
public class CheckBehindDirection implements Phase {
    public Boolean reachedEnd() {
        return true;
    }

    public String nextDecision(Drone drone, ResponseStorage responseStorage, MapRepresenter map) {
        return null;
    }

>>>>>>> 79d3f183ecfc0db4f4a15b3d5c672ffafe3becf3
    public Phase getNextPhase() {
        return null;
    }

    public Boolean isFinal() {
<<<<<<< HEAD
        return false;
    }
    
    public String nextDecision(ResponseStorage responseStorage, Drone drone, MapRepresenter map) {
        return null;
    }

    public void processResponse(ResponseStorage responseStorage, Drone drone, MapRepresenter map){
        
=======
        return true;
>>>>>>> 79d3f183ecfc0db4f4a15b3d5c672ffafe3becf3
    }
}
