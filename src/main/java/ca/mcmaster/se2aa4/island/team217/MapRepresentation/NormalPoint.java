package ca.mcmaster.se2aa4.island.team217.MapRepresentation;

import ca.mcmaster.se2aa4.island.team217.ResponseStorage;
import java.util.List;
import java.util.ArrayList;

public class NormalPoint implements Point {

    private int rowNumber;
    private int columnNumber;
    private boolean isGround = false;
    private boolean beenScanned = false;

    private List<String> biomes = new ArrayList<>();

    public NormalPoint(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public int getRow() {
        return rowNumber;
    }

    public int getColumn() {
        return columnNumber;
    }

    public Boolean getGround() {
        if (biomes.size() == 0) {
            isGround = false;
            return isGround;
        }
        if (biomes.size() == 1 && biomes.get(0).equals("OCEAN")) {
            isGround = false;
        } else {
            isGround = true;
        }
        return isGround;
    }

    public boolean getBeenScanned() {
        return beenScanned;
    }
    public void setBeenScanned(boolean beenScanned) {
        this.beenScanned = beenScanned;
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