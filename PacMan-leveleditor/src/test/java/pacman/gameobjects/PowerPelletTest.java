package pacman.gameobjects;

import pacman.gameobjects.PowerPellet;
import pacman.gameobjects.Direction;
import pacman.gameobjects.PacMan;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.pacman.leveleditor.ImageGetter;

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
    
    @Test
    public void setImage(){
        ImageGetter imgGetter = new ImageGetter();
        BufferedImage img = imgGetter.getSubImage(10);
        pp.setImage(img);
        assertEquals(img, pp.getImage());
    }
    
    @Test
    public void rotate(){
        assertEquals(0, pp.getRotation(), 0.01);
        pp.move();
        assertEquals(0.1, pp.getRotation(), 0.01);
        pp.setRotation(Math.PI * 2 - 0.01);
        assertEquals(Math.PI * 2, pp.getRotation(), 0.05);
        pp.move();
        assertEquals(0, pp.getRotation(), 0.01);
        pp.setRotation(Math.PI * 2);
        pp.move();
        assertEquals(0, pp.getRotation(), Double.MIN_VALUE);
    }

}
