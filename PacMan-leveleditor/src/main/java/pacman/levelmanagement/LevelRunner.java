package pacman.levelmanagement;

import java.awt.Color;
import java.awt.Font;
import pacman.gameobjects.*;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.JPanel;
import javax.swing.Timer;
import pacman.pacman.leveleditor.WindowHandler;

/**
 * This class is responsible for painting the gameobjects and Keeping track of
 * the lives and scores, it also directs to correct screen based on compliting
 * or failing the level.
 *
 * @author eerop
 *
 */
public class LevelRunner extends JPanel {

    private GameLoop gl;
    private CollisionChecker cc;
    protected PacMan pacman;
    private HashSet<Wall> walls;
    public HashSet<Ghost> ghosts;
    private HashSet<Pointbubble> points;
    private HashSet<PowerPellet> pp;
    private HashMap<Integer, HashSet<Integer>> ghostStartingPositions;
    private Timer timer;
    private boolean respawning;
    private int score, lives;
    private String name;
    private WindowHandler wh;

    public LevelRunner(WindowHandler wh, String name, String level) {
        this.wh = wh;
        build(level);
        ControlSetUp csu = new ControlSetUp(this);
        this.gl = new GameLoop(this, pacman, walls, ghosts, points, pp, ghostStartingPositions);
        this.cc = new CollisionChecker(this, gl, pacman, walls, ghosts, points, pp, ghostStartingPositions);
        gl.setCc(cc);
        timer = new Timer(15, gl);
        setBackground(Color.black);
        lives = 3;
        respawning = false;
        this.name = name;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pacman.paint(g);
        for (Wall wall : walls) {
            wall.paint(g);
        }
        for (Pointbubble point : points) {
            point.paint(g);
        }
        for (PowerPellet pp1 : pp) {
            pp1.paint(g);
        }
        for (Ghost ghost : ghosts) {
            ghost.paint(g);
        }
        g.setColor(Color.black);
        g.fillRect(0, 640, 960, 32);
        g.setColor(Color.white);
        g.setFont(new Font("Verdana", Font.BOLD, 25));
        g.drawString("Score: " + score, 32, 665);
        g.fillOval(850, 640, 32, 32);
        g.drawString("x " + lives, 900, 665);
        if (respawning) {
            g.setColor(Color.black);
            g.fillRect(85, 275, 795, 120);
            g.setFont(new Font("Verdana", Font.BOLD, 128));
            g.setColor(new Color(180, 0, 0));
            g.drawString("LIFE LOST!", 90, 384);
        }
        if (lives < 0) {
            timer.stop();
            wh.lvlFailed();
        }
    }

    public void addScore(int s) {
        this.score += s;
    }

    public void loseLife() {
        this.lives--;
    }

    public void setRespawning(boolean respawning) {
        this.respawning = respawning;
    }

    public void start() {
        timer.start();
    }

    public void build(String s) {
        LevelBuilder lb = new LevelBuilder(this, s);
        lb.build();
        pacman = lb.getPacman();
        walls = lb.getWalls();
        ghosts = lb.getGhosts();
        points = lb.getPoints();
        pp = lb.getPp();
        ghostStartingPositions = new HashMap<>();
        for (Ghost ghost : ghosts) {
            if (ghostStartingPositions.containsKey(ghost.getX())) {
                ghostStartingPositions.get(ghost.getX()).add(ghost.getY());
            } else {
                ghostStartingPositions.put(ghost.getX(), new HashSet<>());
                ghostStartingPositions.get(ghost.getX()).add(ghost.getY());
            }
        }
    }

    public void completed() {
        timer.stop();
        wh.lvlCompleted(name, score);
    }

    public void failed() {
        timer.stop();
        wh.lvlFailed();
    }
}
