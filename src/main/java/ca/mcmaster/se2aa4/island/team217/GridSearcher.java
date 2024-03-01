package ca.mcmaster.se2aa4.island.team217;

//import 3.4;

import ca.mcmaster.se2aa4.island.team217.MapRepresenter;
import ca.mcmaster.se2aa4.island.team217.Drone.Heading;
import ca.mcmaster.se2aa4.island.team217.Initializer;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;

public class GridSearcher {
    // private final Logger logger = LogManager.getLogger();

    // Drone drone;
    // MapRepresenter map;

    // int counter = 0;
    // Boolean echoed = false;
    // Boolean flyCheck = false;
    // Boolean atEdge = false;
    // Heading gridSearchDirection;

    // public GridSearch(Drone drone, MapRepresenter map) {
    //     this.drone = drone;
    //     this.map = map;
    // }

    // public String gridSearch(HashMap<String, List<String>> responseStorage) {
    //     if (echoed) {
    //         if (counter == 0 && responseStorage.get("found").get(0).equals("OUT_OF_RANGE")) {
    //             atEdge = true;
    //         }
    //         // implement turning around method
    //         if (atEdge) {
    //             if (counter == 6) {
    //                 counter = 0;
    //                 gridSearchDirection = drone.currentHeading;
    //                 if (gridSearchDirection.equals("Down")) {
    //                     gridSearchDirection = "Up";
    //                 } else {
    //                     gridSearchDirection = "Down";
    //                 }
    //                 flyCheck = false;
    //                 atEdge = false;
    //                 // return drone.scan();
    //                 return drone.stop(); // testing this
    //             } else {
    //                 return turnAroundGridSearch();
    //             }
    //         }
    //         return drone.scan();
    //     }
    //     if (!drone.currentLocation.getGround()) {
    //         echoed = true ? echoed == false : false;
    //         return drone.echo(drone.currentHeading);
    //     } else if (!flyCheck) {
    //         flyCheck = true;
    //         echoed = false;
    //         return drone.scan();
    //     } else if (flyCheck) {
    //         flyCheck = false;
    //         echoed = false;
    //         return drone.fly();
    //     }

    // }


    // private String turnAroundGridSearch(Heading headingToTurn) {
    //     switch (gridSearchDirection) {
    //         case "Down":
    //             if (counter == 0) {
    //                 counter++;
    //                 return drone.fly();
    //             } else if (counter == 1) {
    //                 counter++;
    //                 return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
    //             } else if (counter == 2) {
    //                 counter++;
    //                 return drone.fly();
    //             } else if (counter == 3) {
    //                 counter++;
    //                 return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
    //             } else if (counter == 4) {
    //                 counter++;
    //                 return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
    //             } else if (counter3 == 5) {
    //                 counter++;
    //                 return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
    //             }
    //         case "Up":
    //             if (counter == 0) {
    //                 counter++;
    //                 return drone.fly();
    //             } else if (counter == 1) {
    //                 counter++;
    //                 return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
    //             } else if (counter == 2) {
    //                 counter++;
    //                 return drone.fly();
    //             } else if (counter == 3) {
    //                 counter++;
    //                 return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
    //             } else if (counter == 4) {
    //                 counter++;
    //                 return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
    //             } else if (counter == 4) {
    //                 counter++;
    //                 return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
    //             }
    //         default:
    //             return null;
    //     }

    // }
}
