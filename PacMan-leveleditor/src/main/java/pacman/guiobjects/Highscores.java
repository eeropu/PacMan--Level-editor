package pacman.guiobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import pacman.database.HighscoresDAO;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 */
public class Highscores extends JPanel {

    private WindowHandler wh;
    private String level;
    private HighscoresDAO hsdao;

    public Highscores(WindowHandler wh, String s) {
        this.wh = wh;
        level = s;
        hsdao = new HighscoresDAO();
        setLayout(null);
        header();
        setScores();
        setReturn();
    }

    private void header() {
        JLabel header = new JLabel(level);
        header.setOpaque(true);
        header.setFont(new Font("Verdana", Font.BOLD, 40));
        header.setHorizontalAlignment((int)CENTER_ALIGNMENT);
        header.setBackground(Color.black);
        header.setForeground(new Color(87, 0, 127));
        header.setBounds(0, 0, 960, 96);
        add(header);
    }

    private void setScores() {
        String[][] s = hsdao.getScores(level);
        JPanel panel = new JPanel(new GridLayout(1, 4));
        panel.setBounds(0, 96, 60, 512);

        String[] i = new String[s[0].length];
        for (int j = 0; j < s[0].length; j++) {
            i[j] = "" + (j + 1);
        }
        
        Color c = new Color(87, 0, 127);
        Font f = new Font("Verdana", Font.BOLD, 12);
        JList<String> position = new JList(i);
        position.setForeground(Color.black);
        position.setBackground(c);
        position.setFont(f);
        JList<String> name = new JList(s[0]);
        name.setForeground(Color.black);
        name.setBackground(c);
        name.setFont(f);
        JList<String> score = new JList(s[1]);
        score.setForeground(Color.black);
        score.setBackground(c);
        score.setFont(f);
        JList<String> date = new JList<>(s[2]);
        date.setForeground(Color.black);
        date.setBackground(c);
        date.setFont(f);
        
        panel.add(position);
        panel.add(name);
        panel.add(score);
        panel.add(date);
        
        JScrollPane jsp = new JScrollPane(panel);
        
        JPanel help = new JPanel();
        help.setLayout(new BoxLayout(help, WIDTH));
        help.setBounds(0, 96, 960, 544);
        help.add(jsp);
        
        add(help);
    }
    
    private void setReturn(){
        JButton r = new JButton("Return");
        r.setBounds(0, 640, 960, 32);
        r.setForeground(new Color(87, 0, 127));
        r.setBackground(Color.black);
        r.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                wh.startMenu();
            }
        });
        add(r);
    }

}
