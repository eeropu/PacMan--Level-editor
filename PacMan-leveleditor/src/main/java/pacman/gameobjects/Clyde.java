package pacman.gameobjects;

/**
 *
 * @author eerop
 *
 * This class is responsible for managing the Clyde-ghost. Clyde is the Ghost
 * that follows pacman until it gets close enough and then it runs away towards
 * "home".
 */
public class Clyde extends Ghost {

    private int homeX;
    private int homeY;

    /**
     * Constructor that defines the basic x, y and pacman of all ghosts and sets
     * the homecoordinates to (1, 20) by default.
     *
     * @param x starting x position of this ghost.
     * @param y starting y position of this ghost.
     * @param pacman reference to pacman that is used to by the checkCollision
     * method.
     */
    public Clyde(int x, int y, PacMan pacman) {
        super(x, y, pacman);
        homeX = 1;
        homeY = 20;
    }

    /**
     * Used to move this ghost and to set its' direction and movement speed.
     */
    @Override
    public void move() {
        if (ppEaten) {
            eatableTimer(System.currentTimeMillis());
        }
        if (move == 1 && !ppEaten) {
            if (x % 32 == 0 && y % 32 == 0) {
                move = 2;
            }
        }
        if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) > 192) {
            dSetRandomly = false;
            if (x % 32 == 0 && y % 32 == 0) {
                setDirectionAStar(pacman.getX(), pacman.getY(), ppEaten);
            }
        } else {
            dSetRandomly = true;
            if (x % 32 == 0 && y % 32 == 0) {
                setDirectionAStar(homeX * 32 - 32, homeY * 32 - 32, false);
            }
        }

        super.move();
    }

    /**
     *
     * Sets the graph that is used for updating direction of this ghost. Changes
     * the home coordinate if it's not reachable.
     *
     * @param graph
     */
    @Override
    public void setGraph(int[][] graph) {
        this.graph = graph;
        while (graph[homeX][homeY] == 0) {
            if (homeX < 30) {
                homeX++;
            } else {
                homeY--;
                homeX = 0;
            }
        }
    }

    public int getHomeX() {
        return homeX;
    }

    public int getHomeY() {
        return homeY;
    }

    public void setHomeX(int homeX) {
        this.homeX = homeX;
    }

    public void setHomeY(int homeY) {
        this.homeY = homeY;
    }
}
