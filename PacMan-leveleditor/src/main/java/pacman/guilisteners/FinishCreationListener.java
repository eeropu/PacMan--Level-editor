package pacman.guilisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import pacman.database.LevelsDAO;
import pacman.pacman.leveleditor.WindowHandler;

/**
 * Used to give actions to the buttons in the FinishCreation -screen.
 *
 * @author eerop
 */
public class FinishCreationListener implements ActionListener {

    private JTextPane txtname, txtlives, txttime, pPerSec;
    private JRadioButton xtrapoints, deadline, fillbubbles;
    private JButton finished;
    private WindowHandler wh;
    private boolean ok;
    private String level;
    private LevelsDAO ldao;

    /**
     * Constructor for the FinishCreationListener-class.
     *
     * @param txtname user inputs name of the level
     * @param txtlives user inputs the amount of lives for the level
     * @param txttime user inputs the timeframe in which the level is supposed
     * to be completed
     * @param pPerSec user inputs the amount of points player gets for
     * compliting the level in time
     * @param xtrapoints Radiobutton used to enable extra points for every
     * second faster than the given timeframe when compliting the level
     * @param deadline Radiobutton used to enable the absolute timeframe in
     * which the level must be completed or the level will be failed
     * @param fillbubbles Radiobutton used to fill all empty spaces in the level
     * with pointbubbles
     * @param finished Button that directs to the startmenu and saves the
     * created level if all inputs are acceptable
     * @param wh windowhandler used to change the content on the screen
     * @param level String format of the objectpositioning for the level
     */
    public FinishCreationListener(JTextPane txtname, JTextPane txtlives, JTextPane txttime,
            JTextPane pPerSec, JRadioButton xtrapoints, JRadioButton deadline,
            JRadioButton fillbubbles, JButton finished, WindowHandler wh, String level) {

        this.wh = wh;
        this.txtname = txtname;
        this.txtlives = txtlives;
        this.txttime = txttime;
        this.pPerSec = pPerSec;
        this.xtrapoints = xtrapoints;
        this.deadline = deadline;
        this.fillbubbles = fillbubbles;
        this.finished = finished;
        this.ok = true;

        this.level = level;
        this.ldao = new LevelsDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == finished) {
            if (txtname.getText().length() < 1 || txtname.getText().length() > 30) {
                ok = false;
                JOptionPane.showMessageDialog(null, "Wrong input in the name-field!");
            }
            try {
                int lives = Integer.parseInt(txtlives.getText());
                if (lives > 9 || lives < 0) {
                    ok = false;
                    JOptionPane.showMessageDialog(null, "Wrong input in the amount of lives!");
                }
            } catch (NumberFormatException ex) {
                ok = false;
                JOptionPane.showMessageDialog(null, "Wrong input in the amount of lives!");
            }
            if (xtrapoints.isSelected() || deadline.isSelected()) {
                try {
                    int time = Integer.parseInt(txttime.getText());
                    if (time > 300 || time < 0) {
                        ok = false;
                        JOptionPane.showMessageDialog(null, "Wrong input in the timesetting!");
                    }
                } catch (NumberFormatException ex) {
                    ok = false;
                    JOptionPane.showMessageDialog(null, "Wrong input in the timesetting!");
                }
            }
            if (xtrapoints.isSelected()) {
                try {
                    int pps = Integer.parseInt(pPerSec.getText());
                    if (pps < 1 || pps > 100) {
                        ok = false;
                        JOptionPane.showMessageDialog(null, "Wrong input in points per second!");
                    }
                } catch (NumberFormatException ex) {
                    ok = false;
                    JOptionPane.showMessageDialog(null, "Wrong input in points per second!");
                }
            }

            if (ok) {
                finish();
            }
            ok = true;
        }
    }

    /**
     * Finishes levels creation and saves it to the database if all inputs are
     * valid.
     */
    public void finish() {
        level = level + txtlives.getText();
        if (xtrapoints.isSelected() || deadline.isSelected()) {

            if (txttime.getText().length() == 1) {
                level = level + 1;
            } else if (txttime.getText().length() == 2) {
                level = level + 2;
            } else if (txttime.getText().length() == 3) {
                level = level + 3;
            }
            level = level + txttime.getText();

            if (deadline.isSelected()) {
                level = level + 1;
            } else {
                level = level + 0;
            }

            if (xtrapoints.isSelected()) {
                level = level + 1;
                level = level + pPerSec.getText();
            } else {
                level = level + 0;
            }

        } else {
            level = level + 0;
        }

        if (fillbubbles.isSelected()) {
            for (int i = 0; i < level.length(); i++) {
                level = level.replace('x', 'b');
            }
        }
        String check = ldao.add(txtname.getText(), level);
        switch (check) {
            case "double":
                JOptionPane.showMessageDialog(null, "There already exists a level with the given name!",
                        "Warning", JOptionPane.ERROR_MESSAGE);
                break;
            case "error":
                JOptionPane.showMessageDialog(null, "Something went wrong, please try again",
                        "Warning", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                wh.startMenu();
                break;
        }
    }

}
