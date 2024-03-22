package ca.mcmaster.se2aa4.island.team217;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class HeadingTest {
    @Test
    public void testLeftSide() {
        assertEquals(Heading.W, Heading.N.leftSide());
        assertEquals(Heading.N, Heading.E.leftSide());
        assertEquals(Heading.E, Heading.S.leftSide());
        assertEquals(Heading.S, Heading.W.leftSide());
    }

    @Test
    public void testRightSide() {
        assertEquals(Heading.E, Heading.N.rightSide());
        assertEquals(Heading.S, Heading.E.rightSide());
        assertEquals(Heading.W, Heading.S.rightSide());
        assertEquals(Heading.N, Heading.W.rightSide());
    }

    @Test
    public void testBackSide() {
        assertEquals(Heading.S, Heading.N.backSide());
        assertEquals(Heading.W, Heading.E.backSide());
        assertEquals(Heading.N, Heading.S.backSide());
        assertEquals(Heading.E, Heading.W.backSide());
    }

}
