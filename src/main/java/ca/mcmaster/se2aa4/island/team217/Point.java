package ca.mcmaster.se2aa4.island.team217; 

public class Point {

    private int x;
    private int y; 
    boolean isGround = false;
    boolean isPOI = false;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setGround(){
        isGround = true;
    }

    public Boolean getGround(){
        return isGround;
    }

    public boolean getPOI(){
        return isPOI;
    }
}
