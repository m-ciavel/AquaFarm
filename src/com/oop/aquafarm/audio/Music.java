package com.oop.aquafarm.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {

    private static Clip clip;

    public static String fpath = "res/audio/Les Petits Poissons Dans l'Eau Instrumental.wav";

    public Music() {
        // Default constructor
    }

    public static void playEatSound() {
        playsound("res/audio/eat.wav");
    }

    public static void playHoverSound() {
        playsound("res/audio/hover.wav");
    }

    public static void playSummonSound() {
        playsound("res/audio/summon.wav");
    }

    public static void playMusic(String location) {
        File musicPath = new File(location);
        try {
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                startMusic(clip);
                Music.clip = clip;
            } else {
                System.out.println("ERROR: could not load file: " + musicPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startMusic(Clip clip){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopMusic(Clip clip){
        clip.close();
    }

    public static Clip getClip(){
        return clip;
    }

    public static void playsound(String location) {
        File soundPath = new File(location);
        try {
            if (soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else {
                System.out.println("ERROR: could not load file: " + soundPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
