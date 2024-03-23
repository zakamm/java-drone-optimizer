package ca.mcmaster.se2aa4.island.team217;

import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;

public class ResponseStorage {

    private Integer range;
    private Integer cost;
    private String found;
    private List<String> creeks;
    private List<String> biomes;
    private String site;

    // used for singleton pattern implementation
    private static ResponseStorage uniqueInstance = null;

    public ResponseStorage() {

    }

    public static ResponseStorage getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ResponseStorage();
        }
        return uniqueInstance;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public void setBiomes(List<String> biomes) {
        this.biomes = biomes;
    }

    public void setCreeks(List<String> creeks) {
        this.creeks = creeks;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Integer getRange() {
        return this.range;
    }

    public Integer getCost() {
        return this.cost;
    }

    public String getFound() {
        return this.found;
    }

    public List<String> getBiomes() {
        return this.biomes;
    }

    public List<String> getCreeks() {
        return this.creeks;
    }

    public String getSite() {
        return this.site;
    }

    public void clear() {
        this.range = -1;
        this.cost = null;
        this.found = "null";
        this.biomes = null;
        this.creeks = null;
        this.site = null;
    }

    public void storeResponse(String action, JSONObject previousResponse) {
        // want to clear at the start of each iteration, sets all values to null
        clear();

        // all actions will have cost
        List<String> temp = new ArrayList<String>();
        setCost(previousResponse.getInt("cost"));

        if (action.equals("echo")) {
            setRange(previousResponse.getJSONObject("extras").getInt("range"));
            setFound(previousResponse.getJSONObject("extras").getString("found"));
        }

        // store as lists with first item being null if empty
        else if (action.equals("scan")) {
            temp = new ArrayList<String>();
            JSONArray creeksArray = previousResponse.getJSONObject("extras").getJSONArray("creeks");
            if (creeksArray.length() == 0) {
                temp.add("null");
            } else {
                for (int i = 0; i < creeksArray.length(); i++) {
                    temp.add(creeksArray.getString(i));
                }
            }
            setCreeks(temp);

            temp = new ArrayList<String>();
            JSONArray biomesArray = previousResponse.getJSONObject("extras").getJSONArray("biomes");
            if (biomesArray.length() == 0) {
                temp.add("null");
            } else {
                for (int i = 0; i < biomesArray.length(); i++) {
                    temp.add(biomesArray.getString(i));
                }
            }
            setBiomes(temp);

            // assuming there is only one site, we only store the first value
            temp = new ArrayList<String>();
            JSONArray sitesArray = previousResponse.getJSONObject("extras").getJSONArray("sites");
            if (sitesArray.length() == 0) {
                temp.add("null");
            } else {
                temp.add(sitesArray.getString(0));
            }
            setSite(temp.get(0));
        }
    }

}
