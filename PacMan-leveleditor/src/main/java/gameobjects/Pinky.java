package gameobjects;

import java.awt.Color;
import java.awt.Graphics;

/*
 * The ghost that tries to position itself in front of pacman
 */
public class Pinky extends Ghost {

    public Pinky(int x, int y, PacMan pacman) {
        super(x, y, pacman);
    }

    @Override
    public void move() {
        if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) <= 160) {
            if (x % 32 == 0 && y % 32 == 0) {
                setDirectionAStar(pacman.getX(), pacman.getY());
            }
        } else if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) < 320) {
            if (x % 32 == 0 && y % 32 == 0) {
                if (pacman.getCurrent() == Direction.Right) {
                    setDirectionAStar(pacman.getX() + 128, pacman.getY());
                } else if (pacman.getCurrent() == Direction.Left) {
                    setDirectionAStar(pacman.getX() - 128, pacman.getY());
                } else if (pacman.getCurrent() == Direction.Down) {
                    setDirectionAStar(pacman.getX(), pacman.getY() + 128);
                } else if (pacman.getCurrent() == Direction.Up) {
                    setDirectionAStar(pacman.getX(), pacman.getY() - 128);
                }
            }
        } else {
            if (x % 32 == 0 && y % 32 == 0) {
                randomDirection();
            }
        }
        super.move();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(x, y, 32, 32);
    }
}
