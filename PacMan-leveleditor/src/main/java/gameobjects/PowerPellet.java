package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PowerPellet implements GameObject{
    
    private int x, y;

    public PowerPellet(int x, int y) {
        this.x = x * 32 - 32;
        this.y = y * 32 - 32;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(x + 6, y + 6, 20, 20);
    }

    @Override
    public void move() {
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 6, y + 6, 20, 20);
    }

    @Override
    public boolean checkCollision(PacMan pacman) {
        return this.getBounds().intersects(pacman.getBounds());
    }
    
    
}
