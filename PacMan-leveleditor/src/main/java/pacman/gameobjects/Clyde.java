package pacman.gameobjects;

import java.awt.Color;
import java.awt.Graphics;

/*
 * The ghost that follows pacman until he gets too close and then turns "home"
 */
public class Clyde extends Ghost {

    private int homeX;
    private int homeY;

    public Clyde(int x, int y, PacMan pacman, boolean randomghost) {
        super(x, y, pacman, randomghost);
        homeX = 1;
        homeY = 20;
    }

    @Override
    public void move() {
        eatableTimer();
        if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) < 320 && ppEaten) {
            if (x % 32 == 0 && y % 32 == 0) {
                setDirectionAStar(pacman.getX(), pacman.getY(), true);
            }
        } else {
            if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) > 160) {
                if (x % 32 == 0 && y % 32 == 0) {
                    setDirectionAStar(pacman.getX(), pacman.getY(), false);
                }
            } else {
                if (x % 32 == 0 && y % 32 == 0) {
                    setDirectionAStar(homeX * 32 - 32, homeY * 32 - 32, false);
                }
            }
        }
        super.move();
    }

    @Override
    public void paint(Graphics g) {
        if (ppEaten) {
            g.setColor(Color.blue);
            g.fillRect(x, y, 32, 32);
        } else {
            if (randomghost) {
                g.setColor(Color.green);
                g.fillRect(x, y, 32, 32);
            } else {
                g.setColor(Color.ORANGE);
                g.fillRect(x, y, 32, 32);
            }
        }
    }

    @Override
    public void setGraph(int[][] graph) {
        this.graph = graph;
        while (graph[homeX][homeY] == 0) {
            if (homeX < 30) {
                homeX++;
            } else {
                homeY--;
                homeX = 0;
            }
        }
    }

    public int getHomeX() {
        return homeX;
    }

    public int getHomeY() {
        return homeY;
    }
}