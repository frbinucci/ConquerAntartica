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

/**
 * FXML Controller class
 *
 * @author franc
 */
public class InstructionWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private JFXButton backButton;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void onBackButtonClicked()
    {
        View.getInstance().openStartWindow();
    }
    
}
