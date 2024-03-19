package ca.mcmaster.se2aa4.island.team217;

import java.util.List;
import java.util.ArrayList;

public class NormalPoint implements Point {

    private int x;
    private int y;
    boolean isGround = false;
    boolean isPOI = false;
    boolean beenScanned = false;
    List<String> biomes = new ArrayList<>();

    public NormalPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getRow() {
        return x;
    }

    public int getColumn() {
        return y;
    }

    public Boolean getGround() {
        if (biomes.size() == 1 && biomes.get(0).equals("OCEAN")) {
            isGround = false;
        } else {
            isGround = true;
        }
        return isGround;
    }

    public boolean getPOI() {
        return isPOI;
    }

    public void addBiomes(List<String> biome) {
        for (String b : biome) {
            this.biomes.add(b);
        }
    }

    public void storeScanResults(ResponseStorage scanResults) {
        beenScanned = true;
        addBiomes(scanResults.getBiomes());

    }
}