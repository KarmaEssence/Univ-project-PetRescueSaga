package com.gamecomponent.board;

import com.gamecomponent.animal.Cat;
import com.gamecomponent.Element;
import com.gamecomponent.animal.Dog;
import com.gamecomponent.block.*;

import java.util.Random;

/**
 * Correspond au plateau.
 * @version 1.0
 */
public class Board{
	private final int length;
	private final int width;
	private Case[][] tab;
	private final int[][] configuration;

	/**
	 * Construit un plateau.
	 * @param length la longueur du plateau.
	 * @param width la largeur du plateau.
	 * @param configuration configuration du plateau.
	 */
	public Board(int length,int width,int[][] configuration){
		this.length = length;
		this.width = width;
		tab = new Case[length][width];
		for(int i=0;i<tab.length;i++){
			for(int j=0;j<tab[i].length;j++){
				tab[i][j] = new Case();
			}
		}
		this.configuration = configuration;

	}

	/**
	 * Renvoie la longueur du plateau.
	 * @return longueur du plateau.
	 */
	public int getLength(){
		return length;
	}

	/**
	 * Renvoie la largeur du plateau.
	 * @return largeur du plateau.
	 */
	public int getWidth(){
		return width;
	}

	/**
	 * Verifie que les coordonnees sont bien.
	 * celle d un element du plateau.
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 * @return true si les coordonnees sont dans le plateau.
	 */
	public boolean outOfBound(int x,int y){
		return (x<0 || y<0 || x>=length || y>=width);
	}

	/**
	 * Retourne une case du plateau.
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 * @return la case en fonction des coordonnees choisie.
	 */
	public Case getCase(int x,int y){
		return tab[x][y];
	}



	/**
	 * Vide la case specifie.
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 */
	public void cleanCase(int x,int y){
		tab[x][y].removeElement();
	}

	/**
	 * Rempli la case spécifier avec un element
	 * @param x ligne dans le plateau.
	 * @param y colonne dans le plateau.
	 * @param e element que l on veut ajouter.
	 */
	public void fillCase(int x, int y, Element e){
		tab[x][y].addElement(e);
	}

	/**
	 * Clone le board pour le robot.
	 * @return une copie du plateau.
	 */
	public Board clone(){
		Board res = new Board(length,width,configuration);
		for(int i=0;i<tab.length;i++){
			for(int j=0;j<tab[i].length;j++){
				try{
					res.tab[i][j] = (Case)tab[i][j].clone();
				}catch(CloneNotSupportedException e){
					e.printStackTrace();
				}
			}
		}
		return res;
	}



	/**
	 * Place les blocs fixes sur le plateau.
	 */
	private void setFixedBlock(){
		for(int i=0;i<configuration.length;i++){
			for(int j=0;j<configuration[i].length;j++){
				if(configuration[i][j] == 5){
					Element e = new FixedBlock();
					fillCase(i,j,e);
				}
			}
		}
	}



	/**
	 * Verifie si une couleur est deja presente,
	 * utilise avec BlockColor().
	 * @param rand une couleur aleatoire,c est a dire
	 *             un entier entre 1 et 4.
	 * @param color tableau des couleurs representant
	 *              l ensemble des 4 couleurs du jeu.
	 * @return true si la couleur est deja dans le tableau,
	 * false sinon.
	 */
	private static boolean colorInTable(int rand,int[] color){
		for(int i=0;i<color.length;i++){
			if(rand == color[i]){
				return true;
			}
		}
		return false;
	}



	/**
	 * Attribution des couleurs pour chaque pattern de bloc.
	 * @return un tableau contenant l ordre des couleurs repartis
	 * aleatoirement.
	 */
	private int[] BlockColor(){
		int[] color = new int[4];
		Random random = new Random();
		int rand = 0;
		int i =0;
		while(i < color.length){
			rand = random.nextInt(4)+1;
			if(!colorInTable(rand,color)){
				color[i] = rand;
				i++;
			}
		}
		return color;
	}



	/**
	 * Place les blocks abstraits sur le plateau.
	 */
	private void setAbstractBlock(){
		for(int i=0;i<configuration.length;i++){
			for(int j=0;j<configuration[i].length;j++){
				if(configuration[i][j]==0){
					Element e = new AbstractBlock();
					fillCase(i,j,e);
				}
			}
		}
	}

	/**
	 * Place les animaux sur le plateau.
	 */
	private void setAnimal(){
		Random random = new Random();
		int rand = 0;
		for(int i=0;i<configuration.length;i++) {
			for(int j=0;j<configuration[i].length;j++){
				if(configuration[i][j] ==6){
					Element tmp = null;
					rand = random.nextInt(2);
					switch(rand){
						case 0: tmp = new Dog();break;
						case 1: tmp = new Cat();break;
					}
					tab[i][j].addElement(tmp);
				}
			}
		}
		
	}

	/**
	 * Place tous les elements sur le plateau.
	 */
	public void setBoard(){
		setAbstractBlock();
		setFixedBlock();
		setAnimal();
		int[] color = BlockColor();
		int patern = 0;
		for(int i=0;i<length;i++){
			for(int j=0;j<width;j++){
				if(tab[i][j].isEmpty()){
					patern = configuration[i][j] -1;
					Element tmp = null;
					switch(color[patern]){
						case 1: tmp = new RedBlock();break;
						case 2: tmp = new BlueBlock();break;
						case 3: tmp = new GreenBlock();break;
						case 4: tmp = new YellowBlock();break;
					}
					tab[i][j].addElement(tmp);
				}
			}
		}
	}
}	