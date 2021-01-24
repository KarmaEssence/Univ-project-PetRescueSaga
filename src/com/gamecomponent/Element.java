package com.gamecomponent;

/**
 * Correspond au element qui vont etre
 * place sur le plateau.
 * @version 1.0
 */
public interface Element{

	/**
	 * Renvoie La premiere lettre du block en minuscule.
	 * @return la premiere lettre du block en minuscule.
	 */
	String toString();

	/**
	 * Regarde si l element est destructible.
	 * @return true si l element est destructible,false sinon.
	 */
	boolean isDestroyable();

	/**
	 * Regarde si l element est fixe.
	 * @return true si l element est fixe,false sinon.
	 */
	boolean isFixed();
}