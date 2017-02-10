/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.gameobjects;

import pacman.gameobjects.Clyde;
import pacman.gameobjects.Direction;
import pacman.gameobjects.PacMan;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eerop
 */
public class ClydeTest {

    private Clyde clyde;
    private PacMan pacman;

    public ClydeTest() {
        pacman = new PacMan(8, 1, Direction.Up);
        clyde = new Clyde(1, 1, pacman, false);
    }

    public void setGraph() {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 22; j++) {
                clyde.graph[i][j] = 1;
            }
        }
    }

    @Test
    public void setUp() {
        assertEquals(0, clyde.x);
        assertEquals(0, clyde.x);
        assertEquals(1, clyde.getHomeX());
        assertEquals(20, clyde.getHomeY());
    }

    @Test
    public void move() {
        clyde.graph = new int[32][22];
        setGraph();
        clyde.setGraph(clyde.graph);
        for (int i = 0; i < 17; i++) {
            assertEquals(i * 2, clyde.x);
            assertEquals(0, clyde.y);
            clyde.move();

        }
        assertEquals(32, clyde.x);
        assertEquals(2, clyde.y);
        clyde.x = 0;
        clyde.y = 4;
        clyde.d = Direction.Up;
        clyde.move();
        assertEquals(0, clyde.x);
        assertEquals(2, clyde.y);
        assertEquals(Direction.Up, clyde.d);
        clyde.move();
        assertEquals(0, clyde.x);
        assertEquals(0, clyde.y);
        clyde.move();
        assertEquals(Direction.Right, clyde.d);
        for (int i = 0; i < 14; i++) {
            clyde.move();
        }
        assertEquals(30, clyde.x);
        assertEquals(0, clyde.y);
        assertEquals(Direction.Right, clyde.d);
        clyde.move();
        assertEquals(32, clyde.x);
        assertEquals(0, clyde.y);
        clyde.move();
        assertEquals(Direction.Down, clyde.d);
    }

    @Test
    public void homeCoordinates() {
        clyde.graph = new int[32][22];
        setGraph();
        clyde.setGraph(clyde.graph);
        assertEquals(1, clyde.getHomeX());
        assertEquals(20, clyde.getHomeY());
        clyde.graph[1][20] = 0;
        clyde.setGraph(clyde.graph);
        assertEquals(2, clyde.getHomeX());
        assertEquals(20, clyde.getHomeY());
        clyde.graph[2][20] = 0;
        clyde.graph[3][20] = 0;
        clyde.setGraph(clyde.graph);
        assertEquals(4, clyde.getHomeX());
        assertEquals(20, clyde.getHomeY());
        for (int i = 0; i < 30; i++) {
            clyde.graph[i][20] = 0;
        }
        clyde.setGraph(clyde.graph);
        assertEquals(30, clyde.getHomeX());
        assertEquals(20, clyde.getHomeY());
        clyde.graph[30][20] = 0;
        clyde.setGraph(clyde.graph);
        assertEquals(0, clyde.getHomeX());
        assertEquals(19, clyde.getHomeY());
    }
    
    @Test
    public void eatableTimer(){
        clyde.graph = new int[32][22];
        setGraph();
        clyde.eatable =  -1;
        clyde.eatPowerpellet(System.currentTimeMillis());
        assertTrue(clyde.ppEaten);
        clyde.move();
        assertFalse(clyde.ppEaten);
        clyde.eatable = 0;
        clyde.eatPowerpellet(System.currentTimeMillis());
        clyde.move();
        assertTrue(clyde.ppEaten);
        clyde.eatable = 1;
        clyde.eatPowerpellet(System.currentTimeMillis());
        clyde.move();
        assertTrue(clyde.ppEaten);
    }
    
    @Test
    public void moveToTwo(){
        clyde.graph = new int[32][22];
        setGraph();
        clyde.eatable = 100000;
        clyde.timer = Long.MAX_VALUE;
        clyde.move = 0;
        clyde.ppEaten = false;
        clyde.move();
        assertEquals(0, clyde.move);
        clyde.ppEaten = true;
        clyde.move();
        assertEquals(0, clyde.move);
        clyde.move = 1;
        clyde.move();
        assertEquals(1, clyde.move);
        clyde.ppEaten = false;
        clyde.move();
        assertEquals(1, clyde.move);
        clyde.y = 321;
        clyde.move();
        assertEquals(1, clyde.move);
        clyde.x = 321;
        clyde.d = Direction.Right;
        clyde.move();
        assertEquals(1, clyde.move);
        clyde.x = 319;
        clyde.move();
        assertEquals(1, clyde.move);
        clyde.x = 320;
        clyde.y = 320;
        clyde.move();
        
        assertEquals(2, clyde.move);
    }

    @Test
    public void moveDistance() {
        clyde.graph = new int[32][22];
        setGraph();
        clyde.pacman = new PacMan(1, 1, Direction.Up);
        clyde.dSetRandomly = true;
        clyde.x = 64;
        clyde.y = 182;
        clyde.move();
        assertFalse(clyde.dSetRandomly);
        clyde.dSetRandomly = true;
        clyde.x = -64;
        clyde.y = 182;
        clyde.move();
        assertFalse(clyde.dSetRandomly);
        clyde.dSetRandomly = true;
        clyde.x = 64;
        clyde.y = -182;
        clyde.move();
        assertFalse(clyde.dSetRandomly);
        clyde.dSetRandomly = true;
        clyde.x = -64;
        clyde.y = -182;
        clyde.move();
        assertFalse(clyde.dSetRandomly);
        clyde.x = 64;
        clyde.y = 181;
        clyde.move();
        assertTrue(clyde.dSetRandomly);
        clyde.dSetRandomly = false;
        clyde.x = -64;
        clyde.y = 181;
        clyde.move();
        assertTrue(clyde.dSetRandomly);
        clyde.dSetRandomly = false;
        clyde.x = 64;
        clyde.y = -181;
        clyde.move();
        assertTrue(clyde.dSetRandomly);
        clyde.dSetRandomly = false;
        clyde.x = -64;
        clyde.y = -181;
        clyde.move();
        assertTrue(clyde.dSetRandomly);
        clyde.pacman = new PacMan(16, 1, Direction.Up);
        clyde.dSetRandomly = false;
        clyde.x = 480 + 192;
        clyde.y = 0;
        clyde.move();
        assertTrue(clyde.dSetRandomly);
        clyde.dSetRandomly = false;
        clyde.x = 480 - 192;
        clyde.y = 0;
        clyde.move();
        assertTrue(clyde.dSetRandomly);
        clyde.dSetRandomly = false;
        clyde.x = 480 + 191;
        clyde.y = 0;
        clyde.move();
        assertTrue(clyde.dSetRandomly);
        clyde.dSetRandomly = false;
        clyde.x = 480 - 191;
        clyde.y = 0;
        clyde.move();
        assertTrue(clyde.dSetRandomly);
        clyde.dSetRandomly = true;
        clyde.x = 480 + 193;
        clyde.y = 0;
        clyde.move();
        assertFalse(clyde.dSetRandomly);
        clyde.dSetRandomly = true;
        clyde.x = 480 - 193;
        clyde.y = 0;
        clyde.move();
        assertFalse(clyde.dSetRandomly);
        clyde.pacman = new PacMan(1, 16, Direction.Up);
        clyde.dSetRandomly = false;
        clyde.x = 0;
        clyde.y = 480 - 191;
        clyde.move();
        assertTrue(clyde.dSetRandomly);
        clyde.dSetRandomly = false;
        clyde.x = 0;
        clyde.y = 480 - 192;
        clyde.move();
        assertTrue(clyde.dSetRandomly);
        clyde.x = 0;
        clyde.y = 480 - 193;
        clyde.move();
        assertFalse(clyde.dSetRandomly);
    }
    
    @Test
    public void moveDirection(){
        clyde.graph = new int[32][22];
        setGraph();
        clyde.pacman = new PacMan(0, 11, Direction.Up);
        clyde.x = 322;
        clyde.move();
        assertEquals(Direction.Up, clyde.d);
        clyde.x = 318;
        clyde.move();
        assertEquals(Direction.Up, clyde.d);
        clyde.x = 320;
        clyde.y = 318;
        clyde.move();
        assertEquals(Direction.Up, clyde.d);
        clyde.y = 322;
        clyde.move();
        assertEquals(Direction.Up, clyde.d);
        clyde.move();
        assertEquals(Direction.Left, clyde.d);
    }
    
    @Test
    public void moveDirectionHome(){
        clyde.graph = new int[32][22];
        setGraph();
        clyde.pacman = new PacMan(1, 1, Direction.Up);
        clyde.x = 0;
        clyde.y = 62;
        clyde.d = Direction.Up;
        clyde.move();
        assertEquals(Direction.Up, clyde.d);
        clyde.y = 66;
        clyde.move();
        assertEquals(Direction.Up, clyde.d);
        clyde.x = -2;
        clyde.move();
        assertEquals(Direction.Up, clyde.d);
        clyde.x = 2;
        clyde.move();
        assertEquals(Direction.Up, clyde.d);
        clyde.x = 0;
        clyde.y = 64;
        clyde.move();
        assertEquals(Direction.Down, clyde.d);
        clyde.x = 0;
        clyde.y = 64;
        clyde.setHomeX(10);
        clyde.setHomeY(3);
        clyde.move();
        assertEquals(Direction.Right, clyde.d);
        clyde.x = 64;
        clyde.y = 64;
        clyde.setHomeX(0);
        clyde.setHomeY(3);
        clyde.move();
        assertEquals(Direction.Left, clyde.d);
        clyde.x = 0;
        clyde.y = 64;
        clyde.setHomeX(1);
        clyde.setHomeY(1);
        clyde.move();
        assertEquals(Direction.Up, clyde.d);
    }

}
