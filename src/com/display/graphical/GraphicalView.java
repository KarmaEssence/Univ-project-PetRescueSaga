package com.display.graphical;

import com.gamecomponent.board.Goal;
import com.display.View;
import com.gamecomponent.board.Board;
import com.user.human.GraphicalHumanInteract;

/**
 * Affichage du jeu sur la fenetre genere.
 * @version 1.0
 */
public class GraphicalView extends View {
    private PrintLevel print;
    private GraphicalHumanInteract graphInteract;

    /**
     * La vue graphique a besoin de connaitre les choix des joueurs.
     * Pour ensuite etre donnee au print, est utile pour l interaction modele et vue.
     * @param g les choix de l utilisateur.
     */
    public void setGraphInteract(GraphicalHumanInteract g){
        graphInteract  = g;
    }

    /**
     * Met en place l affichage courant.
     * @param print affichage courant.
     */
    public void setPrint(PrintLevel print){this.print=print;}

    /**
     * Renvoie la fenetre courant, pour l associer au choix du joueur.
     * @return la fenetre d application.
     */
    public PrintLevel getPrint(){
        return print;
    }

    /**
     * Affiche la page d accueil.
     */
    @Override
    public void printHostPage() {
        if(print==null){
            print = new PrintLevel(graphInteract,null,null,-1);
        }
        print.printHostPage(graphInteract);
        graphInteract.setPrint(print);
        print.revalidate();

    }

    /**
     * Retourne le type de vue choisie.
     * @return type de vue
     */
    @Override
    public String typeOfView() {
        return "Graphical";
    }

    /**
     * Met a jour l affichage pour correspondre a celui
     * du plateau courant.
     */
    @Override
    public void print() {
        print.update();
        print.revalidate();
    }

    /**
     * Fais un resume du niveau, en fonction du boolean
     * affiche different type de resume.
     * @param bool pour choisir le type de description.
     */
    @Override
    public void summarize(boolean bool) {
        if(graphInteract.getInGame() && !bool)return;
        graphInteract.setInGame(false);
        if(!bool && graphInteract.getAlreadyInit()){
            print.generateSumLevel(false);
        }
        if(bool){
            if (!graphInteract.getAnswer().equals("quit")){
                waitToAnalyzeFinalBoard();
            }
            print.generateSumLevel(true);
        }
        while(!graphInteract.getInGame()){
            try{
                Thread.sleep(500*1);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Permet de mettre en pause le processus,
     * le temps que l utilisateur chois
     */
    private void waitToAnalyzeFinalBoard(){
        graphInteract.setCanCont(false);
        print.analyzeAtTheEnd();
        while(!graphInteract.getCanCont()){
            try{
                Thread.sleep(500*1);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Quand l on initialise le niveau (depuis Level),
     * la vue graphique a besoin de recevoir, les composantes
     * utile pour l affichage.
     * @param goal les objectifs du niveau.
     * @param board le plateau.
     * @param numLevel le numero du niveau.
     */
    @Override
    public void setPrintLevel(Goal goal, Board board, int numLevel) {
        if(print == null){
            print = new PrintLevel(graphInteract,board, goal, numLevel);
        }else{
            print.changeLevel(board,goal,numLevel);
            print.update();
        }
    }

    /**
     * Affiche le message d erreur.
     * @param title titre du message d erreur.
     * @param message message d erreur.
     */
    @Override
    public void printErrorMessage(String title,String message){
        print.printAnError(title,message);
    }
}
