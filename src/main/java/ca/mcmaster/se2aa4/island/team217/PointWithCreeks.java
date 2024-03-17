package ca.mcmaster.se2aa4.island.team217;

import java.util.ArrayList;
import java.util.List;

public class PointWithCreeks extends PointOfInterest {

    List<String> identifiers = new ArrayList<String>();

    public PointWithCreeks(Point poi) {
        super(poi);
    }

    public List<String> getIdentifiers() {
        return identifiers;
    }

    @Override
    public void storeScanResults(ResponseStorage scanResults) {
        poi.storeScanResults(scanResults);
        for (String creekIdentifier : scanResults.getCreeks()) {
            identifiers.add(creekIdentifier);
        }
    }
}