/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.controller;

import conquerantartica.model.Model;
import conquerantartica.view.View;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author franc
 */
public class ControllerForModel implements IControllerForModel {
        
    //STATIC FIELDS
    //--------------------------------------------------------------------------
    private static ControllerForModel instance = null;  
    //--------------------------------------------------------------------------
    
    //Method Area
    //--------------------------------------------------------------------------
    @Override
    public void notifyDeadPenguin(int arrayIndex,boolean isAllied)
    {
        View.getInstance().killPenguin(arrayIndex, isAllied);
    }
    
    @Override
    public void notifyHurtedPenguin(int arrayIndex)
    {
        View.getInstance().hurtPenguin(arrayIndex,Model.getInstance().getPenguins().get(arrayIndex).isAllied());
    }
    
    @Override
    public void updateScore(int score)
    {
        View.getInstance().updatePlayerScore(score);
    }
    
    @Override
    public void notifyException(String message)
    {
        View.getInstance().showErrorDialog(message);
    }
    
    @Override
    public void notifyGameOver(boolean isCampaign,boolean isMatchWin,boolean isTwoPlayerMatch,int score,int bonus)
    {
        String scoreInformation = new StringBuilder().append("\nPunti ottenuti: ").append(score)
                .append("\nDei quali: ").append(bonus).append(" di Bonus").toString();
        if (isMatchWin && isCampaign) {
            Platform.runLater(() -> {
                boolean continueCampaign = View.getInstance().showConfirmationDialog(
                        new StringBuilder().append("Hai completato la missione, complimenti!")
                                .append(scoreInformation).toString(),
                        "Missione Compiuta!",
                        "Prosegui",
                        "Torna al menù principale");
                if (continueCampaign) {
                    boolean campaignMode = true;
                    ControllerForView.getInstance().loadLevel(ControllerForView.getInstance().getPlayerMission(),
                            campaignMode);
                } else {
                    View.getInstance().openMainMenu();
                }

            });
        } else {
            if (isTwoPlayerMatch) {
                Platform.runLater(() -> {
                    boolean replayMultiplayer = false;
                    String message = "Ha vinto il giocatore 1!";
                    if (!isMatchWin) {
                        message = "Ha vinto il giocatore 2!";
                    }

                    replayMultiplayer = View.getInstance().showConfirmationDialog(message,
                            "Partita Terminata",
                            "Rivincita",
                            "Torna al menù principale");
                    if (replayMultiplayer) {
                        boolean campaignMode = false;
                        ControllerForView.getInstance().loadLevel(ControllerForView.getInstance().getCurrentMission(), campaignMode);
                        ControllerForView.getInstance().setMultiplayerMode(true);
                    } else {
                        View.getInstance().openMainMenu();
                    }
                });
            } else {
                if (Model.getInstance().getPlayerNumberOfLives() != 0) {
                    Platform.runLater(() -> {
                        boolean replay = View.getInstance().showConfirmationDialog(
                                new StringBuilder().append("Partita terminata!")
                                        .append(scoreInformation).toString(),
                                "Partita Terminata",
                                "Rigioca",
                                "Torna al menù principale");
                        if (replay) {
                            ControllerForView.getInstance().loadLevel(ControllerForView.getInstance().getCurrentMission(),
                                    Model.getInstance().getCampaignMode());
                        } else {
                            View.getInstance().openMainMenu();
                        }

                    });
                } else {
                    Platform.runLater(() -> {
                        View.getInstance().showInformationDialog("Hai terminato le vite a disposizione! Il profilo sarà cancellato.",
                                "Game Over");
                        View.getInstance().openStartWindow();
                    });
                }
            }
        }
    }
    //--------------------------------------------------------------------------
    
    //--------------------------------------------------------------------------
    //Other Methods 
    public static IControllerForModel getInstance() {
	if (instance == null)
            instance = new ControllerForModel();
	return instance;
    }
    
}
