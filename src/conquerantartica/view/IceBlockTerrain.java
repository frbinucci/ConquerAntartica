/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;

import java.util.ArrayList;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import conquerantartica.utils.Resources;

/**
 *
 * @author franc
 */
public abstract class IceBlockTerrain {
    
    protected ArrayList<Rectangle> pattern;
    protected double startXPosition;
    protected double startYPosition;
    protected static final double BLOCK_WIDTH = 50;
    protected static final double BLOCK_HEIGHT = 50;
    
    public IceBlockTerrain(double startXPosition,double startYPosition)
    {
        this.startXPosition = startXPosition;
        this.startYPosition = startYPosition;
        this.pattern = new ArrayList<>();       
        this.setPattern();
    }
    
    public abstract void setPattern();
    
    public void setFillPattern()
    {
        pattern.forEach((element) -> {
                element.setFill(new ImagePattern(Resources.GeneralImages.ICEBLOCKSPRITE.getImage()));
        });
    }
    
    public void setPatternToBattlefield(Battlefield battlefield)
    {
        this.getPattern().forEach((rect) -> {
            battlefield.getChildren().add(rect);
        });
    }
    
    public ArrayList<Rectangle> getPattern()
    {
        return this.pattern;
    }
    
    public double getStartX()
    {
        return this.startXPosition;
    }
    
    public double getStartY()
    {
        return this.startYPosition;
    }
   
    
    
}
