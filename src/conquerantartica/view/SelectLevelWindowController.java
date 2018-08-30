/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import conquerantartica.controller.ControllerForView;
import conquerantartica.utils.Constants;

/**
 * FXML Controller class
 *
 * @author franc
 */
public class SelectLevelWindowController implements Initializable {
    
    @FXML private JFXListView missionView;
    @FXML private JFXButton confirmButton;
    @FXML private JFXButton backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int missionsCompletedByPlayer = ControllerForView.getInstance().getPlayerMission();
        int numberMission = 1;
        while(numberMission < missionsCompletedByPlayer && numberMission<=Constants.MISSION_FIVE_ID)
        {
            Label newVoice = new Label("Missione n."+numberMission);
            newVoice.setId(String.valueOf(numberMission));
            missionView.getItems().add(newVoice);
            numberMission++;
        }
    } 
    
    public void onConfirmButtonClicked()
    {
        
        Label get = (Label) missionView.getSelectionModel().getSelectedItem();
        if(get!=null)
        {
            View.getInstance().setCampaignMode(false);
            ControllerForView.getInstance().loadLevel(Integer.parseInt(get.getId()),false);
        }

    }
    
        
    public void onBackButtonClicked()
    {
        View.getInstance().openMainMenu();
    }
    
}
