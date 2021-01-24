package com.config.game;

import com.config.save.LevelConfig;
import com.config.save.PlayerConfig;
import com.config.save.SaveData;
import com.display.GeneralView;

import java.util.ArrayList;
import java.io.File;

/**
 * Permet de faire la gestion des niveaux.
 * @version 1.0
 */
public class Game{
	private ArrayList<Level> listOfLevel;
	private final GeneralView generalView;
	private int actualLevel;
	private boolean endOfGame;
	private PlayerConfig playerConfig;

	/**
	 * Construit le jeu avec une liste vide
	 * de niveau et applique la vue.
	 * @param gv vue generale.
	 */
	public Game(GeneralView gv){
		listOfLevel = new ArrayList<>();
		generalView = gv;
		actualLevel = 0;
	}

	/**
	 * Etablie la sauvegarde choisie.
	 * @param playerConfig
	 */
	public void setSave(PlayerConfig playerConfig){
		this.playerConfig = playerConfig;
	}


	/**
	 * Initialise la liste des niveaux a partir des fichiers dans LevelData.
	 * Si le repertoire ou les fichiers n existe pas, alors les genere.
	 */
	private void initialize(){
		int i = 1;
		File levelConfig = SaveData.completeFolder();
		int max = levelConfig.listFiles().length;
		while(i<=max){
			LevelConfig lc = LevelConfig.getConfig("LevelData","Level"+i);
			if(lc!=null){
				Level l = new Level(generalView,lc);
				listOfLevel.add(l);	
			}
			i++;
		}
	}

	/**
	 * Recharge la liste des niveaux.
	 * Utilise lorsque l on recommence le meme niveau.
	 */
	private void reloadLevel(){
		listOfLevel.clear();
		initialize();
	}

	/**
	 * La partie commencera ici,
	 * les transitions entre deux niveaux se feront ici
	 * et termine le jeu lorsque l utilisateur le specifie.
	 * @param choiceOfLevel true lorsque l utilisateur doit
	 *                      choisir le niveau, false sinon.
	 */
	public void playGame(boolean choiceOfLevel){
		reloadLevel();
		int num = actualLevel;
		endOfGame = false;
		if(choiceOfLevel){
			num = generalView.choiceOfLevel(listOfLevel.size());
			actualLevel = num;
		}
		if(num<=listOfLevel.size() || num>=0){
			Level l = listOfLevel.get(num);
			l.initializeLevel();
			generalView.summarizeLevel(false);
			while(!l.checkGoal() && !l.isFinish()){
				l.playLevel();
				if(l.isFinish() && !l.checkGoal() && 
				generalView.ask("Do you want retry ?(y/n): ",true)){
					playGame(false);
				}
			}
			playerConfig.saveResults(l);
			if(!endOfGame && actualLevel!=listOfLevel.size()-1 &&
					generalView.ask("Do you want to do the next level ?(y/n): ",
					true)){
				actualLevel++;
				playGame(false);
			}
			if(!endOfGame && generalView.ask("Do you want choose the level ?(y/n): ",true)){
				playGame(true);
			}
		}else{
			generalView.makeAnErrorMessage(0);
			playGame(true);
		}
		
	}
}