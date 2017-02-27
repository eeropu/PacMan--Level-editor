package pacman.levelmanagement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import pacman.gameobjects.Coordinate;
import pacman.gameobjects.Ghost;
import pacman.gameobjects.PacMan;
import pacman.gameobjects.Pointbubble;
import pacman.gameobjects.PowerPellet;
import pacman.gameobjects.Wall;

/**
 * Used to check if components collide with pacman.
 *
 * @author eerop
 */
public class CollisionChecker {

    private LevelRunner lr;
    private PacMan pacman;
    private HashSet<Wall> walls;
    private HashSet<Ghost> ghosts;
    private HashSet<Pointbubble> points;
    private HashSet<PowerPellet> pp;
    private HashMap<Integer, HashSet<Integer>> ghostStartingPositions;
    private GameLoop gl;

    /**
     * Constructor for the CollisionChecker-class.
     *
     * @param lr reference to the levelrunner
     * @param gl reference to the gameloop
     * @param pacman current levels pacman
     * @param walls current levels walls
     * @param ghosts current levels ghosts
     * @param points current levels pointbubbles
     * @param pp current levels powerpellets
     * @param ghostStartingPositions current levels ghostStartingPositions
     */
    public CollisionChecker(LevelRunner lr, GameLoop gl, PacMan pacman, HashSet<Wall> walls, HashSet<Ghost> ghosts,
            HashSet<Pointbubble> points, HashSet<PowerPellet> pp, HashMap<Integer, HashSet<Integer>> ghostStartingPositions) {
        this.lr = lr;
        this.gl = gl;
        this.pacman = pacman;
        this.walls = walls;
        this.ghosts = ghosts;
        this.points = points;
        this.pp = pp;
        this.ghostStartingPositions = ghostStartingPositions;
    }

    /**
     * Checks if some of the gameobjects collide with Pacman.
     */
    public void checkCollision() {
        for (Wall wall : walls) {
            if (wall.checkCollision(pacman)) {
                pacman.stop();
            }
        }
        Iterator<Pointbubble> pbit = points.iterator();
        while (pbit.hasNext()) {
            if (pbit.next().checkCollision(pacman)) {
                pbit.remove();
                lr.addScore(10);
            }
        }
        if (points.isEmpty()) {
            lr.completed();
        }
        Iterator<PowerPellet> ppit = pp.iterator();
        while (ppit.hasNext()) {
            if (ppit.next().checkCollision(pacman)) {
                ppit.remove();
                lr.addScore(50);
                for (Ghost ghost : ghosts) {
                    ghost.eatPowerpellet(System.currentTimeMillis());
                }
            }
        }
        for (Ghost ghost : ghosts) {
            for (Wall wall : walls) {
                if (ghost.getBounds().intersects(wall.getBounds())) {
                    ghost.stop();
                }
            }
            if (ghost.checkCollision(pacman)) {
                if (ghost.isPpEaten()) {
                    PriorityQueue<Coordinate> pq = new PriorityQueue();
                    for (Integer i : ghostStartingPositions.keySet()) {
                        for (Integer j : ghostStartingPositions.get(i)) {
                            pq.add(new Coordinate(i, j, 0, pacman.getX(), pacman.getY(), null, true));
                        }
                    }
                    Coordinate farthest = pq.poll();
                    ghost.setX(farthest.getX());
                    ghost.setY(farthest.getY());
                    ghost.setPpEaten(false);
                    lr.addScore(200);
                } else {
                    pacman.reset();
                    for (Ghost g : ghosts) {
                        g.reset();
                    }
                    gl.setRespawn(System.currentTimeMillis());
                    lr.loseLife();
                    lr.setRespawning(true);
                }
            }
        }
    }

}
