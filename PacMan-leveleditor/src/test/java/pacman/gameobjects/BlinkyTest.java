package pacman.gameobjects;

import org.junit.Test;
import static org.junit.Assert.*;

public class BlinkyTest {
    
    private Blinky blinky;
    private int[][] graph2;
    
    public BlinkyTest() {
        blinky = new Blinky(11, 11, new PacMan(1, 11, Direction.Up), false);
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
        blinky.setGraph(graph);
    }
    
    @Test
    public void setUp(){
        assertEquals(320, blinky.x);
        assertEquals(320, blinky.y);
        assertFalse(blinky.randomghost);
    }
    
    @Test
    public void eatableTimer(){
        blinky.eatable =  -1;
        blinky.eatPowerpellet(System.currentTimeMillis());
        assertTrue(blinky.ppEaten);
        blinky.move();
        assertFalse(blinky.ppEaten);
        blinky.eatable = 0;
        blinky.eatPowerpellet(System.currentTimeMillis());
        blinky.move();
        assertTrue(blinky.ppEaten);
        blinky.eatable = 1;
        blinky.eatPowerpellet(System.currentTimeMillis());
        blinky.move();
        assertTrue(blinky.ppEaten);
    }
    
    @Test
    public void moveToTwo(){
        blinky.eatable = 100000;
        blinky.timer = Long.MAX_VALUE;
        blinky.move = 0;
        blinky.ppEaten = false;
        blinky.move();
        assertEquals(0, blinky.move);
        blinky.ppEaten = true;
        blinky.move();
        assertEquals(0, blinky.move);
        blinky.move = 1;
        blinky.move();
        assertEquals(1, blinky.move);
        blinky.ppEaten = false;
        blinky.move();
        assertEquals(1, blinky.move);
        blinky.y = 321;
        blinky.move();
        assertEquals(1, blinky.move);
        blinky.x = 321;
        blinky.d = Direction.Right;
        blinky.move();
        assertEquals(1, blinky.move);
        blinky.x = 319;
        blinky.move();
        assertEquals(1, blinky.move);
        blinky.x = 320;
        blinky.y = 320;
        blinky.move();
        
        assertEquals(2, blinky.move);
    }
    
    @Test
    public void moveDistance(){
        blinky.pacman = new PacMan(1, 1, Direction.Up);
        blinky.dSetRandomly = true;
        blinky.x = 64;
        blinky.y = 181;
        blinky.move();
        assertFalse(blinky.dSetRandomly);
        blinky.dSetRandomly = true;
        blinky.x = -64;
        blinky.y = 181;
        blinky.move();
        assertFalse(blinky.dSetRandomly);
        blinky.dSetRandomly = true;
        blinky.x = 64;
        blinky.y = -181;
        blinky.move();
        assertFalse(blinky.dSetRandomly);
        blinky.dSetRandomly = true;
        blinky.x = -64;
        blinky.y = -181;
        blinky.move();
        assertFalse(blinky.dSetRandomly);
        blinky.x = 64;
        blinky.y = 182;
        blinky.move();
        assertTrue(blinky.dSetRandomly);
        blinky.dSetRandomly = false;
        blinky.x = -64;
        blinky.y = 182;
        blinky.move();
        assertTrue(blinky.dSetRandomly);
        blinky.dSetRandomly = false;
        blinky.x = 64;
        blinky.y = -182;
        blinky.move();
        assertTrue(blinky.dSetRandomly);
        blinky.dSetRandomly = false;
        blinky.x = -64;
        blinky.y = -182;
        blinky.move();
        assertTrue(blinky.dSetRandomly);
        blinky.pacman = new PacMan(16, 1, Direction.Up);
        blinky.dSetRandomly = false;
        blinky.x = 480 + 192;
        blinky.y = 0;
        blinky.move();
        assertTrue(blinky.dSetRandomly);
        blinky.dSetRandomly = false;
        blinky.x = 480 - 192;
        blinky.y = 0;
        blinky.move();
        assertTrue(blinky.dSetRandomly);
        blinky.dSetRandomly = false;
        blinky.x = 480 + 193;
        blinky.y = 0;
        blinky.move();
        assertTrue(blinky.dSetRandomly);
        blinky.dSetRandomly = false;
        blinky.x = 480 - 193;
        blinky.y = 0;
        blinky.move();
        assertTrue(blinky.dSetRandomly);
        blinky.dSetRandomly = true;
        blinky.x = 480 + 191;
        blinky.y = 0;
        blinky.move();
        assertFalse(blinky.dSetRandomly);
        blinky.dSetRandomly = true;
        blinky.x = 480 - 191;
        blinky.y = 0;
        blinky.move();
        assertFalse(blinky.dSetRandomly);
        blinky.pacman = new PacMan(1, 16, Direction.Up);
        blinky.dSetRandomly = false;
        blinky.x = 0;
        blinky.y = 480 - 193;
        blinky.move();
        assertTrue(blinky.dSetRandomly);
        blinky.dSetRandomly = false;
        blinky.x = 0;
        blinky.y = 480 - 192;
        blinky.move();
        assertTrue(blinky.dSetRandomly);
        blinky.x = 0;
        blinky.y = 480 - 191;
        blinky.move();
        assertFalse(blinky.dSetRandomly);
    }
    
    @Test
    public void moveDirection(){
        blinky.x = 322;
        blinky.move();
        assertEquals(Direction.Up, blinky.d);
        blinky.x = 318;
        blinky.move();
        assertEquals(Direction.Up, blinky.d);
        blinky.x = 320;
        blinky.y = 318;
        blinky.move();
        assertEquals(Direction.Up, blinky.d);
        blinky.y = 322;
        blinky.move();
        assertEquals(Direction.Up, blinky.d);
        blinky.graph = graph2;
        blinky.move();
        assertEquals(Direction.Left, blinky.d);
        blinky.pacman = new PacMan(8, 11, Direction.Up);
        blinky.d = Direction.Up;
        blinky.x = 322;
        blinky.move();
        assertEquals(Direction.Up, blinky.d);
        blinky.x = 318;
        blinky.move();
        assertEquals(Direction.Up, blinky.d);
        blinky.x = 320;
        blinky.y = 318;
        blinky.move();
        assertEquals(Direction.Up, blinky.d);
        blinky.y = 322;
        blinky.move();
        assertEquals(Direction.Up, blinky.d);
        blinky.move();
        assertEquals(Direction.Left, blinky.d);
    }
    
}
