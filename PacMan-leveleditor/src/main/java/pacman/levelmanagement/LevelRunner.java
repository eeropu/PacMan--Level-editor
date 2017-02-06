package pacman.levelmanagement;

import pacman.gameobjects.Ghost;
import pacman.gameobjects.PacMan;
import pacman.gameobjects.Pointbubble;
import pacman.gameobjects.PowerPellet;
import pacman.gameobjects.Wall;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LevelRunner extends JPanel {

    private GameLoop gl;
    protected PacMan pacman;
    private HashSet<Wall> walls;
    public HashSet<Ghost> ghosts;
    private HashSet<Pointbubble> points;
    private HashSet<PowerPellet> pp;
    private Timer timer;

    public LevelRunner() {
        build();
        ControlSetUp csu = new ControlSetUp(this);
        gl = new GameLoop(this);
        timer = new Timer(15, gl);
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
    }

    public void move() {
        pacman.move();
        for (Ghost ghost : ghosts) {
            ghost.move();
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
            }
        }
        Iterator<PowerPellet> ppit = pp.iterator();
        while (ppit.hasNext()) {
            if (ppit.next().checkCollision(pacman)) {
                ppit.remove();
            }
        }
        for (Ghost ghost : ghosts) {
            for (Wall wall : walls) {
                if (ghost.getBounds().intersects(wall.getBounds())) {
                    ghost.stop();
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
        return "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
                + "xPxxxxxxxxxxxxxxxxxxxxxxxxxxLx"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxIxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxRxxxxxxxxxxxCxxxxxxxW"
                + "WxxxxxxxxxxxxpxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxxW"
                + "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW";
    }
}
