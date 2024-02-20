package ca.mcmaster.se2aa4.island.team217;
import ca.mcmaster.se2aa4.island.team217.Point;

import java.util.ArrayList;

public class PointOfInterest extends Point{

    // this is based off what we see in the pois.json file, it has a iD, type and
    // location
    private String identifier;
    private String type;
    
    public PointOfInterest(int x, int y, String identifier, String type) {
        super(x,y); // Explicitly invoke the constructor of the superclass Point
        this.identifier = identifier;
        this.type = type;
    }

    public String getIdentifier() {
        return null;
    }

    public String getType() {
        return null;
    }

    public double[] getLocation() {
        return null;
    }

    public void setIdentifier(String identifier) {

    }

    // could just get all the info at once if needed

    public ArrayList<String> getInfo() {
        return null;

    }

}
