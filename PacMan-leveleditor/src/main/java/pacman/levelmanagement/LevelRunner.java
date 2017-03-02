package pacman.levelmanagement;

import java.awt.Color;
import java.awt.Font;
import pacman.gameobjects.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
    private BufferedImage img;
    private HashSet<Wall> walls;
    public HashSet<Ghost> ghosts;
    private HashSet<Pointbubble> points;
    private HashSet<PowerPellet> pp;
    private HashMap<Integer, HashSet<Integer>> ghostStartingPositions;
    private Timer loop, timer;
    private boolean respawning, deadline;
    private int score, lives, time, pointspersec;
    private String name;
    private WindowHandler wh;

    /**
     * Constructor for the LevelRunner-class.
     *
     * @param wh windowhandler that is used to change the contents on the screen
     * @param name name of the level
     * @param level String presentation of the objectpositioning for the current
     * level
     */
    public LevelRunner(WindowHandler wh, String name, String level) {
        this.wh = wh;
        this.name = name;
        this.deadline = false;
        this.time = 0;
        this.pointspersec = 0;
        build(level);
        ControlSetUp csu = new ControlSetUp(this);
        this.gl = new GameLoop(this, pacman, walls, ghosts, points, pp, ghostStartingPositions);
        this.cc = new CollisionChecker(this, gl, pacman, walls, ghosts, points, pp, ghostStartingPositions);
        gl.setCc(cc);
        loop = new Timer(15, gl);
        setBackground(Color.black);
        respawning = false;
        img = pacman.getMouthClosed();
        this.setTImer();
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
        g.drawImage(img, 850, 640, this);
        g.drawString("x " + lives, 900, 665);
        if (respawning) {
            g.setColor(Color.black);
            g.fillRect(85, 275, 795, 120);
            g.setFont(new Font("Verdana", Font.BOLD, 128));
            g.setColor(new Color(180, 0, 0));
            g.drawString("LIFE LOST!", 90, 384);
        }
        if (lives < 0) {
            failed();
        }
        if (deadline) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.white);
        }
        if (time > 0) {
            g.setFont(new Font("Verdana", Font.BOLD, 25));
            if (time >= 300) {
                g.drawString("5:", 448, 665);
                g.drawString("" + (time % 60), 475, 665);
            } else if (time >= 240) {
                g.drawString("4:", 448, 665);
                g.drawString("" + (time % 60), 475, 665);
            } else if (time >= 180) {
                g.drawString("3:", 448, 665);
                g.drawString("" + (time % 60), 475, 665);
            } else if (time >= 120) {
                g.drawString("2:", 448, 665);
                g.drawString("" + (time % 60), 475, 665);
            } else if (time >= 60) {
                g.drawString("1:", 448, 665);
                g.drawString("" + (time % 60), 475, 665);
            } else {
                g.drawString("0:", 448, 665);
                g.drawString("" + (time % 60), 475, 665);
            }
        }
    }

    /**
     * This method is used to add points to the score-counter.
     *
     * @param s amount of points to be added
     */
    public void addScore(int s) {
        this.score += s;
    }

    /**
     * used to decrease lifecount by one.
     */
    public void loseLife() {
        this.lives--;
    }

    public void setRespawning(boolean respawning) {
        this.respawning = respawning;
    }

    /**
     * Starts the gameloop.
     */
    public void start() {
        loop.start();
        timer.start();
    }

    /**
     * This method builds the level using the LevelBuilder-class.
     *
     * @param s the String presentation of the objectpositioning for the current
     * level
     */
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

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setPointspersec(int pointspersec) {
        this.pointspersec = pointspersec;
    }

    public void setDeadline(boolean deadline) {
        this.deadline = deadline;
    }

    /**
     * Creates a timer that is used to display the time left in the level.
     */
    public void setTImer() {
        this.timer = new Timer(1000, (ActionEvent e) -> {
            time = time - 1;
            System.out.println(time);
            System.out.println(loop.isRunning());
            if (deadline && time <= 0) {
                failed();
            }
        });
    }

    /**
     * Stops the gameloop and directs to the LevelCompleted-screen.
     */
    public void completed() {
        loop.stop();
        timer.stop();
        int i = 0;
        if (time > 0) {
            i = time * pointspersec;
        }
        i += lives * 1000;
        wh.lvlCompleted(name, score + i);
    }

    /**
     * Stops the gameloop and directs to the LevelFailed-screen.
     */
    public void failed() {
        loop.stop();
        timer.stop();
        wh.lvlFailed();
    }
}
