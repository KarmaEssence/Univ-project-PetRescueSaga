package com.config.save;

import com.config.game.Level;

import java.io.*;

/**
 * Gere la recuperation et la sauvegarde des
 * sauvegarde de l utilisateur.
 * @version 1.0
 */
public class PlayerConfig implements Serializable {
    private final String playerName;
    private final boolean isHuman;
    private int[] highScores;
    private int[] stars;
    private int slot;

    /**
     * Cree une configuration pour le joueur.
     * @param playerName le nom d utilisateur.
     * @param human regarde si le joueur est humain.
     * @param highScores le score de chaque niveau.
     * @param stars le nombre d etoile de chaque niveau.
     * @param slot le numero de la partie choisi.
     */
    public PlayerConfig(String playerName, boolean human, int[] highScores, int[] stars, int slot){
        this.playerName = playerName;
        isHuman = human;
        this.highScores = highScores;
        this.stars = stars;
        this.slot = slot;
    }

    /**
     * Renvoie le nom d utilisateur.
     * @return le nom d'utilisateur.
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Regarde si le joueur est un humain.
     * @return true si le joueur un humain.
     */
    public boolean isHuman(){
        return isHuman;
    }

    /**
     * Affiche les informations de la partie.
     */
    public String toString(){
        return "Player: "+playerName+"/ total score :"+countTotalScore()+ "/ number of stars: "+countStars();
    }

    /**
     * Renvoie le highscore du niveau passe en parametre.
     * @param i le numéro du niveau.
     */
    public int getHighscore(int i){
        if(highScores.length==0){
            highScores = new int[SaveData.countFiles("LevelData","Level")];
        }
        return highScores[i];
    }

    /**
     * Change le highscore du niveau i par la valeur score.
     * @param i le numero du niveau.
     * @param score la nouvelle valeur du highscore du niveau i.
     */
    public void setHighscore(int i,int score){
        highScores[i] = score;
    }

    /**
     * Compte et renvoie la valeur cumule de tous les highscore.
     * @return la valeur cumule de tous les highscore.
     */
    public int countTotalScore(){
        int res = 0;
        for(int i=0;i<highScores.length;i++){
            res+= highScores[i];
        }
        return res;
    }

    /**
     * Renvoie le nombre d etoile obtenu au niveau i.
     * @param i le numero du niveau.
     * @return le nombre d etoile du niveau i.
     */
    public int getStars(int i){
        if(stars.length==0){
            stars = new int[SaveData.countFiles("LevelData","Level")];
        }
        return stars[i];
    }

    /**
     * Change le nombre d etoile du niveau i.
     * @param i le numero du niveau.
     * @param star le nouveau nombre d etoile.
     */
    public void setStars(int i, int star){
        stars[i] = star;
    }

    /**
     * Compte et renvoie nombre cumule d etoile.
     * @return le nombre cumule d etoile obtenu.
     */
    public int countStars(){
        int res = 0;
        for(int i = 0;i<stars.length;i++){
            res+= stars[i];
        }
        return res;
    }

    /**
     * Cree une nouvelle sauvegarde.
     * @param folder le dossier ou enregistrer le fichier.
     * @param file le nom du fichier a enregistrer.
     * @param number le numéro de la Partie.
     */
    public void createSave(String folder,String file,int number){
        SaveData.saveConfig(this,folder,file,number);
    }

    /**
     * Cherche le fichier contenant le PlayerConfig et retourne son contenu.
     * @param folder le dossier ou cherche.
     * @param file le nome du fichier recherche.
     * @return la sauvegarde voulu.
     */
    public static PlayerConfig getConfig(String folder,String file){
        return (PlayerConfig)SaveData.getConfig(folder,file);
    }

    /**
     * Sauvegarde dans playerConfig les resultats de fin de partie
     * si ces derniers sont plus grand que ceux dans le fichier de
     * sauvegarde.
     * @param l le niveau actuel joue par le joueur.
     */
    public void saveResults(Level l){
        if(l.getGoal().getScore()> getHighscore(l.getNumLevel()-1)){
            setHighscore(l.getNumLevel()-1, l.getGoal().getScore());

        }
        if(l.getGoal().numberOfStar()> getStars(l.getNumLevel()-1)){
            setStars(l.getNumLevel()-1, l.getGoal().numberOfStar());

        }
        SaveData.saveConfig(this,"PlayerData","Partie",slot);
    }
}
