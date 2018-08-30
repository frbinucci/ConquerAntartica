/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.model;

import conquerantartica.utils.Constants;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author franc
 */
public class Level2AlliedModel extends IceBlockTerrainModel {

    public Level2AlliedModel(double startXPosition, double startYPosition) {
        super(startXPosition, startYPosition);
    }

    @Override
    public void setPattern() {
        pattern.add(new Rectangle(startXPosition,
                startYPosition,
                Constants.BLOCK_DIMENSION,
                Constants.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition+50,
                startYPosition,
                Constants.BLOCK_DIMENSION,
                Constants.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition+100,
                startYPosition,
                Constants.BLOCK_DIMENSION,
                Constants.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition+150,
                startYPosition,
                Constants.BLOCK_DIMENSION,
                Constants.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition,
                startYPosition-50,
                Constants.BLOCK_DIMENSION,
                Constants.BLOCK_DIMENSION)); 
    }
    
}
