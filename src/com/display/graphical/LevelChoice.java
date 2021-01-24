package com.display.graphical;

import com.config.save.SaveData;
import com.user.human.GraphicalHumanInteract;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panneau de selection du niveau.
 * @version 1.0
 */
public class LevelChoice extends JPanel{
    private final JPanel titlePanel;
    private final JPanel contentPanel;
    private final BufferedImage image;

    /**
     * Construction de la page pour
     * choisir les niveaux.
     * @param numMaxLevel numero du niveau actuel.
     * @param graphInteract le choix de l utilisateur.
     */
    public LevelChoice(int numMaxLevel,GraphicalHumanInteract graphInteract){
        image= SaveData.getImageContent("Main");
        titlePanel = new JPanel();
        contentPanel = new JPanel();
        this.setLayout(new GridLayout(2,1));

        setBackground(Color.DARK_GRAY);
        titlePanel.setOpaque(false);
        contentPanel.setOpaque(false);

        fillTitlePanel();
        fillContentPanel(numMaxLevel,graphInteract);


        add(titlePanel);
        add(contentPanel);
    }

    /**
     * Met a jour l image de fond.
     * @param g composant graphique.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

    /**
     * Met en place le titre de la page.
     */
    public void fillTitlePanel(){
        JLabel title = new JLabel("<html><B>"+"Choice of Level"+"</B></html>");
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setForeground(Color.ORANGE);
        titlePanel.add(title);
    }

    /**
     * Met en place les bouttons de la page.
     * @param numMaxLevel numero maximum de niveau.
     * @param graphInteract choix de l utilisateur.
     */
    public void fillContentPanel(int numMaxLevel,GraphicalHumanInteract graphInteract){
        JButton[] button = new JButton[numMaxLevel+1];
        JPanel subContent = new JPanel();
        subContent.setOpaque(false);
        subContent.setPreferredSize(new Dimension(500,70*(numMaxLevel/7+1)));
        for(int i=1;i<=numMaxLevel;i++){
            button[i] = new JButton("Level " + i);
            button[i].setEnabled(true);
            button[i].setPreferredSize(new Dimension(100,25));
            int finalI = i;
            button[i].addActionListener((ActionEvent e) ->{
                graphInteract.setLevelChoice(finalI);
            });
            subContent.add(button[i]);
        }
        contentPanel.add(subContent);
    }
}


