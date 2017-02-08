package pacman.pacman.leveleditor;

import pacman.guilisteners.StartMenuListener;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartMenu extends JPanel {

    private JButton create, play, highscores;
    private WindowHandler wh;

    public StartMenu(WindowHandler wh) {
        this.wh = wh;
        
        setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 1));
        panel1.setBounds(0, 400, 400, 272);

        play = new JButton("PLAY");
        create = new JButton("CREATE");
        highscores = new JButton("HIGHSCORES");
        Font font = new Font("Verdana", Font.BOLD, 30);
        play.setFont(font);
        create.setFont(font);
        highscores.setFont(font);
        StartMenuListener sml = new StartMenuListener(play, create, highscores, wh);
        play.addActionListener(sml);
        
        panel1.add(play);
        panel1.add(create);
        panel1.add(highscores);
        
        this.add(panel1);
    }

}
