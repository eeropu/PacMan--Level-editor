package pacman.levelmanagement;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.KeyStroke;

/**
 * This class is used by Levelrunner and it enables controlling of PacMan.
 *
 * @author eerop
 */
public final class ControlSetUp {

    private final LevelRunner lr;

    /**
     * Constructor for the ControlSetUp-class.
     *
     * @param lr reference to LevelRunner
     */
    public ControlSetUp(LevelRunner lr) {
        this.lr = lr;
        right();
        left();
        down();
        up();
    }

    /**
     * Sets the action for the right arrow-key.
     */
    public void right() {
        Action right = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lr.pacman.right();
            }
        };

        lr.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "right");
        lr.getActionMap().put("right", right);
    }

    /**
     * Sets the action for the left arrow-key.
     */
    public void left() {
        Action left = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lr.pacman.left();
            }
        };

        lr.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "left");
        lr.getActionMap().put("left", left);
    }

    /**
     * Sets the action for the down arrow-key.
     */
    public void down() {
        Action down = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lr.pacman.down();
            }
        };

        lr.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "down");
        lr.getActionMap().put("down", down);
    }

    /**
     * Sets the action for the up arrow-key.
     */
    public void up() {
        Action up = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                lr.pacman.up();
            }
        };

        lr.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "up");
        lr.getActionMap().put("up", up);
    }
}
