package com.display.textual;

import com.gamecomponent.board.Goal;

/**
 * Gere l affichage des objectifs.
 * @version 1.0
 */
public class PrintGoal{
	private final Goal goal;

	/**
	 * Pour l affichage des objectifs.
	 * @param goal
	 */
	public PrintGoal(Goal goal){
		this.goal = goal;
	}

	/**
	 * Produit des espaces vides.
	 * @param n nombre d espace a effectue.
	 */
	private void printSpace(int n){
		for(int i=0;i<n;i++)System.out.print(" ");
	}

	/**
	 * Affichage du score.
	 * @param maxJ nombre maximal de tiret a mettre.
	 */
	private void loopForScore(int maxJ){
		String tmp = String.valueOf(goal.getScore());
		while(tmp.length()<6){
			tmp = "0"+tmp;
		}
		int c = 0;
		for(int j=0;j<maxJ;j++){
			if(j%2==0){
				System.out.print("|");
			}else{
				System.out.print(tmp.charAt(c));
				c+=1;
			}			
		}
	}

	/**
	 * Affiche le nombre d'animaux sauves et
	 * le nombre d etoile remporte.
	 * @param maxJ nombre de caractere maximal a inserer.
	 */
	private void loopForOther(int maxJ){
		for (int j=0;j<maxJ;j++) {
			if(j==0 || j==maxJ-6){
				System.out.print("|");
			}else if(j==3){
				printSpace(1);
				System.out.print("Number of animal: " + goal.getSaveAnimal() 
				+ "/" + goal.getNbMinAnimal());
				printSpace(4);
				System.out.print("Number of star: " + goal.numberOfStar()
				+ "/" + 3);
				printSpace(1);
			}	
		}
	}

	/**
	 * Affiche l objectif demande en parametre.
	 * @param n nombre d espace.
	 * @param maxI nombre maximal de ligne du contenu.
	 * @param maxJ nombre maximal de colonne pour le contenu.
	 * @param value1 pour inserer un '*'.
	 * @param value2 pour inserer un '*'.
	 * @param type affiche un contenu different en fonction du type.
	 */
	private void loopForContent(int n,int maxI,int maxJ,int value1,int value2, String type){
		for(int i=0;i<maxI;i++){
			printSpace(n);
			if(i%2==1){
				if(type.equals("Score"))loopForScore(maxJ);
				if(type.equals("Other"))loopForOther(maxJ);
				System.out.println();
			}else{
				for (int j=0;j<maxJ;j++) {
					if(j==value1 || j==value2){
						System.out.print("*");
					}else{
						System.out.print("-");
					}
				}
				System.out.println();
			}
		}
	}

	/**
	 * Affichage des objectifs.
	 */
	public void print(){
		loopForContent(26,3,13,0,12,"Score");
		loopForContent(8,3,48,0,47,"Other");
	}
}