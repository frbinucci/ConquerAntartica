/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javafx.animation.AnimationTimer;

import conquerantartica.model.Level1Model;
import conquerantartica.model.Level2AlliedModel;
import conquerantartica.model.Level2EnemyModel;
import conquerantartica.model.Level3AlliedModel;
import conquerantartica.model.Level3EnemyModel;
import conquerantartica.model.Level4Model;
import conquerantartica.model.Level5Model;

import conquerantartica.model.PenguinModel;
import conquerantartica.utils.Config;
import conquerantartica.utils.Constants;
import conquerantartica.utils.ReadCSV;
import conquerantartica.utils.Resources;
import conquerantartica.utils.WriteCSV;
import conquerantartica.view.Level1;
import conquerantartica.view.Level2Allied;
import conquerantartica.view.Level2Enemy;
import conquerantartica.view.Level3Allied;
import conquerantartica.view.Level3Enemy;
import conquerantartica.view.Level4;
import conquerantartica.view.Level5;

import conquerantartica.view.View;
import conquerantartica.model.Model;
/**
 *
 * @author franc
 */
public class ControllerForView implements IControllerForView {
    //STATIC FIELDS
    //--------------------------------------------------------------------------
    private static ControllerForView instance = null;  
    //--------------------------------------------------------------------------
    
    //Instance Fields
    //--------------------------------------------------------------------------
    private boolean disablePlayerControl;
    private boolean disablePlayerTwoControl;
    private boolean isTwoPlayerMatch;

    //--------------------------------------------------------------------------
    
    //Method Area
    //--------------------------------------------------------------------------
    /*Methods Used in order to pass Information between Model and View module.*/
    @Override
    public double getXStartPosition() {
        return Model.getInstance().getBulletXStartPosition();
    }

    @Override
    public double getYStartPosition() {
       return Model.getInstance().getBulletYStartPosition();
    }
    @Override
    public ArrayList<double[]> getPenguins(boolean allied)
    {
        ArrayList<double[]> coordinates = new ArrayList<>();
        ArrayList<PenguinModel> penguinsModel = Model.getInstance().getPenguins();
        penguinsModel.forEach((nextPenguin) -> {
            if(allied)
            {
                if(nextPenguin.isAllied())
                {
                    double[] coordinatesOfNextPenguin = {
                        nextPenguin.getX(),nextPenguin.getY()
                    };
                    coordinates.add(coordinatesOfNextPenguin);
                }
            }
            else
            {
                if(!nextPenguin.isAllied())
                {
                    double[] coordinatesOfNextPenguin = {
                        nextPenguin.getX(),nextPenguin.getY()
                    };
                    coordinates.add(coordinatesOfNextPenguin);
                }
            }
        });
        
        return coordinates;
    }
    @Override
    public String getPlayerName(){
        return Model.getInstance().getPlayerName();
    }
    
    @Override
    public int getPlayerScore(){
        return Model.getInstance().getPlayerScore();
    }
    @Override
    public void changeDamageFactor(int damageFactor){
        Model.getInstance().changeBulletDamageFactor(damageFactor);
    }
    @Override 
    public void resetBullet(int damageFactor){
        if(this.isPlayerTwoControlDisabled())
            Model.getInstance().setCurrentPlayerBullet(damageFactor);
        else if(this.isControlDisabled())
            Model.getInstance().setSecondPlayerCurrentDamageFactor(damageFactor);
    } 
    
    @Override
    public boolean isControlDisabled(){
        return this.disablePlayerControl;
    }
    @Override
    public boolean isPlayerTwoControlDisabled(){
        return this.disablePlayerTwoControl;
    }
    @Override
    public void disablePlayerTwoControl(){
       this.disablePlayerTwoControl = true;
    }
    @Override
    public int getNumberOfLives(){
        return Model.getInstance().getPlayerNumberOfLives();
    }
    @Override
    public int getPlayerMission(){
        return Model.getInstance().getPlayerCurrentLevel();
    }
    @Override
    public int getHighScore(){
        return Model.getInstance().getPlayerHighScore();
    }
    @Override
    public boolean isMultiplayer(){
        return this.isTwoPlayerMatch;
    }
    @Override
    public void setMultiplayerMode(boolean isMultiplayer){
        Model.getInstance().setIsMultiplayerMatch(isMultiplayer);
        this.isTwoPlayerMatch = isMultiplayer;
    }
    @Override
    public void disablePlayerControl() {
        this.disablePlayerControl = true;
    }
    @Override 
    public int getCurrentMission(){
        return Model.getInstance().getLevel();
    }
    
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    /*Methods used in order to control the course of the match. These methods include
    most of the logic of the game.
    */
    private void initBulletPositionToShoot(double xPosition,double yPosition)
    {
        View.getInstance().updateBulletCenter(xPosition, yPosition);
        Model.getInstance().updateBulletCenter(xPosition, yPosition);
        Model.getInstance().updateXBulletCoordinate(0);
        Model.getInstance().updateYBulletCoordinate(0);
        View.getInstance().updateXBullet(0);
        View.getInstance().updateYBullet(0);
    }
    
    @Override
    public void shoot(double angle,double speed,double initialXBulletPosition,double initialYBulletPosition)
    {
        initBulletPositionToShoot(initialXBulletPosition, initialYBulletPosition);
        View.getInstance().setBulletVisibility(true);
        new AnimationTimer(){
            double speedX = Math.cos(angle)*speed;
            double speedY = Math.sin(angle)*speed;
            int timeTranslation = 0;
            
            @Override
            public void handle(long now){
                double moveXComponent = speedX * (timeTranslation);
                double moveYComponent = -speedY * (timeTranslation) + (0.1 * Math.pow(timeTranslation, 2)) / 2;
                timeTranslation++;
                if(!Model.getInstance().isBulletGetSceneryLimits())
                {
                    if(Model.getInstance().isBulletHurtingBase())
                    {
                        Resources.SoundEffects.BOUNCE.play();
                        double newAngle = Math.atan2(moveYComponent * -1, moveXComponent) - Math.PI; 
                        this.stopAnimation(false); 
                        ControllerForView.getInstance().shoot(newAngle,
                                speed/2,
                                //L'elevazione a potenza serve a discriminare la direzione di provenienza del proiettile.
                                Model.getInstance().getBulletXPosition() -Math.pow(-1,Model.getInstance().getIndexTurn())*Constants.BOUNCING,
                                Model.getInstance().getBulletYPosition() -Constants.BOUNCING);
                    } else if (!Model.getInstance().penguinHit()) {
                        ControllerForView.getInstance().moveBullet(moveXComponent, moveYComponent);
                    } else {
                        this.stopAnimation(true);
                    }
                } else {
                    this.stopAnimation(true);
                }
            }
            
            private void stopAnimation(boolean updateTurn)
            {
                this.stop();
                if(updateTurn)
                {
                    Model.getInstance().updateTurn();
                    ControllerForView.getInstance().nextTurn();
                    View.getInstance().setBulletVisibility(false);
                }
            }
        }.start();
    }
    
    @Override
    public void nextTurn() {
        if(!Model.getInstance().isMatchLost() && !Model.getInstance().isMatchWin())
        {
            if(Model.getInstance().getIndexTurn()%2==0)
            {                
                if(Model.getInstance().getIndexTurn()!=0)
                {
                    View.getInstance().updateWeapon(Model.getInstance().getCurrentPlayerBullet());
                    View.getInstance().setPlayerTurn(Model.getInstance().getIndexTurn()/2);
                }
                this.disablePlayerControl = false;
                if(isTwoPlayerMatch)
                    this.disablePlayerTwoControl = true;
            }
            else
            {
                this.disablePlayerControl = true;
                if(!isTwoPlayerMatch)
                    computerShoot();  
                else
                {
                    if(Model.getInstance().getIndexTurn()!=1)
                    {
                       View.getInstance().updateWeapon(Model.getInstance().getSecondPlayerCurrentDamageFactor());
                    }   
                    this.disablePlayerTwoControl = false;
                }
            }
        }
        else
        {
            this.disablePlayerControl = true; 
        }
    }
    private void computerShoot(){
        double randomFactor = new Random().nextGaussian()*Constants.STD_DEV;
        //The power fire is computed in a range between 10 and 12.
        double powerFire = (double) new Random().nextInt(Constants.MAX_ENEMY_POWER_FIRE - Constants.MIN_ENEMY_POWER_FIRE + 1) + Constants.MIN_ENEMY_POWER_FIRE;
        //The range of the bullet is computed as the difference between the enemy base cannon and the allied base cannon.
        double rangeOfFire = Config.getInstance().getEnemyBaseCannonXPosition() - Config.getInstance().getAlliedBaseCannonXPosition();
        double angle = 0.5*Math.asin((rangeOfFire*Constants.GRAVITY_ACCELERATION)/Math.pow(powerFire, 2)); 
        angle = Math.toDegrees(angle+randomFactor);
        
        //Random selection of the bullet.
        int[] dmgFactors = {Constants.SNOWBALL_DAMAGE_FACTOR,Constants.BOMB_DAMAGE_FACTOR,Constants.ROCKET_DAMAGE_FACTOR};
        int dmgIndex = (new Random().nextInt(dmgFactors.length));

        View.getInstance().updateWeapon(dmgFactors[dmgIndex]);   
        Model.getInstance().changeBulletDamageFactor(dmgFactors[dmgIndex]);
        
        View.getInstance().rotateEnemyCannon(angle,powerFire);
    }
    

    
    @Override
    public void moveBullet(double moveX,double moveY)
    {
        Model.getInstance().updateXBulletCoordinate(moveX);
        Model.getInstance().updateYBulletCoordinate(moveY);
        View.getInstance().updateXBullet(moveX);
        View.getInstance().updateYBullet(moveY);
    }
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //Methods used in order to manage game profiles.
    @Override
    public void createNewGameProfile(String name,int difficultyLevel) throws IOException
    {
        int playerId=-1;
        int numberOfLives=0;
        switch(difficultyLevel)
        {
            case Constants.EASY_LEVEL:
                numberOfLives = Constants.EASY_LEVEL_NUMBER_OF_LIVES;
                break;
            case Constants.MEDIUM_LEVEL:
                numberOfLives = Constants.MEDIUM_LEVEL_NUMBER_OF_LIVES;
                break;
            case Constants.HARD_LEVEL:
                numberOfLives = Constants.HARD_LEVEL_NUMBER_OF_LIVES;
                break;
            default:
                break;
        }
        
        if(new File("gameprofiles/saved.csv").exists())  
        {
            if (!ReadCSV.getRows("gameprofiles/saved.csv", "UTF-8").isEmpty())
                playerId = Integer.parseInt(ReadCSV.getRows("gameprofiles/saved.csv", "UTF-8").getLast()[0]);
        }
        else
        {
            new File("gameprofiles").mkdir();
            new File("gameprofiles/saved.csv").createNewFile();
        }

        String[] playerData = new String[6];
        playerData[0]=String.valueOf(++playerId); //Setting up the ID of the player.
        playerData[1]=name; //Setting up the name of the player.
        playerData[2]=String.valueOf(0); //Setting up the High-Score.
        playerData[3]=String.valueOf(numberOfLives); //Setting up the number of lives.
        playerData[4]=String.valueOf(difficultyLevel); //Setting upt the current difficulty level.
        playerData[5]=String.valueOf(1); //Setting up the current scenery in the campaign.

        WriteCSV.printRow("gameprofiles/saved.csv","UTF-8" , playerData);

        Model.getInstance().setPlayerData();
    }
    @Override
    public LinkedList<String[]> getListOfPlayers() throws IOException{
        Model.getInstance().setPlayerData();
        return Model.getInstance().getPlayerData().asListOfStringArray();
    }
    @Override 
    public LinkedList<String[]> getLevelStats() throws IOException{
        Model.getInstance().setupResults();
        return Model.getInstance().getLevelData().asListOfStringArray();
    }
    @Override 
    public String[] getLevelData(int level){
        return Model.getInstance().getLevelData().searchForLevelId(level).dataAsStringArray();
    }
    
    @Override
    public void loadGameProfile(int idProfile) throws IOException{
        Model.getInstance().setPlayerData();
        Model.getInstance().getPlayerData().getListOfPlayers().stream().filter((p) -> (p.getPlayerId()==idProfile)).forEachOrdered((p) -> {
            Model.getInstance().setCurrentPlayer(p);
        });
    }
    @Override
    public void loadLevel(int level,boolean modality)
    {
        this.disablePlayerTwoControl = true;
        this.disablePlayerControl = false;
        
        String path = "/resources/config/level"+level+".txt";

        switch(level)
        {
            case Constants.MISSION_ONE_ID:
                Config.getInstance().changeConfigurationFile(path);
                Model.getInstance().init(level, 
                        modality,
                        new Level1Model(Config.getInstance().getPlayerBaseStartX(),Config.getInstance().getPlayerBaseStartY()),
                        new Level1Model(Config.getInstance().getEnemyBaseStartX(),Config.getInstance().getEnemyBaseStartY()));
                View.getInstance().prepareGameScreen(
                        new Level1(Config.getInstance().getPlayerBaseStartX(),Config.getInstance().getPlayerBaseStartY()), 
                        new Level1(Config.getInstance().getEnemyBaseStartX(),Config.getInstance().getEnemyBaseStartY()));
                break;
            case Constants.MISSION_TWO_ID:
                Config.getInstance().changeConfigurationFile(path);
                Model.getInstance().init(level, 
                        modality,
                        new Level2AlliedModel(Config.getInstance().getPlayerBaseStartX(),Config.getInstance().getPlayerBaseStartY()),
                        new Level2EnemyModel(Config.getInstance().getEnemyBaseStartX(),Config.getInstance().getEnemyBaseStartY()));
                View.getInstance().prepareGameScreen(
                        new Level2Allied(Config.getInstance().getPlayerBaseStartX(),Config.getInstance().getPlayerBaseStartY()), 
                        new Level2Enemy(Config.getInstance().getEnemyBaseStartX(),Config.getInstance().getEnemyBaseStartY()));
                break;
            case Constants.MISSION_THREE_ID:
                Config.getInstance().changeConfigurationFile(path);
                Model.getInstance().init(level, 
                        modality,
                        new Level3AlliedModel(Config.getInstance().getPlayerBaseStartX(),Config.getInstance().getPlayerBaseStartY()),
                        new Level3EnemyModel(Config.getInstance().getEnemyBaseStartX(),Config.getInstance().getEnemyBaseStartY()));
                View.getInstance().prepareGameScreen(
                        new Level3Allied(Config.getInstance().getPlayerBaseStartX(),Config.getInstance().getPlayerBaseStartY()), 
                        new Level3Enemy(Config.getInstance().getEnemyBaseStartX(),Config.getInstance().getEnemyBaseStartY()));
                break;
            case Constants.MISSION_FOUR_ID:
                Config.getInstance().changeConfigurationFile(path);
                Model.getInstance().init(level, 
                        modality,
                        new Level4Model(Config.getInstance().getPlayerBaseStartX(),Config.getInstance().getPlayerBaseStartY()),
                        new Level4Model(Config.getInstance().getEnemyBaseStartX(),Config.getInstance().getEnemyBaseStartY()));
                View.getInstance().prepareGameScreen(
                        new Level4(Config.getInstance().getPlayerBaseStartX(),Config.getInstance().getPlayerBaseStartY()), 
                        new Level4(Config.getInstance().getEnemyBaseStartX(),Config.getInstance().getEnemyBaseStartY()));
                break;
            case Constants.MISSION_FIVE_ID:
                Config.getInstance().changeConfigurationFile(path);
                Model.getInstance().init(level, 
                        modality,
                        new Level5Model(Config.getInstance().getPlayerBaseStartX(),Config.getInstance().getPlayerBaseStartY()),
                        new Level5Model(Config.getInstance().getEnemyBaseStartX(),Config.getInstance().getEnemyBaseStartY()));
                View.getInstance().prepareGameScreen(
                        new Level5(Config.getInstance().getPlayerBaseStartX(),Config.getInstance().getPlayerBaseStartY()), 
                        new Level5(Config.getInstance().getEnemyBaseStartX(),Config.getInstance().getEnemyBaseStartY()));
                break;
            default:
                View.getInstance().showInformationDialog("Complimenti, hai completato tutte le missioni. "
                        + "Ora puoi rigiocare un qualunque scenario senza perdere vite",
                        "Campagna Completata");
                break;
        }
    }
    @Override
    public void deleteProfile(int idProfile){
        Model.getInstance().deleteProfile(idProfile);
    }
    //--------------------------------------------------------------------------
    
    //Other Methods.
    //--------------------------------------------------------------------------
    private ControllerForView()
    {
        this.disablePlayerControl = false;
    }
    
    public static IControllerForView getInstance() {
	if (instance == null)
            instance = new ControllerForView();
	return instance;
    }

}
