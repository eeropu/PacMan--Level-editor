package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ghost implements GameObject {

    int x, y;

    public Ghost(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.red);
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
        return getBounds().intersects(pacman.getBounds());
    }

}
