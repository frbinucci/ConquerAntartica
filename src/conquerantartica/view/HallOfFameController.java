/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import conquerantartica.controller.ControllerForView;

/**
 * FXML Controller class
 *
 * @author franc
 */
public class HallOfFameController implements Initializable {
    
    @FXML JFXButton showButton;
    @FXML JFXButton backButton;
    @FXML ListView levelList;
    @FXML ListView resultList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        LinkedList<String[]> lstRows = null;
        try
        {
            lstRows = ControllerForView.getInstance().getLevelStats();
        }
        catch(IOException ioe)
        {
            View.getInstance().showErrorDialog(ioe.toString());
        }
        
        lstRows.forEach((levelData) -> {
                Label newVoice = new Label("Livello: "+levelData[0]);
                newVoice.setId(levelData[0]);
                levelList.getItems().add(newVoice);
        });
    } 
    
    public void onShowButtonClicked()
    {

        Label get = (Label) levelList.getSelectionModel().getSelectedItem();
        if(get!=null)
        {
            resultList.getItems().clear();
            for(String results : ControllerForView.getInstance().getLevelData(Integer.parseInt(get.getId())))
            {
                resultList.getItems().add(new Label(results));
            }
        }
    }
    
    public void onBackButtonClicked()
    {
        View.getInstance().openStartWindow();
    }
    
}
