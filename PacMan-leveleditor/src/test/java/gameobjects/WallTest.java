package gameobjects;

import gameobjects.Direction;
import gameobjects.PacMan;
import gameobjects.Wall;
import java.awt.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eerop
 */
public class WallTest {
    
    private Wall wall;
    
    @Test
    public void setUp(){
        wall = new Wall(11, 11);
        assertEquals(320, wall.getX());
        assertEquals(320, wall.getY());
    }
    
    @Test
    public void getBounds(){
        wall = new Wall(11, 11);
        Rectangle r = wall.getBounds();
        assertEquals(320, r.x);
        assertEquals(320, r.y);
        assertEquals(32, r.height);
        assertEquals(32, r.width);
    }
    
    @Test
    public void checkCollision(){
        PacMan pacman = new PacMan(1, 3, Direction.Up);
        wall = new Wall(1, 1);
        for (int i = 0; i < 17; i++) {
            assertFalse(wall.checkCollision(pacman));
            pacman.move();
        }
        assertTrue(wall.checkCollision(pacman));
    }
}
