package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pointbubble implements GameObject{
    
    private int x, y;

    public Pointbubble(int x, int y) {
        this.x = x * 32 - 32;
        this.y = y * 32 - 32;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(x + 12, y + 12, 8, 8);
    }

    @Override
    public void move() {
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 12, y + 12 , 8, 8);
    }

    @Override
    public boolean checkCollision(PacMan pacman) {
        return this.getBounds().intersects(pacman.getBounds());
    }
}
