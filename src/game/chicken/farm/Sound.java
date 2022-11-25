package game.chicken.farm;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[20];

    public Sound(){
        soundURL[0] =  getClass().getResource("sound/ck.wav");
        soundURL[1] =  getClass().getResource("sound/eggB.wav");
        soundURL[2] =  getClass().getResource("sound/getEgg.wav");
        soundURL[3] =  getClass().getResource("sound/getEgg2.wav");
        soundURL[4] =  getClass().getResource("sound/newScore.wav");
        soundURL[5] =  getClass().getResource("sound/c.wav");
        soundURL[6] =  getClass().getResource("sound/music.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream s = AudioSystem.getAudioInputStream( soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(s);

        } catch (Exception e) {
            
        }
    }

    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
    public void playEF(){

    }

}
