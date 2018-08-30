/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.controller;

/**
 *
 * @author franc
 */
public interface IControllerForModel {

    //Methods used in order to notify events to the ControllerForView.
    public void notifyDeadPenguin(int arrayIndex,boolean isAllied);
    public void notifyHurtedPenguin(int arrayIndex);
    public void updateScore(int score);
    public void notifyGameOver(boolean isCampaign,boolean isMatchWin,boolean isTwoPlayerMatch,int score,int bonus);
    
    public void notifyException(String message);
    
}
