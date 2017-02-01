package gameobjects;

import java.awt.Color;
import java.awt.Graphics;

/*
 * The ghost that follows pacman until he gets too close and then turns "home"
 */

public class Clyde extends Ghost {
    
    private int homeX;
    private int homeY;

    public Clyde(int x, int y, PacMan pacman) {
        super(x, y, pacman);
        homeX = 1;
        homeY = 20;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 32, 32);
    }
    
    @Override
    public void move(){
        if(Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) > 160){
            super.move();
        } else {
            if(x % 32 == 0 && y % 32 == 0) {
                setDirectionAStar(homeX * 32 - 32, homeY * 32 - 32);
            }
            super.move();
        }
    }
    
    @Override
    public void setGraph(int[][] graph){
        this.graph = graph;
        int i = 0;
        while(graph[homeX][homeY] == 0){
            if(i % 2 == 0){
                homeX++;
            } else {
                homeY--;
            }
            i = i + 1;
        }
    }

    public int getHomeX() {
        return homeX;
    }

    public int getHomeY() {
        return homeY;
    }
}
