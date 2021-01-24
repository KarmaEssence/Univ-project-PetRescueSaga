package com.gamecomponent.block;

/**
 * Correspond au bloc du decors.
 * @version 1.0
 */
public class AbstractBlock extends FixedBlock {

	/**
	 * Construit un bloc abstrait.
	 */
	public AbstractBlock(){
		super();
	}

	/**
	 * Renvoie un caractere vide.
	 * @return un caractere vide.
	 */
	@Override
	public String toString(){
		return " ";
	}

	/**
	 * Regarde si l element est destructible.
	 * @return true si l element est destructible,false sinon.
	 */
	@Override
	public boolean isDestroyable(){
		return false;
	}

	/**
	 * Regarde si l element est fixe.
	 * @return true si l element est fixe,false sinon.
	 */
	@Override
	public boolean isFixed(){
		return true;
	}


}