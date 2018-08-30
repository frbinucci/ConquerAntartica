/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import conquerantartica.controller.ControllerForView;
import conquerantartica.utils.Config;
import conquerantartica.utils.Constants;
import conquerantartica.utils.Resources;

/**
 *
 * @author franc
 */
public class Battlefield extends Group {
    
    private final ImageView landscape;
    private Circle playerBullet;
    private ArrayList<ImageView> penguins;
    
    private IceBlockTerrain playerBase;
    private IceBlockTerrain enemyBase;
    
    private Rectangle baseCannon;
    private Rectangle cannon;
    
    private Rectangle enemyBaseCannon;
    private Rectangle enemyCannon;
    
    
    public Battlefield() 
    {
        super();
        this.landscape = new ImageView();
    }
    
    
    public void initBattlefield(IceBlockTerrain playerBase,IceBlockTerrain enemyBase)
    {
        
        penguins = new ArrayList<>();
        Resources.SoundEffects.READYFIGHT.play();
            //Creazione e posizionamento dei cannoni sulla mappa.
            baseCannon = new Rectangle(Config.getInstance().getAlliedBaseCannonXPosition(),
                    Config.getInstance().getBaseCannonYPosition(),
                    Constants.BASE_CANNON_WIDTH,
                    Constants.BASE_CANNON_HEIGHT);
            cannon = new Rectangle(Config.getInstance().getAlliedCannonXPosition(),
                    Config.getInstance().getCannonYPosition(),
                    Constants.CANNON_WIDTH,
                    Constants.CANNON_HEIGHT);
            
            enemyBaseCannon = new Rectangle(Config.getInstance().getEnemyBaseCannonXPosition(),
                    Config.getInstance().getBaseCannonYPosition(),
                    Constants.BASE_CANNON_WIDTH,
                    Constants.BASE_CANNON_HEIGHT);
            enemyCannon = new Rectangle(Config.getInstance().getEnemyCannonXPosition(),
                    Config.getInstance().getCannonYPosition(),
                    Constants.CANNON_WIDTH,
                    Constants.CANNON_HEIGHT);
            
            baseCannon.setFill(
                        new ImagePattern(Resources.GeneralImages.BASECANNON.getImage()));
            cannon.setFill(
                        new ImagePattern(Resources.GeneralImages.CANNON.getImage()));
            enemyCannon.setFill(
                        new ImagePattern(Resources.GeneralImages.ENEMYCANNON.getImage()));
            enemyBaseCannon.setFill(
                        new ImagePattern(Resources.GeneralImages.BASECANNON.getImage())); 
            landscape.setImage(Resources.GeneralImages.MAINLANDSCAPE.getImage());
          
        
            playerBullet = new Circle(Config.getInstance().getPlayerBulletStartXPosition(),
                    Config.getInstance().getPlayerBulletStartYPosition(),
                    Config.getInstance().getBulletRadius());
            playerBullet.setFill(
                        new ImagePattern(Resources.WeaponsImages.SNOWBALL.getImage()));
            //Setting up allied penguins.
            for(double[] nextAlliedPenguinCoordinates : ControllerForView.getInstance().getPenguins(true))
            {
                ImageView newPenguin = new ImageView(Resources.GeneralImages.ALLIEDPENGUIN.getImage());
                newPenguin.setX(nextAlliedPenguinCoordinates[0]);
                newPenguin.setY(nextAlliedPenguinCoordinates[1]);
                penguins.add(newPenguin);
            }
            //Setting up enemy penguins.
            for(double[] nextEnemyPenguinCoordinates : ControllerForView.getInstance().getPenguins(false))
            {
                ImageView newPenguin = new ImageView(Resources.GeneralImages.ENEMYPENGUIN.getImage());
                newPenguin.setX(nextEnemyPenguinCoordinates[0]);
                newPenguin.setY(nextEnemyPenguinCoordinates[1]);
                penguins.add(newPenguin);
            }            
            this.getChildren().addAll(landscape,playerBullet);
            this.getChildren().add(cannon);       
            this.getChildren().add(baseCannon);
            this.getChildren().add(enemyCannon);
            this.getChildren().add(enemyBaseCannon);
            
            playerBase.setFillPattern();
            enemyBase.setFillPattern();
            playerBase.setPatternToBattlefield(this);
            enemyBase.setPatternToBattlefield(this);
            this.playerBullet.setVisible(false);
            
            penguins.forEach((penguin) -> {
               
                this.getChildren().add(penguin);
        });
        
    }
    public Circle getPlayerBullet()
    {
        return this.playerBullet;
    }

    
    public ArrayList<ImageView> getPenguins()
    {
        return this.penguins;
    }
    
    public Rectangle getCannon()
    {
        return this.cannon;
    }

    public Rectangle getEnemyCannon()
    {
        return this.enemyCannon;
    }

    
    
    
    
    
    
    
    
    
}
