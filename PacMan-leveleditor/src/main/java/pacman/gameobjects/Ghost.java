package pacman.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.PriorityQueue;
import pacman.levelmanagement.LevelRunner;

/**
 *
 * @author eerop
 *
 * The parent class of all ghosts that contains the needed methods for the
 * ghosts' functions. e.g. getting the ghosts direction with a* -search
 * algorithm.
 */
public class Ghost implements GameObject {

    protected int x, origX, y, origY, move, eatable;
    protected Direction d;
    protected PacMan pacman;
    protected int[][] graph;
    protected RandomDirection randir;
    protected boolean randomghost, ppEaten;
    protected long timer, now;
    protected boolean dSetRandomly;  //This variable is for testpurposes
    protected BufferedImage img, ppImg;
    protected LevelRunner lr;

    public Ghost(int x, int y, PacMan pacman) {
        this.x = x * 32 - 32;
        this.y = y * 32 - 32;
        this.origX = this.x; 
        this.origY = this.y;
        this.move = 2;
        this.d = Direction.Up;
        this.pacman = pacman;
        this.randir = new RandomDirection();
        this.ppEaten = false;
        this.eatable = 10000;
    }

    @Override
    public void paint(Graphics g) {
        if(ppEaten){
            g.drawImage(ppImg, x, y, lr);
        } else {
            g.drawImage(img, x, y, lr);
        }
    }

    @Override
    public void move() {
        if (d == Direction.Right) {
            x += move;
        } else if (d == Direction.Left) {
            x -= move;
        } else if (d == Direction.Down) {
            y += move;
        } else if (d == Direction.Up) {
            y -= move;
        }
    }

    protected void setDirectionAStar(int searchX, int searchY, boolean runAway) {
        int[][] map = new int[graph.length][];
        for (int i = 0; i < graph.length; i++) {
            map[i] = new int[map.length];
            System.arraycopy(graph[i], 0, map[i], 0, graph[i].length);
        }
        PriorityQueue<Coordinate> priorityq = new PriorityQueue<>();
        Coordinate current = new Coordinate(x, y, 0, searchX, searchY, null, runAway);
        priorityq.add(current);
        while (!priorityq.isEmpty()) {
            current = priorityq.poll();
            if (current.getHeuristic() < 32) {
                break;
            } //right from current
            try {
                if (map[(current.getX() + 64) / 32][(current.getY() + 32) / 32] == 1) {
                    priorityq.add(new Coordinate(current.getX() + 32, current.getY(),
                            current.getDistance() + 32, searchX, searchY, current, runAway));

                    map[(current.getX() + 64) / 32][(current.getY() + 32) / 32] = 0;
                } //left from current
                if (map[(current.getX()) / 32][(current.getY() + 32) / 32] == 1) {
                    priorityq.add(new Coordinate(current.getX() - 32, current.getY(),
                            current.getDistance() + 32, searchX, searchY, current, runAway));

                    map[(current.getX()) / 32][(current.getY() + 32) / 32] = 0;
                } //down from current
                if (map[(current.getX() + 32) / 32][(current.getY() + 64) / 32] == 1) {
                    priorityq.add(new Coordinate(current.getX(), current.getY() + 32,
                            current.getDistance() + 32, searchX, searchY, current, runAway));

                    map[(current.getX() + 32) / 32][(current.getY() + 64) / 32] = 0;
                } //up from current
                if (map[(current.getX() + 32) / 32][(current.getY()) / 32] == 1) {
                    priorityq.add(new Coordinate(current.getX(), current.getY() - 32,
                            current.getDistance() + 32, searchX, searchY, current, runAway));

                    map[(current.getX() + 32) / 32][(current.getY()) / 32] = 0;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        }
        if (priorityq.isEmpty()) {
            randir.randomDirection(this);
            return;
        }
        while (true) {
            if (current.getPrevious() == null) {
                break;
            }
            if (current.getPrevious().getPrevious() == null) {
                if (current.getX() < current.getPrevious().getX()) {
                    this.d = Direction.Left;
                } else if (current.getX() > current.getPrevious().getX()) {
                    this.d = Direction.Right;
                } else if (current.getY() < current.getPrevious().getY()) {
                    this.d = Direction.Up;
                } else if (current.getY() > current.getPrevious().getY()) {
                    this.d = Direction.Down;
                }
                break;
            }
            current = current.getPrevious();
        }

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public boolean checkCollision(PacMan pacman) {
        return getBounds().intersects(pacman.getBounds());
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }

    public void stop() {
        if (d == Direction.Right) {
            x -= move;
        } else if (d == Direction.Left) {
            x += move;
        } else if (d == Direction.Down) {
            y -= move;
        } else if (d == Direction.Up) {
            y += move;
        }
        d = Direction.Stop;
    }

    public void eatPowerpellet(long ln) {
        ppEaten = true;
        timer = ln;
        move = 1;
    }

    public void eatableTimer(long now) {
        this.now = now;
        if (now - timer > eatable) {
            ppEaten = false;
            if (x % 32 == 0 && y % 32 == 0) {
                move = 2;
            }
        }
    }

    public long timeLeft() {
        return now - timer;
    }

    public boolean isPpEaten() {
        return ppEaten;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPpEaten(boolean ppEaten) {
        this.ppEaten = ppEaten;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void reset() {
        x = origX;
        y = origY;
    }
    
    public void setImages(BufferedImage img, BufferedImage ppImg){
        this.img = img;
        this.ppImg = ppImg;
    }
    
    public void setImageObserver(LevelRunner lr){
        this.lr = lr;
    }
}
