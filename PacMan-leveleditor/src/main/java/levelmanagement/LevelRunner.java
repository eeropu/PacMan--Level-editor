package levelmanagement;

import gameobjects.Ghost;
import gameobjects.PacMan;
import gameobjects.Pointbubble;
import gameobjects.PowerPellet;
import gameobjects.Wall;
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
        timer = new Timer(10, gl);
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
        while(pbit.hasNext()){
            if(pbit.next().checkCollision(pacman)){
                pbit.remove();
            }
        }
        Iterator<PowerPellet> ppit = pp.iterator();
        while(ppit.hasNext()){
            if(ppit.next().checkCollision(pacman)){
                ppit.remove();
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

    public String testi() {
        return "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW"
                + "xPxxxxxxxxxxxxxxxxxxxWxxxxxWBx"
                + "WxWWWxxxxxxxxxxxxxxWxxxWxWxxbW"
                + "WxxWxWWWxWxWWWWWWxWWWWxWxWWWWW"
                + "WWxWxWbbbWxxxxxxWxxxxWxWxxxxbW"
                + "xxxxxxbWbWWWWWWxWWWWxWxWWWWWbx"
                + "WxWWWWbbbWxxxxxxWxxxxWxWxxxxbW"
                + "WxxxxWWWWWxWWWWWWxWWWWxWxWWWWW"
                + "WWWWxxxxxxxxxxxxxxxxxxxxxxxxbW"
                + "WxxxxWWWWWxWWWWWWWWWWWWWWWWWbW"
                + "WxWWWWxxxxxxxxxxxxxxxxxxxxxxbW"
                + "WxWxxxxxxxxxxxxxxxxxxxxxxxxxbW"
                + "WxWxxxxxxxxxxxxxxxxxxxxxxxxxbW"
                + "WxWxxxxxxxxxxpxxxxxxxxxxxxxxbW"
                + "WxWxxxxxWWxWWWWWxxxxxxxxxxxxbW"
                + "WxWxxxxxWxxxxxxxxxxxxxxxxxxxbW"
                + "WxxxxxxxWxxxxxxxxxxxxxxxxxxxbW"
                + "WxWWWxWWWWWxWWWxWWWWWxWWWxWWbW"
                + "WxxxxxxxxxxxxxxxxxxxxxxxxxxxbW"
                + "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW";
    }
}
