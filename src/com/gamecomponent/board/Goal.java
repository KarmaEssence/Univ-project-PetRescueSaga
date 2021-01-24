package com.gamecomponent.board;

/**
 * Correspond aux objectifs du niveau.
 * @version 1.0
 */
public class Goal implements Cloneable{
	private final int nbAnimal;
	private final int maxScore;
	private final int nbMinAnimal;
	private int saveAnimal;
	private int score;
	private boolean completed;

	/**
	 * Construit un objet contenant les objectifs du niveau.
	 * @param nbAnimal nombre d animal du niveau.
	 * @param maxScore le score maximal a obtenir.
	 * @param nbMinAnimal nombre minimal d animal a sauver.
	 */
	public Goal(int nbAnimal,int maxScore,int nbMinAnimal){
		this.nbAnimal = (nbAnimal>0)?nbAnimal:3;
		this.maxScore = (maxScore>0)?maxScore:100;
		this.nbMinAnimal = nbMinAnimal;
		score = 0;
		saveAnimal = 0;
	}

	/**
	 * Retourne le score maximale.
	 * @return le score maximal.
	 */
	public int getMaxScore(){
		return maxScore;
	}

	/**
	 * Retourne le nombre minimale d animaux a sauver.
	 * @return le nombre minimal d animaux a sauver.
	 */
	public int getNbMinAnimal(){
		return nbMinAnimal;
	}

	/**
	 * Retourne le nombre d animaux sauves.
	 * @return le nombre d animaux sauves.
	 */
	public int getSaveAnimal(){
		return saveAnimal;
	}

	/**
	 * Retourne le score courant.
	 * @return le score courant.
	 */
	public int getScore(){
		return score;
	}

	/**
	 * Regarde si les objectifs minimals sont accomplis.
	 * @return true si les objectifs minimals sont accomplis,
	 * false sinon.
	 */
	public boolean isCompleted(){
		return completed;
	}

	/**
	 * Met a jour les objectifs.
	 * @param tab contenant le nombre de bloc detruit
	 *            et le nombre d animaux sauves.
	 */
	public void updateGoal(int[] tab){
		saveAnimal+= tab[0];
		score+=tab[1]*(tab[1]*10)+tab[0]*1000;
		if(saveAnimal >= nbMinAnimal)completed = true;
	}

	/**
	 * Compte le nombre d etoiles gagnees.
	 * @return le nombre d etoile obtenu.
	 */
	public int numberOfStar(){
		int score = getScore();
		int maxScore = getMaxScore();
		int res = 0;
		if(score>=maxScore/3)res=1;
		if(score>=maxScore/2)res=2;
		if(score>=maxScore/1)res=3;
		return res;
	}

	/**
	 * Clone la liste des objectifs.
	 * @return une copie des objectifs.
	 * @throws CloneNotSupportedException si la liste
	 * des objectifs n a pas ete clone.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		return super.clone();
	}
}