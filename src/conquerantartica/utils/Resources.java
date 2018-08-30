/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conquerantartica.utils;

import java.io.FileNotFoundException;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 *
 * @author franc
 */
public class Resources {
    
    public enum SoundEffects{
        
        PENGUINHIT("/resources/sounds/penguinhit.mp3"),
        PENGUINKILL("/resources/sounds/penguindeath.wav"),
        CANNON("/resources/sounds/cannon.wav"),
        BOUNCE("/resources/sounds/bounce.wav"),
        READYFIGHT("/resources/sounds/startfight.wav");
        
        
        private boolean soundEnabled;
        private AudioClip media;
        
        SoundEffects(String fileSrc)
        {
            URL url = this.getClass().getResource(fileSrc);
            media = new AudioClip(url.toString());
            this.soundEnabled = true;
        }
        
        public void play()
        {
            if(this.soundEnabled)
                media.play();
        }
        
        public void toggleSoundEnabled()
        {
            this.soundEnabled = !soundEnabled;
        }
    }
    
    public enum AlliedPenguinDeath
    {
        ALLIEDPENGUINDEATH1("/resources/images/penguins/alliedpenguindeath1.png"),
        ALLIEDPENGUINDEATH2("/resources/images/penguins/alliedpenguindeath2.png"),
        ALLIEDPENGUINDEATH3("/resources/images/penguins/alliedpenguindeath3.png"),
        ALLIEDPENGUINDEATH4("/resources/images/penguins/alliedpenguindeath4.png"),
        ALLIEDPENGUINDEATH5("/resources/images/penguins/penguinexplosion1.png"),
        ALLIEDPENGUINDEATH6("/resources/images/penguins/penguinexplosion2.png"),
        ALLIEDPENGUINDEATH7("/resources/images/penguins/penguinexplosion3.png"),
        ALLIEDPENGUINDEATH8("/resources/images/penguins/penguinexplosion4.png"),
        ALLIEDPENGUINDEATH9("/resources/images/penguins/penguinexplosion5.png");
        
        private Image img = null;
        
        AlliedPenguinDeath(String fileSrc)
        {
            URL imgSrc = this.getClass().getResource(fileSrc);            
            img = new Image(imgSrc.toString());

        }  
        public Image getImage()
        {
            return this.img;
        }        
    }
    
    public enum EnemyPenguinDeath
    {
        ENEMYPENGUINDEATH1("/resources/images/penguins/enemypenguindeath1.png"),
        ENEMYPENGUINDEATH2("/resources/images/penguins/enemypenguindeath2.png"),
        ENEMYPENGUINDEATH3("/resources/images/penguins/enemypenguindeath3.png"),
        ENEMYPENGUINDEATH4("/resources/images/penguins/enemypenguindeath4.png"),
        ENEMYPENGUINDEATH5("/resources/images/penguins/penguinexplosion1.png"),
        ENEMYPENGUINDEATH6("/resources/images/penguins/penguinexplosion2.png"),
        ENEMYPENGUINDEATH7("/resources/images/penguins/penguinexplosion3.png"),
        ENEMYPENGUINDEATH8("/resources/images/penguins/penguinexplosion4.png"),
        ENEMYPENGUINDEATH9("/resources/images/penguins/penguinexplosion5.png");
        
        private Image img = null;
        
        EnemyPenguinDeath(String fileSrc)
        {
            URL imgSrc = this.getClass().getResource(fileSrc);
            img = new Image(imgSrc.toString());
        }
        
        public Image getImage()
        {
            return this.img;
        }
    } 
    
    public enum WeaponsImages
    {
        ROCKET("/resources/images/weapons/rocket.png"),
        SNOWBALL("/resources/images/weapons/snowball.png"),
        BOMB("/resources/images/weapons/bomb.png");
        
        private Image img = null;

        WeaponsImages(String fileSrc)
        {
            URL imgSrc = this.getClass().getResource(fileSrc);            
            img = new Image(imgSrc.toString());
        }
        
        public Image getImage()
        {
            return this.img;
        }
    }
    
    public enum GeneralImages
    {
        ALLIEDPENGUIN("/resources/images/penguins/alliedpenguin.png"),
        ENEMYPENGUIN("/resources/images/penguins/enemypenguin.png"),
        ALLIEDPENGUINHURTED("/resources/images/penguins/alliedpenguinhurted.png"),
        ENEMYPENGUINHURTED("/resources/images/penguins/enemypenguinhurted.png"),
        BASECANNON("/resources/images/basecannon.png"),
        ENEMYCANNON("/resources/images/enemycannon.png"),
        ICEBLOCKSPRITE("/resources/images/iceblocksprite.png"),
        CANNON("/resources/images/cannon.png"),
        MAINLANDSCAPE("/resources/images/mainlandscape.jpg"),
        ICON("/resources/images/icons/gameicon.png");
        
        private Image img = null;
        
        GeneralImages(String fileSrc)
        {
            URL imgSrc = this.getClass().getResource(fileSrc);            
            img = new Image(imgSrc.toString());
        }
        
        public Image getImage()
        {
            return this.img;
        }        
        
    }
    
    public enum Music
    {
        SOUNDTRACK("/resources/sounds/gametheme.mp3");
        private boolean musicEnabled;
        private MediaPlayer audioMedia;
        private final static double MUSIC_VOLUME = 0.25;
        Music(String fileSrc)
        {
            URL url = this.getClass().getResource(fileSrc);
            audioMedia = new MediaPlayer(new Media(url.toString()));
            audioMedia.setCycleCount(MediaPlayer.INDEFINITE);
            this.musicEnabled = true;
        }
        
        public void play()
        {
            if(this.musicEnabled)
            {
                this.audioMedia.setVolume(MUSIC_VOLUME);
                this.audioMedia.play();
            }
            else
                this.audioMedia.stop();
        }
        
        public void stop()
        {
            this.audioMedia.stop();
        }
        
        public void toggleMusicEnabled()
        {
            this.musicEnabled = !musicEnabled;
        }
        
        
    }
    
}
