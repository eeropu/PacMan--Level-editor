package gameobjects;

import java.awt.Color;
import java.awt.Graphics;

/*
 * This ghost moves randomly until it finds PacMan. After that it will follow pacman for 10 seconds
 */
public class RandomGhost extends Ghost {

    private long timer, now;
    private boolean chasePacMan;

    public RandomGhost(int x, int y, PacMan pacman) {
        super(x, y, pacman);
        chasePacMan = false;
    }

    @Override
    public void move() {
        if (chasePacMan) {
            super.move();
            now = System.currentTimeMillis();
            if (now - timer >= 10000) {
                chasePacMan = false;
                timer = System.currentTimeMillis();
            }
        } else {
            if (x % 32 == 0 && y % 32 == 0) {
                randomDirection();
            }
            if (d == Direction.Right) {
                x += move;
            } else if (d == Direction.Left) {
                x -= move;
            } else if (d == Direction.Down) {
                y += move;
            } else if (d == Direction.Up) {
                y -= move;
            }
            now = System.currentTimeMillis();
        }
        if (Math.sqrt(Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2)) < 160) {
            if (now - timer >= 10000) {
                chasePacMan = true;
                timer = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 32, 32);
    }
}
