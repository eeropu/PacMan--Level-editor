package gameobjects;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eerop
 */
public class CoordinateTest {

    private Coordinate c;

    public CoordinateTest() {
        this.c = new Coordinate(0, 0, 100, 300, 400, null);
    }

    @Test
    public void comparing() {
        Coordinate a = new Coordinate(0, 0, 150, 300, 400, c);
        assertEquals(-1, c.compareTo(a));
        assertEquals(1, a.compareTo(c));
        Coordinate b = new Coordinate(0, 0, 100, 300, 400, a);
        assertEquals(-1, b.compareTo(c));
        assertEquals(-1, b.compareTo(c));
    }

    @Test
    public void previous() {
        Coordinate a = new Coordinate(0, 0, 150, 300, 400, c);
        assertTrue(a.getPrevious() == c);
        c.setPrevious(a);
        assertTrue(c.getPrevious() == a);
    }
    
    @Test
    public void getters(){
        assertEquals(100, c.getDistance());
        assertEquals(500, c.getHeuristic(), 0.1);
    }

}
