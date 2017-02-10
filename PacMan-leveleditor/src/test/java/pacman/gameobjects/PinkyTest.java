package pacman.gameobjects;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eerop
 */
public class PinkyTest {
    
    private Pinky pinky;
    private int[][] graph2;
    
    public PinkyTest() {
        this.pinky = new Pinky(11, 11, new PacMan(1, 11, Direction.Up), false);
        int[][] graph = new int[32][22];
        graph2 = new int[32][22];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 22; j++) {
                graph[i][j] = 1;
                graph2[i][j] = 1;
            }
        }
        graph2[11][10] = 0;
        graph2[12][11] = 0;
        pinky.setGraph(graph);
    }
    
    @Test
    public void setUp(){
        assertEquals(320, pinky.x);
        assertEquals(320, pinky.y);
        assertFalse(pinky.randomghost);
    }
    
    @Test
    public void eatableTimer(){
        pinky.eatable =  -1;
        pinky.eatPowerpellet(System.currentTimeMillis());
        assertTrue(pinky.ppEaten);
        pinky.move();
        assertFalse(pinky.ppEaten);
        pinky.eatable = 0;
        pinky.eatPowerpellet(System.currentTimeMillis());
        pinky.move();
        assertTrue(pinky.ppEaten);
        pinky.eatable = 1;
        pinky.eatPowerpellet(System.currentTimeMillis());
        pinky.move();
        assertTrue(pinky.ppEaten);
    }
    
    @Test
    public void moveToTwo(){
        pinky.eatable = 100000;
        pinky.timer = Long.MAX_VALUE;
        pinky.move = 0;
        pinky.ppEaten = false;
        pinky.move();
        assertEquals(0, pinky.move);
        pinky.ppEaten = true;
        pinky.move();
        assertEquals(0, pinky.move);
        pinky.move = 1;
        pinky.move();
        assertEquals(1, pinky.move);
        pinky.ppEaten = false;
        pinky.move();
        assertEquals(1, pinky.move);
        pinky.y = 321;
        pinky.move();
        assertEquals(1, pinky.move);
        pinky.x = 321;
        pinky.d = Direction.Right;
        pinky.move();
        assertEquals(1, pinky.move);
        pinky.x = 319;
        pinky.move();
        assertEquals(1, pinky.move);
        pinky.x = 320;
        pinky.y = 320;
        pinky.move();
        
        assertEquals(2, pinky.move);
    }
    
    @Test
    public void moveDistance(){
        //Postitioning infront of PacMan
        pinky.pacman = new PacMan(1, 1, Direction.Right);
        pinky.x = 0;
        pinky.y = 288;
        for (int i = 0; i < 10; i++) {
            pinky.graph[2][i] = 0;
        }
        assertEquals(Direction.Up, pinky.d);
        pinky.move();
        assertEquals(Direction.Right, pinky.d);
        pinky.pacman = new PacMan(5, 1, Direction.Left);
        pinky.x = 64;
        pinky.move();
        assertEquals(Direction.Left, pinky.d);
        for (int i = 0; i < 10; i++) {
            pinky.graph[2][i] = 1;
        }
        for (int i = 0; i < 10; i++) {
            pinky.graph[i][2] = 0;
        }
        pinky.pacman = new PacMan(1, 1, Direction.Down);
        pinky.x = 288;
        pinky.y = 0;
        pinky.move();
        assertEquals(Direction.Down, pinky.d);
        pinky.pacman = new PacMan(1, 5, Direction.Up);
        pinky.y = 64;
        pinky.move();
        assertEquals(Direction.Up, pinky.d);
        //Setting Direction randomly
        pinky.x = 320;
        pinky.y = 320;
        pinky.graph = graph2;
        pinky.d = Direction.Up;
        pinky.move();
        assertEquals(Direction.Left, pinky.d);
        //Following PacMan
        pinky.x = 0;
        pinky.y = 160;
        pinky.d = Direction.Right;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 22; j++) {
                pinky.graph[i][j] = 1;
            }
        }
        for (int i = 0; i < 5; i++) {
            pinky.graph[2][i] = 0;
        }
        pinky.pacman = new PacMan(1, 1, Direction.Right);
        pinky.move();
        assertEquals(Direction.Up, pinky.d);
        
        pinky.eatPowerpellet(Long.MAX_VALUE);
        pinky.x = 0;
        pinky.y = 322;
        pinky.d = Direction.Up;
        for (int i = 0; i < 22; i++) {
            pinky.graph[0][i] = 0;
            pinky.graph[2][i] = 0;
        }
        pinky.dSetRandomly = true;
        pinky.move();
        assertTrue(pinky.dSetRandomly);
        assertEquals(Direction.Up, pinky.d);
        pinky.move();
        assertTrue(pinky.dSetRandomly);
        assertEquals(Direction.Up, pinky.d);
        pinky.move();
        assertTrue(pinky.dSetRandomly);
        assertEquals(Direction.Up, pinky.d);
        for (int i = 0; i < 30; i++) {
            pinky.move();
            assertFalse(pinky.dSetRandomly);
            assertEquals(Direction.Up, pinky.d);
        }
        pinky.move();
        assertTrue(pinky.ppEaten);
        assertEquals(0, pinky.x);
        assertEquals(288, pinky.y);
        assertEquals(0, pinky.pacman.getX());
        assertEquals(0, pinky.pacman.getY());
        pinky.y = 256;
        assertTrue(pinky.x % 32 == 0 && pinky.y % 32 == 0);
        pinky.move();
        assertEquals(Direction.Up, pinky.d);
    }
    
}
