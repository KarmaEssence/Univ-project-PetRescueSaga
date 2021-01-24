package com.launcher;

import com.config.game.Game;
import com.config.save.PlayerConfig;
import com.config.save.SaveData;
import com.display.GeneralView;
import com.user.Player;
import com.user.human.HumanPlayer;
import com.user.robot.Robot;


/**
 * Correspond au lanceur du jeu.
 * @version 1.0
 */
public class PetRescueSaga{

	private GeneralView generalView;
	private Game game;
	private PlayerConfig[] saveOfSomething;

	/**
	 * Construit un element qui va
	 * lancer le jeu.
	 */
	public PetRescueSaga(){
		generalView = new GeneralView();
		saveOfSomething = new PlayerConfig[3];
		for(int i=0;i<saveOfSomething.length;i++){
			saveOfSomething[i] = PlayerConfig.getConfig("PlayerData","Partie"+(i+1));

		}
	}

	/**
	 * Cree une nouvelle partie.
	 * @param index identifiant de la partie.
	 */
	private void newSave(int index){
		Player player = generalView.choiceOfPlayer();
		saveOfSomething[index-1] = new PlayerConfig(player.getName(),player.isHuman(),
				new int[SaveData.countFiles("LevelData","Level")],
				new int[SaveData.countFiles("LevelData","Level")],index);
		saveOfSomething[index-1].createSave("PlayerData","Partie",index);
	}

	/**
	 * L utilisateur,choisit le niveau qui va etre joue.
	 */
	private boolean choiceOfSave(){
		Player temp = new HumanPlayer("To choose");
		generalView.initializePlayer(temp);
		if(!printHostPage())return false;
		String select = putTheSameQuestion();


		if(saveOfSomething[Integer.parseInt(select)-1]==null){
			newSave(Integer.parseInt(select));
		}
		Player player;
		if(saveOfSomething[Integer.parseInt(select)-1].isHuman()){
			player = new HumanPlayer(saveOfSomething[Integer.parseInt(select)-1].getPlayerName());
		}else{
			player = new Robot(saveOfSomething[Integer.parseInt(select)-1].getPlayerName());
		}
		SaveData.countFiles("LevelData","Level");
		generalView.initializePlayer(player);
		game = new Game(generalView);
		game.setSave(saveOfSomething[Integer.parseInt(select)-1]);
		return true;
	}

	/**
	 * Pour choisir et supprimer une partie.
	 * @return le choix de la partie.
	 */
	private String putTheSameQuestion(){
		String res = "nothing";
		while(res.equals("nothing")){
			String getAnswer = generalView.askToChooseSave(saveOfSomething);
			if(getAnswer.contains("d")){
				SaveData.deleteConfig("PlayerData","Partie",Integer.valueOf(getAnswer.substring(1,2)));
				for(int i=0;i<saveOfSomething.length;i++){
					saveOfSomething[i] = PlayerConfig.getConfig("PlayerData","Partie"+(i+1));
				}
			}else{
				res = getAnswer;
			}
		}
		return res.substring(1,2);
	}

	/**
	 * Affiche la page d accueil et
	 * regarde le choix de l utilisateur.
	 * @return true si le joueur veut jouer,false sinon.
	 */
	public boolean printHostPage(){
		return generalView.printHostPage();
	}

	/**
	 * Pour un meilleur affichage du jeu.
	 */
	private static void clearConsole(){
		for(int clear = 0; clear <20; clear++){
     		System.out.println("\n") ;
  		}
	}

	/**
	 * Methode de lancement du jeu.
	 * @param args argument donne du terminal.
	 */
	public static void main(String[] args){
		clearConsole();
		PetRescueSaga project = new PetRescueSaga();
		boolean wantToPlay = project.choiceOfSave();
		while(wantToPlay){
			project.game.playGame(true);
			wantToPlay = project.choiceOfSave();
		}
		project.generalView.endOfChoice();
	}
}