package pacman.gameobjects;

import pacman.gameobjects.Direction;
import pacman.gameobjects.PacMan;
import pacman.gameobjects.Wall;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.pacman.leveleditor.ImageGetter;

/**
 *
 * @author eerop
 */
public class WallTest {

    private Wall wall;

    @Test
    public void setUp() {
        wall = new Wall(11, 11);
        assertEquals(320, wall.getX());
        assertEquals(320, wall.getY());
    }

    @Test
    public void getBounds() {
        wall = new Wall(11, 11);
        Rectangle r = wall.getBounds();
        assertEquals(320, r.x);
        assertEquals(320, r.y);
        assertEquals(32, r.height);
        assertEquals(32, r.width);
    }

    @Test
    public void checkCollision() {
        PacMan pacman = new PacMan(1, 3, Direction.Up);
        wall = new Wall(1, 1);
        for (int i = 0; i < 17; i++) {
            assertFalse(wall.checkCollision(pacman));
            pacman.move();
        }
        assertTrue(wall.checkCollision(pacman));
    }
    
    @Test
    public void setImage(){
        wall = new Wall(11, 11);
        ImageGetter imgGetter = new ImageGetter();
        BufferedImage img = imgGetter.getSubImage(1);
        wall.setImage(img);
        assertEquals(img, wall.getImage());
    }
}
