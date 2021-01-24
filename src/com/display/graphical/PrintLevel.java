package com.display.graphical;

import com.config.save.PlayerConfig;
import com.gamecomponent.board.Board;
import com.gamecomponent.board.Goal;
import com.user.human.GraphicalHumanInteract;

import javax.swing.*;
import java.awt.*;

/**
 * Fenetre de l application.
 * @version 1.0
 */
public class PrintLevel extends JFrame{
    private GraphicalHumanInteract graphInteract;
    private Board board;
    private Goal goal;
    private int numLevel;
    private LevelContent levelContent;
    private SummarizeLevel sumContent;

    /**
     * Creation de la fenetre de l application, contenant le niveau.
     * @param graphInteract les choix de l utilisateur.
     * @param goal les objectifs du niveau.
     * @param board le plateau.
     * @param numLevel le numero du niveau.
     */
    public PrintLevel(GraphicalHumanInteract graphInteract, Board board, Goal goal, int numLevel){
        this.setTitle("Group57-PetRescueSaga");
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.graphInteract = graphInteract;
        this.board = board;
        this.goal = goal;
        this.numLevel = numLevel;

        graphInteract.setAlreadyInit(true);
        this.setVisible(true);

    }

    /**
     * Retourne le plateau courant.
     * @return le plateau courant.
     */
    public Board getBoard(){ return board; }

    /**
     * Retourne les objectifs courant.
     * @return les objectifs courant.
     */
    public Goal getGoal(){ return goal;}

    /**
     * Retourne le numero du niveau actuel.
     * @return numero du niveau actuel.
     */
    public int getNumLevel(){ return numLevel; }

    /**
     * Affiche la page d accueil.
     * @param graphInteract choix du joueur.
     */
    public void printHostPage(GraphicalHumanInteract graphInteract){
        getContentPane().removeAll();
        HostPage hostPage = new HostPage(graphInteract);
        getContentPane().add(hostPage);
        setTitle("Group57-PetRescueSaga-HostPage");
    }

    /**
     * Genere la page de choix de partie.
     * @param graphInteract choix du joueur.
     * @param saveOfSomething toute les parties disponible.
     */
    public void chooseGameData(GraphicalHumanInteract graphInteract,
                               PlayerConfig[] saveOfSomething){
        getContentPane().removeAll();
        this.graphInteract = graphInteract;
        setTitle("Group57-PetRescueSaga-GameSelector");
        GameChoice gameChoice = new GameChoice(graphInteract,saveOfSomething);
        gameChoice.setBackground(Color.DARK_GRAY);
        getContentPane().add(gameChoice);
    }

    /**
     * Genere la page de choix de niveau
     * @param update choix de l utilisateur.
     * @param numMaxLevel nombre maximum de niveau.
     */
    public void makeChoiceOfLevel(GraphicalHumanInteract update,int numMaxLevel){
        getContentPane().removeAll();
        setTitle("Group57-PetRescueSaga-ChoiceOfLevel");
        graphInteract = update;
        LevelChoice levelChoice = new LevelChoice(numMaxLevel,graphInteract);
        getContentPane().add(levelChoice);
    }

    /**
     * Retire la page de resume,
     * et met le status du jeu
     * en place.
     */
    void clearSummarize(){
        remove(sumContent);
        graphInteract.setInGame(true);
    }

    /**
     * Met a jour le plateau et les objectifs.
     */
    public void update(){
        getContentPane().removeAll();
        setTitle("Group57-PetRescueSaga-Level " + numLevel);
        levelContent = new LevelContent(graphInteract,board,goal);
        add(levelContent);
        repaint();
    }

    /**
     * Change les donnees lors du lancement
     * d un niveau
     * @param board plateau courant.
     * @param goal objectif courant.
     * @param numLevel numero du niveau actuel.
     */
    public void changeLevel(Board board, Goal goal, int numLevel){
        this.board = board;
        this.goal = goal;
        this.numLevel = numLevel;
    }

    /**
     * Affiche les boutons lorsque
     * le mode analyse est choisit
     */
    public void needAnAction(){
        levelContent.needAnAction();
        revalidate();
    }

    /**
     * Affiche une erreur
     * @param title titre de l erreur.
     * @param message message d erreur.
     */
    public void printAnError(String title,String message){
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Genere la page de resumer de chaque niveau.
     * @param choice true pour affiche le resumer
     *               des objectifs du niveau,false sinon.
     */
    public void generateSumLevel(boolean choice){
        getContentPane().removeAll();
        setTitle("Group57-PetRescueSaga-Summarize");
        sumContent = new SummarizeLevel(this,graphInteract, choice);
        getContentPane().add(sumContent);
    }

    /**
     * Met en place un bouton finish pour quitter le niveau,
     * quand celui ci est finit
     */
    public void analyzeAtTheEnd(){
        levelContent.analyzeAtTheEnd();
        revalidate();
    }
}
