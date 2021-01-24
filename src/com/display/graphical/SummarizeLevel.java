package com.display.graphical;

import com.config.save.LevelGenerator;
import com.config.save.SaveData;
import com.user.human.GraphicalHumanInteract;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Page permettant de faire le resume du niveau.
 * @version 1.0
 */
public class SummarizeLevel extends JPanel {
    private final PrintLevel print;
    private final GraphicalHumanInteract graphInteract;
    private JButton play;
    private JLabel title;
    private final JPanel titlePanel;
    private final JPanel contentPanel;
    private final JPanel actionPanel;
    private final BufferedImage image;

    /**
     * Construit la page de resume au debut
     * et a la fin du niveau choisi.
     * @param print La fenetre de l application.
     * @param graphInteract Le choix de l utilisateur.
     * @param bool true si c est le debut du niveau, false sinon.
     */
    public SummarizeLevel(PrintLevel print, GraphicalHumanInteract graphInteract, boolean bool){
        image= SaveData.getImageContent("Summarize");
        this.print = print;
        this.graphInteract = graphInteract;
        this.setLayout(new GridLayout(3,2));
        titlePanel = new JPanel();
        contentPanel = new JPanel();
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel,BoxLayout.Y_AXIS));

        constructTitle();

        if(!bool) {
            constructSummarizePanel();
        }else{
            constructEndSummarizePanel();
        }
        apply(bool);

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
     * Construit le titre de la page.
     */
    public void constructTitle(){
        title = new JLabel("<html><B>Summarize of Level</B></html>");
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        title.setForeground(Color.ORANGE);
    }

    /**
     * Construit les boutons du resumer
     * du debut du niveau.
     */
    public void constructSummarizePanel(){
        play = new JButton("Play");
        play.addActionListener((event) -> print.clearSummarize());
        JMenuBar analyzeContent = new JMenuBar();
        analyzeContent.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel analyzeText = new JLabel("Analyze Mode");
        JCheckBox analyzeMode = new JCheckBox("");
        analyzeMode.addActionListener((event)->{
            if(graphInteract.getAnswer().equals("yes")){
                graphInteract.setAnswer("no");
            }else{
                graphInteract.setAnswer("yes");
            }
        });
        analyzeContent.add(analyzeMode);
        analyzeContent.add(analyzeText);

        analyzeText.setForeground(Color.BLACK);
        analyzeContent.setBorder(BorderFactory.createLineBorder(Color.WHITE, 0));
        analyzeContent.setOpaque(false);
        analyzeMode.setOpaque(false);

        actionPanel.add(analyzeContent);
        actionPanel.add(Box.createRigidArea(new Dimension(50,50)));

    }

    /**
     * Construit les boutons pour passer a un autre niveau
     * ou quitter le jeu.
     */
    public void constructEndSummarizePanel(){
        JPanel optionContent = new JPanel();
        optionContent.setOpaque(false);
        optionContent.setLayout(new BoxLayout(optionContent,BoxLayout.X_AXIS));
        JButton retryLevel = new JButton("Retry Level");
        JButton nextLevel = new JButton("Next level");
        JButton chooseLevel = new JButton("Choose the level");

        if(!print.getGoal().isCompleted()){
            optionContent.add(retryLevel);
            optionContent.add(Box.createRigidArea(new Dimension(50,50)));
        }
        optionContent.add(chooseLevel);
        boolean checkLastLevel = (print.getNumLevel() == LevelGenerator.getLeveltosave());
        if(print.getGoal().isCompleted() && !checkLastLevel) {
            optionContent.add(Box.createRigidArea(new Dimension(50,50)));
            optionContent.add(nextLevel);
        }

        retryLevel.addActionListener(actionEvent->{
            graphInteract.setInGame(true);
            graphInteract.setAnswer("retry");
        });
        nextLevel.addActionListener(actionEvent->{
            graphInteract.setInGame(true);
            graphInteract.setAnswer("next");
        });
        chooseLevel.addActionListener(actionEvent->{
            graphInteract.setInGame(true);
            graphInteract.setAnswer("choose");
        });
        play = new JButton("Return to the host page");
        play.addActionListener((event) -> {
            graphInteract.setInGame(true);
            graphInteract.setAnswer("quit");
        });

        actionPanel.add(Box.createRigidArea(new Dimension(20,20)));
        actionPanel.add(optionContent);
        actionPanel.add(Box.createRigidArea(new Dimension(50,50)));
    }

    /**
     * Enregistre les elements dans le panneau SummarizeLevel.
     * @param bool true si c est le debut du niveau,false sinon.
     */
    public void apply(boolean bool){
        titlePanel.setOpaque(false);
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new GridLayout(5,2));
        actionPanel.setOpaque(false);

        play.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title);
        actionPanel.add(play);
        addSummarize(bool);
        add(titlePanel);
        add(contentPanel);
        add(actionPanel);

    }

    /**
     * Genere le contenu a resumer.
     * @param bool true si c est le debut du niveau,false sinon.
     */
    public void addSummarize(boolean bool){
        summarize(bool);
    }

    /**
     * Retourne un logo en fonction des criteres de reussite.
     * @param subGoal affiche le resumer pour le score.
     * @param finish regarde si le niveau est finis.
     * @param completed regarde si les objectif est rempli.
     * @return le logo correspondant.
     */
    private String logoGoal(String subGoal,boolean finish, boolean completed){
        if(!finish)return "";
        if(subGoal.equals("Score")){
            if(print.getGoal().getMaxScore()>print.getGoal().getScore()){
                return "<html><p style=color:red;>x ";
            }
        }
        return (completed)?"<html><p style=color:green;>V ":"<html><p " +
                "style=color:red;>x ";
    }

    /**
     * Affiche le resume du niveau.
     * @param bool true si c est le debut du niveau,false sinon.
     */
    public void summarize(boolean bool){
        int size = (bool)?7:5;
        String logoAnimal = logoGoal("Animal",bool,print.getGoal().isCompleted());
        String logoScore = logoGoal("Score",bool,print.getGoal().isCompleted());
        if(logoAnimal.equals("") && logoScore.equals("")){
            logoAnimal = "<html><p style=color:Black>"; logoScore="<html><p style=color:Black;>";
        }
        for(int i=0;i<size;i++){
            JLabel line=null;
            if(i==1){
                line = new JLabel("<html><p style=color:orange;>Level: "
                        + print.getNumLevel() +"</p></html>");
            }else if(bool && i==2){
                int star = print.getGoal().numberOfStar();
                String color = (star>1)?"green":"red";
                line = new JLabel("<html><p style=color:"+ color +";>Number of star: "
                        + star + "/" + 3 +"</p></html>");
            }else if((!bool && i==2)||(bool && i==3)){
                line = new JLabel(logoAnimal + "Save "
                        + print.getGoal().getNbMinAnimal() + " animal</p></html>");
            }else if((!bool && i==3)||(bool && i==4)){
                line = new JLabel(logoScore + " Get "
                        + print.getGoal().getMaxScore() + " points</p></html>");
            }else if(bool && i==5){
                line = new JLabel("<html><p style=color:Black;> Score: " +
                        print.getGoal().getScore() + " points </p></html>");
            }
            applyFilter(line);
        }
    }

    /**
     * Modifie l apparence du texte dans les JLabel,
     * et l ajoute au contentPanel.
     * @param line ligne courante.
     */
    private void applyFilter(JLabel line){
        if(line!=null){
            line.setFont(new Font("Serif", Font.PLAIN, 18));
            line.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(line);
        }
    }
}
