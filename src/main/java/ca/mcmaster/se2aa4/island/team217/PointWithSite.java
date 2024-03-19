package ca.mcmaster.se2aa4.island.team217;

import java.util.ArrayList;

public class PointWithSite extends PointOfInterest {

    String identifier;

    public PointWithSite(Point poi) {
        super(poi);
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void storeScanResults(ResponseStorage scanResults) {
        poi.storeScanResults(scanResults);
        this.identifier = scanResults.getSite();
    }
}