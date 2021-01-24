package com.display.graphical;

import com.config.save.SaveData;
import com.user.human.GraphicalHumanInteract;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Page d accueil.
 * @version 1.0
 */
public class HostPage extends JPanel {
    private final GraphicalHumanInteract graphInteract;
    private final JPanel titlePanel;
    private final JPanel actionPanel;
    private final BufferedImage image;

    /**
     * Cree la page d accueil
     * @param graphInteract le choix du joueur.
     */
    public HostPage(GraphicalHumanInteract graphInteract){
        image= SaveData.getImageContent("Main");
        this.graphInteract = graphInteract;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);

        titlePanel = new JPanel();
        actionPanel = new JPanel();
        titlePanel.setOpaque(false);
        actionPanel.setOpaque(false);

        fillInTitlePanel();
        fillInActionPanel();

        add(titlePanel);
        add(actionPanel);
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
    private void fillInTitlePanel(){
        ImageIcon icon = new ImageIcon(SaveData.getImageContent("Logo"));
        JLabel title = new JLabel(icon);
        titlePanel.add(title);
    }

    /**
     * Met en place les bouttons de la page.
     */
    private void fillInActionPanel() {
        JButton play = new JButton("play");
        JButton quit = new JButton("quit");

        play.addActionListener((event)-> graphInteract.setAnswer("play"));

        quit.addActionListener((event)-> System.exit(0));

        actionPanel.add(play);
        actionPanel.add(Box.createHorizontalGlue());
        actionPanel.add(Box.createHorizontalGlue());
        actionPanel.add(quit);
    }

}
