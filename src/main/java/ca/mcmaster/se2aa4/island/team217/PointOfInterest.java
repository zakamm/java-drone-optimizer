package ca.mcmaster.se2aa4.island.team217;

import java.util.ArrayList;

public class PointOfInterest {

    // this is based off what we see in the pois.json file, it has a iD, type and
    // location
    private String identifier;
    private String type;
    private double[] location; // x and y coordinates of the point of interest

    public PointOfInterest(String identifier, String type, double[] location) {
        this.identifier = identifier;
        this.type = type;
        this.location = location;
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
