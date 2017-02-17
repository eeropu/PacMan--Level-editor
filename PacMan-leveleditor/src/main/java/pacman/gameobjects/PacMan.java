package pacman.gameobjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import pacman.levelmanagement.LevelRunner;

/**
 *
 * @author eerop
 *
 * This class is responsible for PacMans operations.
 */
public class PacMan implements GameObject {

    private int x, y, origX, origY, move;
    private Direction current, changed;
    private double rotationCurrent, rotationChanged;
    private AffineTransform AT;
    private BufferedImage drawable, mouthClosed, mouthOpen;
    private LevelRunner lr;

    public PacMan(int x, int y, Direction d) {
        this.x = 32 * x - 32;
        this.y = 32 * y - 32;
        this.origX = this.x;
        this.origY = this.y;
        this.rotationCurrent = 0;
        this.rotationChanged = 0;
        this.current = d;
        this.changed = d;
        this.move = 2;
        this.AT = new AffineTransform();
        
        Timer timer = new Timer(200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeImg();
            }
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AT.rotate(0, 0, 0);
        g2d.rotate(rotationCurrent, x + 16, y + 16);
        g2d.drawImage(drawable, x, y, lr);
        g2d.setTransform(AT);
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
                rotationCurrent = rotationChanged;
            }
        }
    }

    public void right() {
        this.changed = Direction.Right;
        this.rotationChanged = 0;
    }

    public void left() {
        this.changed = Direction.Left;
        this.rotationChanged = Math.PI;
    }

    public void down() {
        this.changed = Direction.Down;
        this.rotationChanged = Math.PI/2;
    }

    public void up() {
        this.changed = Direction.Up;
        this.rotationChanged = - Math.PI/2;
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

    public void reset() {
        x = origX;
        y = origY;
    }
    
    public void setImages(BufferedImage img, BufferedImage img2){
        this.drawable = img;
        this.mouthOpen = img;
        this.mouthClosed = img2;
    }
    
    public void setImageObserver(LevelRunner lr){
        this.lr = lr;
    }
    
    public void changeImg(){
        if(drawable == mouthOpen){
            drawable = mouthClosed;
        } else {
            drawable = mouthOpen;
        }
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

    public BufferedImage getDrawable() {
        return drawable;
    }

    public BufferedImage getMouthClosed() {
        return mouthClosed;
    }

    public BufferedImage getMouthOpen() {
        return mouthOpen;
    }
}
