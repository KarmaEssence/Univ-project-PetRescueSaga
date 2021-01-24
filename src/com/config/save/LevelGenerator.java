package com.config.save;

import java.util.ArrayList;

/**
 * Utilise pour contruire les niveaux.
 * @version 1.0
 */
public class LevelGenerator {
    private final static int LEVELTOSAVE=6;

    /**
     * Retourne le nombre de niveau a sauvegarder
     * @return le nombre de niveau a sauvegarder
     */
    public static int getLeveltosave(){return LEVELTOSAVE;}

    /**
     * Cree et enregistre les configurations de niveau.
     */
    public static void saveAllLevel(){
        ArrayList<LevelConfig> list = new ArrayList<>();
        list.add(level1());
        list.add(level2());
        list.add(level3());
        list.add(level4());
        list.add(level5());
        list.add(level6());
        int index = 1;
        for(LevelConfig lc: list){
            lc.saveConfig(index);
            index++;
        }
    }

    /* Pour mieux comprendre la génération des niveaux
    les chiffres ont une correspondance à un type d'élément:
    - 0 : Bloc abstrait.
    - 1 à 4 : Bloc de couleur (les bloc du même chiffre ont la même couleur).
    - 5 : Bloc fixe.
    - 6 : Animal.*/

    /**
     * Construction du niveau 1.
     * @return une configuration du niveau 1.
     */
    public static LevelConfig level1(){
        int[][] config = {
                {2,6,1,1},
                {3,2,1,1},
                {3,3,4,4},
                {2,2,4,5}
        };
        LevelConfig l = new LevelConfig(1,4,4,config,1);
        return l;
    }

    /**
     * Construction du niveau 2.
     * @return une configuration du niveau 2.
     */
    public static LevelConfig level2(){
        int[][] config = {
                {2,6,0,1,6,2},
                {3,2,0,1,2,3},
                {3,3,0,4,1,3},
                {2,2,0,4,4,2}
        };
        LevelConfig l = new LevelConfig(2,4,6,config,2);
        return l;
    }


    /**
     * Construction du niveau 3.
     * @return une configuration du niveau 3.
     */
    public static LevelConfig level3(){
        int[][] config = {
                {0,0,0,0,6,1},
                {0,0,2,1,2,5},
                {6,3,2,4,5,5},
                {2,2,2,5,5,5}
        };
        LevelConfig l = new LevelConfig(2,4,6,config,2);
        return l;
    }

    /**
     * Construction du niveau 4.
     * @return une configuration du niveau 4.
     */
    public static LevelConfig level4(){
        int[][] config = {
                {0,0,2,1,6,2},
                {0,3,2,1,2,5},
                {6,3,2,1,5,5},
                {2,3,2,5,5,5}
        };
        LevelConfig l = new LevelConfig(2,4,6,config,2);
        return l;
    }

    /**
     * Construction du niveau 5.
     * @return une configuration du niveau 5.
     */
    public static LevelConfig level5(){
        int[][] config = {
                {1,6,2,5,2,6},
                {1,3,2,5,3,3},
                {1,5,5,1,2,2},
                {1,3,2,1,3,3},
                {1,2,2,2,3,3},
                {1,2,2,2,3,3},
        };
        LevelConfig l = new LevelConfig(1,6,6,config,2);
        return l;
    }

    /**
     * Construction du niveau 6.
     * @return une configuration du niveau 6.
     */
    public static LevelConfig level6(){
        int[][] config = {
                {5,5,5,5,6,1,2,6},
                {6,2,3,3,3,4,1,3},
                {3,2,1,1,3,4,2,3},
                {3,4,4,3,2,3,3,2},
                {3,5,2,5,5,4,5,2},
        };
        LevelConfig l = new LevelConfig(2,5,8,config,3);
        return l;
    }
}
