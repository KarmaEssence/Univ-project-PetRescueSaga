package com.config.game;

import com.display.GeneralView;
import com.gamecomponent.board.Goal;
import com.config.save.LevelConfig;
import com.gamecomponent.update.ActionBoard;
import com.gamecomponent.board.Board;

/**
 * Represente le niveau que l utilisateur va jouer.
 * @version 1.0
 */
public class Level {
	private final GeneralView generalView;
	private Goal goal;
	private Board board;
	private LevelStatus levelStatus;
	private final ActionBoard actionBoard;
	private final int numLevel;
	private boolean finish = false;
	private boolean analyzeMode = false;

	/**
	 * Construit un niveau a partir d une vue globale et d une configuration.
	 * @param generalView vue generale.
	 * @param lc configuration du niveau.
	 */
	public Level(GeneralView generalView, LevelConfig lc) {
		numLevel = lc.getNumber();
		goal = new Goal(lc.getNbAnimal(), lc.getMaxScore(), lc.getNbMinAnimal());
		int[] size = checkSize(lc.getLength(), lc.getWidth());
		board = new Board(size[0], size[1], lc.getConfiguration());
		actionBoard = new ActionBoard(board);
		this.generalView = generalView;
	}

	/**
	 * Verifie que la taille du plateau donne en parametre ne soit
	 * ni trop petit, ni trop grand.
	 * @param length longueur du plateau.
	 * @param width  largeur du plateau.
	 * @return les dimensions du plateau.
	 */
	public static int[] checkSize(int length, int width) {
		int[] res = new int[2];
		if (length < 4 || length > 12) length = 7;
		if (width < 4 || width > 12) width = 7;
		res[0] = length;
		res[1] = width;
		return res;
	}

	/**
	 * Renvoie l objectif courant.
	 * @return l objectif courant.
	 */
	public Goal getGoal(){return goal;}

	/**
	 * Renvoie le numero du niveau.
	 * @return le numero du niveau.
	 */
	public int getNumLevel(){return numLevel;}

	/**
	 * Verifie si le niveau est termine.
	 * @return true si le niveau est fini, false sinon.
	 */
	public boolean isFinish() {
		return finish;
	}

	/**
	 * Verification des objectifs.
	 * @return verifie si les objectifs sont accomplies.
	 */
	public boolean checkGoal() {
		return goal.isCompleted();
	}

	/**
	 * Initialisation des blocks et des animaux,
	 * et initialisation de l analyze mode.
	 */
	public void initializeLevel() {
		finish = false;
		board.setBoard();
		generalView.setPrintLevel(goal, board, numLevel);
		levelStatus = new LevelStatus();
		analyzeMode= generalView.ask("Do you want play the level step by step?(y/n)",
				false);
		saveNewLevelStep();
	}

	/**
	 * L utilisateur joue le niveau.
	 */
	public void playLevel(){
		generalView.print();
		while (!checkGoal() && !finish) {
			if (actionBoard.boardIsPlayable()) {
				generalView.robotSetBoard(board);
				int[] tab = generalView.getPosition();
				int[] res = obtainsResult(tab);
				goal.updateGoal(res);
				if (res.length > 0) generalView.print();
				if(res[1]!=0) {
					makeLevelStepByStep();
				}
			} else {
				finish = true;
			}
		}
		generalView.summarizeLevel(true);
		finish = true;
	}

	/**
	 * Pour faire le niveau par etape,
	 * utile pour corriger du code.
	 */
	private void makeLevelStepByStep(){
		if(analyzeMode && !goal.isCompleted() && levelStatus.getSize()>0){
			String userAnswer = generalView.askPerStep("Do you want continue,quit or return "
					+ "to the last step?(quit,cont,last)");
			executeUserOrder(userAnswer);
		}
	}

	/**
	 * Sauvegarde l etape courante.
	 */
	private void saveNewLevelStep(){
		try{
			levelStatus.includeNewStep(board,goal);
		}catch (CloneNotSupportedException e){
			e.printStackTrace();
		}
	}

	/**
	 * Verifie que le resultat obtenu ne soit pas une erreur.
	 * @param tab position d une case sur le plateau.
	 * @return le nombre de block detruits et d animaux sauves.
	 */
	private int[] obtainsResult(int[] tab) {
		try {
			int[] res = actionBoard.actionOnBoard(tab[0], tab[1]);
			return res;
		} catch (IllegalStateException e){
			generalView.makeAnErrorMessage(1);
		} catch(IndexOutOfBoundsException e){
			generalView.makeAnErrorMessage(2);
		}
		return new int[2];
	}

	/**
	 * Applique la reponse de l utilisateur.
	 * @param answer reponse de l utilisateur.
	 */
	private void executeUserOrder(String answer){
		switch (answer){
			case "quit": finish=true; break;
			case "last":
				Object[] change = levelStatus.getLastStep();
				board = (Board) change[0];
				goal = (Goal) change[1];
				actionBoard.setBoard(board);
				generalView.setPrintLevel(goal, board, numLevel);
				generalView.print();
				makeLevelStepByStep();
				if (levelStatus.isEmpty())saveNewLevelStep();
				break;
			case "cont": saveNewLevelStep();break;
		}
	}
}