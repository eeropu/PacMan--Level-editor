package pacman.guiobjects;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import pacman.database.LevelsDAO;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 *
 * Menu that will be shown to the player once he/she has decided to play or
 * modify a level.
 */
public class LevelSelectionMenu extends JPanel {

    private final LevelsDAO ldao;
    private ArrayList<String> levels;
    private final WindowHandler wh;
    private String selection;

    public LevelSelectionMenu(LevelsDAO ldao, WindowHandler wh) {
        this.ldao = ldao;
        this.wh = wh;
    }

    /**
     * Sets the buttons to the levelselection screen.
     *
     * @param s defines what will happen when a button has been clicked.
     */
    public void build(String s) {
        this.selection = s;

        removeAll();

        levels = ldao.getAllLevels();

        int h = 128;
        int w = 320;
        JPanel panel = new JPanel();
        panel.setMinimumSize(new Dimension(960, 640));
        if (levels.size() > 30) {
            h = 64;
            w = 314;
            if (levels.size() % 3 == 0) {
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
        panel2.setLayout(new GridLayout(1, 1));

        JButton back = new JButton("Return");
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                wh.startMenu();
            }
        });

        panel2.add(back);

        int x = 0;
        int y = 0;
        Font font = new Font("Verdana", Font.BOLD, 20);
        for (String level : levels) {

            JButton button = new JButton(level);
            button.setBounds(x * w, y * h, w, h);
            button.setFont(font);

            button.addActionListener((ActionEvent e) -> {
                levelAction(button.getText());
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

    /**
     * Used to start playing, modifying or to delete a level based on the
     * parameter s in the build-method.
     *
     * @param s
     */
    public void levelAction(String s) {
        if (selection.equals("play")) {
            wh.runLevel(s, ldao.getLevel(s));
        } else if (selection.equals("modify")) {
            int dialogresult = JOptionPane.showConfirmDialog(null, "Warning! this will permanently delete all "
                    + "information regarding the chosen level, including highscores and the older "
                    + "version of the level. Continue?",
                    "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (dialogresult == JOptionPane.YES_OPTION) {
                wh.createMode(ldao.getLevel(s));
                ldao.delete(s);
            }
        } else if (selection.equals("delete")) {
            int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete"
                    + "this level and all information regarding it?", "Warning!",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (dialogresult == JOptionPane.YES_OPTION) {
                ldao.delete(s);
                wh.creationmenu();
            }
        }
    }
}
