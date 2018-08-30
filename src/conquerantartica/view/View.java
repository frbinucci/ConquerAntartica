/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import conquerantartica.utils.Config;
import conquerantartica.utils.Constants;
import conquerantartica.utils.Resources;
import java.util.concurrent.atomic.AtomicBoolean;


import conquerantartica.controller.ControllerForView;

/**
 *
 * @author franc
 */
public class View implements IView {
   
    //STATIC FIELDS
    //--------------------------------------------------------------------------

    private final static int START_WINDOW_WIDTH = 550;
    private final static int START_WINDOW_HEIGHT = 550;
    
    private final static int NEW_PROFILE_WINDOW_WIDTH = 550;
    private final static int NEW_PROFILE_WINDOW_HEIGHT = 215;
    
    private final static int LOAD_PROFILE_WINDOW_WIDTH = 600;
    private final static int LOAD_PROFILE_WINDOW_HEIGHT = 450;
    
    private final static int MAIN_MENU_WINDOW_WIDTH = 550;
    private final static int MAIN_MENU_WINDOW_HEIGHT = 350;
    
    private final static int SCENERY_SELECTION_WINDOW_WIDTH = 550;
    private final static int SCENERY_SELECTION_WINDOW_HEIGHT = 300;
    
    private final static int HALL_OF_FAME_WINDOW_WIDTH = 550;
    private final static int HALL_OF_FAME_WINDOW_HEIGHT = 400;
    
    private final static int INSTRUCTION_WINDOW_WIDTH = 600;
    private final static int INSTRUCTION_WINDOW_HEIGHT = 500;
    //--------------------------------------------------------------------------
    
    //INSTANCE FIELDS
    //--------------------------------------------------------------------------
    private static View instance = null;    
    private GameWindow gameWindow = null;
    private Stage shownWindow; 
    private boolean campaignMode;
    //--------------------------------------------------------------------------
        
    //METHODS AREA
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //Methods used in order to manage the transition between different game screens.
    private void prepareSceneToShowWindow(String xmlSrc,String title,int windowWidth,int windowHeight)
    {
        closeGameWindow();
        Parent root;
        try
        {
            root = FXMLLoader.load(getClass().getResource(xmlSrc));
            shownWindow.getIcons().add(Resources.GeneralImages.ICON.getImage());
            shownWindow.setScene(new Scene(root,windowWidth,windowHeight));
            shownWindow.setTitle(title);
            shownWindow.show();
            shownWindow.setResizable(false);   
        }catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void prepareGameScreen(IceBlockTerrain playerBase,IceBlockTerrain enemyBase)
    {
        closeGameWindow();
        this.gameWindow = new GameWindow();
        gameWindow.getIcons().add(Resources.GeneralImages.ICON.getImage());
        this.gameWindow.getBattlefield().initBattlefield(playerBase,enemyBase);
        Resources.Music.SOUNDTRACK.play();
        gameWindow.show();
        shownWindow.setScene(null);
        shownWindow.hide();
        
    }
    @Override
    public void openStartWindow()
    {
        prepareSceneToShowWindow("MainWindow.fxml",
                "ConquerAntartica - Java Edition",
                START_WINDOW_WIDTH,
                START_WINDOW_HEIGHT);
    }
    @Override
    public void openInstructionWindow()
    {
        prepareSceneToShowWindow("InstructionWindow.fxml",
        "Istruzioni di Gioco",
        INSTRUCTION_WINDOW_WIDTH,
        INSTRUCTION_WINDOW_HEIGHT);
    }
    @Override
    public void openMainMenu() {
        prepareSceneToShowWindow("GameMenu.fxml",
                "Menu Principale",
                MAIN_MENU_WINDOW_WIDTH,
                MAIN_MENU_WINDOW_HEIGHT);  
    }
    
    @Override
    public void openMultiplayerSelection()
    {
        prepareSceneToShowWindow("MultiplayerScenerySelection.fxml",
                "Modalità Multigiocatore",
                SCENERY_SELECTION_WINDOW_WIDTH,
                SCENERY_SELECTION_WINDOW_HEIGHT);
    }

    private void closeGameWindow()
    {
        if(this.gameWindow!=null)
        {
            Resources.Music.SOUNDTRACK.stop();
            gameWindow.close();
            gameWindow = null;
        }
    }
     
    @Override
    public void openNewProfileWindow() {
        this.prepareSceneToShowWindow("NewProfileWindow.fxml",
                "Creazione di un nuovo profilo di gioco", 
                NEW_PROFILE_WINDOW_WIDTH, 
                NEW_PROFILE_WINDOW_HEIGHT);
    }

    @Override
    public void openLoadProfileWindow() {
        prepareSceneToShowWindow("LoadProfileWindow.fxml",
                "Selezione del profilo di gioco",
                LOAD_PROFILE_WINDOW_WIDTH,
                LOAD_PROFILE_WINDOW_HEIGHT);
    }
    
    @Override
    public void openSelectMissionWindow()
    {
        this.prepareSceneToShowWindow("SelectLevelWindow.fxml",
                "Selezione Scenario",
                SCENERY_SELECTION_WINDOW_WIDTH,
                SCENERY_SELECTION_WINDOW_HEIGHT);
    }
    @Override
    public void openHallOfFameWindow()
    {
        this.prepareSceneToShowWindow("HallOfFame.fxml",
                "Hall Of Fame",
                HALL_OF_FAME_WINDOW_WIDTH,
                HALL_OF_FAME_WINDOW_HEIGHT);
    }
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //Methods used in order to show Dialog Windows.

    @Override
    public void showInformationDialog(String message, String title)
    {

            Alert dialogWindow = new Alert(AlertType.INFORMATION);
            ((Stage) dialogWindow.getDialogPane().getScene().getWindow()).getIcons()
                .add(Resources.GeneralImages.ICON.getImage());
            dialogWindow.setTitle(title);
            dialogWindow.setContentText(message);
            dialogWindow.showAndWait();

    }

    @Override
    public boolean showConfirmationDialog(String message,String title, String firstOption, String secondOption)
    {
        AtomicBoolean userChoose = new AtomicBoolean();
        Alert dialogWindow = new Alert(AlertType.INFORMATION);
        dialogWindow.setTitle(title);
        dialogWindow.setHeaderText(null);
        dialogWindow.setContentText(message);
        ((Stage) dialogWindow.getDialogPane().getScene().getWindow()).getIcons()
                .add(Resources.GeneralImages.ICON.getImage());
        
        ButtonType opt1 = new ButtonType(firstOption);
        ButtonType opt2 = new ButtonType(secondOption);
       
        dialogWindow.getButtonTypes().setAll(opt1,opt2);
       
        Optional<ButtonType> result = dialogWindow.showAndWait();
        userChoose.set(result.get() == opt1);

        dialogWindow.close();
        return userChoose.get();
    }
    @Override
    public void showErrorDialog(String message)
    {
        Alert error = new Alert(AlertType.ERROR);
        error.setTitle("Si è verificato un errore...");
        error.setHeaderText("Si è verificato l'errore:");
        error.setContentText(message); 
        error.show();
    }
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //Methods used in order to move the bullet in the screen. 
    @Override
    public void updateXBullet(double xTranslation) {
        this.gameWindow.getBattlefield().getPlayerBullet().setTranslateX(xTranslation);
        
    }
    @Override
    public void updateYBullet(double yTranslation) {
        this.gameWindow.getBattlefield().getPlayerBullet().setTranslateY(yTranslation);
    }
    @Override
    public void updateBulletCenter(double xCenter, double yCenter) {
        this.gameWindow.getBattlefield().getPlayerBullet().setCenterX(xCenter);
        this.gameWindow.getBattlefield().getPlayerBullet().setCenterY(yCenter);
    }

    @Override
    public double getXBulletPosition() {
        return (this.gameWindow.getBattlefield().getPlayerBullet().getTranslateX()+this.gameWindow.getBattlefield().getPlayerBullet().getCenterX());
    }

    @Override
    public double getYBulletPosition() {
        return (this.gameWindow.getBattlefield().getPlayerBullet().getTranslateY()+this.gameWindow.getBattlefield().getPlayerBullet().getCenterY());
    } 
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //Methods used in order to manage the battlefield.
    @Override
    public Battlefield getBattlefield()
    {
        return this.gameWindow.getBattlefield();
    }
    @Override
    public ArrayList<ImageView> getPenguins()
    {
        return this.gameWindow.getBattlefield().getPenguins();
    }
    @Override
    public void rotateCannon(Rectangle cannon,double angle) {
        
        double x =cannon.getLocalToSceneTransform().getMyx();
        double y = cannon.getLocalToSceneTransform().getMyy();        

        RotateTransition rotation = new RotateTransition(Duration.seconds(0.01),cannon);
        
        double nextAngle = Math.atan2(x,y)+(angle*Math.PI/180);

        if(ControllerForView.getInstance().isPlayerTwoControlDisabled())
        {
            if(Math.abs(nextAngle)<=Math.PI/2 && nextAngle<=0)
                rotation.setByAngle(angle);    
        }
        else
        {
            if(Math.abs(nextAngle)<=Math.PI/2 && nextAngle>=0)
                rotation.setByAngle(angle);
        }
        rotation.play();

    }
    @Override
    public double getCannonAngle(Rectangle cannon){
        double x = cannon.getLocalToSceneTransform().getMyx();
        double y = cannon.getLocalToSceneTransform().getMyy();
        
        return Math.atan2(x,y)*-1;
    }
    private Rectangle getPlayerCannon() {
        return this.gameWindow.getBattlefield().getCannon();
    }
    private Rectangle getEnemyCannon() {
        return this.gameWindow.getBattlefield().getEnemyCannon();
    } 
    @Override
    public void rotateEnemyCannon(double angle,double powerFire){
        RotateTransition rotation = new RotateTransition(Duration.seconds(2),this.getEnemyCannon());
      
        rotation.setByAngle(angle);

        rotation.setOnFinished(event -> {
            double shootAngle = (this.getEnemyCannon().getRotate())*Math.PI/180;
            ControllerForView.getInstance().shoot(Math.PI-shootAngle, 
                    powerFire,
                    Config.getInstance().getEnemyBulletStartX(),
                    Config.getInstance().getEnemyBulletStartY());
            RotateTransition finalRotation = new RotateTransition(Duration.seconds(2),this.getEnemyCannon());
            finalRotation.setToAngle(0);
            finalRotation.play();
            Resources.SoundEffects.CANNON.play();
        });
        
        rotation.play();
        
    }
    
    @Override
    public void hurtPenguin(int index,boolean isAllied) {
        int rotationFactor = 90; //Set the direction of the rotation.
        if(isAllied)
        {
            this.getBattlefield().getPenguins().get(index).setImage(
                    Resources.GeneralImages.ALLIEDPENGUINHURTED.getImage());
            rotationFactor = -90;
        }
        else
        {
            this.getBattlefield().getPenguins().get(index).setImage(
                    Resources.GeneralImages.ENEMYPENGUINHURTED.getImage());
        }
        Resources.SoundEffects.PENGUINHIT.play();
        RotateTransition rt = new RotateTransition(Duration.millis(1000),this.getBattlefield().getPenguins().get(index));
        rt.setToAngle(rotationFactor);
        rt.setFromAngle(rotationFactor);
        rt.setToAngle(0);
        rt.setOnFinished(event -> { 
            if(isAllied)
            {
                this.getBattlefield().getPenguins().get(index).setImage(
                    Resources.GeneralImages.ALLIEDPENGUIN.getImage());
            }
            else
            {
                this.getBattlefield().getPenguins().get(index).setImage(
                    Resources.GeneralImages.ENEMYPENGUIN.getImage());     
            }
        });
        rt.play();
    }
    
    @Override
    public void killPenguin(int arrayIndex,boolean isAllied){
        ImageView penguinToKill = this.gameWindow.getBattlefield().getPenguins().get(arrayIndex);
        String penguinType = "";
        
        final Image[] deathAnimationImages = new Image[9];
        int frame =0;
        if(isAllied)
            for(Resources.AlliedPenguinDeath currentFrame : Resources.AlliedPenguinDeath.values())
            {
                deathAnimationImages[frame] = currentFrame.getImage();
                frame++;
            }        
        else
            for(Resources.EnemyPenguinDeath currentFrame : Resources.EnemyPenguinDeath.values())
            {
                deathAnimationImages[frame] = currentFrame.getImage();
                frame++;
            }   
            
        Resources.SoundEffects.PENGUINKILL.play();
        Duration frameDuration = Duration.seconds(0.5d / deathAnimationImages.length); // 1 second for complete animation
        
        Timeline deathAnimation = new Timeline(new KeyFrame(frameDuration, new EventHandler<ActionEvent>() {
            private int index = 0;    
            @Override
                    public void handle(ActionEvent event) {
                        penguinToKill.setImage(deathAnimationImages[index]);
                        index++;
                    }
            }));
        deathAnimation.setCycleCount(deathAnimationImages.length);
        deathAnimation.setOnFinished(event -> { 
                this.getPenguins().remove(arrayIndex);
                this.gameWindow.getBattlefield().getChildren().remove(penguinToKill);
        });
        deathAnimation.play();

    }      
    @Override
    public void setCampaignMode(boolean campaignMode){
        this.campaignMode = campaignMode;
    }
    @Override
    public void setBulletVisibility(boolean visibility)
    {
        this.gameWindow.getBattlefield().getPlayerBullet().setVisible(visibility);
    }
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //Methods used in order to manage the data about the player     
    @Override
    public void updatePlayerScore(int score)
    {
        this.gameWindow.getPlayerData().setPlayerScore(score);
    }
    
    @Override 
    public void setPlayerTurn(int turn)
    {
        this.gameWindow.getPlayerData().setPlayerTurn(turn);
    }
    
    @Override
    public void updateFireProgressBar(double powerFire)
    {
        gameWindow.getPlayerData().setProgressBar(powerFire);
    }
    
    @Override
    public void updateWeapon(int weaponType)
    {
        switch (weaponType) {
            case Constants.BOMB_DAMAGE_FACTOR:
                    this.gameWindow.getBattlefield().getPlayerBullet().setFill(
                            new ImagePattern(Resources.WeaponsImages.BOMB.getImage()));
                    ControllerForView.getInstance().changeDamageFactor(Constants.BOMB_DAMAGE_FACTOR);
                    break;
            case Constants.ROCKET_DAMAGE_FACTOR:
                    this.gameWindow.getBattlefield().getPlayerBullet().setFill(
                            new ImagePattern(Resources.WeaponsImages.ROCKET.getImage()));
                    ControllerForView.getInstance().changeDamageFactor(Constants.ROCKET_DAMAGE_FACTOR);
                    break;
            case Constants.SNOWBALL_DAMAGE_FACTOR:
                    this.gameWindow.getBattlefield().getPlayerBullet().setFill(
                            new ImagePattern(Resources.WeaponsImages.SNOWBALL.getImage()));
                    ControllerForView.getInstance().changeDamageFactor(Constants.SNOWBALL_DAMAGE_FACTOR);
                break;
            default:
                break; 
        }
    }
    

    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //Other Methods

    private View()
    {
        shownWindow = new Stage();
    }

    public static IView getInstance() {
	if (instance == null)
            instance = new View();
	return instance;  
    } 

}
