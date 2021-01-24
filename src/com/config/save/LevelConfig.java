package com.config.save;

import java.io.Serializable;

/**
 * Gere la recuperation et la sauvegarde des niveaux.
 * @version 1.0
 */
public class LevelConfig implements Serializable{

	private final int maxScore;
	private final int nbMinAnimal;
	private final int nbAnimal ;
	private final int length;
	private final int width;
	private final int[][] configuration;
	private final int number;
	private static int totalNumber;

	/**
	 * Construit une configuration de niveau.
	 * @param nbMinAnimal nombre minimal d animal a sauver dans le niveau.
	 * @param length la longueur du plateau.
	 * @param width la largeur du plateau.
	 * @param configuration configuration du plateau.
	 * @param nbAnimal nombre maximal d animal a sauver dans le niveau.
	 */
	public LevelConfig(int nbMinAnimal, int length, int width, int[][] configuration, int nbAnimal){
		this.nbMinAnimal = nbMinAnimal;
		this.length = length;
		this.width = width;
		this.configuration = configuration;
		totalNumber++;
		number = totalNumber;
		this.nbAnimal = nbAnimal;
		this.maxScore = obtainsMaxScorePerLevel();
	}

	/**
	 * Calcule le score maximum possible et le sauvegarde.
	 * @return le score maximum du niveau.
	 */
	private int obtainsMaxScorePerLevel(){
		int res = 0;
		for(int i=0;i<configuration.length;i++){
			for(int j=0;j<configuration[i].length;j++){
				if(configuration[i][j] == 6){
					res += 1000;
				}else if(configuration[i][j] != 6 &&
						configuration[i][j] != 5 &&
						configuration[i][j] != 0){
					res += 10;
				}
			}
		}
		return res;
	}

	/**
	 * Retourne le score maximal.
	 * @return le score maximal du niveau.
	 */
	public int getMaxScore(){
		return maxScore;
	}

	/**
	 * Retourne le nombre minimal d animaux a sauver.
	 * @return nombre minimal d animal a sauver dans le niveau.
	 */
	public int getNbMinAnimal(){
		return nbMinAnimal;
	}

	/**
	 * Retourne la longueur du plateau.
	 * @return la longueur du plateau.
	 */
	public int getLength(){
		return length;
	}

	/**
	 * Retourne la largeur du plateau.
	 * @return la largeur du plateau.
	 */
	public int getWidth(){
		return width;
	}

	/**
	 * Retourne le contenu du plateau
	 * @return configuration du plateau.
	 */
	public int[][] getConfiguration(){
		return configuration;
	}

	/**
	 * Retourne le numero du niveau.
	 * @return numero du niveau
	 */
	public int getNumber(){
		return number;
	}

	/**
	 * Retourne le nombre maximal d animaux du niveau.
	 * @return nombre maximal d animal a sauver dans le niveau.
	 */
	public int getNbAnimal(){
		return nbAnimal;
	}

	/**
	 * Sauvegarde une configuration de niveau.
	 * @param index indice du niveau.
	 */
	public void saveConfig(int index){
		SaveData.saveConfig(this,"LevelData","Level",index);
	}

	/**
	 * Recupere la configuration d un niveau.
	 * @param folder dossier contenant la configuration.
	 * @param file fichier contenant les donnees du niveau.
	 * @return renvoie la configuration d un niveau.
	 */
	public static LevelConfig getConfig(String folder,String file){
		return (LevelConfig)SaveData.getConfig(folder,file);
	}


}