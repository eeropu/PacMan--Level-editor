package pacman.guilisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 */
public class CreationModeButtonListener implements ActionListener{
    
    private JButton help, ready;
    private WindowHandler wh;
    private String[][] objectPositioning;

    public CreationModeButtonListener(JButton help, JButton ready, WindowHandler wh, String[][] op) {
        this.help = help;
        this.ready = ready;
        this.wh = wh;
        this.objectPositioning = op;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == help){
            wh.help();
        } else if (e.getSource() == ready){
            
            String level = "";
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 30; j++) {
                    level = level + objectPositioning[j][i];
                }
            }
            
            if(!level.contains("P")){
                JOptionPane.showMessageDialog(null, "You need to add PacMan!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(!level.contains("b")){
                JOptionPane.showMessageDialog(null, "There needs to be atleast one pointbubble!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            wh.finishedCreating(level);
        }
    }
    
}
