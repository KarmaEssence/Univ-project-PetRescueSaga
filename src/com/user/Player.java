package com.user;

import com.display.View;
import com.user.human.GraphicalHumanInteract;
import com.user.human.HumanInteract;
import com.user.human.TextualHumanInteract;

/**
 * Represente l utilisateur.
 * @version 1.0
 */
public abstract class Player{
	private final String name;
	private HumanInteract playerChoice;
	protected View view;

	/**
	 * Construit un joueur.
	 * @param name nom d utilisateur.
	 */
	public Player(String name){
		this.name = name;
	}

	/**
	 * Renvoie le choix du joueur.
	 * @return le choix du joueur.
	 */
	public HumanInteract getPlayerChoice(){
		return playerChoice;
	}

	/**
	 * Choisi le type de choix en
	 * fonction de la vue chosie.
	 */
	public void choosePlayerInteractForView(){
		String type = view.typeOfView();
		if(type.equals("Textual")){
			playerChoice = new TextualHumanInteract();
		}else{
			playerChoice = new GraphicalHumanInteract(view);
		}
	}

	/**
	 * Etablie la vue en fonction
	 * de celle choisi.
	 * @param view vue choisie.
	 */
	public void setView(View view){
		this.view = view;
	}

	/**
	 * Verifie si l utilisateur est human.
	 * @return true si l utilisateur est human.
	 */
	public abstract boolean isHuman();

	/**
	 * Retourne le nom d utilisateur du joueur.
	 * @return le nom d utilisateur.
	 */
	public String getName(){return name;}

}