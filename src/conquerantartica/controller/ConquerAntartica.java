 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.controller;

import javafx.application.Application;
import javafx.stage.Stage;

import conquerantartica.view.View;

/**
 *
 * @author franc
 */
public class ConquerAntartica extends Application {
    
    @Override
    public void start(Stage primaryStage)  {
        
        View.getInstance().openStartWindow();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
