package com.user.human;

import com.user.Player;

/**
 * Correspond au joueur humain.
 * @version 1.0
 */
public class HumanPlayer extends Player {

	/**
	 * Cree un joueur humain.
	 * @param name nom d utilisateur.
	 */
	public HumanPlayer(String name){
		super(name);
	}



	/**
	 * Pour que le joueur utilise le scanner.
	 * @return True si le joueur est humain, false sinon.
	 */
	public boolean isHuman(){
		return true;
	}	
}