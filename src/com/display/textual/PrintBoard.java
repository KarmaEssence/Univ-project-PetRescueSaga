package com.display.textual;

import com.gamecomponent.board.Board;

/**
 * Gere l affichage du plateau.
 * @version 1.0
 */
public class PrintBoard{
	private final Board board;

	/**
	 * Pour afficher le plateau.
	 * @param board le plateau courant.
	 */
	public PrintBoard(Board board){
		this.board = board;
	}

	/**
	 * Produit des espaces vides.
	 * @param n nombre d'espace.
	 */
	private void printSpace(int n){
		for(int i=0;i<n;i++)System.out.print(" ");
	}

	/**
	 * Renvoie le nombre d espace a gauche en fonction de la taille du plateau.
	 * @param leftSpace nombre d'espace gauche.
	 * @param initialLengthContent taille du contenu initial.
	 * @return renvoie le nombre d espace a effectue.
	 */
	private int findNbLeftSpace(int leftSpace,int initialLengthContent){
		if(board.getWidth() == initialLengthContent) return leftSpace;
		if(board.getWidth()<initialLengthContent){
			for(int i=board.getWidth();i<initialLengthContent;i++)leftSpace+=1;
		}else{
			for(int i=initialLengthContent;i<board.getWidth();i++)leftSpace-=1;
		}
		return leftSpace;

	}

	/**
	 * Affiche la partie au dessus du plateau.
	 */
	private void printTop(){
		char letter = 65;
		for(int i = 0;i<2;i++){
			int leftSpace = findNbLeftSpace(20,9);
			printSpace(leftSpace);
			if(i%2 == 0){
				printSpace(4);
				for(int j=0;j<board.getWidth();j++){
					System.out.print(letter + " ");
					letter+=1;
				}
			}else{
				printSpace(2);
				for(int j=0;j<board.getWidth()*2+3;j++){
					if((j==0)||(j==board.getWidth()*2+2)){
						System.out.print("*");
					}else{
						System.out.print("-");	
					}
				}
			}
			System.out.println();
		}
	}

	/**
	 * 	Affiche la partie en dessous
	 * 	du plateau.
	 */
	private void printBottom(){
		int leftSpace = findNbLeftSpace(22,9);
		printSpace(leftSpace);
		for(int j=0;j<board.getWidth()*2+3;j++){
			if((j==0)||(j==board.getWidth()*2+2)){
				System.out.print("*");
			}else{
				System.out.print("-");	
			}
		}
		System.out.println();
	}

	/**
	 * Affiche le plateau.
	 */
	public void print(){
		printTop();
		for(int i=0;i<board.getLength();i++){
			for(int j=-1;j<board.getWidth()+1;j++){
				if(j==-1){
					int leftSpace = findNbLeftSpace(20,9);
					printSpace(leftSpace);
					System.out.print((i+1)+" | ");
				}else if(j>-1 && j<board.getWidth()){
					System.out.print(board.getCase(i,j).toString() + " ");
				}else{
					System.out.print("|");
				}
			}
			System.out.println();
		}
		printBottom();
	}
}