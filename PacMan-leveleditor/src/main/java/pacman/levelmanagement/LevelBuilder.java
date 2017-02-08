package pacman.levelmanagement;

import pacman.gameobjects.*;
import java.util.HashSet;
import java.util.Random;

public class LevelBuilder {

    private String objectpositioning;
    private HashSet<Wall> walls;
    private HashSet<Ghost> ghosts;
    private HashSet<Pointbubble> points;
    private HashSet<PowerPellet> pp;
    private PacMan pacman;
    private Random random;

    public LevelBuilder(String objectpositioning) {
        this.objectpositioning = objectpositioning;
        ghosts = new HashSet<>();
        walls = new HashSet<>();
        points = new HashSet<>();
        pp = new HashSet<>();
        random = new Random();
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
                    Blinky blinky = new Blinky(j + 1, i + 1, pacman, false);
                    blinky.setGraph(graph);
                    ghosts.add(blinky);
                } else if (c == 'I') {
                    Pinky pinky = new Pinky(j + 1, i + 1, pacman, false);
                    pinky.setGraph(graph);
                    ghosts.add(pinky);
                } else if (c == 'C') {
                    Clyde clyde = new Clyde(j + 1, i + 1, pacman, false);
                    clyde.setGraph(graph);
                    ghosts.add(clyde);
                } else if (c == 'R') {
                    int r = random.nextInt(3);
                    if(r == 0){
                        Blinky blinky = new Blinky(j+1, i+1, pacman, true);
                        blinky.setGraph(graph);
                        ghosts.add(blinky);
                    } else if(r == 1){
                        Pinky pinky = new Pinky(j+1, i+1, pacman, true);
                        pinky.setGraph(graph);
                        ghosts.add(pinky);
                    } else {
                        Clyde clyde = new Clyde(j+1, i+1, pacman, true);
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
}
