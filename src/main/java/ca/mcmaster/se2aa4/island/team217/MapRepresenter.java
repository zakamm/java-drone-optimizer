
package main.java.ca.mcmaster.se2aa4.island.team217;
import main.java.ca.mcmaster.se2aa4.island.team217.PointOfInterest;

import java.util.ArrayList;

public class MapRepresenter {
    // I think the way we represent the map is have a 2D arraylist of POI's and then add POI's to the arraylist
    public ArrayList<PointOfInterest> pois = new ArrayList<>();

    MapRepresenter(){ // what goes in the constructor needs to be determined
        System.out.println("MapRepresenter is created");
    }

    public PointOfInterest getPOI(int x, int y){
        return null;
    }

    // add poi method
    public void addPOI(PointOfInterest poi){
        pois.add(poi);
    }
}
