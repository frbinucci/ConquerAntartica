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
public class LevelStats {
    
    private int levelId;

    public int getLevelId() {
        return levelId;
    }
    
    private int idBest;
    private int idBestTwo;
    private int idBestThree;
    
    private String nameBest;
    private String nameBestTwo;
    private String nameBestThree;
    
    private int scoreBest;
    private int scoreBestTwo;
    private int scoreBestThree;

    public LevelStats(int levelId,int idBest, String nameBest, int scoreBest, int idBestTwo, String nameBestTwo, int scoreBestTwo, int idBestThree, String nameBestThree, int scoreBestThree) {
        this.levelId = levelId;
        this.idBest = idBest;
        this.idBestTwo = idBestTwo;
        this.idBestThree = idBestThree;
        this.nameBest = nameBest;
        this.nameBestTwo = nameBestTwo;
        this.nameBestThree = nameBestThree;
        this.scoreBest = scoreBest;
        this.scoreBestTwo = scoreBestTwo;
        this.scoreBestThree = scoreBestThree;
    }

    public LevelStats(int levelId, int idBest, String nameBest, int scoreBest) {
        this.levelId = levelId;
        this.idBest = idBest;
        this.nameBest = nameBest;
        this.scoreBest = scoreBest;
        this.idBestTwo = 0;
        this.nameBestTwo = "";
        this.scoreBestTwo = 0;
        this.idBestThree = 0;
        this.nameBestThree = "";
        this.scoreBestThree = 0;
    }
    


    public int getIdBest() {
        return idBest;
    }

    public int getIdBestTwo() {
        return idBestTwo;
    }

    public int getIdBestThree() {
        return idBestThree;
    }

    public String getNameBest() {
        return nameBest;
    }

    public String getNameBestTwo() {
        return nameBestTwo;
    }

    public String getNameBestThree() {
        return nameBestThree;
    }

    public int getScoreBest() {
        return scoreBest;
    }

    public int getScoreBestTwo() {
        return scoreBestTwo;
    }

    public int getScoreBestThree() {
        return scoreBestThree;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public void setIdBest(int idBest) {
        this.idBest = idBest;
    }

    public void setIdBestTwo(int idBestTwo) {
        this.idBestTwo = idBestTwo;
    }

    public void setIdBestThree(int idBestThree) {
        this.idBestThree = idBestThree;
    }

    public void setNameBest(String nameBest) {
        this.nameBest = nameBest;
    }

    public void setNameBestTwo(String nameBestTwo) {
        this.nameBestTwo = nameBestTwo;
    }

    public void setNameBestThree(String nameBestThree) {
        this.nameBestThree = nameBestThree;
    }

    public void setScoreBest(int scoreBest) {
        this.scoreBest = scoreBest;
    }

    public void setScoreBestTwo(int scoreBestTwo) {
        this.scoreBestTwo = scoreBestTwo;
    }

    public void setScoreBestThree(int scoreBestThree) {
        this.scoreBestThree = scoreBestThree;
    }

    @Override
    public String toString() {
        return "LevelStats{" + "idBest=" + idBest + ", idBestTwo=" + idBestTwo + ", idBestThree=" + idBestThree + ", nameBest=" + nameBest + ", nameBestTwo=" + nameBestTwo + ", nameBestThree=" + nameBestThree + ", scoreBest=" + scoreBest + ", scoreBestTwo=" + scoreBestTwo + ", scoreBestThree=" + scoreBestThree + '}';
    }
    
    public String[] dataAsStringArray()
    {
        String[] levelData = new String[3];
        levelData[0]= new StringBuilder().append("Miglior Giocatore: ")
                .append(this.nameBest)
                .append(", Punteggio: ")
                .append(this.getScoreBest()).toString();
        
        levelData[1] = new StringBuilder().append("Secondo miglior giocatore: ")
                .append(this.nameBestTwo)
                .append(", Punteggio: ")
                .append(this.getScoreBestTwo()).toString();
        
        levelData[2] = new StringBuilder().append("Terzo miglior giocatore: ")
                .append(this.nameBestThree)
                .append(", Punteggio: ")
                .append(this.getScoreBestThree()).toString();
        
        return levelData;
        
    }
    
    
    
    
    
    
}
