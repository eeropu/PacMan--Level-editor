package pacman.guiobjects;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import pacman.database.LevelsDAO;
import pacman.pacman.leveleditor.WindowHandler;

public class LevelSelectionMenu extends JPanel {

    private LevelsDAO ldao;
    private ArrayList<String> levels;
    private WindowHandler wh;

    public LevelSelectionMenu(LevelsDAO ldao, WindowHandler wh) {
        this.ldao = ldao;
        this.wh = wh;
    }
    
    public void build(){
        removeAll();
        
        levels = ldao.getAllLevels();

        int h = 128;
        int w = 320;
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(960, 640));
        if (levels.size() > 30){
            h = 64;
            w = 314;
            if(levels.size() % 3 == 0){
                panel.setPreferredSize(new Dimension(942, levels.size() / 3 * 64));
            } else {
                panel.setPreferredSize(new Dimension(942, levels.size() / 3 * 64 + 64));
            }
        } else if (levels.size() > 15) {
            h = 64;
        }
        panel.setLayout(null);

        JPanel panel2 = new JPanel();
        panel2.setMaximumSize(new Dimension(960, 32));
        panel2.add(new JButton("test"));

        int x = 0;
        int y = 0;
        Font font = new Font("Verdana", Font.BOLD, 20);
        for (String level : levels) {
            
            JButton button = new JButton(level);
            button.setBounds(x * w, y * h, w, h);
            button.setFont(font);
            
            button.addActionListener((ActionEvent e) -> {
                startLevel(button.getText());
            });
            
            panel.add(button);
            if (x == 2) {
                x = 0;
                y++;
            } else {
                x++;
            }
        }

        setLayout(new BoxLayout(this, WIDTH));

        JScrollPane jsp = new JScrollPane(panel);

        this.add(jsp);
        this.add(panel2);
    }

    public void startLevel(String s) {
        wh.runLevel(ldao.getLevel(s));
    }

}
