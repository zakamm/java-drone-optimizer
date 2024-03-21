package ca.mcmaster.se2aa4.island.team217.MapRepresentation;

import java.util.List;
import ca.mcmaster.se2aa4.island.team217.ResponseStorage;

public interface Point {
    int getRow();
    int getColumn();
    void addBiomes(List<String> biomes);
    Boolean getGround();

    void storeScanResults(ResponseStorage scanResults);
}