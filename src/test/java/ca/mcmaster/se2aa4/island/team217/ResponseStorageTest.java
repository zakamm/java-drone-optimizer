package ca.mcmaster.se2aa4.island.team217;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseStorageTest {
    @Test
    void testClear() {
        ResponseStorage responseStorage = ResponseStorage.getInstance();
        responseStorage.clear();

        assertNull(responseStorage.getCost());
        assertEquals(responseStorage.getRange(), -1);
        assertEquals(responseStorage.getFound(), "null");
        assertNull(responseStorage.getBiomes());
        assertNull(responseStorage.getCreeks());
        assertNull(responseStorage.getSite());
    }

    @Test
    void testGetters() {
        ResponseStorage responseStorage = ResponseStorage.getInstance();
        responseStorage.setCost(10);
        responseStorage.setRange(3);
        responseStorage.setFound("GROUND");
        responseStorage.setBiomes(List.of("biome1", "biome2"));
        responseStorage.setCreeks(List.of("creek1", "creek2"));
        responseStorage.setSite("site1");

        assertEquals(10, responseStorage.getCost());
        assertEquals(3, responseStorage.getRange());
        assertEquals("GROUND", responseStorage.getFound());
        assertEquals(List.of("biome1", "biome2"), responseStorage.getBiomes());
        assertEquals(List.of("creek1", "creek2"), responseStorage.getCreeks());
        assertEquals("site1", responseStorage.getSite());
    }
    @Test
    void testSetAndGetRange() {
        ResponseStorage storage = ResponseStorage.getInstance();
        storage.setRange(100);
        assertEquals(100, storage.getRange());
    }
    @Test
    void testSetAndGetFound() {
        ResponseStorage storage = ResponseStorage.getInstance();
        storage.setFound("GROUND");
        assertEquals("GROUND", storage.getFound());
    }
    @Test
    void testSetAndGetBiomes() {
        ResponseStorage storage = ResponseStorage.getInstance();
        storage.setBiomes(List.of("biome1", "biome2"));
        assertEquals(List.of("biome1", "biome2"), storage.getBiomes());
    }
    @Test
    void testSetAndGetCreeks() {
        ResponseStorage storage = ResponseStorage.getInstance();
        storage.setCreeks(List.of("creek1", "creek2"));
        assertEquals(List.of("creek1", "creek2"), storage.getCreeks());
    }
    @Test
    void testSetAndGetSite() {
        ResponseStorage storage = ResponseStorage.getInstance();
        storage.setSite("site1");
        assertEquals("site1", storage.getSite());
    }
    @Test
    void testGetInstance() {
        ResponseStorage storage = ResponseStorage.getInstance();
        assertNotNull(storage);
    }
    @Test
    void testSetCost() {
        ResponseStorage storage = ResponseStorage.getInstance();
        storage.setCost(100);
        assertEquals(100, storage.getCost());
    }


}

