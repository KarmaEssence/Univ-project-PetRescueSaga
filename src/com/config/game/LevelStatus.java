package com.config.game;

import com.gamecomponent.board.Goal;
import com.gamecomponent.board.Board;

import java.util.Stack;

/**
 * Correspond a l utilisation du
 * mode analyse.
 * @version 1.0
 */
public class LevelStatus {
    private final Stack<Board> boardStep;
    private final Stack<Goal> goalStep;

    /**
     * Construction d un systeme de sauvegarde
     * des etapes d un niveau.
     */
    public LevelStatus(){
        boardStep = new Stack<>();
        goalStep = new Stack<>();
    }

    /**
     * Verifie la taille de la pile.
     * @return la taille de la pile.
     */
    public int getSize(){
        return boardStep.size();
    }

    /**
     * Verifie que la pile est vide.
     * @return true si la pile est vide,
     * false sinon.
     */
    public boolean isEmpty(){
        return boardStep.isEmpty() && goalStep.isEmpty();
    }

    /**
     * Enregistre l etape courante.
     * @throws CloneNotSupportedException dans le cas ou
     * l on arrive pas a cloner les element demande.
     */
    public void includeNewStep(Board board,Goal goal) throws CloneNotSupportedException {
        boardStep.push(board.clone());
        goalStep.push((Goal) goal.clone());
    }

    /**
     * Retourne l etape precedente.
     * @return l etape precedente.
     */
    public Object[] getLastStep(){
        if(isEmpty())return null;
        Object[] res = new Object[2];
        res[0] = boardStep.pop();
        res[1] = goalStep.pop();
        return res;
    }
}
