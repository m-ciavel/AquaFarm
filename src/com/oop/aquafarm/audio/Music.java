package com.oop.aquafarm.audio;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {

    public static String fpath = "res/audio/Les Petits Poissons Dans l'Eau Instrumental.wav";
//    public static boolean play;

    public Music(){
//        playMusic(fpath);
    }

    public static void playMusic(String location){
            File musicPath = new File(location);
            try {
                if(musicPath.exists()){
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }else {
                    System.out.println("ERROR: could not load file: " + musicPath);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


    }


}
