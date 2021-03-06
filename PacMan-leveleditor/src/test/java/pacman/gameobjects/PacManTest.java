/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.gameobjects;

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
public class PacManTest {

    private PacMan pacman;

    @Test
    public void setUp() {
        pacman = new PacMan(11, 11, Direction.Up);
        assertEquals(320, pacman.getX());
        assertEquals(320, pacman.getY());
        assertEquals(Direction.Up, pacman.getCurrent());
        assertEquals(Direction.Up, pacman.getChanged());
    }

    @Test
    public void moveRight() {
        pacman = new PacMan(11, 11, Direction.Right);
        pacman.move();
        assertEquals(320 + pacman.getMove(), pacman.getX());
        assertEquals(320, pacman.getY());
    }

    @Test
    public void moveLeft() {
        pacman = new PacMan(11, 11, Direction.Left);
        pacman.move();
        assertEquals(320 - pacman.getMove(), pacman.getX());
        assertEquals(320, pacman.getY());
    }

    @Test
    public void moveDown() {
        pacman = new PacMan(11, 11, Direction.Down);
        pacman.move();
        assertEquals(320 + pacman.getMove(), pacman.getY());
        assertEquals(320, pacman.getX());
    }

    @Test
    public void moveUp() {
        pacman = new PacMan(11, 11, Direction.Up);
        pacman.move();
        assertEquals(320 - pacman.getMove(), pacman.getY());
        assertEquals(320, pacman.getX());
    }

    @Test
    public void outOfWindowDown() {
        pacman = new PacMan(11, 20, Direction.Down);
        pacman.move();
        assertEquals(320, pacman.getX());
        assertEquals(610, pacman.getY());
        for (int i = 0; i < 15; i++) {
            pacman.move();
        }
        assertEquals(640, pacman.getY());
        pacman.move();
        assertEquals(-32, pacman.getY());
    }

    @Test
    public void outOfWindowUp() {
        pacman = new PacMan(11, 1, Direction.Up);
        pacman.move();
        assertEquals(320, pacman.getX());
        assertEquals(-2, pacman.getY());
        for (int i = 0; i < 15; i++) {
            pacman.move();
        }
        assertEquals(-32, pacman.getY());
        pacman.move();
        assertEquals(640, pacman.getY());
    }

    @Test
    public void outOfWindowRight() {
        pacman = new PacMan(30, 11, Direction.Right);
        pacman.move();
        assertEquals(930, pacman.getX());
        assertEquals(320, pacman.getY());
        for (int i = 0; i < 15; i++) {
            pacman.move();
        }
        assertEquals(960, pacman.getX());
        pacman.move();
        assertEquals(-32, pacman.getX());
    }

    @Test
    public void outOfWindowLeft() {
        pacman = new PacMan(1, 11, Direction.Left);
        pacman.move();
        assertEquals(-2, pacman.getX());
        assertEquals(320, pacman.getY());
        for (int i = 0; i < 15; i++) {
            pacman.move();
        }
        assertEquals(-32, pacman.getX());
        pacman.move();
        assertEquals(960, pacman.getX());
    }

    @Test
    public void directionChanging() {
        pacman = new PacMan(2, 2, Direction.Up);
        pacman.move();
        pacman.right();
        pacman.move();
        assertEquals(Direction.Up, pacman.getCurrent());
        assertEquals(Direction.Right, pacman.getChanged());
        for (int i = 0; i < 14; i++) {
            pacman.move();
        }
        assertEquals(Direction.Right, pacman.getCurrent());
        pacman.move();
        pacman.down();
        pacman.move();
        assertEquals(Direction.Right, pacman.getCurrent());
        assertEquals(Direction.Down, pacman.getChanged());
        for (int i = 0; i < 14; i++) {
            pacman.move();
        }
        assertEquals(Direction.Down, pacman.getCurrent());
        pacman.move();
        pacman.left();
        pacman.move();
        assertEquals(Direction.Down, pacman.getCurrent());
        assertEquals(Direction.Left, pacman.getChanged());
        for (int i = 0; i < 14; i++) {
            pacman.move();
        }
        assertEquals(Direction.Left, pacman.getCurrent());
        pacman.move();
        pacman.up();
        pacman.move();
        assertEquals(Direction.Left, pacman.getCurrent());
        assertEquals(Direction.Up, pacman.getChanged());
        for (int i = 0; i < 14; i++) {
            pacman.move();
        }
        assertEquals(Direction.Up, pacman.getCurrent());
        assertEquals(32, pacman.getX());
        assertEquals(32, pacman.getY());
    }

    @Test
    public void directionChangingWhileGoingOutOfScreen() {
        pacman = new PacMan(1, 1, Direction.Up);
        assertEquals(Direction.Up, pacman.getCurrent());
        assertEquals(Direction.Up, pacman.getChanged());
        pacman.move();
        pacman.left();
        for (int i = 0; i < 32; i++) {
            assertEquals(Direction.Up, pacman.getCurrent());
            pacman.move();
        }
        assertEquals(Direction.Left, pacman.getCurrent());
        pacman.move();
        pacman.down();
        for (int i = 0; i < 32; i++) {
            assertEquals(Direction.Left, pacman.getCurrent());
            pacman.move();
        }
        assertEquals(Direction.Down, pacman.getCurrent());
        pacman.move();
        pacman.right();
        for (int i = 0; i < 32; i++) {
            assertEquals(Direction.Down, pacman.getCurrent());
            pacman.move();
        }
        assertEquals(Direction.Right, pacman.getCurrent());
        pacman.move();
        pacman.up();
        for (int i = 0; i < 32; i++) {
            assertEquals(Direction.Right, pacman.getCurrent());
            pacman.move();
        }
        assertEquals(Direction.Up, pacman.getCurrent());
    }

    @Test
    public void stopUp() {
        pacman = new PacMan(11, 11, Direction.Up);
        pacman.stop();
        assertEquals(322, pacman.getY());
    }

    @Test
    public void stopDown() {
        pacman = new PacMan(11, 11, Direction.Down);
        pacman.stop();
        assertEquals(318, pacman.getY());
    }

    @Test
    public void stopRight() {
        pacman = new PacMan(11, 11, Direction.Right);
        pacman.stop();
        assertEquals(318, pacman.getX());
    }

    @Test
    public void stopLeft() {
        pacman = new PacMan(11, 11, Direction.Left);
        pacman.stop();
        assertEquals(322, pacman.getX());
    }

    @Test
    public void getBounds() {
        pacman = new PacMan(11, 11, Direction.Up);
        Rectangle r = pacman.getBounds();
        assertEquals(320, r.x);
        assertEquals(320, r.y);
        assertEquals(32, r.height);
        assertEquals(32, r.width);
    }

    @Test
    public void checkCollision() {
        pacman = new PacMan(11, 11, Direction.Up);
        assertFalse(pacman.checkCollision(pacman)); //method returns always false
    }
    
    @Test
    public void reset(){
        pacman = new PacMan(1, 1, Direction.Down);
        for (int i = 0; i < 16; i++) {
            pacman.move();
        }
        pacman.right();
        pacman.changeDirection();
        pacman.move();
        assertEquals(2, pacman.getX());
        assertEquals(32, pacman.getY());
        pacman.reset();
        assertEquals(0, pacman.getX());
        assertEquals(0, pacman.getY());
    }
    
    @Test
    public void setImages(){
        pacman = new PacMan(11, 11, Direction.Up);
        ImageGetter imgGetter = new ImageGetter();
        BufferedImage img1 = imgGetter.getSubImage(2);
        BufferedImage img2 = imgGetter.getSubImage(3);
        pacman.setImages(img1, img2);
        assertEquals(img1, pacman.getDrawable());
        assertEquals(img1, pacman.getMouthOpen());
        assertEquals(img2, pacman.getMouthClosed());
        pacman.changeImg();
        assertEquals(img2, pacman.getDrawable());
        pacman.changeImg();
        assertEquals(img1, pacman.getDrawable());
    }
}
