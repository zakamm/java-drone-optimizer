package ca.mcmaster.se2aa4.island.team217;

import java.util.List;

import ca.mcmaster.se2aa4.island.team217.ResponseStorage;

public abstract class PointOfInterest implements Point {
    protected Point poi;

    public PointOfInterest(Point poi) {
        this.poi = poi;
    }

    @Override
    public int getX() {
        return poi.getX();
    }

    @Override
    public int getY() {
        return poi.getY();
    }

    @Override
    public Boolean getGround() {
        return poi.getGround();
    }

    @Override
    public void addBiomes(List<String> biomes) {
        poi.addBiomes(biomes);
    }

    // Abstract method for storeScanResults
    @Override
    public abstract void storeScanResults(ResponseStorage scanResults);
}