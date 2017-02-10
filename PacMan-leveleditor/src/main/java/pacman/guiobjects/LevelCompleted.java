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
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import pacman.guilisteners.LevelCompleteListener;

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

    public LevelCompleted(LevelCompleteListener lcl) {

        this.lcl = lcl;

        ClassLoader cl = this.getClass().getClassLoader();
        URL resource = cl.getResource("Pictures/PacMan_lvlcomplete.png");
        try {
            image = ImageIO.read(resource);
        } catch (IOException e) {
        }
        repaint();
        setLayout(null);

        label = new JLabel("Submit your score!");
        label.setFont(new Font("Verdana", Font.BOLD, 25));
        label.setForeground(Color.white);
        label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        label.setBounds(320, 320, 320, 40);
        add(label);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBounds(320, 360, 320, 80);
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
        skip.setBounds(450, 608, 64, 32);
        lcl.setSkip(skip);
        skip.addActionListener(lcl);

        add(panel);
        add(skip);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
