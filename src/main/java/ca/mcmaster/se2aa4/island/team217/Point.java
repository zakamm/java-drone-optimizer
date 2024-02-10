package main.java.ca.mcmaster.se2aa4.island.team217; 

public class Point {
    // default: 0, 0 should be top left corner
    private int x;
    private int y; 
    boolean isGround;
    boolean isPOI;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
