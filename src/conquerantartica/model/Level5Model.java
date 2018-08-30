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
public class Level5Model extends IceBlockTerrainModel{

    public Level5Model(double startXPosition, double startYPosition) {
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
        pattern.add(new Rectangle(startXPosition+50,
                startYPosition-50,
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
        pattern.add(new Rectangle(startXPosition+150,
                startYPosition-50,
                Constants.BLOCK_DIMENSION,
                Constants.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition+200,
                startYPosition,
                Constants.BLOCK_DIMENSION,
                Constants.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition+250,
                startYPosition,
                Constants.BLOCK_DIMENSION,
                Constants.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition+250,
                startYPosition-50,
                Constants.BLOCK_DIMENSION,
                Constants.BLOCK_DIMENSION));
        pattern.add(new Rectangle(startXPosition+300,
                startYPosition,
                Constants.BLOCK_DIMENSION,
                Constants.BLOCK_DIMENSION));
    }
    
}
