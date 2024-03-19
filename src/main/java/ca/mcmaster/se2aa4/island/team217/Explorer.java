package ca.mcmaster.se2aa4.island.team217;

import java.io.StringReader;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.util.List;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();

    private JSONObject checker = new JSONObject(); // used by explorer system

    Drone drone;
    MapRepresenter map;
    MissionControl missionControl; 

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");

        map = new MapRepresenter();
        drone = new Drone(batteryLevel, direction, map);
        missionControl = new MissionControl(drone, map);
        

        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        String decision = missionControl.nextDecision();
        logger.info("** Decision: {}", decision.toString());
        return decision;
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        missionControl.storeResponse(drone.getAction(), response);

        logger.info("** Response received:\n" + response.toString(2));
        Integer cost = response.getInt("cost");
        drone.updateBatteryLevel(cost);
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        checker.put("check", response.getJSONObject("extras"));
    }

    @Override
    public String deliverFinalReport() {
        List<PointWithCreeks> creeks = map.creeks;

        for (PointWithCreeks creek : creeks) {
            logger.info("Creek: {}", creek.getIdentifiers().get(0));
            logger.info("Location: {}", creek.getRow() + ", " + creek.getColumn());
        }
        
        PointWithSite site = map.site;
        double distance = map.computeMinDistance();
        if (map.closestCreek == null) {
            map.closestCreek = creeks.get(0);
        }
        String report = map.closestCreek.getIdentifiers().get(0);
        logger.info("** The identifier of the emergency site is {}", site.getIdentifier());
        logger.info("The location of the emergency site is {}", site.getRow() + ", " + site.getColumn());
        logger.info("** The distance between emergency site and closest creek is {}", distance);
        logger.info("** The identifier of the closest creek is {}", map.closestCreek.getIdentifiers().get(0));
        logger.info("** The location of the closest creek is {}", map.closestCreek.getRow() + ", " + map.closestCreek.getColumn());
        logger.info("** Delivering the final report");
        logger.info("** The drone has stopped");
        
        return report;
    }

    public static void main(String[] args) {
        Explorer e = new Explorer();
        e.initialize("{\"budget\":1000,\"heading\":\"N\"}");
        e.takeDecision();
        e.acknowledgeResults("{\"cost\":1,\"status\":\"success\",\"extras\":{\"range\":1}}");
        e.deliverFinalReport();
    }

}