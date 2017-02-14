package pacman.guiobjects;

import pacman.guilisteners.StartMenuListener;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import pacman.pacman.leveleditor.ImageGetter;
import pacman.pacman.leveleditor.WindowHandler;

/**
 *
 * @author eerop
 *
 * Menu that will be shown to the user when the program starts. From here user
 * can go to Levelselectionmenu and play a level, go to creation- mode to make
 * new levels or modify existing levels or view the highscores of the levels.
 */
public class StartMenu extends JPanel {

    private JButton create, play, highscores;
    private ImageGetter imgGetter;
    private BufferedImage[] images;
    private BufferedImage image, pacmanRunaway, chasingGhost, ghostRunaway, chaisingPacman;
    private WindowHandler wh;
    private int pacmanrunawayX, chasingGhostX, chaisingpacmanX, ghostrunawayX;
    private Timer timer;

    public StartMenu(WindowHandler wh, ImageGetter imgGetter) {
        this.wh = wh;
        this.imgGetter = imgGetter;
        this.images = new BufferedImage[32];
        this.pacmanRunaway = imgGetter.getImage("Pictures/Startmenu/pacman-runaway.png");
        this.chasingGhost = imgGetter.getImage("Pictures/Startmenu/chaising-ghost.png");
        this.chaisingPacman = imgGetter.getImage("Pictures/Startmenu/chaising-pacman.png");
        this.ghostRunaway = imgGetter.getImage("Pictures/Startmenu/ghost-runaway.png");
        this.pacmanrunawayX = -128;
        this.chasingGhostX = -384;
        this.chaisingpacmanX = 1216;
        this.ghostrunawayX = 960;
        setImages();

        timer = new Timer(20, new StartMenuAnimation(this, images));
        setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 1));
        panel1.setBounds(0, 416, 416, 256);

        play = new JButton("PLAY");
        create = new JButton("CREATE");
        highscores = new JButton("HIGHSCORES");
        Font font = new Font("Verdana", Font.BOLD, 30);
        play.setFont(font);
        create.setFont(font);
        highscores.setFont(font);
        StartMenuListener sml = new StartMenuListener(play, create, highscores, wh);
        play.addActionListener(sml);
        create.addActionListener(sml);

        panel1.add(play);
        panel1.add(create);
        panel1.add(highscores);

        this.add(panel1);
        timer.start();
    }

    public void setImages() {
        for (int i = 1; i <= 16; i++) {
            String s = "Pictures/Startmenu/PacMan_Animation" + i + ".png";
            images[i - 1] = imgGetter.getImage(s);
        }
        for (int i = 0; i < 16; i++) {
            int j = 16 - i;
            String s = "Pictures/Startmenu/PacMan_Animation" + j + ".png";
            images[i + 16] = imgGetter.getImage(s);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
        g.drawImage(pacmanRunaway, pacmanrunawayX, 96, this);
        g.drawImage(chasingGhost, chasingGhostX, 96, this);
        g.drawImage(chaisingPacman, chaisingpacmanX, 96, this);
        g.drawImage(ghostRunaway, ghostrunawayX, 96, this);
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setDelay(int delay) {
        timer.setDelay(delay);
    }

    public void pacmanrunaway() {
        this.pacmanrunawayX += 2;
        this.chasingGhostX += 2;
    }
    
    public void resetPacmanrun(){
        this.pacmanrunawayX = -128;
        this.chasingGhostX = -384;
    }
    
    public void pacmanchase(){
        this.chaisingpacmanX -= 2;
        this.ghostrunawayX -= 2;
    }
    
    public void resetPacmanchase(){
        this.chaisingpacmanX = 1216;
        this.ghostrunawayX = 960;
    }
}
