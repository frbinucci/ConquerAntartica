/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author franc
 */
public interface IModel {
    
    /*Methods Used in order to set/get the position of the bullet.*/
    public void updateBulletCenter(double x,double y);
    public void updateXBulletCoordinate(double xTranslation);
    public void updateYBulletCoordinate(double yTranslation);
    public double getBulletXPosition();
    public double getBulletYPosition();
    public double getBulletXStartPosition();
    public double getBulletYStartPosition();
    
    //Methods used in order to control the match.
    public void setSecondPlayerCurrentDamageFactor(int secondPlayerCurrentDamageFactor);
    public void setIsMultiplayerMatch(boolean isMultiplayerMatch);
    public void resetSecondPlayerBullet();
    public int getSecondPlayerCurrentDamageFactor(); 
    public LevelData getLevelData();
    public void setupResults();
    public int getLevel();

    //Methods used in order to Manage the collisions.
    public boolean penguinHit();
    public boolean isBulletGetSceneryLimits();
    public boolean isBulletHurtingBase();
 
    /*Methods used in order to pass information about the description of the
    battlefield.
    */ 
    public IceBlockTerrainModel getPlayerBase();
    public IceBlockTerrainModel getEnemyBase();
    public ArrayList<PenguinModel> getPenguins();
    
    //Methods used in order to get/set the data about the match.
    public boolean isMatchWin();
    public boolean isMatchLost();
    public int getIndexTurn();
    public void updateTurn();
    public String getPlayerName();
    public int getPlayerScore();
    public int getPlayerHighScore();
    public int getPlayerNumberOfLives();
    public int getPlayerCurrentLevel();
    public int getPlayerDifficultyLevel();
    public void setCurrentPlayer(Player p);
    public void changeBulletDamageFactor(int damageFactor);
    public void init(int level,boolean isCampaign,IceBlockTerrainModel playerBase,IceBlockTerrainModel enemyBase);
    public void setPlayerData() throws IOException, FileNotFoundException;
    public PlayerData getPlayerData();
    public void resetPlayerBullet();
    public int getCurrentPlayerBullet();
    public void setCurrentPlayerBullet(int damageFactor);
    public boolean getCampaignMode();
    public void deleteProfile(int idProfile);

}
