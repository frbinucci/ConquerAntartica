/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.model;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author franc
 */
public class PenguinModel extends Rectangle {
    
    private int penguinLife;
    private boolean allied;
    
    
    public PenguinModel(double xPosition,double yPosition,int penguinLife,boolean allied)
    {
        super(xPosition,yPosition,50,50);
        this.penguinLife = penguinLife; 
        this.allied = allied;
    }
    
    public void decreasePenguinLife(BulletModel bullet)
    {
        penguinLife-=bullet.getDamageFactor();
    }
    
    public boolean isDead()
    {
        return penguinLife<=0;
    }
    
    public boolean isAllied()
    {
        return this.allied;
    }
    
    public int getPenguinLife()
    {
        return this.penguinLife;
    }
    
}
