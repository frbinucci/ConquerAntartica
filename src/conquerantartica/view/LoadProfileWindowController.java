/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import conquerantartica.controller.ControllerForView;
import java.util.Optional;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;

/**
 * FXML ControllerForView class
 *
 * @author franc
 */
public class LoadProfileWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private JFXListView profileView;
    @FXML private JFXButton confirmButton;
    @FXML private JFXButton backButton;
    @FXML private JFXButton deleteButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.printList();

    }
    
    public void printList()
    {
        LinkedList<String[]> lstRows = null;
        profileView.getItems().clear();

        try
        {
            lstRows = ControllerForView.getInstance().getListOfPlayers();
        }
        catch(IOException ioe)
        {
            View.getInstance().showErrorDialog(ioe.toString());
        }
        
            lstRows.forEach((profileData) -> {
                Label newVoice = new Label("Profilo con id." + profileData[0] + ". Nome giocatore: " + profileData[1]);
                newVoice.setId(profileData[0]);
                profileView.getItems().add(newVoice);
        });
    }
    public void onDeleteButtonClicked()
    {
        Label get = (Label) profileView.getSelectionModel().getSelectedItem();
        
        if(get!=null)
        {
            boolean delete = View.getInstance().showConfirmationDialog("Sei sicuro di voler eliminare il profilo selezionato?",
                    "Eliminazione del profilo di gioco",
                    "Conferma",
                    "Annulla");
            if(delete)
            {
                ControllerForView.getInstance().deleteProfile(Integer.parseInt(get.getId()));
                this.printList();  
            }
        }
    }
    public void onConfirmButtonClicked()
    {

        Label get = (Label) profileView.getSelectionModel().getSelectedItem();
        
        if(get!=null)
        {
            try
            {
                ControllerForView.getInstance().loadGameProfile(Integer.parseInt(get.getId()));
            }catch(IOException ioe)
            {
                View.getInstance().showErrorDialog(ioe.toString());
            }
            View.getInstance().openMainMenu();
        }
    
    }
    
    public void onBackButtonClicked()
    {
        View.getInstance().openStartWindow();
    }
    
}
