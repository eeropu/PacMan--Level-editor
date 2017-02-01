package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.PriorityQueue;

public class Ghost implements GameObject {

    protected int x, y, move;
    protected Direction d;
    protected PacMan pacman;
    protected int[][] graph;

    public Ghost(int x, int y, PacMan pacman) {
        this.x = x * 32 - 32;
        this.y = y * 32 - 32;
        this.move = 2;
        this.pacman = pacman;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 32, 32);
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
        if (x == 960) {
            x = -32;
        } else if (x == -32) {
            x = 960;
        } else if (y == 640) {
            y = -32;
        } else if (y == -32) {
            y = 640;
        } else if (x % 32 == 0 && y % 32 == 0) {
            setDirectionAStar(pacman.getX(), pacman.getY());
        }
    }
    
    /*
    * A*-algorithm
    */
    protected void setDirectionAStar(int searchX, int searchY) {
        int[][] map = new int[graph.length][];
        for (int i = 0; i < graph.length; i++) {
            map[i] = new int[map.length];
            System.arraycopy(graph[i], 0, map[i], 0, graph[i].length);
        }
        PriorityQueue<Coordinate> priorityq = new PriorityQueue<>();
        Coordinate current = new Coordinate(x, y, 0, searchX, searchY, null);
        priorityq.add(current);
        while (!priorityq.isEmpty()) {
            current = priorityq.poll();
            if (current.getHeuristic() < 32) {
                break;
            } //right from current
            if (map[(current.getX() + 64) / 32][(current.getY() + 32) / 32] == 1) {
                priorityq.add(new Coordinate(current.getX() + 32, current.getY(),
                        current.getDistance() + 32, searchX, searchY, current));

                map[(current.getX() + 64) / 32][(current.getY() + 32) / 32] = 0;
            } //left from current
            if (map[(current.getX()) / 32][(current.getY() + 32) / 32] == 1) {
                priorityq.add(new Coordinate(current.getX() - 32, current.getY(),
                        current.getDistance() + 32, searchX, searchY, current));

                map[(current.getX()) / 32][(current.getY() + 32) / 32] = 0;
            } //down from current
            if (map[(current.getX() + 32) / 32][(current.getY() + 64) / 32] == 1) {
                priorityq.add(new Coordinate(current.getX(), current.getY() + 32,
                        current.getDistance() + 32, searchX, searchY, current));

                map[(current.getX() + 32) / 32][(current.getY() + 64) / 32] = 0;
            } //up from current
            if (map[(current.getX() + 32) / 32][(current.getY()) / 32] == 1) {
                priorityq.add(new Coordinate(current.getX(), current.getY() - 32,
                        current.getDistance() + 32, searchX, searchY, current));

                map[(current.getX() + 32) / 32][(current.getY()) / 32] = 0;
            }
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

}
