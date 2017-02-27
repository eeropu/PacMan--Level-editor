package pacman.levelmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import pacman.gameobjects.Ghost;
import pacman.gameobjects.PacMan;
import pacman.gameobjects.Pointbubble;
import pacman.gameobjects.PowerPellet;
import pacman.gameobjects.Wall;

/**
 * This class is used by the levelrunner to control the updating of gameobjects.
 *
 * @author eerop
 */
public class GameLoop implements ActionListener {

    private LevelRunner lr;
    private PacMan pacman;
    private HashSet<Wall> walls;
    private HashSet<Ghost> ghosts;
    private HashSet<Pointbubble> points;
    private HashSet<PowerPellet> pp;
    private HashMap<Integer, HashSet<Integer>> ghostStartingPositions;
    private long now, respawn;
    private CollisionChecker cc;

    /**
     * Constructor for the GameLoop-class.
     *
     * @param lr reference to LevelRunner
     * @param pacman current levels pacman
     * @param walls current levels walls
     * @param ghosts current levels ghosts
     * @param points current levels pointbubbles
     * @param pp current levels powerpellets
     * @param ghostStartingPositions current levels ghost starting positions
     */
    public GameLoop(LevelRunner lr, PacMan pacman, HashSet<Wall> walls, HashSet<Ghost> ghosts, HashSet<Pointbubble> points,
            HashSet<PowerPellet> pp, HashMap<Integer, HashSet<Integer>> ghostStartingPositions) {

        this.lr = lr;
        this.pacman = pacman;
        this.walls = walls;
        this.ghosts = ghosts;
        this.points = points;
        this.pp = pp;
        this.ghostStartingPositions = ghostStartingPositions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        cc.checkCollision();
        lr.repaint();
    }

    /**
     * Moves the gameobjects.
     */
    public void move() {
        now = System.currentTimeMillis();
        if (now - respawn > 3000) {
            lr.setRespawning(false);
            pacman.move();
            for (Ghost ghost : ghosts) {
                ghost.move();
            }
        }
        for (PowerPellet pp1 : pp) {
            pp1.move();
        }
    }

    public void setRespawn(long respawn) {
        this.respawn = respawn;
    }

    public void setCc(CollisionChecker cc) {
        this.cc = cc;
    }

}
