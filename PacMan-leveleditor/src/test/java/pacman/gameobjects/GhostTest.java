package pacman.gameobjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.levelmanagement.LevelRunner;
import pacman.pacman.leveleditor.ImageGetter;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 */
public class GhostTest {
    
    private Ghost ghost;
    
    public GhostTest() {
        this.ghost = new Ghost(11, 11, new PacMan(1, 11, Direction.Up));
    }
    
    @Test
    public void move(){
        ghost.move();
        assertEquals(320, ghost.getX());
        assertEquals(318, ghost.getY());
        ghost.d = Direction.Down;
        ghost.move();
        assertEquals(320, ghost.getX());
        assertEquals(320, ghost.getY());
        ghost.d = Direction.Right;
        ghost.move();
        assertEquals(322, ghost.getX());
        assertEquals(320, ghost.getY());
        ghost.d = Direction.Left;
        ghost.move();
        assertEquals(320, ghost.getX());
        assertEquals(320, ghost.getY());
    }
    
    @Test
    public void getBounds(){
        Rectangle r = ghost.getBounds();
        assertEquals(320, r.x);
        assertEquals(320, r.y);
        assertEquals(32, r.width);
        assertEquals(32, r.height);
        assertEquals(320, ghost.x);
        assertEquals(320, ghost.y);
    }
    
    @Test
    public void checkCollision(){
        ghost.d = Direction.Left;
        for (int i = 0; i < 144; i++) {
            ghost.move();
            assertFalse(ghost.checkCollision(ghost.pacman));
        }
        ghost.move();
        assertTrue(ghost.checkCollision(ghost.pacman));
    }
    
    @Test
    public void setGraph(){
        int[][] graph = new int[10][10];
        ghost.setGraph(graph);
        assertArrayEquals(graph, ghost.graph);
    }
    
    @Test
    public void stop(){
        ghost.stop();
        assertEquals(322, ghost.getY());
        assertEquals(320, ghost.getX());
        assertEquals(Direction.Stop, ghost.d);
        ghost.d = Direction.Down;
        ghost.stop();
        assertEquals(320, ghost.getY());
        assertEquals(320, ghost.getX());
        assertEquals(Direction.Stop, ghost.d);
        ghost.d = Direction.Right;
        ghost.stop();
        assertEquals(320, ghost.getY());
        assertEquals(318, ghost.getX());
        assertEquals(Direction.Stop, ghost.d);
        ghost.d = Direction.Left;
        ghost.stop();
        assertEquals(320, ghost.getY());
        assertEquals(320, ghost.getX());
        assertEquals(Direction.Stop, ghost.d);
    }
    
    @Test
    public void eatPowerPellet(){
        ghost.timer = 10;
        assertFalse(ghost.ppEaten);
        assertEquals(10, ghost.timer);
        assertEquals(2, ghost.move);
        ghost.eatPowerpellet(100);
        assertTrue(ghost.ppEaten);
        assertEquals(100, ghost.timer);
        assertEquals(1, ghost.move);
    }
    
    @Test
    public void eatableTimer(){
        ghost.eatPowerpellet(0);
        ghost.x = 63;
        ghost.y = 65;
        ghost.timer = 50;
        ghost.eatable = 100;
        assertTrue(ghost.ppEaten);
        assertEquals(1, ghost.move);
        ghost.eatableTimer(149);
        assertTrue(ghost.ppEaten);
        assertEquals(1, ghost.move);
        ghost.eatableTimer(150);
        assertTrue(ghost.ppEaten);
        assertEquals(1, ghost.move);
        ghost.eatableTimer(151);
        assertFalse(ghost.ppEaten);
        assertEquals(1, ghost.move);
        ghost.move();
        ghost.eatableTimer(151);
        assertEquals(1, ghost.move);
        ghost.d = Direction.Right;
        ghost.move();
        ghost.eatableTimer(151);
        assertEquals(2, ghost.move);
        
    }
    
    @Test
    public void timeleft(){
        ghost.now = 100;
        ghost.timer = 50;
        assertEquals(50, ghost.timeLeft());
    }
    
    @Test
    public void settingXY(){
        ghost.setX(100);
        ghost.setY(100);
        assertEquals(100, ghost.x);
        assertEquals(100, ghost.y);
    }
    
    @Test
    public void ppEaten(){
        assertFalse(ghost.isPpEaten());
        ghost.setPpEaten(true);
        assertTrue(ghost.isPpEaten());
        ghost.setPpEaten(false);
        assertFalse(ghost.isPpEaten());
    }
    
    @Test
    public void getx(){
        assertEquals(320, ghost.getX());
    }
    
    @Test
    public void gety(){
        assertEquals(320, ghost.getY());
    }
    
    @Test
    public void reset(){
        ghost.move();
        assertEquals(318, ghost.y);
        ghost.d = Direction.Right;
        ghost.move();
        assertEquals(322, ghost.x);
        ghost.reset();
        assertEquals(320, ghost.x);
        assertEquals(320, ghost.y);
    }
    
    @Test
    public void setImages(){
        ImageGetter imgGetter = new ImageGetter();
        BufferedImage bf1 = imgGetter.getSubImage(1);
        BufferedImage bf2 = imgGetter.getSubImage(2);
        ghost.setImages(bf1, bf2);
        assertEquals(bf1, ghost.img);
        assertEquals(bf2, ghost.ppImg);
    }
    
}
