package pacman.gameobjects;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eerop
 */
public class RandomDirectionTest {

    private RandomDirection rd;
    private Ghost ghost;

    public RandomDirectionTest() {
        this.rd = new RandomDirection();
        ghost = new Ghost(11, 11, new PacMan(1, 1, Direction.Up));
        ghost.setGraph(setGraph());
    }

    @Test
    public void RandomDirecitonUp() {
        //Only one available direction
        ghost.d = Direction.Up;
        ghost.x = 256;
        ghost.y = 384;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(5, rd.getI());
        ghost.d = Direction.Up;
        ghost.x = 320;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(0, rd.getI());
        ghost.d = Direction.Up;
        ghost.x = 384;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(3, rd.getI());
        ghost.d = Direction.Up;
        ghost.x = 320;
        ghost.y = 256;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(1, rd.getI());
        //Two available directions:
        //UP and right
        rd.setRandom(new Random0());
        ghost.x = 384;
        ghost.y = 256;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(4, rd.getI());
        rd.setRandom(new Random1());
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(4, rd.getI());
        //Up and Left
        ghost.d = Direction.Up;
        ghost.x = 256;
        ghost.y = 256;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(6, rd.getI());
        rd.setRandom(new Random0());
        ghost.d = Direction.Up;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(6, rd.getI());
        //Right and Left
        ghost.x = 288;
        ghost.y = 416;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(8, rd.getI());
        rd.setRandom(new Random1());
        ghost.d = Direction.Up;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(8, rd.getI());
        //All directions available
        ghost.x = 416;
        ghost.y = 416;
        ghost.d = Direction.Up;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(9, rd.getI());
        rd.setRandom(new Random2());
        ghost.d = Direction.Up;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(9, rd.getI());
        rd.setRandom(new Random0());
        ghost.d  = Direction.Up;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(9, rd.getI());
    }
    
    @Test
    public void randomDirectionDown(){
        //Only one available direction
        ghost.d = Direction.Down;
        ghost.x = 256;
        ghost.y = 256;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(5, rd.getI());
        ghost.d = Direction.Down;
        ghost.x = 320;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(0, rd.getI());
        ghost.d = Direction.Down;
        ghost.x = 384;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(3, rd.getI());
        ghost.d = Direction.Down;
        ghost.x = 320;
        ghost.y = 384;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(1, rd.getI());
        //Two available directions:
        //Down and right
        rd.setRandom(new Random0());
        ghost.x = 384;
        ghost.y = 384;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(4, rd.getI());
        rd.setRandom(new Random1());
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(4, rd.getI());
        //Down and Left
        ghost.d = Direction.Down;
        ghost.x = 256;
        ghost.y = 384;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(6, rd.getI());
        rd.setRandom(new Random0());
        ghost.d = Direction.Down;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(6, rd.getI());
        //Right and Left
        ghost.x = 288;
        ghost.y = 224;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(8, rd.getI());
        rd.setRandom(new Random1());
        ghost.d = Direction.Down;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(8, rd.getI());
        //All directions available
        ghost.x = 416;
        ghost.y = 416;
        ghost.d = Direction.Down;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(9, rd.getI());
        rd.setRandom(new Random2());
        ghost.d = Direction.Down;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(9, rd.getI());
        rd.setRandom(new Random0());
        ghost.d  = Direction.Down;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(9, rd.getI());
    }
    
    @Test
    public void setDirectionRight(){
        //Only one available direction
        ghost.d = Direction.Right;
        ghost.x = 256;
        ghost.y = 384;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(5, rd.getI());
        ghost.d = Direction.Right;
        ghost.y = 320;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(0, rd.getI());
        ghost.d = Direction.Right;
        ghost.y = 256;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(3, rd.getI());
        ghost.d = Direction.Right;
        ghost.x = 384;
        ghost.y = 320;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(1, rd.getI());
        //Two available directions:
        //UP and right
        rd.setRandom(new Random0());
        ghost.x = 384;
        ghost.y = 256;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(4, rd.getI());
        rd.setRandom(new Random1());
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(4, rd.getI());
        //Right and down
        ghost.d = Direction.Right;
        ghost.x = 384;
        ghost.y = 384;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(6, rd.getI());
        rd.setRandom(new Random0());
        ghost.d = Direction.Right;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(6, rd.getI());
        //Up and Down
        ghost.x = 224;
        ghost.y = 288;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(8, rd.getI());
        rd.setRandom(new Random1());
        ghost.d = Direction.Right;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(8, rd.getI());
        //All directions available
        ghost.x = 416;
        ghost.y = 416;
        ghost.d = Direction.Right;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(9, rd.getI());
        rd.setRandom(new Random2());
        ghost.d = Direction.Right;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(9, rd.getI());
        rd.setRandom(new Random0());
        ghost.d  = Direction.Right;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(9, rd.getI());
    }
    
    @Test
    public void randomDirectionLeft(){
        //Only one available direction
        ghost.d = Direction.Left;
        ghost.x = 384;
        ghost.y = 384;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(5, rd.getI());
        ghost.d = Direction.Left;
        ghost.y = 320;
        rd.randomDirection(ghost);
        assertEquals(Direction.Right, ghost.d);
        assertEquals(0, rd.getI());
        ghost.d = Direction.Left;
        ghost.y = 256;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(3, rd.getI());
        ghost.d = Direction.Left;
        ghost.x = 256;
        ghost.y = 320;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(1, rd.getI());
        //Two available directions:
        //UP and left
        rd.setRandom(new Random0());
        ghost.x = 256;
        ghost.y = 256;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(4, rd.getI());
        rd.setRandom(new Random1());
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(4, rd.getI());
        //Down and left
        ghost.d = Direction.Left;
        ghost.x = 256;
        ghost.y = 384;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(6, rd.getI());
        rd.setRandom(new Random0());
        ghost.d = Direction.Left;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(6, rd.getI());
        //Up and Down
        ghost.x = 416;
        ghost.y = 288;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(8, rd.getI());
        rd.setRandom(new Random1());
        ghost.d = Direction.Left;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(8, rd.getI());
        //All directions available
        ghost.x = 416;
        ghost.y = 416;
        ghost.d = Direction.Left;
        rd.randomDirection(ghost);
        assertEquals(Direction.Up, ghost.d);
        assertEquals(9, rd.getI());
        rd.setRandom(new Random2());
        ghost.d = Direction.Left;
        rd.randomDirection(ghost);
        assertEquals(Direction.Down, ghost.d);
        assertEquals(9, rd.getI());
        rd.setRandom(new Random0());
        ghost.d  = Direction.Left;
        rd.randomDirection(ghost);
        assertEquals(Direction.Left, ghost.d);
        assertEquals(9, rd.getI());
    }

    public int[][] setGraph() {
        int[][] graph = new int[32][22];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 22; j++) {
                graph[i][j] = 1;
            }
        }

        graph[10][9] = 0;
        graph[12][9] = 0;
        graph[9][10] = 0;
        graph[11][10] = 0;
        graph[13][10] = 0;
        graph[10][11] = 0;
        graph[12][11] = 0;
        graph[9][12] = 0;
        graph[11][12] = 0;
        graph[13][12] = 0;
        graph[10][13] = 0;
        graph[12][13] = 0;

        return graph;
    }
}

class Random0 extends Random {

    @Override
    public int nextInt(int boundary) {
        return 0;
    }
}

class Random1 extends Random {

    @Override
    public int nextInt(int boundary) {
        return 1;
    }
}

class Random2 extends Random {

    @Override
    public int nextInt(int boundary) {
        return 2;
    }
}
