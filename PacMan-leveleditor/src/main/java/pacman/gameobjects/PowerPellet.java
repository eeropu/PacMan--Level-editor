package pacman.gameobjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import pacman.levelmanagement.LevelRunner;

/**
 * This class contains the functions of the Powerpellets. Powerpellets can be
 * collected by PacMan. After one is collected PacMan will be able to eat the
 * ghosts for a short period of time.
 *
 * @author eerop
 */
public class PowerPellet implements GameObject {

    private int x, y;
    private BufferedImage image;
    private LevelRunner lr;
    private AffineTransform aT;
    private double rotation;

    /**
     * Costructor for the PowerPellet-class.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public PowerPellet(int x, int y) {
        this.x = x * 32 - 32;
        this.y = y * 32 - 32;
        this.aT = new AffineTransform();
        this.rotation = 0;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        aT.rotate(0, 0, 0);
        g2d.rotate(rotation, x + 16, y + 16);
        g2d.drawImage(image, x, y, lr);
        g2d.setTransform(aT);
    }

    @Override
    public void move() {
        rotation += 0.1;
        if (rotation >= Math.PI * 2) {
            rotation = 0;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 6, y + 6, 20, 20);
    }

    @Override
    public boolean checkCollision(PacMan pacman) {
        return this.getBounds().intersects(pacman.getBounds());
    }

    public void setImage(BufferedImage img) {
        this.image = img;
    }

    public void setImageObserver(LevelRunner lr) {
        this.lr = lr;
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

    public BufferedImage getImage() {
        return image;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

}
