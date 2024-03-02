
package main.java.ca.mcmaster.se2aa4.island.team217;

import java.util.List;
import java.util.ArrayList;


public class Point {

    private int x;

    private int y; 
    boolean isGround = false;
    boolean isPOI = false;
    List<String> biomes = new ArrayList<>();


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

    public void initializeGround(){
        if (biomes.size() == 1 && biomes.get(0).equals("OCEAN")){
            isGround = false;
        }
        else {
            isGround = true;
        }
    }

    public Boolean getGround(){
        initializeGround();
        return isGround;
    }

    public boolean getPOI(){
        return isPOI;
    }

    public void addBiomes(List<String> biome, Point currentLocation){
        for (String b : biome){
            this.biomes.add(b);
        }
    }
}

