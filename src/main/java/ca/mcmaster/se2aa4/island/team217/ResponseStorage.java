package ca.mcmaster.se2aa4.island.team217;

import java.util.List;

public class ResponseStorage {
   
    private Integer range;
    private Integer cost;
    private String found;
    private List<String> creeks;
    private List<String> biomes;
    private String site;

    public ResponseStorage(){

    }

    public void setRange(Integer range){
        this.range = range;
    }

    public void setCost(Integer cost){
        this.cost = cost;
    }

    public void setFound(String found){
        this.found = found;
    }

    public void setBiomes(List<String> biomes){
        this.biomes = biomes;
    }

    public void setCreeks(List<String> creeks){
        this.creeks = creeks;
    }

    public void setSite(String site){
        this.site = site;
    }

    public Integer getRange(){
        return this.range;
    }

    public Integer getCost(){
        return this.cost;
    }

    public String getFound(){
        return this.found;
    }

    public List<String> getBiomes(){
        return this.biomes;
    }

    public List<String> getCreeks(){
        return this.creeks;
    }

    public String getSite(){
        return this.site;
    }

    public void clear(){
        this.range = -1;
        this.cost = null;
        this.found = "null";
        this.biomes = null;
        this.creeks = null;
        this.site = null;
    }



}
