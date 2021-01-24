package com.display;

import com.gamecomponent.board.Goal;
import com.gamecomponent.board.Board;

/**
 * Correspond au type d interface
 * choisie par l utilisateur.
 */
public abstract class View{

	/**
	 * Affiche la page d accueil.
	 */
	public abstract void printHostPage();

	/**
	 * Retourne le type de vue choisie.
	 * @return type de vue
	 */
	public abstract String typeOfView();

	/**
	 * Met a jour l affichage pour correspondre a celui
	 * du plateau courant.
	 */
	public abstract void print();

	/**
	 * Fais un resume du niveau, en fonction du boolean
	 * affiche different type de resume.
	 * @param bool pour choisir le type de description.
	 */
	public abstract void summarize(boolean bool);

	/**
	 * Donne les composantes du niveau pour l affichage.
	 * @param goal les objectifs du niveau.
	 * @param board le plateau.
	 * @param numLevel le numero du niveau.
	 */
	public abstract void setPrintLevel(Goal goal, Board board, int numLevel);

	/**
	 * Cree le message d erreur.
	 * @param type type du message d erreur.
	 */
	public void makeAnErrorMessage(int type){
		String title = "";
		String message = "";
		switch (type){
			case 0:
				title ="Wrong number of Level";
				message = "Level does not exist";
				break;
			case 1:
				title ="Indestructible block";
				message = "This element can't be destroyed !";
				break;
			case 2:
				title ="Change your value";
				message = "You cannot choose this!";
				break;
		}
		printErrorMessage(title,message);
	}

	/**
	 * Affiche le message d erreur.
	 * @param title titre du message d erreur.
	 * @param message message d erreur.
	 */
	public abstract void printErrorMessage(String title,String message);
}