package com.display.textual;

import com.gamecomponent.board.Goal;
import com.gamecomponent.board.Board;

/**
 * Gere l affichage du niveau
 * @version 1.0
 */
public class PrintLevel{
	private final int numLevel;
	private final Goal goal;
	private final PrintGoal printGoal;
	private final PrintBoard printBoard;

	/**
	 * Permet d affiche le niveau.
	 * @param goal les objectifs.
	 * @param board les plateau.
	 * @param num le numero du niveau.
	 */
	public PrintLevel(Goal goal,Board board,int num){
		numLevel = num;
		this.goal = goal;
		printGoal = new PrintGoal(goal);
		printBoard = new PrintBoard(board);
	}

	/**
	 * Produit des espaces vides.
	 * @param n le nombre d espace a effectuer.
	 */
	private void printSpace(int n){
		for(int i=0;i<n;i++)System.out.print(" ");
	}

	/**
	 * Affiche une ligne de separation.
	 */
	private void printSepator(){
		for(int i=0;i<79;i++)System.out.print("-");
		System.out.println();
	}

	/**
	 * Affiche le type de l objectif dans le cadre.
	 * @param content contenu a afficher.
	 * @param leftSpace le nombre d espace gauche a effectuer.
	 * @param rightSpace le nombre d espace droite a effectuer.
	 */
	private void printSummarizeContent(String content,int leftSpace,int rightSpace){
		for(int j=0;j<3;j++){
			if(j==1){
				printSpace(leftSpace);
				System.out.print(content);
				printSpace(rightSpace);
			}else{
				System.out.print("|");
			}
		}
		System.out.println();
	}

	/**
	 * Renvoie le nombre d'espace a droite en fonction de la taille des objectifs.
	 * @param content utilise pour calculer les espaces droit.
	 * @param rightSpace utilise pour calculer les espaces droit.
	 * @param initialLengthContent taille initial du contenu.
	 * @return le nombre de caractere vide a droite.
	 */
	private int findNbRightSpace(String content,int rightSpace,int initialLengthContent){
		if(content.length() <= initialLengthContent) return rightSpace;
		for(int i=initialLengthContent;i<content.length();i++)rightSpace-=1;
		return (rightSpace>=0)?rightSpace:0;

	}

	/**
	 * Fais un cadre ou sont ranges les objectifs.
	 */
	private void printFrame(){
		for (int j=0;j<21;j++) {
			if(j==0 || j==20){
				System.out.print("*");
			}else{
				System.out.print("-");
			}
		}
		System.out.println();
	}

	/**
	 * Seulement quand le jeu est fini affiche un logo a cote
	 * des objectifs.
	 * @param subGoal le sous objectif.
	 * @param finish regarde si le niveau est fini.
	 * @param completed regarde si le sous objectif est complete.
	 * @return renvoie le logo correspond.
	 */
	private String logoGoal(String subGoal,boolean finish, boolean completed){
		if(!finish)return "";
		if(subGoal.equals("Score")){
			if(goal.getMaxScore()>goal.getScore()){
				return "x";
			}
		}
		return (completed)?"V":"x";
	}

	/**
	 * Affiche le resume d un niveau.
	 * @param finish si false affiche le resume
	 *               de debut de niveau,
	 *               si true affiche
	 *               le resume de fin de niveau.
	 */
	public void summarize(boolean finish){
		printSepator();
		int size = (finish)?7:5;
		String logoAnimal = logoGoal("Animal",finish,goal.isCompleted());
		String logoScore = logoGoal("Score",finish,goal.isCompleted());
		for(int i=0;i<size;i++){
			printSpace(22);
			int rightSpace = 0;
			int leftSpaceforlogo=0;
			if(i==1){
				rightSpace = findNbRightSpace("Level: " + numLevel,6,8);
				printSummarizeContent("Level: " + numLevel,5,rightSpace);
			}else if(finish && i==2){
				printSummarizeContent("Number of star: " + goal.numberOfStar()
				+ "/" + 3,0,0);
			}else if((!finish && i==2)||(finish && i==3)){
				leftSpaceforlogo = (finish)?1:2;
				rightSpace = findNbRightSpace("Save " + goal.getNbMinAnimal()+ " animal",3,13);
				printSummarizeContent(logoAnimal + " Save " + goal.getNbMinAnimal()+ " animal",leftSpaceforlogo,rightSpace);
			}else if((!finish && i==3)||(finish && i==4)){
				leftSpaceforlogo = (finish)?0:1;
				rightSpace = findNbRightSpace(" Get " + goal.getMaxScore() + " points",6,12);
				printSummarizeContent(logoScore + " Get " + goal.getMaxScore() + " points",leftSpaceforlogo,rightSpace);
			}else if(finish && i==5){
				rightSpace = findNbRightSpace("Score: " + goal.getScore(),6,8);
				printSummarizeContent("Score: " + goal.getScore(),5,rightSpace);
			}else{
				printFrame();	
			}
		}
		printSepator();
	}

	/**
	 * Affiche le plateau et
	 * les objectifs courant.
	 */
	public void print(){
		printGoal.print();
		printBoard.print();
	}
}