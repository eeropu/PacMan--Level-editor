package pacman.guilisteners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JTextPane;
import pacman.database.HighscoresDAO;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 *
 * Gives functionality to the components in the "Level complete" -screen.
 */
public class LevelCompleteListener implements ActionListener, MouseListener {

    private WindowHandler wh;
    private HighscoresDAO hsdao;
    private JButton submit, skip;
    private JTextPane txt;
    private int score;
    private String level;

    public LevelCompleteListener(WindowHandler wh, HighscoresDAO hsdao) {
        this.hsdao = hsdao;
        this.wh = wh;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (txt.getText().equals("")) {
                txt.setText("Name");
                txt.setForeground(Color.gray);
            } else if (txt.getText().equals("Name")) {
                txt.setForeground(Color.red);
            } else {
                hsdao.add(txt.getText(), score, level);
                wh.startMenu();
            }
        } else if (e.getSource() == skip) {
            wh.startMenu();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == txt) {
            if (txt.getText().equals("Name")) {
                txt.setForeground(Color.white);
                txt.setText("");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }

    public void setSkip(JButton skip) {
        this.skip = skip;
    }

    public void setTxt(JTextPane txt) {
        this.txt = txt;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public void setlevel(String level){
        this.level = level;
    }

}
