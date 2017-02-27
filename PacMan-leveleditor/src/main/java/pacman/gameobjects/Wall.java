package pacman.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import pacman.levelmanagement.LevelRunner;

/**
 * The class that manages the wall-objects.
 *
 * @author eerop
 */
public class Wall implements GameObject {

    private int x, y;
    private BufferedImage image;
    private LevelRunner lr;

    /**
     * Constructor for Wall-class.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Wall(int x, int y) {
        this.x = 32 * x - 32;
        this.y = 32 * y - 32;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image, x, y, lr);
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

    public void setImage(BufferedImage img) {
        this.image = img;
    }

    public void setImageObserver(LevelRunner lr) {
        this.lr = lr;
    }

    //Following classes are for test purposes
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }

}
