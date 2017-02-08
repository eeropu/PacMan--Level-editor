package pacman.levelmanagement;

import java.awt.Color;
import java.awt.Font;
import pacman.gameobjects.*;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import javax.swing.JPanel;
import javax.swing.Timer;
import pacman.pacman.leveleditor.WindowHandler;

public class LevelRunner extends JPanel {

    private GameLoop gl;
    protected PacMan pacman;
    private HashSet<Wall> walls;
    public HashSet<Ghost> ghosts;
    private HashSet<Pointbubble> points;
    private HashSet<PowerPellet> pp;
    private HashMap<Integer, HashSet<Integer>> ghostStartingPositions;
    private Timer timer;
    private int score, lives;
    private long now, respawn;
    private WindowHandler wh;

    public LevelRunner(WindowHandler wh) {
        this.wh = wh;
        build();
        ControlSetUp csu = new ControlSetUp(this);
        gl = new GameLoop(this);
        timer = new Timer(15, gl);
        lives = 3;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        pacman.paint(g);
        for (Wall wall : walls) {
            wall.paint(g);
        }
        for (Ghost ghost : ghosts) {
            ghost.paint(g);
        }
        for (Pointbubble point : points) {
            point.paint(g);
        }
        for (PowerPellet pp1 : pp) {
            pp1.paint(g);
        }
        g.setColor(Color.black);
        g.fillRect(0, 640, 960, 32);
        g.setColor(Color.white);
        g.setFont(new Font("Verdana", Font.BOLD, 25));
        g.drawString("Score: " + score, 32, 665);
        g.fillOval(850, 640, 32, 32);
        g.drawString("x " + lives, 900, 665);
        if (now - respawn < 3000) {
            g.setColor(Color.black);
            g.fillRect(85, 275, 795, 120);
            g.setFont(new Font("Verdana", Font.BOLD, 128));
            g.setColor(new Color(180, 0, 0));
            g.drawString("LIFE LOST!", 90, 384);
        }
    }

    public void move() {
        now = System.currentTimeMillis();
        if (now - respawn > 3000) {
            pacman.move();
            for (Ghost ghost : ghosts) {
                ghost.move();
            }
        }
    }

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
                score += 10;
            }
        }
        if(points.isEmpty()){
            completed();
        }
        Iterator<PowerPellet> ppit = pp.iterator();
        while (ppit.hasNext()) {
            if (ppit.next().checkCollision(pacman)) {
                ppit.remove();
                score += 50;
                for (Ghost ghost : ghosts) {
                    ghost.eatPowerpellet();
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
                    score += 200;
                } else {
                    pacman.reset();
                    for (Ghost g : ghosts) {
                        g.reset();
                    }
                    respawn = System.currentTimeMillis();
                    lives--;
                }
            }
        }
    }

    public void start() {
        timer.start();
    }

    public void build() {
        LevelBuilder lb = new LevelBuilder(testi());
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
    
    public void completed(){
        timer.stop();
        wh.lvlCompleted();
    }

    /*
     * Changing the following string modifies the test level
     * P changes the starting position of Pacman. There can be only one PacMan, 
     * therefore it will be postioined to the corresponding coordinate of the
     * last instance of character P.
     * The amount of other objects is unlimited
     * W adds a wall
     * b adds a pointbubble
     * p adds a powerpellet
     * L adds a Blinky (ghost)
     * I adds a Pinky (ghost)
     * C adds a Clyde (ghost)
     * R adds a random ghost
     * Any other character leads to empty coordinate
     * Casing is important
     * "Opening" the border on one side of the screen but not the other will
     * lead to error where PacMan is not controllable (this is on purpose and
     * such positioning of the walls will not be possible in the final version)
     * 
     * ! THE LENGHT OF THE STRING MUST BE EXACTLY 600 CHARACTERS !
     * 
     */
    public String testi() {
        return "WWWWWWWWWWWWWWWWWWWWWWWWWWxWWW"
                + "xPxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                + "WxWWWWWxWWWxWWWWWWxWWWxWWWWWxW"
                + "WxWbbbbxxWxxWbbbbWxxWxxbbbLWxW"
                + "WxWbWWWxxWxxxbWWbxxxWxxWWWbWxW"
                + "xxWbWbbxxWxxWbWWbWxxWxxbbWbWxx"
                + "WxWbWbWxxWxxWbbbbWxxWxxWbWbWxW"
                + "WxxxxxxxxWxxWxWWxWxxWxxxxxxxxW"
                + "WxWWWWWWxxxxxxxxxxxxxxWWWWWWxW"
                + "WLxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxWxWxWxWxWxWxWWxWxWxWxWxWxWxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxWxWxWxWxWxWLWWxWxWxWxWxWxWxW"
                + "WxWxWxWxWxWxWpWWxWxWxWxWxWxWxW"
                + "WxWxxxxxxxxxxxxxxxxxxxxxxxxxLW"
                + "WxWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WWWWWWWWWWWWWWWWWWWWWWWWWWWWxW"
                + "xxxxxxxxxxxxxxxxxxxxxxxxxxxWxx"
                + "WWWWWWWWWWWWWWWWWWWWWWWWWWxWWW";
    }
}
