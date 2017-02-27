package pacman.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Interface that is implemented by all in-game objects.
 *
 * @author eerop
 */
public interface GameObject {

    /**
     * Used to paint the gameobject.
     *
     * @param g graphics used by JPanel
     */
    public void paint(Graphics g);

    /**
     * Used to move the gameobject.
     */
    public void move();

    /**
     * Returns a rectangle that represents the area this object occupies.
     *
     * @return rectangle-object
     */
    public Rectangle getBounds();

    /**
     * Checs if this object hits the PacMan.
     *
     * @param pacman reference to current levels pacman
     * @return true if pacman hits this object, else false.
     */
    public boolean checkCollision(PacMan pacman);
}
