/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.image.ImageView;
import conquerantartica.view.IceBlockTerrain;

/**
 *
 * @author franc
 */
public interface IControllerForView {
    

    
    //Methods Used in order to pass Information between Model and View module.
    public double getXStartPosition();
    public double getYStartPosition();
    public ArrayList<double[]> getPenguins(boolean allied);
    public int getNumberOfLives();
    public int getHighScore();   
    public void changeDamageFactor(int damageFactor);
    public String getPlayerName();
    public int getPlayerScore();
    public boolean isMultiplayer();
    public void setMultiplayerMode(boolean isMultiplayer);
    public boolean isPlayerTwoControlDisabled();
    
    //Methods used in order to control the match.
    public void nextTurn();
    public boolean isControlDisabled();
    public void disablePlayerControl();   
    public void moveBullet(double xMove,double yMove);
    public void shoot(double angle,double speed,double xStartPosition,double yStartPosition);
    public void resetBullet(int damageFactor);
    public void loadLevel(int level,boolean modality);
    public int getPlayerMission();
    public int getCurrentMission();
    public void disablePlayerTwoControl(); 
    
    //Method used in order to manage the game profiles.
    public void createNewGameProfile(String name,int difficultyLevel) throws IOException;
    public void loadGameProfile(int idProfile) throws IOException, FileNotFoundException;   
    public LinkedList<String[]> getListOfPlayers() throws IOException, FileNotFoundException;
    public LinkedList<String[]> getLevelStats() throws IOException;
    public String[] getLevelData(int level);
    public void deleteProfile(int idProfile);
    
}
