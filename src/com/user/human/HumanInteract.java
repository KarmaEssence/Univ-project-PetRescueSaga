package com.user.human;

import com.config.save.PlayerConfig;
import com.user.Player;

/**
 * Represente les choix de l utilisateur.
 * @version 1.0
 */
public interface HumanInteract {

	/**
	 * Demande au joueur s il veut jouer.
	 * @return true s il veut joueur,
	 * false sinon.
	 */
	boolean chooseToPlay();

	/**
	 * Position de l element.
	 * @return la position de l element.
	 */
	int[] position();

	/**
	 * Permet de choisir un niveau.
	 * @param numMaxLevel le nombre maximal de niveau.
	 * @return le numero du niveau choisi
	 */
	int choiceOfLevel(int numMaxLevel);

	/**
	 * Pose une question a l utilisateur.
	 * @param sentence une question.
	 * @param endOfGame regarde si ce sont les
	 *                  dernieres questions.
	 * @return true si la reponse est favorable.
	 */
	boolean ask(String sentence,boolean endOfGame);

	/**
	 * Pose une question a chaque etape du niveau.
	 * @param sentence la question.
	 * @return l une des trois options.
	 */
	String askPerStep(String sentence);

	/**
	 * Demande la sauvegarde a choisir.
	 * @param saveOfSomething toutes les sauvegardes des parties
	 *                        disponibles.
	 * @return la reponse du joueur.
	 */
	String askToChooseSave(PlayerConfig[] saveOfSomething);

	/**
	 * Renvoie le type de joueur en fonction du choix de l utilisateur.
	 * @return Player
	 */
	Player choiceOfPlayer();

	/**
	 * Termine les choix de l utilisateur.
	 */
	void close();
}