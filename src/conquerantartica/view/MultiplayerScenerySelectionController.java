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
public class MultiplayerScenerySelectionController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     * 
     */
    @FXML private JFXListView missionView;
    @FXML private JFXButton confirm;
    @FXML private JFXButton back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int numberMission = 1;
        while(numberMission<=ControllerForView.getInstance().getPlayerMission() && numberMission<=Constants.MISSION_FIVE_ID)
        {
            Label newVoice = new Label("Missione n."+numberMission);
            newVoice.setId(String.valueOf(numberMission));
            missionView.getItems().add(newVoice);
            numberMission++;
        }

    } 
    
    public void onConfirmButtonClicked()
    {
        ControllerForView.getInstance().setMultiplayerMode(true);
        Label get = (Label) missionView.getSelectionModel().getSelectedItem();
        boolean isCampaignMode = false;
        View.getInstance().setCampaignMode(isCampaignMode );
        ControllerForView.getInstance().loadLevel(Integer.parseInt(get.getId()),isCampaignMode);
   
        //ControllerForView.getInstance().loadLevel(0, true);
    }
    
    public void onBackButtonClicked()
    {
        View.getInstance().openMainMenu();
    }
    
    
}
