package com.display.graphical;

import com.config.save.SaveData;
import com.gamecomponent.board.Board;
import com.gamecomponent.board.Case;
import com.gamecomponent.board.Goal;
import com.user.human.GraphicalHumanInteract;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Panneau s occupant de l affichage du niveau.
 * @version 1.0
 */
public class LevelContent extends JPanel {

    private final GraphicalHumanInteract graphInteract;
    private final Board board;
    private final Goal goal;
    private final JPanel emptyPanel;
    private final BufferedImage image;


    /**
     * Contenu de levelContent.
     * @version 1.0
     */
    private class PanelBoard extends JPanel{
        JButton[][] button;

        /**
         * Creation du panneau representant le plateau du jeu.
         */
        public PanelBoard(){
            GridLayout grid = new GridLayout(board.getLength(), board.getWidth());
            button = new JButton[board.getLength()][board.getWidth()];
            this.setLayout(grid);
            for(int i=0;i<button.length;i++){
                for(int j=0;j<button[i].length;j++){
                    button[i][j] = new JButton(board.getCase(i,j).toString());
                    button[i][j].setFont(getFont().deriveFont(Font.BOLD));
                    button[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    attributeColor(i,j);
                    deleteText(i,j);
                    int finalI = i;
                    int finalJ = j;
                    button[i][j].addActionListener(actionEvent-> graphInteract.setPosition(new int[]{finalI, finalJ}));
                    this.add(button[i][j]);
                }
            }
        }

        /**
         * Donne une couleur a la case en fonction de son type d element
         * @param i numero de la colonne.
         * @param j numero de la ligne.
         */
        private void attributeColor(int i,int j){
            Case c = board.getCase(i,j);
            String typeOfElement = obtainOnlyName(c.getTypeOfElement());
            Color color;
            switch (typeOfElement){
                case "RedBlock": color = Color.RED;break;
                case "BlueBlock": color = Color.BLUE;break;
                case "YellowBlock": color = Color.YELLOW;break;
                case "GreenBlock": color = Color.GREEN;break;
                case "FixedBlock": color = Color.BLACK;break;
                case "AbstractBlock": color = Color.GRAY;break;
                case "Dog": color = Color.PINK;break;
                case "Cat": color = Color.PINK;break;
                default: color = Color.white;
            }
            button[i][j].setBackground(color);
        }

        /**
         * Pour obtenir uniquement le nom de la classe.
         * @param className nom complet de la classe.
         * @return uniquement le nom de la classe.
         */
        private String obtainOnlyName(String className){
            int i = className.lastIndexOf(".");
            return className.substring(i+1);
        }

        /**
         * Retire le texte du JLabel.
         * @param i numero de la colonne.
         * @param j numero de la ligne.
         */
        private void deleteText(int i,int j){
            if(button[i][j].getText() != null){
                button[i][j].setText(" ");
            }
        }
    }

    /**
     * Contenu de levelContent.
     * @version 1.0
     */
    private class GoalPanel extends JPanel{
        private JTextArea content;
        private String display;

        /**
         * Creation du panneau representant le plateau du jeu.
         */
        public GoalPanel(){
            display="";
            print();
            content = new JTextArea(display);
            content.setForeground(Color.BLACK);
            content.setFont(new Font("Serif", Font.PLAIN, 18));
            content.setEditable(false);
            content.setOpaque(false);
            this.add(content);
        }

        /**
         * Affiche des espaces.
         * @param n nombre d espace blanc.
         */
        private void printSpace(int n){
            for(int i=0;i<n;i++)display+=" ";
        }

        /**
         * Affiche le score courant
         * @param maxJ option pour selectionner la taille
         *             du conteneur.
         */
        private void loopForScore(int maxJ){
            String tmp = String.valueOf(goal.getScore());
            printSpace(1);
            while(tmp.length()<6){
                tmp = "0"+tmp;
            }
            int c = 0;
            for(int j=0;j<maxJ;j++){
                if(j%2==0){
                    display+= " ";
                }else{
                    display+= tmp.charAt(c);
                    c+=1;
                }
            }
        }

        /**
         * Pour afficher les objectifs.
         * @param maxJ option pour selectionner la taille
         *             du conteneur.
         */
        private void loopForOther(int maxJ){
            printSpace(1);
            for (int j=0;j<maxJ;j++) {
                if(j==0 || j==maxJ-6){
                }else if(j==3){
                    printSpace(1);
                    display+= ("Number of animal: " + goal.getSaveAnimal()
                            + "/" + goal.getNbMinAnimal());
                    printSpace(4);
                    display+= "Number of star: " + goal.numberOfStar()
                            + "/" + 3;
                    printSpace(1);
                }
            }
        }

        /**
         * Appelle les sous methodes pour afficher les objectifs
         * @param n nombre d espace blanc.
         * @param maxI option pour choisir la hauteur
         *             du conteneur.
         * @param maxJ option pour choisir la largeur
         *             du conteneur.
         * @param value1 position specifique pour debuter le conteneur.
         * @param value2 position specifique pour cloturer le conteneur.
         * @param type type de l objectif.
         */
        private void loopForContent(int n,int maxI,int maxJ,int value1,int value2, String type){
            for(int i=0;i<maxI;i++){
                printSpace(n);
                if(i%2==1){
                    if(type.equals("Score"))loopForScore(maxJ);
                    if(type.equals("Other"))loopForOther(maxJ);
                    display+= "\n";
                }else{
                    /*if(type.equals("Score")){
                        for (int j=0;j<maxJ;j++) {
                            if (j == value1 || j == value2) {
                                display += "*";
                            } else {
                                display += "-";
                            }
                        }
                    }*/
                    display+= "\n";
                }
            }
        }

        /**
         * Affiche les objectifs.
         */
        public void print(){
            loopForContent(26,3,13,0,12,"Score");
            loopForContent(0,3,57,0,56,"Other");
        }

    }

    /**
     * Construction du plateau et des objectifs du niveau.
     * @param graphicalHumanInteract Le choix de l utilisateur.
     * @param board Le plateau courant.
     * @param goal Les objectifs courant.
     */
    public LevelContent(GraphicalHumanInteract graphicalHumanInteract,Board board, Goal goal){
        image= SaveData.getImageContent("Main");

        this.graphInteract = graphicalHumanInteract;
        this.board = board;
        this.goal = goal;

        PanelBoard boardContent = new PanelBoard();
        GoalPanel goalContent = new GoalPanel();
        emptyPanel = new JPanel();

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        boardContent.setOpaque(false);
        goalContent.setOpaque(false);
        emptyPanel.setOpaque(false);
        goalContent.setPreferredSize(new Dimension(50,20));

        add(goalContent);
        add(boardContent);
        add(emptyPanel);

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
     * Remplie le panneau vide quand
     * l analyze mode est active.
     */
    public void needAnAction() {
        emptyPanel.removeAll();
        JButton quit = new JButton("quit");
        JButton last = new JButton("last");
        JButton cont = new JButton("continue");

        quit.addActionListener(actionEvent-> graphInteract.setAnswer("quit"));
        last.addActionListener(actionEvent-> graphInteract.setAnswer("last"));
        cont.addActionListener(actionEvent->{
            quit.setEnabled(false);
            last.setEnabled(false);
            cont.setEnabled(false);
            graphInteract.setAnswer("cont");
        });
        JPanel menu = new JPanel();
        menu.setOpaque(false);
        menu.setSize(menu.getWidth()+50,menu.getHeight());
        menu.add(quit);
        menu.add(last);
        menu.add(cont);
        emptyPanel.add(menu);
        add(emptyPanel);
        setVisible(true);
    }

    /**
     * Remplie le panneau empty
     * a la fin du niveau.
     */
    public void analyzeAtTheEnd() {
        emptyPanel.removeAll();
        JButton finish = new JButton("finish");
        finish.addActionListener((event)-> graphInteract.setCanCont(true));
        emptyPanel.add(finish);
        add(emptyPanel);
        setVisible(true);
    }
}
