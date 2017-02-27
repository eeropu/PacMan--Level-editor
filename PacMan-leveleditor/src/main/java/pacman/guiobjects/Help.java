package pacman.guiobjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import pacman.pacman.leveleditor.ImageGetter;
import pacman.pacman.leveleditor.WindowHandler;

/**
 * Used to provide the instructionscreen to creationmode.
 *
 * @author eerop
 */
public final class Help extends JPanel {

    private ImageGetter imgGetter;
    private JLabel pacman, blinky, pinky, clyde, randomghost, wall, point, power, additionalInfo;
    private WindowHandler wh;

    /**
     * Constructor for the Help-class.
     *
     * @param imgGetter provides the graphical contents for this screen
     * @param wh windowhandler that is used to change the contents on the screen
     */
    public Help(ImageGetter imgGetter, WindowHandler wh) {
        this.imgGetter = imgGetter;
        this.wh = wh;

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(942, 850));
        panel.setBackground(Color.BLACK);
        panel.add(setPacman());
        panel.add(setBlinky());
        panel.add(setPinky());
        panel.add(setClyde());
        panel.add(setRandomGhost());
        panel.add(setWall());
        panel.add(setPoint());
        panel.add(setPower());
        panel.add(setAdditionalInfo());
        panel.setOpaque(false);

        for (JLabel l : setImages()) {
            panel.add(l);
        }

        JButton button = new JButton("Return");
        button.setBounds(412, 800, 96, 32);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                wh.returnToCreation();
            }
        });

        panel.add(button);

        JScrollPane jsp = new JScrollPane(panel);
        setLayout(new BorderLayout());
        this.add(jsp);
    }

    private HashSet<JLabel> setImages() {
        HashSet<JLabel> set = new HashSet<>();

        JLabel a = new JLabel(new ImageIcon(imgGetter.getSubImage(3)));
        a.setBounds(32, 32, 32, 32);
        set.add(a);
        JLabel b = new JLabel(new ImageIcon(imgGetter.getSubImage(5)));
        b.setBounds(512, 32, 32, 32);
        set.add(b);
        JLabel c = new JLabel(new ImageIcon(imgGetter.getSubImage(4)));
        c.setBounds(32, 160, 32, 32);
        set.add(c);
        JLabel d = new JLabel(new ImageIcon(imgGetter.getSubImage(6)));
        d.setBounds(512, 160, 32, 32);
        set.add(d);
        JLabel e = new JLabel(new ImageIcon(imgGetter.getSubImage(7)));
        e.setBounds(32, 288, 32, 32);
        set.add(e);
        JLabel f = new JLabel(new ImageIcon(imgGetter.getSubImage(9)));
        f.setBounds(512, 288, 32, 32);
        set.add(f);
        JLabel g = new JLabel(new ImageIcon(imgGetter.getSubImage(10)));
        g.setBounds(32, 416, 32, 32);
        set.add(g);
        JLabel h = new JLabel(new ImageIcon(imgGetter.getSubImage(1)));
        h.setBounds(512, 416, 32, 32);
        set.add(h);

        return set;
    }

    private JLabel setPacman() {
        pacman = new JLabel();
        pacman.setBounds(80, 32, 384, 48);
        pacman.setText("<html><p>-This is PacMan. It's the character that player controls. It's mission is to eat all the pointbubbles as fast as possible without getting eaten by ghosts</p></html>");
        return pacman;
    }

    private JLabel setBlinky() {
        blinky = new JLabel();
        blinky.setBounds(560, 32, 384, 32);
        blinky.setText("<html><p>-This is Blinky. He starts to follow PacMan when he gets close enough</p></html>");
        return blinky;
    }

    private JLabel setPinky() {
        pinky = new JLabel();
        pinky.setBounds(80, 160, 384, 16);
        pinky.setText("<html><p>-This is Pinky. He tries to position himself infront of pacman</p></html>");
        return pinky;
    }

    private JLabel setClyde() {
        clyde = new JLabel();
        clyde.setBounds(560, 160, 384, 32);
        clyde.setText("<html><p>-This is Clyde. He goes towards PacMan until he's too close and then turns 'home'</p></html>");
        return clyde;
    }

    private JLabel setRandomGhost() {
        randomghost = new JLabel();
        randomghost.setBounds(80, 288, 384, 32);
        randomghost.setText("<html><p>-This is the randomghost. It's behaviour is set randomly every time and it mimics the actions of some of the other ghosts</p></html>");
        return randomghost;
    }

    private JLabel setPoint() {
        point = new JLabel();
        point.setBounds(560, 288, 384, 48);
        point.setText("<html><p>-This is a pointbubble. PacMan's mission is to collect all of these in a level to complete it. Every pointbubble collected grants the player 10 points.</p></html>");
        return point;
    }

    private JLabel setPower() {
        power = new JLabel();
        power.setBounds(80, 416, 384, 64);
        power.setText("<html><p>-This is a Powerpellet. PacMan can eat one of these to enable him to eat ghosts, making them harmless for a while. Eating a Powerpellet grants the player 50 points and while it's active, eating a ghost grants 200 points.</p></html>");
        return power;
    }

    private JLabel setWall() {
        wall = new JLabel();
        wall.setBounds(560, 416, 384, 16);
        wall.setText("<html><p>-This is a wall. It limits the movement of PacMan and the ghosts.</p></html>");
        return wall;
    }

    private JLabel setAdditionalInfo() {
        additionalInfo = new JLabel();
        additionalInfo.setBounds(32, 480, 896, 400);
        additionalInfo.setText("<html><p>-You can add components to a level by selecting one with the radiobuttons at the bottom of the creationmode-screen and the clicking the desired position for the component."
                + "<br>"
                + "-Clicking a square that already contains an object will replace it with the newly selected component, unless the component is the same as previously added, in which case the component will be removed"
                + "<br>"
                + "-You can only add one PacMan"
                + "<br>"
                + "-Adding a wall to the border of the screen will add one to the corresponding position on the other side. (This also happends when removing walls)"
                + "<br>"
                + "You can leave some of the borders open, allowing pacman to 'teleport' from one side of the screen to the other"
                + "<br>"
                + "-Enabling autofill will allow player to create walls faster. When it is on, clicking a square will paint the clicked square pink and the selectable squares gray. "
                + "If one of the gray squares is clicked, all the squares between the pink one and the clicked one, not already containing a wall, will be changed to a wall. If the squares contains "
                + "a wall, it will be removed. If the second square to be clicked is not one of the gray ones (or on the row/column of gray squares), nothing will be done."
                + "<br>"
                + "-The Ready-button directs you to the finishing screen of the levelcreation where you can:"
                + "<br>     * choose the amount of lives"
                + "<br>     * choose the timelimit that can give extra points per saved second or limit the completion of the level"
                + "<br>     * fill empty squares with pointbubbles</p></html>");
        return additionalInfo;
    }

}
