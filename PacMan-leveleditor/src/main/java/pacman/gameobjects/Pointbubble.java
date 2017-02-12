package pacman.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import pacman.levelmanagement.LevelRunner;

/**
 *
 * @author eerop
 *
 * This class contains the functions for the Pointbubbles. PointBubbles are
 * collectables that PacMan needs to collect to gain points and finally complete
 * a level.
 */
public class Pointbubble implements GameObject {

    private int x, y;
    private BufferedImage image;
    private LevelRunner lr;

    public Pointbubble(int x, int y) {
        this.x = x * 32 - 32;
        this.y = y * 32 - 32;
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
        return new Rectangle(x + 12, y + 12, 8, 8);
    }

    @Override
    public boolean checkCollision(PacMan pacman) {
        return this.getBounds().intersects(pacman.getBounds());
    }
    
    public void setImage(BufferedImage img){
        this.image = img;
    }
    
    public void setImageObserver(LevelRunner lr){
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
}
