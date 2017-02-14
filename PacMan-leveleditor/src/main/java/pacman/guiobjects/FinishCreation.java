package pacman.guiobjects;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import pacman.guilisteners.FinishCreationListener;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 */
public class FinishCreation extends JPanel{
    
    private String objectPositioning;
    private JTextPane txtname, txtlives, txttime, pPerSec;
    private Font font;
    private JRadioButton xtrapoints, deadline, fillbubbles;
    private JButton button;
    private WindowHandler wh;

    public FinishCreation(String objectPositioning, WindowHandler wh) {
        this.objectPositioning = objectPositioning;
        this.wh = wh;
        setLayout(null);
        createLabels();
        txtname = new JTextPane();
        txtname.setBounds(608, 35, 320, 26);
        txtname.setFont(new Font("Verdana", Font.BOLD, 15));
        txtlives = new JTextPane();
        txtlives.setBounds(736, 128, 64, 32);
        txtlives.setFont(font);
        txttime = new JTextPane();
        txttime.setBounds(736, 240, 64, 32);
        txttime.setFont(font);
        pPerSec = new JTextPane();
        pPerSec.setBounds(384, 352, 64, 32);
        pPerSec.setFont(font);
        add(txtname);
        add(txtlives);
        add(txttime);
        add(pPerSec);
        
        xtrapoints = new JRadioButton();
        xtrapoints.setBounds(288, 304, 32, 32);
        add(xtrapoints);
        deadline = new JRadioButton();
        deadline.setBounds(655, 304, 32, 32);
        add(deadline);
        fillbubbles = new JRadioButton();
        fillbubbles.setBounds(544, 448, 32, 32);
        add(fillbubbles);
        
        button = new JButton("FINISH!");
        button.setFont(new Font("Verdana", Font.BOLD, 35));
        button.setBounds(320, 544, 320, 96);
        add(button);
        
        FinishCreationListener fcl = new FinishCreationListener(txtname, txtlives, txttime, pPerSec,
                xtrapoints, deadline, fillbubbles, button, wh, objectPositioning);
        button.addActionListener(fcl);
    }
    
    public void createLabels(){
        font = new Font("Verdana", Font.BOLD, 20);
        JLabel name = new JLabel("Give your level a name! (MAX 30 characters)");
        name.setFont(font);
        name.setBounds(32, 32, 512, 32);
        add(name);
        JLabel lives = new JLabel("Enter the numer of lives when starting this level (0 - 9):");
        lives.setFont(font);
        lives.setBounds(32, 128, 704, 32);
        add(lives);
        JLabel time = new JLabel("<html><p>Enter the time frame (seconds: 0 - 300) <br> in which this level needs to be completed to:</p><html>");
        time.setFont(font);
        time.setBounds(32, 224, 704, 64);
        add(time);
        JLabel time2 = new JLabel("Gain extra points: ");
        time2.setFont(font);
        time2.setBounds(64, 304, 224, 32);
        add(time2);
        JLabel time3 = new JLabel("Complete the level: ");
        time3.setFont(font);
        time3.setBounds(416, 304, 224, 32);
        add(time3);
        JLabel pointPerSec = new JLabel("Points / second (1 - 100):");
        pointPerSec.setFont(font);
        pointPerSec.setBounds(64, 352, 320, 32);
        add(pointPerSec);
        JLabel fill = new JLabel("Fill empty coordinates with pointbubbles:");
        fill.setFont(font);
        fill.setBounds(32, 448, 544, 32);
        add(fill);
    }
    
    
}
