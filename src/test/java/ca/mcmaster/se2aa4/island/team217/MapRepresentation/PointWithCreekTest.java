package ca.mcmaster.se2aa4.island.team217.MapRepresentation;

import ca.mcmaster.se2aa4.island.team217.ResponseStorage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointWithCreekTest {
     @Test
     public void testGetIdentifiers() {
         NormalPoint normalPoint = new NormalPoint(5, 10);
         PointWithCreeks pointWithCreeks = new PointWithCreeks(normalPoint);
         List<String> identifiers = Arrays.asList("creek1", "creek2", "creek3");
         for (String identifier : identifiers) {
             pointWithCreeks.getIdentifiers().add(identifier);}
         assertEquals(identifiers, pointWithCreeks.getIdentifiers());
     }
     // this test checks if the point with creeks is created correctly
     @Test
     public void testStoreScanResults() {
         ResponseStorage responseStorage = new ResponseStorage();
         responseStorage.setBiomes(new ArrayList<>());
         NormalPoint normalPoint = new NormalPoint(5, 10);
         PointWithCreeks pointWithCreeks = new PointWithCreeks(normalPoint);
         List<String> creeks = Arrays.asList("creek1", "creek2", "creek3");
         responseStorage.setCreeks(creeks);
         pointWithCreeks.storeScanResults(responseStorage);
         assertEquals(creeks, pointWithCreeks.getIdentifiers());
     }
     //this test checks if the type is returned correctly
}
