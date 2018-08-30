/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.model;

/**
 *
 * @author franc
 */
public class Player {
    
    private int playerId;
    private String playerName;
    private int playerHighScore;
    private int playerNumberOfLives;




    private int currentMission;
    private int difficultyLevel;

    public Player(int playerId,String playerName,int playerHighScore,int playerNumberOfLives,int difficultyLevel,int currentMission)
    {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerHighScore = playerHighScore;
        this.playerNumberOfLives = playerNumberOfLives;
        this.currentMission = currentMission;
        this.difficultyLevel = difficultyLevel;
    }
    
    public String getPlayerName()
    {
        return this.playerName;
    }
    
    public int getPlayerId()
    {
        return this.playerId;
    }
    
    public int getPlayerHighScore()
    {
        return this.playerHighScore;
    }
    
    public int getPlayerNumberOfLives()
    {
        return this.playerNumberOfLives;
    }
    
    public void setPlayerHighScore(int highScore)
    {
        this.playerHighScore = highScore;
    }
    
    public void setCurrentMission(int currentMission) 
    {
        this.currentMission = currentMission;
    }
    
    public void decreaseNumberOfLives()
    {
        this.playerNumberOfLives--;
    }
    
    public boolean hasPlayerTerminatedLives()
    {
        return this.playerNumberOfLives ==0;
    }
    
    public int getCurrentMission() {
        return this.currentMission;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }    
    
    
}
