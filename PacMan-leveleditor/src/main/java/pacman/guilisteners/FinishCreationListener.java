package pacman.guilisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 */
public class FinishCreationListener implements ActionListener {

    private JTextPane txtname, txtlives, txttime, pPerSec;
    private JRadioButton xtrapoints, deadline, fillbubbles;
    private JButton button;
    private WindowHandler wh;
    private boolean ok;

    public FinishCreationListener(JTextPane txtname, JTextPane txtlives, JTextPane txttime,
            JTextPane pPerSec, JRadioButton xtrapoints, JRadioButton deadline,
            JRadioButton fillbubbles, JButton button, WindowHandler wh) {

        this.wh = wh;
        this.txtname = txtname;
        this.txtlives = txtlives;
        this.txttime = txttime;
        this.pPerSec = pPerSec;
        this.xtrapoints = xtrapoints;
        this.deadline = deadline;
        this.fillbubbles = fillbubbles;
        this.button = button;
        this.ok = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
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
                    if(time > 300 || time < 0){
                        ok = false;
                        JOptionPane.showMessageDialog(null, "Wrong input in the timesetting!");
                    }
                } catch (NumberFormatException ex) {
                    ok = false;
                    JOptionPane.showMessageDialog(null, "Wrong input in the timesetting!");
                }
            }
            if(xtrapoints.isSelected()){
                try{
                    int pps = Integer.parseInt(pPerSec.getText());
                    if(pps < 1 || pps > 100){
                        ok = false;
                        JOptionPane.showMessageDialog(null, "Wrong input in points per second!");
                    }
                } catch (NumberFormatException ex) {
                    ok = false;
                    JOptionPane.showMessageDialog(null, "Wrong input in points per second!");
                }
            }
        }
    }

}
