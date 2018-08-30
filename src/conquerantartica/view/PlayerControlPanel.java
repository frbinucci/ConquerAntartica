/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import conquerantartica.controller.ControllerForView;
import conquerantartica.utils.Constants;
import conquerantartica.utils.Resources;
import javafx.application.Platform;

/**
 *
 * @author franc
 */
public class PlayerControlPanel extends GridPane {
    
    private final static int BUTTON_WIDTH = 100;
    private final static int BUTTON_HEIGHT = 25;
    
    private final Label playerNameDescription;
    private final Label playerScoreDescription;
    private final Label playerTurnDescription;
    private final Label launchForceDescription;
    private final Label weaponSelectionDescription;
    private final Label playerNumberOfLivesDescription;
    private final Label playerHighScoreDescription;
    
    private final Label playerName;
    private final Label playerScore;
    private final Label playerTurn;
    private final Label playerNumberOfLives;
    private final Label playerHighScore;

    private final ProgressBar launchForce;
    private final JFXButton rocket;
    private final JFXButton bomb;
    private final JFXButton snowBall;
    
    private final JFXButton exit;
    
    private final JFXButton soundManager;
    private final JFXButton musicManager;
    
    public PlayerControlPanel() 
    {
        super();
        this.playerNameDescription = new Label("Giocatore: ");
        this.playerScoreDescription = new Label("Punteggio: ");
        this.playerTurnDescription = new Label("Numero di turno: ");
        this.launchForceDescription = new Label("Potenza del tiro");
        this.weaponSelectionDescription = new Label("Seleziona Arma");
        this.playerHighScoreDescription = new Label("Punteggio massimo: ");
        this.playerNumberOfLivesDescription = new Label("Numbero di vite: ");
        
        this.setVgap(1);
        
        this.getStylesheets().add(getClass().getResource("buttons.css").toExternalForm());
        
        rocket = new JFXButton("Razzo");
        bomb = new JFXButton("Bomba");
        snowBall = new JFXButton("Palla di neve");
        
        exit = new JFXButton("Esci");
        soundManager = new JFXButton("Suono");
        musicManager = new JFXButton("Musica");

        rocket.getStyleClass().add("weapon_button");
        bomb.getStyleClass().add("weapon_button");
        snowBall.getStyleClass().add("weapon_button");
        exit.getStyleClass().add("exit_button");
        soundManager.getStyleClass().add("sound_button");
        musicManager.getStyleClass().add("sound_button");
        
        bomb.setMinSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        bomb.setMaxSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        rocket.setMinSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        rocket.setMaxSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        snowBall.setMinSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        snowBall.setMaxSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        
        
        exit.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        exit.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        soundManager.setMaxSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        soundManager.setMinSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        musicManager.setMaxSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        musicManager.setMinSize(BUTTON_WIDTH,BUTTON_HEIGHT);
        
        rocket.setOnAction((ActionEvent ae) -> {
            double angle = 0;
            if (!ControllerForView.getInstance().isControlDisabled() || !ControllerForView.getInstance().isPlayerTwoControlDisabled()) {
                View.getInstance().updateWeapon(Constants.ROCKET_DAMAGE_FACTOR);
                ControllerForView.getInstance().resetBullet(Constants.ROCKET_DAMAGE_FACTOR);
            }

        });

        bomb.setOnAction((ActionEvent ae) -> {
            if (!ControllerForView.getInstance().isControlDisabled() || !ControllerForView.getInstance().isPlayerTwoControlDisabled()) {
                View.getInstance().updateWeapon(Constants.BOMB_DAMAGE_FACTOR);
                ControllerForView.getInstance().resetBullet(Constants.BOMB_DAMAGE_FACTOR);
            }

        });

        snowBall.setOnAction((ActionEvent ae) -> {
            if (!ControllerForView.getInstance().isControlDisabled() || !ControllerForView.getInstance().isPlayerTwoControlDisabled()) {
                View.getInstance().updateWeapon(Constants.SNOWBALL_DAMAGE_FACTOR);
                ControllerForView.getInstance().resetBullet(Constants.SNOWBALL_DAMAGE_FACTOR);
            }

        });

        exit.setOnAction((ActionEvent ae) -> {

            Platform.runLater(() -> {
                boolean exit = View.getInstance().showConfirmationDialog("Sei sicuro di voler uscire?",
                        "Uscita",
                        "Torna al menù principale",
                        "Annulla");
                if (exit) {
                    View.getInstance().openMainMenu();
                }

            });
        });
        
        soundManager.setOnAction((ActionEvent ae) -> {
            for(Resources.SoundEffects soundEffect : Resources.SoundEffects.values())
            {
                soundEffect.toggleSoundEnabled();
            }
        });
        
        musicManager.setOnAction((ActionEvent ae) -> {
            Resources.Music.SOUNDTRACK.toggleMusicEnabled();
            Resources.Music.SOUNDTRACK.play();
        });
  
        this.launchForce = new ProgressBar();
        this.launchForce.setProgress(0);
        
        this.playerName = new Label(ControllerForView.getInstance().getPlayerName());
        this.playerTurn = new Label("0");
        this.playerHighScore = new Label(""+ControllerForView.getInstance().getHighScore());
        this.playerNumberOfLives = new Label(""+ControllerForView.getInstance().getNumberOfLives());
        if(!ControllerForView.getInstance().isMultiplayer())
            this.playerScore = new Label(""+ControllerForView.getInstance().getPlayerScore());
        else
            this.playerScore = new Label("N/D");
        
        this.setHgap(50);
        this.setPadding(new Insets(10,10,10,10));
        this.add(playerNameDescription,0,0);
        this.add(playerScoreDescription,0,1);
        this.add(playerTurnDescription,0,2);
        this.add(playerNumberOfLivesDescription,0,3);
        this.add(playerHighScoreDescription,0,4);
        
        this.add(playerName,1,0);
        this.add(playerScore,1,1);
        this.add(playerTurn,1,2);
        this.add(playerNumberOfLives,1,3);
        this.add(playerHighScore,1,4);
        
        this.add(launchForce,3,1);
        this.add(launchForceDescription,3,0);
        this.add(weaponSelectionDescription,5,0);
        this.add(rocket,5,1);
        this.add(bomb,5,2);
        this.add(snowBall,5,3); 
        
        this.add(soundManager,6,1);
        this.add(musicManager,6,2);
        this.add(exit,6,3);
        
    }
    
    public void setPlayerName(String playerName)
    {
        this.playerName.setText(playerName);
    }
    
    public void setPlayerTurn(int playerTime)
    {
        this.playerTurn.setText(String.valueOf(playerTime));
    }
    
    public void setPlayerScore(int score)
    {
        this.playerScore.setText(String.valueOf(score));
    }
    
    public void setProgressBar(double powerFire)
    {
        this.launchForce.setProgress(powerFire/100);
    }


    
    
}
