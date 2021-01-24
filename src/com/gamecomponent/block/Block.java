package com.gamecomponent.block;

import com.gamecomponent.Element;

/**
 * Correspond au bloc.
 * @version 1.0
 */
public abstract class Block implements Element {
	public final String color;

	/**
	 * Cree un element de type animal.
	 * @param color couleur du block.
	 */
	public Block(String color){
		this.color = color;
	}

	/**
	 * Renvoie La premiere lettre du block en majuscule.
	 * @return la premiere lettre du block en majuscule.
	 */
	public String toString(){
		return (color.length()>0)?color.toUpperCase().substring(0,1):"";
	}

	/**
	 * Regarde si l element est destructible.
	 * @return true si l element est destructible,false sinon.
	 */
	public boolean isDestroyable(){
		return true;
	}

	/**
	 * Regarde si l element est fixe.
	 * @return true si l element est fixe,false sinon.
	 */
	public boolean isFixed(){
		return false;
	}
}