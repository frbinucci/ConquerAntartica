/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author franc
 */
public class MainWindowController implements Initializable {
    
    @FXML private JFXButton startNewGameButton;
    @FXML private JFXButton loadGameButton;
    @FXML private JFXButton hallOfFameButton;
    @FXML private JFXButton exitButton;
    @FXML private JFXButton instructionButton;
    
    
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    
     public void onStartNewGameButtonClicked()
    {
        View.getInstance().openNewProfileWindow();
    }

    
    public void onLoadGameButtonClicked()
    {
        View.getInstance().openLoadProfileWindow();
    }
    
    public void onHallOfFameButtonClicked()
    {
        View.getInstance().openHallOfFameWindow();
    }
    
    public void onExitButtonClicked()
    {
        System.exit(0);
    }
     
     public void onInstructionButtonCliked()
     {
         View.getInstance().openInstructionWindow();
     }
    

    
}
