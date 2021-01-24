package com.user.human;

import com.config.save.PlayerConfig;
import com.display.View;
import com.display.graphical.GraphicalView;
import com.display.graphical.PrintLevel;
import com.user.Player;
import com.user.robot.Robot;

/**
 * Represente les choix de l utilisateur
 * sur l interface graphique.
 * @version 1.0
 */
public class GraphicalHumanInteract implements HumanInteract{
    private final View view;
    private PrintLevel print;
    private String gameChoice;
    private int levelNum;
    private int[] position;
    private String askToPlay;
    private String[] playerData;
    private boolean inGame;
    private boolean alreadyInit;
    private boolean canCont;

    /**
     * Cree un objet permettant
     * les choix du joueur sur
     * l interface graphique.
     * @param view prend une vue courante.
     */
    public GraphicalHumanInteract(View view){
        this.view = view;
    }

    /**
     * Applique l affichage courant.
     * @param print prend l'affichage courant.
     */
    public void setPrint(PrintLevel print){
        this.print = print;
    }

    /**
     * Applique le numero du niveau choisi.
     * @param i prend un numero du niveau.
     */
    public void setLevelChoice(int i){ levelNum = i; }

    /**
     * Applique une position choisie.
     * @param pos prend une position sur le plateau.
     */
    public void setPosition(int[] pos){position = pos;}

    /**
     * Applique la reponse a la question posee.
     * @param answer une reponse.
     */
    public void setAnswer(String answer){askToPlay=answer;}

    /**
     * Sauvegarde le nom d utilisateur ou le type de joueur.
     * @param i Indice de la case choisit.
     * @param content reponse de l utilisateur.
     */
    public void setPlayerData(int i,String content){playerData[i] = content;}

    /**
     * Etablie le status de la partie.
     * @param change true si le jeu commence,false sinon.
     */
    public void setInGame(boolean change){inGame = change;}

    /**
     * Etablie le choix du joueur.
     * @param change true si le choix du joueur est etablie.
     */
    public void setAlreadyInit(boolean change){alreadyInit = change;}

    /**
     * Pour savoir si le joueur veut
     * continuer a jouer.
     */
    public void setCanCont(boolean change){canCont=change;}

    /**
     * Permet de choisir une partie.
     * @param gameChoice pour choisir une option.
     */
    public void setGameChoice(String gameChoice){this.gameChoice = gameChoice;}


    /**
     * Retourne l affichage courant.
     * @return l affichage courant.
     */
    public PrintLevel getPrint(){return print;}

    /**
     * Retourne le status du jeu.
     * @return le status du jeu.
     */
    public boolean getInGame(){return inGame;}

    /**
     * Retourne la reponse a une question.
     * @return la reponse de l utilisateur.
     */
    public String getAnswer(){return askToPlay;}


    /**
     * Regarde si le choix du joueur a ete etablie.
     * @return true si le joueur a ete etablie.
     */
    public boolean getAlreadyInit(){return alreadyInit;}

    /**
     * Retourne le choix du joueur a la fin
     * du niveau
     * @return true s il veut continuer,false sinon.
     */
    public boolean getCanCont(){return canCont;}

    /**
     * Demande au joueur s il veut jouer.
     * @return true s il veut joueur,
     * false sinon.
     */
    @Override
    public boolean chooseToPlay() {
        askToPlay = "nothing";
        while(askToPlay.equals("nothing")){
            waitTheAnswerOfThePlayer();
        }
        return true;
    }

    /**
     * Position de l element.
     * @return la position de l element.
     */
    @Override
    public int[] position() {
        position= new int[]{-1, -1};
        while(position[0] == -1 || position[1]==-1){
            waitTheAnswerOfThePlayer();
        }
        return position;
    }

    /**
     * Permet de choisir un niveau.
     * @param numMaxLevel nombre maximal de niveau.
     * @return le numero du niveau choisi
     */
    @Override
    public int choiceOfLevel(int numMaxLevel) {
        if(levelNum!=0)levelNum=0;
        alreadyInit = true;
        if(print==null){
            print = ((GraphicalView)view).getPrint();
        }
        print.makeChoiceOfLevel(this,numMaxLevel);
        while(levelNum==0){
            waitTheAnswerOfThePlayer();
        }
        return levelNum-1;
    }

    /**
     * Pose une question a l utilisateur.
     * @param sentence une question.
     * @param endOfGame regarde si ce sont les
     *                  dernieres questions.
     * @return true si la reponse est favorable.
     */
    @Override
    public boolean ask(String sentence,boolean endOfGame) {
        if(endOfGame)return analyzeQuestion(sentence);
        askToPlay = "nothing";
        return generateSummarize();
    }

    /**
     * Genere la description de debut de niveau.
     * @return true si l utilisateur veut
     * le niveau.
     */
    private boolean generateSummarize(){
        inGame = false;
        view.summarize(false);
        while(!getInGame()){
            waitTheAnswerOfThePlayer();
        }
        if (askToPlay.equals("yes")){
            askToPlay = "nothing";
            return true;
        }
        askToPlay = "nothing";
        return false;
    }

    /**
     * Lors du resume de fin,regarde quel choix
     * le joueur a fait.
     * @param sentence question.
     * @return true si l une des reponses correspond
     * a la question pose.
     */
    private boolean analyzeQuestion(String sentence){
        if(sentence.contains("retry") && askToPlay.equals("retry")) return true;
        if(sentence.contains("next") && askToPlay.equals("next")) return true;
        return sentence.contains("choose") && askToPlay.equals("choose");
    }

    /**
     * Pose une question a chaque etape.
     * @param sentence question.
     * @return l une des trois options.
     */
    @Override
    public String askPerStep(String sentence) {
        askToPlay = "nothing";
        print = ((GraphicalView)view).getPrint();
        while(!askToPlay.equals("cont") && !askToPlay.equals("quit") && !askToPlay.equals("last")){
            print.needAnAction();
            waitTheAnswerOfThePlayer();
        }
        return askToPlay;
    }

    /**
     * Demande la sauvegarde a choisir.
     * @param saveOfSomething toutes les sauvegardes des parties
     *                        disponible.
     * @return la reponse du joueur.
     */
    @Override
    public String askToChooseSave(PlayerConfig[] saveOfSomething) {
        playerData = new String[2];
        gameChoice = "c0";
        ((GraphicalView)view).setPrint(print);
        print.chooseGameData(this,saveOfSomething);
        while(Integer.parseInt(gameChoice.substring(1,2))==0){
            waitTheAnswerOfThePlayer();
        }
        return gameChoice;
    }

    /**
     * Renvoie le type de joueur en fonction du choix de l utilisateur.
     * @return le type de joueur que l'on est.
     */
    @Override
    public Player choiceOfPlayer() {
        String isHuman = playerData[1];
        if(isHuman.equals("r")){
            return new Robot(playerData[0]);
        }else{
            return new HumanPlayer(playerData[0]);
        }
    }

    /**
     * Attend que l utilisateur est fait son choix.
     */
    private void waitTheAnswerOfThePlayer(){
        try{
            Thread.sleep(500);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ferme la fenetre une fois que le jeu est fini.
     */
    @Override
    public void close() {
        print.setVisible(false);
        print.dispose();
    }


}
