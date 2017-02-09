package pacman.guilisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import pacman.pacman.leveleditor.WindowHandler;

public class StartMenuListener implements ActionListener{
    
    private JButton play, create, highscores;
    private WindowHandler wh;

    public StartMenuListener(JButton play, JButton create, JButton highscores, WindowHandler wh) {
        this.play = play;
        this.create = create;
        this.highscores = highscores;
        this.wh = wh;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == play){
            wh.lvlslctmenu();
        } else if (e.getSource() == create){
            
        } else if (e.getSource() == highscores){
            
        }
    }
    
    
}
