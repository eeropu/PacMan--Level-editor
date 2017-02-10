package pacman.levelmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author eerop
 *
 * This class is used by the levelrunner to control the updating of gameobjects.
 */
public class GameLoop implements ActionListener {

    private LevelRunner lr;

    public GameLoop(LevelRunner lr) {
        this.lr = lr;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lr.move();
        lr.checkCollision();
        lr.repaint();
    }

}
