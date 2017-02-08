package pacman.gameobjects;

import java.awt.Color;
import java.awt.Graphics;

/*
 * The ghost that tries to position itself in front of pacman
 */
public class Pinky extends Ghost {

    public Pinky(int x, int y, PacMan pacman, boolean randomghost) {
        super(x, y, pacman, randomghost);
    }

    @Override
    public void move() {
        eatableTimer();
        if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) <= 160) {
            if (x % 32 == 0 && y % 32 == 0) {
                setDirectionAStar(pacman.getX(), pacman.getY(), ppEaten);
            }
        } else if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) < 320) {
            if (x % 32 == 0 && y % 32 == 0) {
                if (ppEaten) {
                    setDirectionAStar(pacman.getX(), pacman.getY(), true);
                } else {
                    if (pacman.getCurrent() == Direction.Right) {
                        setDirectionAStar(pacman.getX() + 128, pacman.getY(), false);
                    } else if (pacman.getCurrent() == Direction.Left) {
                        setDirectionAStar(pacman.getX() - 128, pacman.getY(), false);
                    } else if (pacman.getCurrent() == Direction.Down) {
                        setDirectionAStar(pacman.getX(), pacman.getY() + 128, false);
                    } else if (pacman.getCurrent() == Direction.Up) {
                        setDirectionAStar(pacman.getX(), pacman.getY() - 128, false);
                    }
                }
            }
        } else {
            if (x % 32 == 0 && y % 32 == 0) {
                randir.randomDirection(this);
            }
        }
        super.move();
    }

    @Override
    public void paint(Graphics g) {
        if (ppEaten) {
            g.setColor(Color.blue);
            g.fillRect(x, y, 32, 32);
        } else {
            if (randomghost) {
                g.setColor(Color.green);
                g.fillRect(x, y, 32, 32);
            } else {
                g.setColor(Color.pink);
                g.fillRect(x, y, 32, 32);
            }
        }
    }
}
