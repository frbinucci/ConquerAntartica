/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author franc
 */
public class Level2Enemy extends IceBlockTerrain {

    public Level2Enemy(double startXPosition, double startYPosition) {
        super(startXPosition, startYPosition);
    }

    @Override
    public void setPattern() {
        pattern.add(new Rectangle(startXPosition,startYPosition,50,50));
        pattern.add(new Rectangle(startXPosition+50,startYPosition,50,50));
        pattern.add(new Rectangle(startXPosition+100,startYPosition,50,50));
        pattern.add(new Rectangle(startXPosition+150,startYPosition,50,50));
        pattern.add(new Rectangle(startXPosition+150,startYPosition-50,50,50));
    }
    
}
