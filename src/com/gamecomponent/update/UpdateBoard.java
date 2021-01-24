package com.gamecomponent.update;

import com.gamecomponent.Element;
import com.gamecomponent.board.Board;
import com.gamecomponent.board.Case;

/**
 * Permettant de mettre a jour le board.
 * @version 1.0
 */
public class UpdateBoard{
	private Board board;

	/**
	 * Initialise un objet qui met
	 * a jour le plateau.
	 * @param board plateau courant.
	 */
	public UpdateBoard(Board board){
		this.board = board;
	}

	/**
	 * Etablie le plateau courant.
	 * @param board plateau courant.
	 */
	public void setBoard(Board board){
		this.board = board;
	}

	/**
	 * Recupere la case demande.
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 * @return renvoie la case demande.
	 */
	private Case getCase(int x, int y){
		return board.getCase(x,y);
	}


	/**
	 * Regarde si la colonne n est pas vide.
	 * @param begin indice de depart de la ligne.
	 * @param x indice d arrive de la ligne.
	 * @param y indice de la colonne.
	 * @return true si une case est vide dans la colonne entre
	 * begin et x.
	 */
	private boolean checkColumn(int begin,int x,int y){
		for(int i=begin;i<x;i++){
			if(!getCase(i,y).isEmpty() && !getCase(i,y).isFixedBlock()){
				return true;
			}
		}
		return false;
	}



	/**
	 * Verifie que la colonne soit vide.
	 * @param j indice de la colonne.
	 * @return true si la colonne est vide.
	 */
	private boolean collumnIsEmpty(int j){
		for(int i=bottomOfFixedBlock(board.getLength(),j);i<board.getLength();i++){
			if(!getCase(i,j).isEmpty() && !getCase(i,j).isFixedBlock()){
				return false;
			}
		}
		return true;
	}

	/**
	 * Verifie que la colonne puisse etre decale a gauche.
	 * @param j indice de la colonne.
	 * @return true si la colonne ne peut pas
	 * etre modifie,false sinon.
	 */
	private boolean columnCannotChange(int j){
		for(int i=bottomOfFixedBlock(board.getLength()-1,j);i<board.getLength();i++){
			if(!getCase(i,j).isEmpty() && !getCase(i,j).isFixedBlock()
			&& !board.outOfBound(i,j-1) && !getCase(i,j-1).isEmpty()){
				return true;
			}
		}
		return false;
	}

	/**
	 * Fais le decalage des colonnes.
	 * @param numCol numero de la colonne.
	 */
	private void leftColumnChange(int numCol){
		for(int j=1;j<board.getWidth();j++){
			if(j>numCol && !columnCannotChange(j)){
				for(int i=bottomOfFixedBlock(board.getLength()-1,j);i<board.getLength();i++){
					if(!getCase(i,j).isFixedBlock()
						&& getCase(i,j-1).isEmpty()){
						Element e = getCase(i,j).getElement();
						board.cleanCase(i,j);
						board.fillCase(i,j-1,e);
					}
				}
			}
		}

	}

	/**
	 * Permet de mettre a jour les colonnes(decalage a gauche des colonnes).
	 */
	private void updateCollumn(){
		updatePos();
		for(int j=board.getWidth()-1;j>=0;j--){
			if(collumnIsEmpty(j)){
				leftColumnChange(j);
			}
		}
		updatePos();
	}

	/**
	 * Recupere le bloc fixe du haut le plus proche.
	 * @param x indice de la ligne.
	 * @param y indice de la colonne.
	 * @return retourne l indice de la ligne
	 * en dessous d un bloc fixe.
	 */
	private int bottomOfFixedBlock(int x,int y){
		for(int i=x-1;i>=0;i--){
			if(getCase(i,y).isFixedBlock()){
				return i+1;
			}
		}
		return 0;
	}

	/**
	 * Recupere le bloc fixe du bas le plus proche.
	 * @param x indice de la ligne.
	 * @param y indice de la colonne.
	 * @return retourne l indice de la ligne
	 * au dessus d un bloc fixe.
	 */
	private int topOfBlockFixed(int x,int y){
		for(int i=x;i<board.getLength();i++){
			if(getCase(i,y).isFixedBlock()){
				return i-1;
			}
		}
		return board.getLength()-1;
	}

	/**
	 * Verifie que le block au dessus
	 * est un bloc fixe.
	 * @param i indice de la ligne.
	 * @param j indice de la colonne.
	 * @return true si l element est
	 * un bloc fixe,false sinon.
	 */
	private boolean fixedBlockTop(int i,int j){
		if(i-1<0)return false;
		return getCase(i-1,j).isFixedBlock();
	}



	/**
	 * Calcul la profondeur d'une colonne.
	 * @param i indice de la ligne.
	 * @param j indice de la colonne.
	 * @return l indice du bloc le plus bas
	 * sur la colonne donne.
	 */
	private int checkProfondeur(int i,int j){
		if(collumnIsEmpty(j)) topOfBlockFixed(i,j);
		if(getCase(i,j).isEmpty()){
			while(!board.outOfBound(i,j) && getCase(i,j).isEmpty()){
				i++;
			}
		}else{
			while(!board.outOfBound(i,j) && !getCase(i,j).isEmpty()){
				i--;
			}
		}
		return i-1;
	} 

	/**
	 * Decale les colonnes qui sont sur des blocs fixes.
	 */
	private void columnOnBlockFixed(){
		for(int i=board.getLength()-1;i>=0;i--){
			for(int j=board.getWidth()-1;j>=0;j--){
				if(!getCase(i,j).isEmpty() && !getCase(i,j).isFixedBlock()
						&& !board.outOfBound(i+1,j) && !board.outOfBound(i+1,j-1)
						&& getCase(i+1,j).isFixedBlock()
					&& getCase(i+1,j-1).isEmpty() && checkProfondeur(bottomOfFixedBlock(i,j),j-1)>=i-1){

					while(!getCase(i,j).isEmpty()){
						Element e = getCase(i,j).getElement();
						board.cleanCase(i,j);
						board.fillCase(checkProfondeur(i,j-1),j-1,e);
						updatePos();
					}
				}
				if(!board.outOfBound(i+1,j) && !board.outOfBound(i,j+1) && !board.outOfBound(i+1,j+1)
						&& getCase(i,j).isEmpty() && !getCase(i,j+1).isEmpty() && !getCase(i,j+1).isFixedBlock()
					&& getCase(i+1,j).isFixedBlock() && getCase(i+1,j+1).isFixedBlock()
						&& checkProfondeur(bottomOfFixedBlock(i,j),j-1)>=i-1){
					int col = j;
					while(!board.outOfBound(i+1,col+1) && getCase(i+1,col+1).isFixedBlock()){
						for(int h = i;h>=bottomOfFixedBlock(i,j);h--){
							Element e = getCase(h,col+1).getElement();
							board.cleanCase(h,col+1);
							board.fillCase(h,col,e);
						}
						col++;
					}
				}
			}	
		}	
	}

	/**
	 * Met a jour la colonne.
	 */
	private void updatePos(){
		for(int i=board.getLength()-1;i>=0;i--){
			for(int j=board.getWidth()-1;j>=0;j--){
				if(getCase(i,j).isEmpty()){
					int tmp = bottomOfFixedBlock(i,j);
					while(!fixedBlockTop(i,j) && (checkColumn(tmp,i,j))&& getCase(i,j).isEmpty()){
						for(int h=i;h>tmp;h--){
						    Element e = getCase(h-1,j).getElement();
							board.cleanCase(h-1,j);
							board.fillCase(h,j,e);
						}
					}
				}
			}
		}
	}

	/**
	 * Verifie si il y a des blocks sur des cases fixes qu il faut deplacer.
	 * @return true si l on peut deplacer des blocs ou colonne qui sont
	 * sur des cases fixes.
	 */
	private boolean checkCaseNearFixedBlock(){
		for(int i=board.getLength()-1;i>=0;i--){
			for(int j=board.getWidth()-1;j>=0;j--){
				if(getCase(i,j).isFixedBlock() && !board.outOfBound(i,j-1) && 
				   getCase(i,j-1).isFixedBlock() && !board.outOfBound(i-1,j-1) && 
				   getCase(i-1,j-1).isEmpty() && !board.outOfBound(i-1,j) && 
				   !getCase(i-1,j).isEmpty() && !getCase(i-1,j).isFixedBlock()
						&& checkProfondeur(bottomOfFixedBlock(i,j),j-1)>=i-1){
					return true;
				}
				if(getCase(i,j).isFixedBlock() && !board.outOfBound(i,j-1) && 
				   getCase(i,j-1).isEmpty() && !board.outOfBound(i-1,j) && 
				   !getCase(i-1,j).isEmpty() && !getCase(i-1,j).isFixedBlock()
						&& checkProfondeur(bottomOfFixedBlock(i,j),j-1)>=i-1){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Met a jour le tableau.
	 */
	public void update(){
		updateCollumn();
		while(checkCaseNearFixedBlock()){
			columnOnBlockFixed();
		}
		updateCollumn();
	}
}