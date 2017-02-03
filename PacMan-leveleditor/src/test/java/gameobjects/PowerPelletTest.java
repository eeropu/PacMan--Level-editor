package gameobjects;

import java.awt.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eerop
 */
public class PowerPelletTest {

    private PowerPellet pp;

    public PowerPelletTest() {
        pp = new PowerPellet(11, 11);
    }

    @Test
    public void setUp() {
        assertEquals(320, pp.getX());
        assertEquals(320, pp.getY());
    }

    @Test
    public void getBounds() {
        Rectangle r = pp.getBounds();
        assertEquals(326, r.x);
        assertEquals(326, r.y);
        assertEquals(20, r.height);
        assertEquals(20, r.width);
    }

    @Test
    public void checkCollision() {
        PacMan p = new PacMan(10, 11, Direction.Right);
        assertFalse(pp.checkCollision(p));
        for (int i = 0; i < 3; i++) {
            p.move();
            assertFalse(pp.checkCollision(p));
        }
        p.move();
        assertTrue(pp.checkCollision(p));
    }

}
