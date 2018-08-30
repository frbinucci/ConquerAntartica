/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.model;

import java.util.LinkedList;

public class LevelData {

    private LinkedList<LevelStats> levelListData;

    public LevelData() {
        this.levelListData = new LinkedList<LevelStats>();
    }

    public void add(LevelStats level) {
        this.levelListData.add(level);
    }
    
    public LevelStats searchForLevelId(int level)
    {
        LevelStats found = null;
        for(LevelStats current : levelListData)
        {
            if(current.getLevelId() == level)
                found = current;
        }
        
        return found;
    }
    
    public void checkForUpdate(Player player, int level,int score)
    {
        int levelIndex = level - 1;
        LevelStats currentLevel = searchForLevelId(level);
        
        if(currentLevel!=null)
        {
            if(score>currentLevel.getScoreBest())
            {
                currentLevel.setIdBestThree(currentLevel.getIdBestTwo());
                currentLevel.setNameBestThree(currentLevel.getNameBestTwo());
                currentLevel.setScoreBestThree(currentLevel.getScoreBestTwo());

                currentLevel.setIdBestTwo(currentLevel.getIdBest());
                currentLevel.setNameBestTwo(currentLevel.getNameBest());
                currentLevel.setScoreBestTwo(currentLevel.getScoreBest());

                currentLevel.setIdBest(player.getPlayerId());
                currentLevel.setNameBest(player.getPlayerName());
                currentLevel.setScoreBest(score);
            } else if(score>currentLevel.getScoreBestTwo())
            {
                currentLevel.setIdBestThree(currentLevel.getIdBestTwo());
                currentLevel.setNameBestThree(currentLevel.getNameBestTwo());
                currentLevel.setScoreBestThree(currentLevel.getScoreBestTwo());

                currentLevel.setIdBestTwo(player.getPlayerId());
                currentLevel.setNameBestTwo(player.getPlayerName());
                currentLevel.setScoreBestTwo(score);            
            } else if(score>currentLevel.getScoreBestThree())
            {
                currentLevel.setIdBestThree(player.getPlayerId());
                currentLevel.setNameBestThree(player.getPlayerName());
                currentLevel.setScoreBestThree(score);      
            }
        }
        else
        {   
            currentLevel = new LevelStats(level,
                    player.getPlayerId(),
                    player.getPlayerName(),
                    score);
            levelListData.add(currentLevel);
        }
    }
    
    public void remove(LevelStats level)
    {
        this.levelListData.remove(level);
    }

    public LinkedList<LevelStats> getListOfLevels() {
        return this.levelListData;
    }

    public LinkedList<String[]> asListOfStringArray() {
        LinkedList<String[]> lstSA = null;

        lstSA = new LinkedList<String[]>();
        String[] sArr = null;
        for (LevelStats level : this.levelListData) {
            sArr = new String[10];
            //Best Player of the level
            sArr[0] = String.valueOf(level.getLevelId());
            
            sArr[1] = String.valueOf(level.getIdBest());
            sArr[2] = level.getNameBest();
            sArr[3] = String.valueOf(level.getScoreBest());
            
            sArr[4] = String.valueOf(level.getIdBestTwo());
            sArr[5] = level.getNameBestTwo();
            sArr[6] = String.valueOf(level.getScoreBestTwo());
            
            sArr[7] = String.valueOf(level.getIdBestThree());
            sArr[8] = level.getNameBestThree();
            sArr[9] = String.valueOf(level.getScoreBestThree());
            
            lstSA.add(sArr);
        }

        return lstSA;
    }

} // end class
