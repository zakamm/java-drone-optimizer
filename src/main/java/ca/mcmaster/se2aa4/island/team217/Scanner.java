package main.java.ca.mcmaster.se2aa4.island.team217; 

// this is a subclass of Drone, Drone containes a Scanner
public class Scanner {
    Drone drone;
    Scanner(Drone drone){
        this.drone = drone;
    }
    
    public void scan(){
        //do the scanning, may want to input Point and return Point of Interest, and add it to the map, or we let mission control add it to the map
    }

    public void assignIdentifier(PointOfInterest poi){
        //assign an identifier to the POI, just a random number I suppose
    }
    
}
