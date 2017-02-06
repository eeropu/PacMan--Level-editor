package pacman.levelmanagement;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;
import javax.swing.KeyStroke;

/*
 * ControlSetUp enables controlling pacman
 */
public final class ControlSetUp {

    private final LevelRunner lr;

    public ControlSetUp(LevelRunner lr) {
        this.lr = lr;
        right();
        left();
        down();
        up();
    }

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
