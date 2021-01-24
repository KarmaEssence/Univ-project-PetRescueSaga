package com.user.human;

import com.config.save.PlayerConfig;
import com.user.Player;
import com.user.robot.Robot;

import java.util.Scanner;

/**
 * Represente les choix de l utilisateur
 * sur l interface textuelle.
 * @version 1.0
 */
public class TextualHumanInteract implements HumanInteract {

	private final Scanner scanner;

	/**
	 * Cree un objet permettant
	 * les choix du joueur sur
	 * l interface textuelle.
	 */
	public TextualHumanInteract(){
		scanner = new Scanner(System.in);
	}

	/**
	 * Demande au joueur s il veut jouer.
	 * @return true s il veut joueur,
	 * false sinon.
	 */
	@Override
	public boolean chooseToPlay() {
		String answer ="nothing";
		while(!answer.equals("p")){
			System.out.println();
			System.out.println("Do you want play or quit ?(p/q)");
			answer = scanner.nextLine();
			if(answer.equals("q"))System.exit(0);
		}
		return true;
	}

	/**
	 * Position de l element.
	 * @return la position de l element.
	 */
	@Override
	public int[] position(){
		System.out.println();
		int[] tab = new int[2];
		System.out.println("Enter a X value: ");
		String answer = scanner.nextLine();
		tab[1] = stringIntoValue(answer);
		System.out.println("Enter a Y value: ");
		answer = scanner.nextLine();
		tab[0] = stringIntoValue(answer);
		return tab;
	}

	/**
	 * Transforme les informations en coordonnee pour la grille.
	 * @param s une chaine de caractere.
	 * @return retourne le premier caractere
	 * transforme en entier.
	 */
	public int stringIntoValue(String s){
		if(!checkArg(s))return -1;
		int res;
		if((s.charAt(0)>=49 && s.charAt(0)<=57)){
			res = Integer.valueOf(s);
		}else{
			res = Integer.valueOf(s.charAt(0))%32;
		}
		return res-1;
	}

	/**
	 * Verification du format de la reponse entre par le joueur.
	 * @param s une chaine de caractere.
	 * @return true si la chaine de caractere
	 * respecte le format,false sinon.
	 */
	public boolean checkArg(String s){
		if(s.length()!=1)return false;
		return ((s.charAt(0)>=49 && s.charAt(0)<=57)
				|| (s.charAt(0)>=65 && s.charAt(0)<=90)
				|| (s.charAt(0)>=97 && s.charAt(0)<=122));
	}

	/**
	 * Verifie que le paramètre donne
	 * contienne un entier entre 0 et 9.
	 * @param s une chaine de caractere.
	 * @return true si la chaine de caractere
	 * respecte le format,false sinon.
	 */
	public boolean checkValue(String s){
		if(s.length()==0)return false;
		for(int i=0;i<s.length();i++){
			if(s.charAt(0)>=49 && s.charAt(0)<57)return true;
		}
		return false;
	}

	/**
	 * Permet de choisir un niveau.
	 * @param numMaxLevel le nombre maximal de niveau.
	 * @return le numero du niveau choisi
	 */
	@Override
	public int choiceOfLevel(int numMaxLevel){
		String res;
		int level = -1;
		while(level<0 || level>=numMaxLevel){
			System.out.println();
			System.out.println("Choose a level between 1 to " + numMaxLevel);
			   res = scanner.nextLine();
			   if(res.length() == 1 && checkValue(res)){
				   level = Integer.valueOf(res)-1;
			   }

		}
		return level;
	}

	/**
	 * Verifie que la chaine de caractere.
	 * contienne le type de reponse demande.
	 * @param s une chaine de caractere.
	 * @return true si la chaine de caractere contient
	 * le type de reponse demande,false sinon.
	 */
	private boolean containsAnswers(String s){
		return s.equals("y") || s.equals("n");
	}

	/**
	 * Demande au joueur s il veut jouer.
	 * @param sentence question pose
	 * @return true si la reponse est favorable,false sinon.
	 */
	private boolean askToPlay(String sentence){
		String res = "";
		boolean b = false;
		while(!b){
			System.out.println();
			System.out.println(sentence);
			res = scanner.nextLine();
			if(containsAnswers(res))b=true;
		}
		return res.equals("y");
	}

	/**
	 * Pose une question a l utilisateur.
	 * @param sentence une question.
	 * @param endOfGame regarde si ce sont les
	 *                  dernieres questions.
	 * @return true si la reponse est favorable.
	 */
	@Override
	public boolean ask(String sentence,boolean endOfGame){
		return askToPlay(sentence);
	}

	/**
	 * Pose une question a chaque etape.
	 * @param sentence question.
	 * @return l une des trois options.
	 */
	@Override
	public String askPerStep(String sentence){
		String res = "";
		boolean b = false;
		while(!b){
			System.out.println();
			System.out.println(sentence);
			res = scanner.nextLine();
			if(res.equals("cont") || res.equals("quit") || res.equals("last"))b=true;
		}
		System.out.println();
		return res;
	}

	/**
	 * Demande a l utilisateur la sauvegarde a choisir.
	 * @param saveOfSomething toutes les sauvegardes des parties
	 *                        disponibles.
	 * @return la reponse du joueur.
	 */
	@Override
	public String askToChooseSave(PlayerConfig[] saveOfSomething){
		String res = "";
		boolean b = false;
		while(!b) {
			System.out.println();
			for (int i = 0; i < saveOfSomething.length; i++) {
				String s;
				if (saveOfSomething[i] == null) {
					s = "New Game";
				} else {
					s = saveOfSomething[i].toString();
				}
				System.out.println((i + 1) + ". " + s);
			}
			System.out.println("To choose: c[1,2,3],To delete: d[1,2,3]");
			System.out.println();
			System.out.println("Select or delete a game save");
			res = scanner.nextLine();
			if(res.length()== 2 && checkValue(res.substring(1,2))){
				int resInt = stringIntoValue(res.substring(1,2))+1;
				if (resInt>0 && resInt<3 &&
						(res.charAt(0) == 'c') || (res.charAt(0) == 'd')) {
					b = true;
				}
			}
		}
		return res;
	}

	/**
	 * Renvoie le type du joueur en fonction du choix de l utilisateur.
	 * @return le type de joueur choisi.
	 */
	@Override
	public Player choiceOfPlayer() {
		Scanner scanner = new Scanner(System.in);
		String typeOfPlayer = "";
		boolean b = false;
		while(!b){
			System.out.println();
			System.out.println("Are you human or robot ?(r/h)");
			typeOfPlayer = scanner.nextLine();
			if(typeOfPlayer.equals("r")
					|| typeOfPlayer.equals("h"))b=true;
		}
		String nameOfPlayer = "";
		while(!verifyUserNameFormat(nameOfPlayer)){
			System.out.println();
			System.out.println("What's your name ?");
			nameOfPlayer = scanner.nextLine();
		}
		if(typeOfPlayer.equals("r"))return new Robot(nameOfPlayer);
		return new HumanPlayer(nameOfPlayer);
	}

	/**
	 * Verifie que le nom d utilisateur est correct.
	 * @param username nom donne par l utilisateur.
	 * @return true si le format est le bon,false sinon.
	 */
	private static boolean verifyUserNameFormat(String username){
		if(username == null || username.length() == 0)return false;
		for(int i=0;i<username.length();i++){
			char tmp = username.charAt(i);
			if(tmp<48 || (tmp>57 && tmp<65) || (tmp>90 && tmp<97) || tmp>122)return false;
		}
		return true;
	}

	/**
	 * Ferme le scanner une fois que la partie est fini.
	 */
	@Override
	public void close(){
		scanner.close();
	}
}