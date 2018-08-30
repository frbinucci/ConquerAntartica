/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;


import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import conquerantartica.controller.ControllerForView;
import conquerantartica.utils.Config;
import conquerantartica.utils.Resources;

/**
 *
 * @author franc
 */
public class GameWindow extends Stage {
    
    private static int NEXT_ANGLE_MOVEMENT = 1;
    
    
    private Battlefield battlefield = null;
    private BorderPane gameWindowLayout;
    private PlayerControlPanel playerData;
    private int powerFire;
    
    public GameWindow()  
    {
        super();
        battlefield = new Battlefield();

        gameWindowLayout = new BorderPane();
        
        playerData = new PlayerControlPanel();
        
        gameWindowLayout.setCenter(battlefield);
        gameWindowLayout.setBottom(playerData);
        
        Scene scene = new Scene(gameWindowLayout,800,600);
        
        scene.getStylesheets().add(getClass().getResource("buttons.css").toExternalForm());
        ControllerForView.getInstance().nextTurn();
        
        scene.setOnKeyPressed((KeyEvent ke) -> {
            double angle = 0;
            if(!ControllerForView.getInstance().isControlDisabled() || !ControllerForView.getInstance().isPlayerTwoControlDisabled())
            {
                switch (ke.getCode()) {
                    case W:
                        if(ControllerForView.getInstance().isMultiplayer())
                        {
                            if(ControllerForView.getInstance().isPlayerTwoControlDisabled())
                                View.getInstance().rotateCannon(this.battlefield.getCannon(),
                                        -NEXT_ANGLE_MOVEMENT);
                            else
                                View.getInstance().rotateCannon(this.battlefield.getEnemyCannon(),
                                        +NEXT_ANGLE_MOVEMENT);
                        }
                        else
                        {
                             View.getInstance().rotateCannon(this.battlefield.getCannon(),
                                     -NEXT_ANGLE_MOVEMENT);
                        }
                        break;
                    case S:
                        if(ControllerForView.getInstance().isMultiplayer())
                        {
                            if(ControllerForView.getInstance().isPlayerTwoControlDisabled())
                                View.getInstance().rotateCannon(this.battlefield.getCannon(),
                                        NEXT_ANGLE_MOVEMENT);
                            else
                            {
                                View.getInstance().rotateCannon(this.battlefield.getEnemyCannon(),
                                        -NEXT_ANGLE_MOVEMENT);
                            }
                        }
                        else
                            View.getInstance().rotateCannon(this.battlefield.getCannon(),
                                    NEXT_ANGLE_MOVEMENT);
                        break;
                    case ENTER:
                        if(powerFire<100)
                            powerFire++;
                            View.getInstance().updateFireProgressBar(powerFire);
                        break;
                    case BACK_SPACE:
                        if(powerFire>0)
                            powerFire--;
                            View.getInstance().updateFireProgressBar(powerFire);
                        break;
                    case F:
                        angle = View.getInstance().getCannonAngle(this.battlefield.getCannon());
                        double shootingStartX = Config.getInstance().getPlayerBulletStartXPosition();
                        double shootingStartY = Config.getInstance().getPlayerBulletStartYPosition();
                        if(ControllerForView.getInstance().isControlDisabled())
                        {
                            shootingStartX = Config.getInstance().getEnemyBulletStartX();
                            shootingStartY = Config.getInstance().getEnemyBulletStartY();
                            angle = Math.PI + View.getInstance().getCannonAngle(this.battlefield.getEnemyCannon());
                        }
                        ControllerForView.getInstance().shoot(angle,
                                    5+powerFire/10,
                                    shootingStartX,
                                    shootingStartY);
                        powerFire = 0;
                        View.getInstance().updateFireProgressBar(powerFire);
                        Resources.SoundEffects.CANNON.play();
                        ControllerForView.getInstance().disablePlayerControl();
                        ControllerForView.getInstance().disablePlayerTwoControl();
                        break;
                    default:
                        break;
                }
            }  
        });

        this.setScene(scene);
        this.setTitle("Conquer Antartica - Java Edition");  
        this.setResizable(false);

   
    }
    
    public Battlefield getBattlefield()
    {
        return this.battlefield;
    }
  
    public PlayerControlPanel getPlayerData()
    {
        return this.playerData;
    }
    
    public static int getNextAngleMovement()
    {
        return NEXT_ANGLE_MOVEMENT;
    }

    
}
