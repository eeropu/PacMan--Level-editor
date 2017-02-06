package pacman.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface GameObject {

    public void paint(Graphics g);

    public void move();

    public Rectangle getBounds();

    public boolean checkCollision(PacMan pacman);
}
