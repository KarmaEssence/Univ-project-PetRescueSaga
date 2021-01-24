package com.display;

import com.config.save.PlayerConfig;
import com.gamecomponent.board.Goal;
import com.display.graphical.GraphicalView;
import com.display.textual.TextualView;
import com.gamecomponent.board.Board;
import com.user.Player;
import com.user.robot.Robot;
import com.user.human.GraphicalHumanInteract;
import com.user.human.HumanInteract;

import java.util.Scanner;

/**
 * Permet de faire la liaison entre
 * la vue, le model et le joueur.
 * @version 1.0
 */
public class GeneralView{
	private View view;
	private Player player;

	/**
	 * Cree une instance contenant un joueur et une vue.
	 */
	public GeneralView(){
		if(choiceOfView().equals("graphical")){
			view = new GraphicalView();
		}else{
			view = new TextualView();
		}
	}

	/**
	 * Initialise un joueur a la partie.
	 * @param player le joueur courant.
	 */
	public void initializePlayer(Player player){
		this.player = player;
		player.setView(view);
		player.choosePlayerInteractForView();
		setGraphicalHumanInteract();
	}

	/**
	 * Verifie que le type de vue est connue.
	 * @param choice type de vue demande.
	 * @return true si la vue demande existe,false sinon.
	 */
	private static boolean viewVerify(String choice){
		return choice.equals("textual") || choice.equals("graphical");
	}

	/**
	 * Demande a l utilisateur le type de vue a choisir.
	 * @return le type de vue choisie.
	 */
	private static String choiceOfView(){
		Scanner sc = new Scanner(System.in);
		String res = "";
		while(!viewVerify(res)){
			System.out.println("What kind of view do you want ?(graphical or textual)");
			res = sc.nextLine();
			System.out.println();
		}
		return res;
	}

	/**
	 * Affiche la page d accueil et
	 * regarde le choix de l utilisateur.
	 * @return true si le joueur veut jouer,false sinon.
	 */
	public boolean printHostPage(){
		view.printHostPage();
		return player.getPlayerChoice().chooseToPlay();
	}

	/**
	 * Donne a la vue les composantes principales d un niveau.
	 * @param goal les objectifs du niveau.
	 * @param board le plateau.
	 * @param numLevel le numero du niveau.
	 */
	public void setPrintLevel(Goal goal, Board board, int numLevel){
		view.setPrintLevel(goal,board,numLevel);
	}

	/**
	 * Retourne la position de la case choisie par l utilisateur.
	 * @return la position du bloc a detruire.
	 */
	public int[] getPosition(){
		if(player.isHuman()){
			return player.getPlayerChoice().position();
		}
		int[] res = ((Robot)player).getPosition();
		return res;
	}

	/**
	 * Retourne le type de vue.
	 * @return type de vue.
	 */
	public String typeOfView(){
		return view.typeOfView();
	}

	/**
	 * Affiche le plateau avec les donnees courantes.
	 */
	public void print(){
		view.print();
	}

	/**
	 * Affiche un resume du niveau.
	 * @param bool type de la description.
	 */
	public void summarizeLevel(boolean bool){
		view.summarize(bool);
	}

	/**
	 * L utilisateur doit faire un choix de niveau.
	 * @param size le nombre de niveau disponible.
	 * @return le numero du niveau choisi.
	 */
	public int choiceOfLevel(int size){
		return player.getPlayerChoice().choiceOfLevel(size);
	}

	/**
	 * Pose une question en fonction du motif en parametre.
	 * @param sentence ce qui est demande a l utilisateur.
	 * @return true si la reponse est favorable,false sinon.
	 */
	public boolean ask(String sentence,boolean endOfGame){
		return player.getPlayerChoice().ask(sentence,endOfGame);
	}

	/**
	 * Pose une question a chaque etape du niveau.
	 * @param sentence la question.
	 * @return l une des trois options.
	 */
	public String askPerStep(String sentence){
		return player.getPlayerChoice().askPerStep(sentence);
	}

	/**
	 * Demande la sauvegarde a choisir.
	 * @param saveOfSomething toutes les sauvegardes des parties
	 *                        disponibles.
	 * @return la reponse du joueur.
	 */
	public String askToChooseSave(PlayerConfig[] saveOfSomething){
		return player.getPlayerChoice().askToChooseSave(saveOfSomething);
	}

	/**
	 * Termine les choix de l utilisateur.
	 */
	public void endOfChoice(){
		player.getPlayerChoice().close();
	}

	public Player choiceOfPlayer(){
		return player.getPlayerChoice().choiceOfPlayer();
	}

	/**
	 * Donne le plateau courant pour le robot
	 * (il interagit directement avec).
	 * @param board plateau courant.
	 */
	public void robotSetBoard(Board board){
		if(!player.isHuman()){
			((Robot)player).setCurrentBoard(board);
		}
		
	}

	/**
	 * Donne a la vue graphique, les choix d un utilisateur.
	 */
	public void setGraphicalHumanInteract(){
		HumanInteract hi = player.getPlayerChoice();
		if(hi.getClass().getName().endsWith("GraphicalHumanInteract")){
			((GraphicalView)view).setGraphInteract((GraphicalHumanInteract) hi);
		}
	}

	/**
	 * Fait des messages d erreurs.
	 * @param type type du message d erreur.
	 */
	public void makeAnErrorMessage(int type){
		view.makeAnErrorMessage(type);
	}

}
