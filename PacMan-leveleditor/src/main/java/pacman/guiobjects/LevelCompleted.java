/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman.guiobjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import pacman.guilisteners.LevelCompleteListener;
import pacman.pacman.leveleditor.ImageGetter;

/**
 *
 * @author eerop
 *
 * Menu that will be shown to the player once a level is finished.
 */
public class LevelCompleted extends JPanel {

    private BufferedImage image;
    private JLabel label;
    private JTextPane txt;
    private JButton submit, skip;
    private LevelCompleteListener lcl;
    private ImageGetter imgGetter;
    private String name;
    private int scores;

    public LevelCompleted(LevelCompleteListener lcl, ImageGetter imgGetter, String name, int scores) {

        this.lcl = lcl;
        this.name = name;
        this.scores = scores;
        
        this.imgGetter = imgGetter;
        this.image = imgGetter.getImage("Pictures/PacMan_lvlcomplete.png");
        repaint();
        setLayout(null);

        label = new JLabel("Submit your score!");
        label.setFont(new Font("Verdana", Font.BOLD, 25));
        label.setForeground(Color.white);
        label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        label.setBounds(336, 352, 272, 32);
        add(label);
        
        JLabel score = new JLabel("" + scores);
        score.setFont(new Font("Verdana", Font.BOLD, 35));
        score.setForeground(Color.YELLOW);
        score.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        score.setBounds(336, 400, 272, 64);
        add(score);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBounds(336, 488, 272, 80);
        panel.setOpaque(true);

        txt = new JTextPane();
        txt.setFont(new Font("Verdana", Font.BOLD, 25));
        txt.setBackground(new Color(20, 20, 20));
        txt.setForeground(Color.gray);
        txt.setText("Name");
        StyledDocument doc = txt.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        lcl.setTxt(txt);
        txt.addMouseListener(lcl);
        panel.add(txt);

        submit = new JButton("Submit!");
        submit.setFont(new Font("Verdana", Font.BOLD, 20));
        submit.setForeground(Color.black);
        lcl.setSubmit(submit);
        submit.addActionListener(lcl);
        panel.add(submit);

        skip = new JButton("Skip");
        skip.setBounds(446, 608, 64, 32);
        lcl.setSkip(skip);
        skip.addActionListener(lcl);
        
        lcl.setScore(scores);
        lcl.setlevel(name);

        add(panel);
        add(skip);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
