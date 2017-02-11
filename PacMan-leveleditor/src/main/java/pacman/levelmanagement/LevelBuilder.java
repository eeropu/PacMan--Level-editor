package pacman.levelmanagement;

import java.awt.image.BufferedImage;
import pacman.gameobjects.*;
import java.util.HashSet;
import java.util.Random;
import pacman.pacman.leveleditor.ImageGetter;

/**
 *
 * @author eerop
 *
 * This class is used by the Levelrunner to build the Level according to the
 * information retrieved from the database.
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

    public void build() {
        int[][] graph = new int[32][22];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 30; j++) {
                int p = i * 30 + j;
                char c = objectpositioning.charAt(p);
                if (c == 'W') {
                    walls.add(new Wall(j + 1, i + 1));
                } else {
                    graph[j + 1][i + 1] = 1;
                }
                if (c == 'P') {
                    pacman = new PacMan(j + 1, i + 1, Direction.Right);
                    pacman.setImages(pacman1, pacman2);
                    pacman.setImageObserver(lr);
                } else if (c == 'p') {
                    pp.add(new PowerPellet(j + 1, i + 1));
                } else if (c == 'b') {
                    points.add(new Pointbubble(j + 1, i + 1));
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
    
    public void prepareImages(){
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
