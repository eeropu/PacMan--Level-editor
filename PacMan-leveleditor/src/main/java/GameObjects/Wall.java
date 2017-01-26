package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Wall implements GameObject{
    
    private int x, y;

    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(x, y, 32, 32);
    }

    @Override
    public void move() {
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public boolean checkCollision(PacMan pacman) {
        return this.getBounds().intersects(pacman.getBounds());
    }
}
