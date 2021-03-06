package pacman.guiobjects;

import java.awt.Color;
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
 * Menu that will be shown to the user when the program starts. From here user
 * can go to Levelselectionmenu and play a level, go to creation- mode to make
 * new levels or modify existing levels or view the highscores of the levels.
 *
 * @author eerop
 */
public class StartMenu extends JPanel {

    private JButton create, play, highscores;
    private ImageGetter imgGetter;
    private BufferedImage[] images;
    private BufferedImage image, pacmanRunaway, chasingGhost, ghostRunaway, chaisingPacman, buttons;
    private WindowHandler wh;
    private int pacmanrunawayX, chasingGhostX, chaisingpacmanX, ghostrunawayX;
    private StartMenuAnimation sma;
    private Timer timer;

    /**
     * Constructor for the StartMenu-class.
     *
     * @param wh windowhandler that is used to change the content on the screen
     * @param imgGetter provides the images for this menu
     */
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

        sma = new StartMenuAnimation(this, images);
        timer = new Timer(20, sma);
        setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 1));
        panel1.setBounds(0, 416, 416, 256);

        play = new JButton("PLAY");
        create = new JButton("CREATE");
        highscores = new JButton("HIGHSCORES");
        Font font = new Font("Verdana", Font.BOLD, 48);
        play.setFont(font);
        play.setBackground(new Color(87, 0, 127));
        play.setForeground(Color.black);
        create.setFont(font);
        create.setBackground(new Color(87, 0, 127));
        create.setForeground(Color.black);
        highscores.setFont(font);
        highscores.setBackground(new Color(87, 0, 127));
        highscores.setForeground(Color.black);
        StartMenuListener sml = new StartMenuListener(play, create, highscores, wh);
        play.addActionListener(sml);
        create.addActionListener(sml);
        highscores.addActionListener(sml);

        panel1.add(play);
        panel1.add(create);
        panel1.add(highscores);

        this.add(panel1);
        timer.start();
    }

    private void setImages() {
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
        if (sma.isb()) {
            g.drawImage(pacmanRunaway, pacmanrunawayX, 96, this);
            g.drawImage(chasingGhost, chasingGhostX, 96, this);
        } else {
            g.drawImage(chaisingPacman, chaisingpacmanX, 96, this);
            g.drawImage(ghostRunaway, ghostrunawayX, 96, this);
        }
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Sets the delay of the timer.
     *
     * @param delay timers delay in milliseconds
     */
    public void setDelay(int delay) {
        timer.setDelay(delay);
    }

    /**
     * Used for the animation in the start screen.
     */
    public void pacmanrunaway() {
        this.pacmanrunawayX += 2;
        this.chasingGhostX += 2;
    }

    /**
     * Used for the animation in the start screen.
     */
    public void resetPacmanrun() {
        this.pacmanrunawayX = -128;
        this.chasingGhostX = -384;
    }

    /**
     * Used for the animation in the start screen.
     */
    public void pacmanchase() {
        this.chaisingpacmanX -= 2;
        this.ghostrunawayX -= 2;
    }

    /**
     * Used for the animation in the start screen.
     */
    public void resetPacmanchase() {
        this.chaisingpacmanX = 1216;
        this.ghostrunawayX = 960;
    }
}
