/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.model;

import javafx.scene.shape.Circle;
/**
 *
 * @author franc
 */
public class BulletModel extends Circle {
    
    private int damageFactor;
    
    public BulletModel(double xStartPosition,double yStartPosition,double radius,int damageFactor)
    {
        super(xStartPosition,yStartPosition,radius);
        this.damageFactor = damageFactor;
    }
    
    public int getDamageFactor()
    {
        return this.damageFactor;
    }
    
    public void setDamageFactor(int damageFactor)
    {
        this.damageFactor = damageFactor;
    }
    
    
    
}
