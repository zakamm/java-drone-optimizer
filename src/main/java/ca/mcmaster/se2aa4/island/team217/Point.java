package ca.mcmaster.se2aa4.island.team217;

import java.util.List;

public interface Point {
    int getX();
    int getY();
    void addBiomes(List<String> biomes);
    Boolean getGround();

    void storeScanResults(ResponseStorage scanResults);
}