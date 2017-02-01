/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobjects;

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
        pacman = new PacMan(7, 1, Direction.Up);
        clyde = new Clyde(1, 1, pacman);
    }
    
    public void setGraph(){
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 22; j++) {
                clyde.graph[i][j] = 1;
            }
        }
    }
    
    @Test
    public void setUp(){
        assertEquals(0, clyde.x);
        assertEquals(0, clyde.x);
        assertEquals(1, clyde.getHomeX());
        assertEquals(20, clyde.getHomeY());
    }
    
    @Test
    public void move(){
        clyde.graph = new int[32][22];
        setGraph();
        clyde.setGraph(clyde.graph);
        for (int i = 0; i < 17; i++) {
            clyde.move();
            assertEquals(i * 2, clyde.x);
            assertEquals(0, clyde.y);
        }
        clyde.move();
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
        assertEquals(Direction.Right, clyde.d);
        for (int i = 0; i < 15; i++) {
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
    public void homeCoordinates(){
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
        assertEquals(3, clyde.getHomeX());
        assertEquals(19, clyde.getHomeY());
        clyde.graph[3][19] = 0;
        clyde.graph[4][19] = 0;
        clyde.graph[4][18] = 0;
        clyde.graph[5][18] = 0;
        clyde.setGraph(clyde.graph);
        assertEquals(5, clyde.getHomeX());
        assertEquals(17, clyde.getHomeY());
    }
    
}
