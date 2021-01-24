package com.gamecomponent.update;

import com.gamecomponent.board.Board;
import com.gamecomponent.board.Case;

/**
 * @version 1.0
 */
public class ActionBoard{
	private Board board;
	private UpdateBoard updateBoard;

	/**
	 * Cree un ActionBoard avec un plateau courant
	 * et cree un UpdateBoard.
	 * @param board plateau courant
	 */
	public ActionBoard(Board board){
		this.board = board;
		updateBoard = new UpdateBoard(board);
	}

	public void setBoard(Board board){
		this.board = board;
		updateBoard.setBoard(board);
	}

	/**
	 * Met a jour le plateau.
	 */
	public void updateBoard(){
		updateBoard.update();
	}

	/**
	 * Recupere la case demande.
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 * @return renvoie la case demande.
	 */
	public Case getCase(int x, int y){
		return board.getCase(x,y);
	}

	/**
	 * /*Verifie que l element donne est le meme que celui de la case specifie.
	 * @param element element.
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 * @return true si c est le meme element,false sinon.
	 */
	private boolean sameElement(String element,int x, int y){
		if(board.outOfBound(x,y)) return false;
		if(element.equals(getCase(x,y).getTypeOfElement())){
			return true;
		}
		return false;
	}

	/**
	 * Verifie qu il existe des cases a detruire.
	 * @return true si l on peut detruire des elements,
	 * false sinon.
	 */
	public boolean boardIsPlayable(){
		for(int i=0;i<board.getLength();i++){
			for(int j=0;j<board.getWidth();j++){
				if(canDeleteblock(i,j)){
					return true;
				}	
			}
		}
		return false;
	}

	/**
	 * /*Verifie que le block puisse etre detruit.
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 * @return true si le block demande peut etre detruit.
	 */
	private boolean canDeleteblock(int x,int y){
		if(!getCase(x,y).isEmpty() && getCase(x,y).getElement().isDestroyable()){
			String element = getCase(x,y).getTypeOfElement();
			if(sameElement(element,x-1,y)){
				return true;
			}
			if(sameElement(element,x,y+1)){
				return true;
			}
			if(sameElement(element,x+1,y)){
				return true;
			}
			if(sameElement(element,x,y-1)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Supprime recursivement les blocks du meme type.
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 * @return renvoie le nombre de block supprime.
	 */
	private int deleteBlock(int x,int y){
		int nbBlock=1;
		String element = getCase(x,y).getTypeOfElement();
		board.cleanCase(x,y);
		if(sameElement(element,x-1,y)){
			nbBlock+=deleteBlock(x-1,y);
		}
		if(sameElement(element,x,y+1)){
			nbBlock+=deleteBlock(x,y+1);
		}
		if(sameElement(element,x+1,y)){
			nbBlock+=deleteBlock(x+1,y);
		}
		if(sameElement(element,x,y-1)){
			nbBlock+=deleteBlock(x,y-1);
		}
		return nbBlock;

	}

	/**
	 * Detecte si un animal est sur la derniere ligne.
	 * @return retourne le nombre d animaux sauves.
	 */
	private int saveAnimal(){
		int c = 0;
		for (int i=0;i<board.getWidth();i++) {
			while(getCase(board.getLength()-1,i).isAnimal()){
				board.cleanCase(board.getLength()-1,i);
				c+=1;
				updateBoard.update();
			}
		}
		return c;
	}



	/**
	 * Applique les changements demandes sur le tableau.
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 * @return	retourne le le nombre d animaux sauves et
	 * le nombre de block supprime.
	 * @throws IllegalStateException
	 * @throws IndexOutOfBoundsException
	 */
	public int[] actionOnBoard(int x,int y)throws IllegalStateException,IndexOutOfBoundsException{
		int[] res = new int[2];
		if(!board.outOfBound(x,y)){
			if(canDeleteblock(x,y)){
				res[1]=deleteBlock(x,y);
				updateBoard.update();
				res[0]=saveAnimal();
			}else{
				throw new IllegalStateException();
			}
		}else{
			throw new IndexOutOfBoundsException();
		}
		return res;
	}

	/**
	 * Suppression des blocks pour le robot.
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 * @return le nombre de block detruit.
	 */
	public int robotInteract(int x,int y) {
		if (canDeleteblock(x, y)) {
			int tmp = deleteBlock(x, y);
			return tmp;
		}
		return -1;
	}
}