package ca.mcmaster.se2aa4.island.team217.MapRepresentation;

import ca.mcmaster.se2aa4.island.team217.ResponseStorage;

public class PointWithSite extends PointOfInterest {

    private String identifier;

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