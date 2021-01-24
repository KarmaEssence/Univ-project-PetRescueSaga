package com.display.textual;

/**
 * Page d accueil.
 * @version 1.0
 */
public class HostPage {

    /**
     * Produit des espaces vides.
     * @param n le nombre d espace a effectuer.
     */
    private static void printSpace(int n){
        for(int i=0;i<n;i++)System.out.print(" ");
    }

    /**
     * Affiche une ligne de separation.
     */
    private static void printSepator(){
        for(int i=0;i<79;i++)System.out.print("-");
        System.out.println();
    }

    /**
     * Affiche la page d accueil.
     */
    public static void print(){
        printSepator();
        printSpace(24);
        System.out.println("Welcome to the PetRescueSaga");
        System.out.println();
        printSpace(19);
        System.out.println("This game was reproduced by the Group57");
        printSepator();
    }

}
