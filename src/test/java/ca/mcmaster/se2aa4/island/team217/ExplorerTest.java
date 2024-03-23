package ca.mcmaster.se2aa4.island.team217;

import ca.mcmaster.se2aa4.island.team217.Explorer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExplorerTest {

    @Test
        void testInitializeAndTakeDecision() {
        Explorer explorer = new Explorer();
        explorer.initialize("{\"budget\":1000,\"heading\":\"N\"}");
        String decision = explorer.takeDecision();
        assertNotNull(decision);
    }
    @Test
        void testAcknowledgeResults() {
        Explorer explorer = new Explorer();
        explorer.initialize("{\"budget\":900,\"heading\":\"N\"}");
        explorer.acknowledgeResults("{\"cost\":1,\"status\":\"success\",\"extras\":{\"range\":1}}");
        assertEquals(899, explorer.drone.getBatteryLevel());
    }
    @Test
        void testDeliverFinalReport() {
        Explorer explorer = new Explorer();
        explorer.initialize("{\"budget\":1000,\"heading\":\"N\"}");
        String report = explorer.deliverFinalReport();
        assertNotNull(report);
    }

}
