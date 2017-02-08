package pacman.gameobjects;

import pacman.gameobjects.Coordinate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eerop
 */
public class CoordinateTest {

    private Coordinate c;

    public CoordinateTest() {
        this.c = new Coordinate(0, 0, 100, 300, 400, null, false);
    }

    @Test
    public void comparing() {
        Coordinate a = new Coordinate(0, 0, 150, 300, 400, c, false);
        assertEquals(-1, c.compareTo(a));
        assertEquals(1, a.compareTo(c));
        Coordinate b = new Coordinate(0, 0, 100, 300, 400, a, false);
        assertEquals(-1, b.compareTo(c));
        assertEquals(-1, b.compareTo(c));
    }

    @Test
    public void previous() {
        Coordinate a = new Coordinate(0, 0, 150, 300, 400, c, false);
        assertTrue(a.getPrevious() == c);
        c.setPrevious(a);
        assertTrue(c.getPrevious() == a);
    }

    @Test
    public void getters() {
        assertEquals(100, c.getDistance());
        assertEquals(500, c.getHeuristic(), 0.1);
        assertEquals(0, c.getX());
        assertEquals(0, c.getY());
    }

}
