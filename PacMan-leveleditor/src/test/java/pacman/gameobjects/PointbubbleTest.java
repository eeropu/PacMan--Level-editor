package pacman.gameobjects;

import pacman.gameobjects.Pointbubble;
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
public class PointbubbleTest {

    private Pointbubble p;

    public PointbubbleTest() {
        p = new Pointbubble(11, 11);
    }

    @Test
    public void setUp() {
        assertEquals(320, p.getX());
        assertEquals(320, p.getY());
    }

    public void getBounds() {
        Rectangle r = p.getBounds();
        assertEquals(332, r.x);
        assertEquals(332, r.y);
        assertEquals(8, r.height);
        assertEquals(8, r.width);
    }

    @Test
    public void checkCollision() {
        PacMan pacman = new PacMan(10, 11, Direction.Right);
        assertFalse(p.checkCollision(pacman));
        for (int i = 0; i < 6; i++) {
            pacman.move();
            assertFalse(p.checkCollision(pacman));
        }
        pacman.move();
        assertTrue(p.checkCollision(pacman));
    }
    
    @Test
    public void setImage(){
        ImageGetter imgGetter = new ImageGetter();
        BufferedImage img = imgGetter.getSubImage(9);
        p.setImage(img);
        assertEquals(img, p.getImage());
    }

}
