package pacman.levelmanagement;

import java.awt.image.BufferedImage;
import pacman.gameobjects.*;
import java.util.HashSet;
import java.util.Random;
import pacman.pacman.leveleditor.ImageGetter;

/**
 * This class is used by the Levelrunner to build the Level according to the
 * information retrieved from the database.
 *
 * @author eerop
 */
public class LevelBuilder {

    private LevelRunner lr;
    private String objectpositioning;
    private HashSet<Wall> walls;
    private HashSet<Ghost> ghosts;
    private HashSet<Pointbubble> points;
    private HashSet<PowerPellet> pp;
    private PacMan pacman;
    private Random random;
    private ImageGetter imgGetter;
    private BufferedImage wall, pacman1, pacman2, pink, blink, clyd, randomghost,
            eatableghost, pbimg, ppimg;

    /**
     * Constructor for the LevelBuilder-class.
     *
     * @param lr reference to LevelRunner
     * @param objectpositioning String representation of the objectpositioning
     * for the current level
     */
    public LevelBuilder(LevelRunner lr, String objectpositioning) {
        this.lr = lr;
        this.objectpositioning = objectpositioning;
        ghosts = new HashSet<>();
        walls = new HashSet<>();
        points = new HashSet<>();
        pp = new HashSet<>();
        random = new Random();
        this.imgGetter = new ImageGetter();
        prepareImages();
    }

    /**
     * Builds the level by adding objects with correct coordinates to the
     * object-sets based on the objectPositioning-string.
     */
    public void build() {
        int[][] graph = new int[32][22];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                int p = i * 30 + j;
                char c = objectpositioning.charAt(p);
                if (c == 'W') {
                    Wall w = new Wall(j + 1, i + 1);
                    w.setImage(wall);
                    w.setImageObserver(lr);
                    walls.add(w);
                } else {
                    graph[j + 1][i + 1] = 1;
                }
                if (c == 'P') {
                    pacman = new PacMan(j + 1, i + 1, Direction.Right);
                    pacman.setImages(pacman1, pacman2);
                    pacman.setImageObserver(lr);
                } else if (c == 'p') {
                    PowerPellet power = new PowerPellet(j + 1, i + 1);
                    power.setImage(ppimg);
                    power.setImageObserver(lr);
                    pp.add(power);
                } else if (c == 'b') {
                    Pointbubble point = new Pointbubble(j + 1, i + 1);
                    point.setImage(pbimg);
                    point.setImageObserver(lr);
                    points.add(point);
                }
            }
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                int p = i * 30 + j;
                char c = objectpositioning.charAt(p);
                if (c == 'L') {
                    Blinky blinky = new Blinky(j + 1, i + 1, pacman);
                    blinky.setImages(blink, eatableghost);
                    blinky.setImageObserver(lr);
                    blinky.setGraph(graph);
                    ghosts.add(blinky);
                } else if (c == 'I') {
                    Pinky pinky = new Pinky(j + 1, i + 1, pacman);
                    pinky.setImages(pink, eatableghost);
                    pinky.setImageObserver(lr);
                    pinky.setGraph(graph);
                    ghosts.add(pinky);
                } else if (c == 'C') {
                    Clyde clyde = new Clyde(j + 1, i + 1, pacman);
                    clyde.setImages(clyd, eatableghost);
                    clyde.setImageObserver(lr);
                    clyde.setGraph(graph);
                    ghosts.add(clyde);
                } else if (c == 'R') {
                    int r = random.nextInt(3);
                    if (r == 0) {
                        Blinky blinky = new Blinky(j + 1, i + 1, pacman);
                        blinky.setImages(randomghost, eatableghost);
                        blinky.setImageObserver(lr);
                        blinky.setGraph(graph);
                        ghosts.add(blinky);
                    } else if (r == 1) {
                        Pinky pinky = new Pinky(j + 1, i + 1, pacman);
                        pinky.setImages(randomghost, eatableghost);
                        pinky.setImageObserver(lr);
                        pinky.setGraph(graph);
                        ghosts.add(pinky);
                    } else {
                        Clyde clyde = new Clyde(j + 1, i + 1, pacman);
                        clyde.setImages(randomghost, eatableghost);
                        clyde.setImageObserver(lr);
                        clyde.setGraph(graph);
                        ghosts.add(clyde);
                    }
                }
            }
        }
        int i = Integer.parseInt("" + objectpositioning.charAt(600));
        lr.setLives(i);
        if (objectpositioning.charAt(601) != '0') {
            if (objectpositioning.charAt(601) == '1') {
                i = 1;
            } else if (objectpositioning.charAt(601) == '2') {
                i = 2;
            } else if (objectpositioning.charAt(601) == '3') {
                i = 3;
            }
            lr.setTime(Integer.parseInt(objectpositioning.substring(602, 602 + i)));
            if (objectpositioning.charAt(602 + i) == '1') {
                lr.setDeadline(true);
            }
            if (objectpositioning.charAt(603 + i) == '1') {
                lr.setPointspersec(Integer.parseInt(objectpositioning.substring(604 + i)));
            }
        }
    }

    public HashSet<Ghost> getGhosts() {
        return ghosts;
    }

    public HashSet<Wall> getWalls() {
        return walls;
    }

    public PacMan getPacman() {
        return pacman;
    }

    public HashSet<Pointbubble> getPoints() {
        return points;
    }

    public HashSet<PowerPellet> getPp() {
        return pp;
    }

    private void prepareImages() {
        this.wall = imgGetter.getSubImage(1);
        this.pacman1 = imgGetter.getSubImage(2);
        this.pacman2 = imgGetter.getSubImage(3);
        this.pink = imgGetter.getSubImage(4);
        this.blink = imgGetter.getSubImage(5);
        this.clyd = imgGetter.getSubImage(6);
        this.randomghost = imgGetter.getSubImage(7);
        this.eatableghost = imgGetter.getSubImage(8);
        this.pbimg = imgGetter.getSubImage(9);
        this.ppimg = imgGetter.getSubImage(10);
    }
}
