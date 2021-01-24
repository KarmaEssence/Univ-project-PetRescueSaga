package com.gamecomponent.board;

import com.gamecomponent.Element;
import com.gamecomponent.animal.Animal;

/**
 * Correspond au case d un plateau.
 * @version 1.0
 */
public class Case implements Cloneable{
	private Element element;

	/**
	 * Cree une case vide.
	 */
	public Case(){
		element = null;
	}

	/**
	 * Clone de la case pour le robot.
	 * @return une copie de la case.
	 * @throws CloneNotSupportedException Si l objet n a
	 * pas pu etre clone.
	 */
	@Override
	public Object clone()throws CloneNotSupportedException{
		return super.clone();
	}

	/**
	 * Retourne l element courant.
	 * @return l element courant.
	 */
	public Element getElement(){
		return element;
	}

	/**
	 * Retourne le type de l element courant.
	 * @return le type de l element courant.
	 */
	public String getTypeOfElement(){
		if(!isEmpty()){
			return element.getClass().getName();
		}
		return " ";	
	}

	/**
	 * Ajoute un element.
	 * @param e element.
	 */
	public void addElement(Element e){
		element = e;
	}

	/**
	 * Supprime l element.
	 */
	public void removeElement(){
		element = null;
	}

	/**
	 * Regarde si l element est null.
	 * @return true si la case ne contient
	 * pas d element, false sinon.
	 */
	public boolean isEmpty(){
		return element==null;
	}

	/**
	 * Verifie si l element est un animal.
	 * @return true si l element est un animal.
	 */
	public boolean isAnimal(){
		return element instanceof Animal;
	}

	/**
	 * Verifie si l element est un block fixe.
	 * @return true si l element est un block fixe.
	 */
	public boolean isFixedBlock(){
		if(element == null) return false;
		return element.isFixed();	
	}

	/**
	 * Affiche la description de la case.
	 * @return une description de la case.
	 */
	public String toString(){
		if(isEmpty()){
			return "-";
		}else{
			return element.toString();
		}
	}
}