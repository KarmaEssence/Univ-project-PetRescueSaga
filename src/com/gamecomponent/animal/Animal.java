package com.gamecomponent.animal;

import com.gamecomponent.Element;

/**
 * Correspond au animaux.
 * @version 1.0
 */
public abstract class Animal implements Element {
	private final String type;

	/**
	 * Cree un element de type animal.
	 * @param type type de l animal.
	 */
	public Animal(String type){
		this.type = type;
	}

	/**
	 * Renvoie La premiere lettre du block en minuscule.
	 * @return la premiere lettre du block en minuscule.
	 */
	public String toString(){
		return (type.length()>0)?type.toLowerCase().substring(0,1):"";
	}

	/**
	 * Regarde si l element est destructible.
	 * @return true si l element est destructible,false sinon.
	 */
	public boolean isDestroyable(){
		return false;
	}

	/**
	 * Regarde si l element est fixe.
	 * @return true si l element est fixe,false sinon.
	 */
	public boolean isFixed(){
		return false;
	}
}