/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.model;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author franc
 */
public abstract class IceBlockTerrainModel {
    
    protected ArrayList<Rectangle> pattern;
    
    protected double startXPosition;
    protected double startYPosition;
    
    public IceBlockTerrainModel(double startXPosition,double startYPosition)
    {
        pattern = new ArrayList<>();
        this.startXPosition = startXPosition;
        this.startYPosition = startYPosition;     
        this.setPattern();
    }
    
    public abstract void setPattern();

    public ArrayList<Rectangle> getPattern()
    {
        return this.pattern;
    }
    
    public double getStartXPosition()
    {
        return this.startXPosition;
    }
    
    public double getStartYPosition()
    {
        return this.startYPosition;
    }
  
}
