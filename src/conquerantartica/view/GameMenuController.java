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
import javafx.scene.control.Label;
import conquerantartica.controller.ControllerForView;

/**
 * FXML ControllerForView class
 *
 * @author franc
 */
public class GameMenuController implements Initializable {
    
    @FXML JFXButton multiplayerButton;
    @FXML JFXButton changeProfileButton;
    @FXML JFXButton exitButton;
    @FXML JFXButton playCampaignButton;
    @FXML JFXButton replayMissionButton;
    
    @FXML Label playerName;
    @FXML Label numberLives;
    @FXML Label highScore;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerName.setText(ControllerForView.getInstance().getPlayerName());
        numberLives.setText(String.valueOf(ControllerForView.getInstance().getNumberOfLives()));
        highScore.setText(String.valueOf(ControllerForView.getInstance().getHighScore()));
        
    } 
    
    public void onPlayCampaignButtonClicked()
    {
        View.getInstance().setCampaignMode(true);
        ControllerForView.getInstance().setMultiplayerMode(false);
        ControllerForView.getInstance().loadLevel(ControllerForView.getInstance().getPlayerMission(), true);

    }
    
        
    @FXML public void onMultiplayerButtonClicked()
    {
        View.getInstance().openMultiplayerSelection();
    }
    
    public void onReplayMissionButtonClicked()
    {
        View.getInstance().openSelectMissionWindow();
        View.getInstance().setCampaignMode(false);
    }
    
    public void onChangeProfileButtonClicked()
    {
        View.getInstance().openLoadProfileWindow();
    }
    
    
    public void onExitButtonClicked()
    {
        System.exit(0);
    }
    
}
