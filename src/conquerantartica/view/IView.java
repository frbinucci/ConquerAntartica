/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;

import java.util.ArrayList;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author franc
 */

//Interfaccia per il Controller.
public interface IView {
    
    //Methods used in order to manage the transition between the different game screens.
    public void openStartWindow();
    public void openNewProfileWindow();
    public void openInstructionWindow();
    public void openLoadProfileWindow();
    public void openMainMenu();
    public void openSelectMissionWindow();
    public void openMultiplayerSelection();
    public void setCampaignMode(boolean campaignMode);
    public void setPlayerTurn(int turn);
    public void showErrorDialog(String message);
    public void openHallOfFameWindow();
    //public void showExitWindow();
    public boolean showConfirmationDialog(String message,String title, String firstOption, String secondOption);

    public void prepareGameScreen(IceBlockTerrain playerBase, IceBlockTerrain enemyBase);
        

    public void showInformationDialog(String message, String title);

    
    //Methods used in order to move the bullet in the game screen.
    public void updateXBullet(double xTranslation);
    public void updateYBullet(double yTranslation);
    public void updateBulletCenter(double xCenter,double yCenter);
    public double getXBulletPosition();
    public double getYBulletPosition();
    
    //Methods used in order to manage the battlefield.
    public Battlefield getBattlefield();
    public void rotateCannon(Rectangle cannon,double angle);
    public double getCannonAngle(Rectangle cannon);
    public void rotateEnemyCannon(double angle,double powerFire);
    public void hurtPenguin(int index,boolean isAllied);
    public void killPenguin(int index,boolean isAllied);
    public void setBulletVisibility(boolean visibility);
    public ArrayList<ImageView> getPenguins();
    
    
    //Methods used in order to update  data about the player.
    public void updatePlayerScore(int score);
    public void updateFireProgressBar(double powerFire);
    public void updateWeapon(int weaponType);
    

    

   

    

}
