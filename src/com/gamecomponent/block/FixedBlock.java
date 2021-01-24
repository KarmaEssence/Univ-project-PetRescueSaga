package com.gamecomponent.block;

/**
 * Correspond au bloc fixe.
 * @version 1.0
 */
public class FixedBlock extends Block {

	/**
	 * Construit un bloc fixe.
	 */
	public FixedBlock(){
		super("Fixed");
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
		return true;
	}
}