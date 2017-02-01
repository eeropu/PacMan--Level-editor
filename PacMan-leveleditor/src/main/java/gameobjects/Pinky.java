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
        try {
            if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) < 160
                    || (pacman.getCurrent() == Direction.Right
                    && graph[(pacman.getX() + 128) / 32][(pacman.getY() + 32) / 32] != 1)) {
                super.move();
            } else if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) < 160
                    || (pacman.getCurrent() == Direction.Left
                    && graph[(pacman.getX() - 64) / 32][(pacman.getY() + 32) / 32] != 1)) {
                super.move();
            } else if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) < 160
                    || (pacman.getCurrent() == Direction.Down
                    && graph[(pacman.getX() + 32) / 32][(pacman.getY() + 128) / 32] != 1)) {
                super.move();
            } else if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) < 160
                    || (pacman.getCurrent() == Direction.Up
                    && graph[(pacman.getX() + 32) / 32][(pacman.getY() - 64) / 32] != 1)) {
                super.move();
            } else {
                if (d == Direction.Right) {
                    x += move;
                } else if (d == Direction.Left) {
                    x -= move;
                } else if (d == Direction.Down) {
                    y += move;
                } else if (d == Direction.Up) {
                    y -= move;
                }
                if (x == 960) {
                    x = -32;
                } else if (x == -32) {
                    x = 960;
                } else if (y == 640) {
                    y = -32;
                } else if (y == -32) {
                    y = 640;
                } else if (x % 32 == 0 && y % 32 == 0) {
                    if (pacman.getCurrent() == Direction.Right) {
                        setDirectionAStar(pacman.getX() + 96, pacman.getY());
                    } else if (pacman.getCurrent() == Direction.Left) {
                        setDirectionAStar(pacman.getX() - 96, pacman.getY());
                    } else if (pacman.getCurrent() == Direction.Down) {
                        setDirectionAStar(pacman.getX(), pacman.getY() + 96);
                    } else if (pacman.getCurrent() == Direction.Up) {
                        setDirectionAStar(pacman.getX(), pacman.getY() - 96);
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            super.move();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect(x, y, 32, 32);
    }
}
