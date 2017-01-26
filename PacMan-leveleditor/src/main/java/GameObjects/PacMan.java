package GameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

public class PacMan implements GameObject {

    private int x, y, move;
    private Direction d;

    public PacMan(int x, int y, Direction d) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.move = 2;
    }

    @Override
    public void paint(Graphics g) {
        g.fillOval(x, y, 32, 32);
    }

    @Override
    public void move() {
        if (this.d == Direction.Right) {
            x += move;
        } else if (this.d == Direction.Left) {
            x -= move;
        } else if (this.d == Direction.Down) {
            y += move;
        } else if (this.d == Direction.Up) {
            y -= move;
        }
        if(x > 960){
            x = -32;
        } else if (x < -32){
            x = 960;
        }
        if(y > 640){
            y = -32;
        } else if (y < -32){
            y = 640;
        }
    }

    public void right() {
        this.d = Direction.Right;
    }

    public void left() {
        this.d = Direction.Left;
    }

    public void down() {
        this.d = Direction.Down;
    }

    public void up() {
        this.d = Direction.Up;
    }
    
    public void stop(){
        if(d == Direction.Right){
            x -= move;
        } else if(d == Direction.Left){
            x += move;
        } else if(d == Direction.Down){
            y -= move;
        } else if(d == Direction.Up){
            y += move;
        }
        this.d = Direction.Stop;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    @Override
    public boolean checkCollision(PacMan pacman) {
        return false;
    }
}
