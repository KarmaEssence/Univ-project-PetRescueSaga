package com.user.robot;

import com.gamecomponent.update.ActionBoard;
import com.gamecomponent.board.Board;
import com.user.Player;

/**
 * Correspond au joueur robot.
 * @version 1.0
 */
public class Robot extends Player {
	private Board currentboard;
	private Board initialboard;
	private ActionBoard actionBoard;

	/**
	 * Cree un joueur robot.
	 * @param name nom d utilisateur.
	 */
	public Robot(String name){
		super(name);
	}

	/**
	 * Verifie si l utilisateur est human.
	 * @return false car l utilisateur est un robot.
	 */
	@Override
	public boolean isHuman(){
		return false;
	}

	/**
	 * Fais une copie du plateau a chaque etape
	 * du niveau.
	 * @param current plateau a l etape donnee
	 */
	public void setCurrentBoard(Board current){
		initialboard = current.clone();
		this.currentboard = current.clone();
		actionBoard = new ActionBoard(currentboard);
	}

	/**
	 * Reinitialise le tableau courant
	 * pour que le robot fasse des tests.
	 */
	public void resetCurrentBoard(){
		currentboard = initialboard.clone();
		actionBoard = new ActionBoard(currentboard);
	}

	/**
	 * Met le programme en pause le temps
	 * que l utilisateur regarde le changement
	 * du plateau.
	 */
	public void waiting(){
		try{
			Thread.sleep(1000*7);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

	/**
	 * Calcule les possibilités pour trouver le bloc
	 * qui rapporte le plus haut score,
	 * et renvoie la position de celui ci.
	 * @return renvoie la position du bloc
	 * qui rapport le plus de score.
	 */
	public int[] getPosition(){
		int[] position = new int[2];
		int betterMove = 0;
		for(int i=0;i<currentboard.getLength();i++){
			for(int j=0;j<currentboard.getWidth();j++){
				int countBlockDeleted = actionBoard.robotInteract(i,j);
				if(countBlockDeleted != -1){
					actionBoard.updateBoard();
					if(betterMove<countBlockDeleted){
						betterMove = countBlockDeleted;
						position[0] = i;
						position[1] = j;
					}
					resetCurrentBoard();
				}
			}
		}
		waiting();
		return position;
	}
}