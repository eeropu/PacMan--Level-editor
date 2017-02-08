package pacman.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

public class PacMan implements GameObject {

    private int x, y, origX, origY, move;
    private Direction current, changed;

    public PacMan(int x, int y, Direction d) {
        this.x = 32 * x - 32;
        this.y = 32 * y - 32;
        this.origX = this.x;
        this.origY = this.y;
        this.current = d;
        this.changed = d;
        this.move = 2;
    }

    @Override
    public void paint(Graphics g) {
        g.fillOval(x, y, 32, 32);
    }

    @Override
    public void move() {
        if (this.current == Direction.Right) {
            x += move;
        } else if (this.current == Direction.Left) {
            x -= move;
        } else if (this.current == Direction.Down) {
            y += move;
        } else if (this.current == Direction.Up) {
            y -= move;
        }
        outOfScreen();
        changeDirection();
    }

    public void outOfScreen() {
        if (x > 960) {
            x = -32;
        } else if (x < -32) {
            x = 960;
        }
        if (y > 640) {
            y = -32;
        } else if (y < -32) {
            y = 640;
        }
    }

    public void changeDirection() {
        if (x >= 0 && x <= 928 && y >= 0 && y <= 608) {
            if (x % 32 == 0 && y % 32 == 0) {
                current = changed;
            }
        }
    }

    public void right() {
        this.changed = Direction.Right;
    }

    public void left() {
        this.changed = Direction.Left;
    }

    public void down() {
        this.changed = Direction.Down;
    }

    public void up() {
        this.changed = Direction.Up;
    }

    public void stop() {
        if (current == Direction.Right) {
            x -= move;
        } else if (current == Direction.Left) {
            x += move;
        } else if (current == Direction.Down) {
            y -= move;
        } else if (current == Direction.Up) {
            y += move;
        }
        this.current = Direction.Stop;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public boolean checkCollision(PacMan pacman) {
        return false;
    }
    
    public void reset(){
        x = origX;
        y = origY;
    }

    //Following classes are for test purposes
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMove() {
        return move;
    }

    public Direction getCurrent() {
        return current;
    }

    public Direction getChanged() {
        return changed;
    }
}
