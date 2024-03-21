package ca.mcmaster.se2aa4.island.team217;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class RunnerTest {

    @Test
    public void testEmptyInputArguments() {
        String[] args = {};
        assertThrows(Exception.class, () -> Runner.main(args));
    }

}
