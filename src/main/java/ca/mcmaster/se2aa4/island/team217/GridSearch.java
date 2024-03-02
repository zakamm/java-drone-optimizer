package main.java.ca.mcmaster.se2aa4.island.team217;

public class GridSearch {
    int counter = 0;
    Boolean echoed = false;
    Boolean flyCheck = false;
    Boolean atEdge = false;

    public GridSearch(Drone drone, MapRepresenter map) {
        this.drone = drone;
        this.map = map;
    }

    public String gridSearch(HashMap<String, List<String>> responseStorage) {

        if (echoed) {
            if (counter == 0 && responseStorage.get("found").get(0).equals("OUT_OF_RANGE")) {
                atEdge = true;
            }
            // implement turning around method
            if (atEdge) {
                if (counter == 6) {
                    counter = 0;
                    gridSearchDirection = "Up";
                    if (gridSearchDirection.equals("Down")) {
                        gridSearchDirection = "Up";
                    } else {
                        gridSearchDirection = "Down";
                    }
                    flyCheck = false;
                    atEdge = false;
                    // return drone.scan();
                    return drone.stop(); // testing this
                } else {
                    return turnAroundGridSearch();
                }
            }
            return drone.scan();
        }
        if (responseStorage.get("found").get(0).equals("OCEAN")) {
            echoed = true ? echoed == false : false;
            return drone.echo(drone.currentHeading);
        } else if (!flyCheck) {
            flyCheck = true;
            echoed = false;
            return drone.scan();
        } else if (flyCheck) {
            flyCheck = false;
            echoed = false;
            return drone.fly();
        }

    }

    private String turnAroundGridSearch() {
        switch (gridSearchDirection) {
            case "Down":
                if (counter == 0) {
                    counter++;
                    return drone.fly();
                } else if (counter == 1) {
                    counter++;
                    return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
                } else if (counter == 2) {
                    counter++;
                    return drone.fly();
                } else if (counter == 3) {
                    counter++;
                    return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
                } else if (counter == 4) {
                    counter++;
                    return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
                } else if (counter3 == 5) {
                    counter++;
                    return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
                }
            case "Up":
                if (counter == 0) {
                    counter++;
                    return drone.fly();
                } else if (counter == 1) {
                    counter++;
                    return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
                } else if (counter == 2) {
                    counter++;
                    return drone.fly();
                } else if (counter == 3) {
                    counter++;
                    return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
                } else if (counter == 4) {
                    counter++;
                    return drone.heading(drone.currentHeading.rightSide(drone.currentHeading));
                } else if (counter == 4) {
                    counter++;
                    return drone.heading(drone.currentHeading.leftSide(drone.currentHeading));
                }
            default:
                return null;
        }

    }
}
