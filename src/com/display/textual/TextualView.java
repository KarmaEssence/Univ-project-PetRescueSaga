package com.display.textual;

import com.display.View;
import com.gamecomponent.board.Goal;
import com.gamecomponent.board.Board;

/**
 * Affichage du jeu sur le terminal.
 * @version 1.0
 */
public class TextualView extends View {
	private PrintLevel printLevel;

	/**
	 * Affiche la page d accueil.
	 */
	@Override
	public void printHostPage() {
		HostPage.print();
	}

	/**
	 * Retourne le type de vue.
	 * @return type de vue.
	 */
	@Override
	public String typeOfView(){
		return "Textual";
	}

	/**
	 * Met a jour l affichage pour correspondre a celui
	 * du plateau courant.
	 */
	@Override
	public void print(){
		printLevel.print();
	}

	/**
	 * Fais un resume du niveau, en fonction du boolean
	 * affiche different type de resume.
	 * @param bool pour choisir le type de description.
	 */
	@Override
	public void summarize(boolean bool){
		printLevel.summarize(bool);
	}

	/**
	 * Quand l on initialise le niveau (depuis Level),
	 * la vue textuel a besoin de recevoir, les composantes
	 * utile pour l affichage.
	 * @param goal les objectifs du niveau.
	 * @param board le plateau.
	 * @param numLevel le numero du niveau.
	 */
	@Override
	public void setPrintLevel(Goal goal, Board board, int numLevel){
		printLevel = new PrintLevel(goal,board,numLevel);
	}

	/**
	 * Affiche le message d erreur.
	 * @param title titre du message d erreur.
	 * @param message message d erreur.
	 */
	@Override
	public void printErrorMessage(String title, String message) {
		System.out.println(message);
	}
}