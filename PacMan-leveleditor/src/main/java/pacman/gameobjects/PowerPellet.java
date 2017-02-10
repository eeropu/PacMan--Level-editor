package pacman.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author eerop
 *
 * This class contains the functions of the Powerpellets. Powerpellets can be
 * collected by PacMan. After one is collected PacMan will be able to eat the
 * ghosts for a short period of time.
 */
public class PowerPellet implements GameObject {

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

    /*
     * Following classes are for test purposes
     */
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
