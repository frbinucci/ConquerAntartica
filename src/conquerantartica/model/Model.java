/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

import conquerantartica.utils.Config;
import conquerantartica.utils.Constants;
import conquerantartica.utils.ReadCSV;
import conquerantartica.utils.WriteCSV;

import conquerantartica.controller.ControllerForModel;
/**
 *
 * @author franc
 */
public class Model implements IModel{

    //STATIC FIELDS
    //--------------------------------------------------------------------------
    private static Model instance = null; 
    //--------------------------------------------------------------------------
    //INSTANCE FIELDS
    //--------------------------------------------------------------------------
    private int playerScore;
    private int numberAlliedPenguins;
    private int numberEnemyPenguins;

    private double playerBulletXStartPosition;
    private double playerBulletYStartPosition;
    private int level;
    private  int indexTurn;
    private int playerCurrentDamageFactor;
    
    private int secondPlayerCurrentDamageFactor;
    private boolean isCampaign;
    private boolean isMultiplayerMatch;
    
    private ArrayList<PenguinModel> penguins;
    private double bulletRadius;
    
    private  double battlefieldWidth;
    private  double battlefieldHeight;
    
    private IceBlockTerrainModel playerBase;
    private IceBlockTerrainModel enemyBase;
    
    private BulletModel bullet;
    private PlayerData playerData;
    private LevelData levelData;
    private Player player;
    //--------------------------------------------------------------------------
    //Method Area
    //--------------------------------------------------------------------------
    
    
    //Methods used in order to set/get the position of the bullet.
    //--------------------------------------------------------------------------
    @Override
    public double getBulletXPosition() {
        return (this.bullet.getTranslateX()+this.bullet.getCenterX());
    }

    @Override
    public double getBulletYPosition() {
        return (this.bullet.getTranslateY()+this.bullet.getCenterY());
    }
    @Override
    public double getBulletXStartPosition()
    {
        return this.bullet.getCenterX();
    }
    @Override
    public double getBulletYStartPosition()
    {
        return this.bullet.getCenterY();
    }
    @Override
    public void updateBulletCenter(double x,double y)
    {
        this.bullet.setCenterX(x);
        this.bullet.setCenterY(y);
    }
    @Override
    public void updateXBulletCoordinate(double xTranslation) {
        this.bullet.setTranslateX(xTranslation);
    }
    @Override
    public void updateYBulletCoordinate(double yTranslation) {
        this.bullet.setTranslateY(yTranslation);
    }    
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    /*Methods Used in order to manage collisions of the Bullet with the elements
    of the scenery.*/
    @Override
    public boolean penguinHit()
    {
        boolean collision = false;
        PenguinModel penguin;
        int penguinPosition=0;
        while(penguinPosition<penguins.size() && !collision)
        {
            penguin = penguins.get(penguinPosition);
            if(penguin.intersects(bullet.getBoundsInParent()))
            {
                penguin.decreasePenguinLife(bullet);
                collision = true;
                if(penguin.isDead())
                {
                    if(!penguin.isAllied())
                    {
                        this.playerScore+=Constants.PENGUIN_POINTS*(this.getPlayerDifficultyLevel()+Constants.DIFFICULTY_LEVEL_POINT_CORRECTOR);
                        numberEnemyPenguins--;
                        ControllerForModel.getInstance().updateScore(this.playerScore);
                    }
                    else
                        numberAlliedPenguins--;
                    ControllerForModel.getInstance().notifyDeadPenguin(penguinPosition,penguin.isAllied());
                    penguins.remove(penguin);
                    int bonus = checkForUpdateGameProfiles(); 
                    if(isMatchWin() || isMatchLost())
                    {
                        ControllerForModel.getInstance().notifyGameOver(isCampaign, 
                            this.isMatchWin(), 
                            this.isMultiplayerMatch, 
                            playerScore,
                            bonus);
                        levelData.checkForUpdate(this.player, level, this.playerScore);
                        
                        try {
                            WriteCSV.print("gameprofiles/stats.csv", "UTF-8", levelData.asListOfStringArray());
                        } catch (IOException ioe) {
                            ControllerForModel.getInstance().notifyException(ioe.toString());
                        }
                    }
                } 
                else
                    ControllerForModel.getInstance().notifyHurtedPenguin(penguinPosition);   
            }
            penguinPosition++;     
        }
        return collision;
    }
   
    @Override
    public void setupResults()
    {
        if(!(new File("gameprofiles/stats.csv").exists()))
        {
                new File("gameprofiles").mkdir();
                try
                {
                    new File("gameprofiles/stats.csv").createNewFile();
                }catch(IOException ioe){
                    ControllerForModel.getInstance().notifyException(ioe.toString());
                }
        }
        this.levelData = new LevelData();
        try
        {
            for(String[] currentLevel : ReadCSV.getRows("gameprofiles/stats.csv", "UTF-8"))
            {
                levelData.add(new LevelStats(Integer.parseInt(currentLevel[0]), //Level - ID
                                            Integer.parseInt(currentLevel[1]), //BestPlayer - ID
                                            currentLevel[2], //BestPlayer - Name
                                            Integer.parseInt(currentLevel[3]), //Best player - Score
                                            Integer.parseInt(currentLevel[4]), //Second Best - ID
                                            currentLevel[5],//Second Best - Name
                                            Integer.parseInt(currentLevel[6]), //Second Best - Score
                                            Integer.parseInt(currentLevel[7]), //Third Best - ID
                                            currentLevel[8],//Third Best - name
                                            Integer.parseInt(currentLevel[9]))); //Third Best - Score
            }
        }catch(IOException ioe){
            ControllerForModel.getInstance().notifyException(ioe.toString());
        }          
    }

    private int checkForUpdateGameProfiles()
    {
        int bonusScore = 0;
        if (isMatchLost() && this.isCampaign) 
        {
            this.player.decreaseNumberOfLives();
            if (this.player.hasPlayerTerminatedLives()) {
                playerData.remove(player);
            }
            try {
                WriteCSV.print("gameprofiles/saved.csv", "UTF-8", playerData.asListOfStringArray());
            } 
            catch (IOException ioe){
                ControllerForModel.getInstance().notifyException(ioe.toString());
            }
        } 
        else 
            if (isMatchWin() && !this.isMultiplayerMatch) 
            {
                bonusScore = Constants.TIME_FACTOR/(this.getIndexTurn()) + Constants.PENGUIN_BONUS * this.numberAlliedPenguins;
                this.playerScore = this.playerScore + bonusScore;
                if (this.playerScore > this.player.getPlayerHighScore()) {
                    this.player.setPlayerHighScore(this.playerScore);
                }
                if(this.isCampaign)            
                    this.player.setCurrentMission(this.player.getCurrentMission()+1);
                try {
                    WriteCSV.print("gameprofiles/saved.csv", "UTF-8", playerData.asListOfStringArray());
                } catch (IOException ioe) {
                    ControllerForModel.getInstance().notifyException(ioe.toString());
                }
            }
        return bonusScore;
        
    }
    
    @Override
    public boolean isBulletGetSceneryLimits()
    {
        double startXPosition = this.getBulletXStartPosition();
        double startYPosition = this.getBulletYStartPosition();
        
        double xLandscapeBound = this.battlefieldWidth-this.bulletRadius;
        double yLandscapeBound = this.battlefieldHeight-this.bulletRadius;
       
        
        boolean minLimitReach = startYPosition+this.bullet.getTranslateY()<0 || startXPosition+this.bullet.getTranslateX()<0;
        boolean maxLimitReach = startYPosition+this.bullet.getTranslateY()>yLandscapeBound || startXPosition+this.bullet.getTranslateX() >xLandscapeBound;
        return minLimitReach || maxLimitReach;
    }  
    
    @Override
    public boolean isBulletHurtingBase()
    {
        boolean isHurting = false;
        for(Rectangle rect : this.playerBase.getPattern())
        {
            if(rect.intersects(bullet.getBoundsInParent()))
            {
                 isHurting = true;  
            }
        }
        
        for(Rectangle rect: this.enemyBase.getPattern())
        {
            if(rect.intersects(bullet.getBoundsInParent()))
            {
                 isHurting = true; 
            }  
        }
        
        return isHurting;
    }
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    /*Methods used in order to set/get the data related to the match (e.g., playername,
    player score, current profile)
    */
        @Override
    public boolean isMatchWin()
    {
        return numberEnemyPenguins == 0;
    }
    
    @Override
    public boolean isMatchLost()
    {
        return numberAlliedPenguins == 0;
    }
    
    @Override
    public int getIndexTurn()
    {
        return this.indexTurn;
    }
    
    @Override
    public void updateTurn()
    {
        this.indexTurn++;
    }

    
    @Override
    public String getPlayerName()
    {
        return this.player.getPlayerName();
    }
    
    @Override
    public int getPlayerScore()
    {
        return this.playerScore;
    }

    @Override
    public void changeBulletDamageFactor(int damageFactor)
    {
        this.bullet.setDamageFactor(damageFactor);
    }

    @Override
    public void setCurrentPlayer(Player p) {
        this.player = p;
    }
    
    @Override
    public int getPlayerHighScore()
    {
        return this.player.getPlayerHighScore();
    }
    
    @Override
    public int getPlayerNumberOfLives()
    {
        return this.player.getPlayerNumberOfLives();
    }
    @Override
    public void setSecondPlayerCurrentDamageFactor(int secondPlayerCurrentDamageFactor) {
        this.secondPlayerCurrentDamageFactor = secondPlayerCurrentDamageFactor;
    }

    @Override
    public void setIsMultiplayerMatch(boolean isMultiplayerMatch) 
    {
        this.isMultiplayerMatch = isMultiplayerMatch;
    }
    @Override
    public int getPlayerCurrentLevel()
    {
        return this.player.getCurrentMission();
    }
    @Override
    public int getPlayerDifficultyLevel()
    {
        return this.player.getDifficultyLevel();
    }
    
    @Override
    public PlayerData getPlayerData()
    {
        return this.playerData;
    }
    @Override
    public LevelData getLevelData() {
        return levelData;
    }
    
    @Override
    public boolean getCampaignMode(){
        return this.isCampaign;
    }    
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    /*Methods used in order to get the element of the Model Scenary. This allow to
    draw the scenery in the View Module.
    */
    @Override
    public ArrayList<PenguinModel> getPenguins()
    {
        return this.penguins;
    }

    @Override
    public IceBlockTerrainModel getPlayerBase()
    {
        return this.playerBase;   
    }
    
    @Override
    public IceBlockTerrainModel getEnemyBase()
    {
        return this.enemyBase;
    }
    
    @Override
    public void resetPlayerBullet()
    {
        this.bullet.setDamageFactor(this.playerCurrentDamageFactor);
    }
    @Override
    public void resetSecondPlayerBullet()
    {
        this.bullet.setDamageFactor(this.secondPlayerCurrentDamageFactor);
    }
    
    @Override
    public int getCurrentPlayerBullet()
    {
        return this.playerCurrentDamageFactor;
    }
    
    @Override
    public void setCurrentPlayerBullet(int damageFactor)
    {
        this.playerCurrentDamageFactor = damageFactor;
    }
    @Override
    public int getSecondPlayerCurrentDamageFactor() 
    {
        return secondPlayerCurrentDamageFactor;
    }
    @Override
    public int getLevel()
    {
        return this.level;
    }
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    /*Methods used in order to set up the Model Module.
    */
    
    private void setupConfiguration()
    {
        this.bulletRadius = Config.getInstance().getBulletRadius();

        this.battlefieldWidth = Config.getInstance().getBattlefieldWidth();
        this.battlefieldHeight = Config.getInstance().getBattlefieldHeight();

    }
    @Override
    public void init(int level,boolean isCampaign,IceBlockTerrainModel playerBase,IceBlockTerrainModel enemyBase)
    {
 
        this.isCampaign = isCampaign;
        this.level = level;
        setupConfiguration();
        setupResults();
        this.playerBase = playerBase;
        this.enemyBase = enemyBase;

        penguins = new ArrayList<>();
        this.playerCurrentDamageFactor = Constants.SNOWBALL_DAMAGE_FACTOR;
        this.secondPlayerCurrentDamageFactor = Constants.SNOWBALL_DAMAGE_FACTOR;

        this.playerScore = 0;
        this.indexTurn = 0;
        
        //Sarebbe opportuno recuperare questi dati da un file di configurazione.
        this.bullet = new BulletModel(Config.getInstance().getPlayerBulletStartXPosition(),
                Config.getInstance().getPlayerBulletStartYPosition(),
                this.bulletRadius,
                this.playerCurrentDamageFactor); 
        this.numberAlliedPenguins = Config.getInstance().getNumberPenguins();
        this.numberEnemyPenguins = Config.getInstance().getNumberPenguins();
        
        int penguinLife = 100;
        switch(this.player.getDifficultyLevel())
        {
            case Constants.EASY_LEVEL:
                penguinLife = Constants.EASY_LEVEL_PENGUIN_LIFE;
                break;
            case Constants.MEDIUM_LEVEL:
                penguinLife = Constants.MEDIUM_LEVEL_PENGUIN_LIFE;
                break;
            case Constants.HARD_LEVEL:
                penguinLife = Constants.HARD_LEVEL_PENGUIN_LIFE;
                break;
            default:
                break;
        }
            
        
        for(int i=0;i<this.numberAlliedPenguins;i++)
        {
            double distance = Config.getInstance().getDistanceBetweenPenguins()*i;
            PenguinModel penguin = new PenguinModel(Config.getInstance().getAlliedPenguinsStartXPosition()+distance,
                    Config.getInstance().getPenguinsStartYPosition(), //Penguin Position
                    penguinLife, //Penguin Life Points
                    true); //Allied Penguin
            this.penguins.add(penguin);  
        }
        
        for(int i=0;i<this.numberEnemyPenguins;i++)
        {
            double distance = Config.getInstance().getDistanceBetweenPenguins()*i;
            PenguinModel penguin = new PenguinModel(Config.getInstance().getEnemyPenguinsStartXPosition()-distance,
                    Config.getInstance().getPenguinsStartYPosition(),
                    penguinLife,
                    false);
            this.penguins.add(penguin);
        }
    }

    @Override
    public void setPlayerData() throws IOException
    {
        LinkedList<String[]> lstRows=null;

            playerData = new PlayerData();
            if(new File("gameprofiles/saved.csv").exists())
            {
                lstRows = ReadCSV.getRows("gameprofiles/saved.csv", "UTF-8");
            }
            else
            {
                new File("gameprofiles").mkdir();
                new File("gameprofiles/saved.csv").createNewFile();
            }
            
            if(lstRows!=null)
            for(String[] currentPlayer : lstRows)
            {
                playerData.add(new Player(Integer.parseInt(currentPlayer[0]),
                        currentPlayer[1],Integer.parseInt(currentPlayer[2]),
                        Integer.parseInt(currentPlayer[3]),
                        Integer.parseInt(currentPlayer[4]),
                        Integer.parseInt(currentPlayer[5])));
            }   
    }
    
    @Override
    public void deleteProfile(int idProfile)
    {
        for(Player p: this.playerData.getListOfPlayers())
            if(p.getPlayerId() == idProfile)
                this.playerData.getListOfPlayers().remove(p);
        try
        {
            WriteCSV.print("gameprofiles/saved.csv", "UTF-8", this.playerData.asListOfStringArray());
        }catch(IOException ioe)
        {
            ControllerForModel.getInstance().notifyException("Si è verificato un errore: "+ioe);
        }
    }
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //Other Methods
    private Model() 
    {

    }
    public static IModel getInstance() {
	if (instance == null)
            instance = new Model();
	return instance;  
    }
     
}
