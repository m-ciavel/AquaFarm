package com.oop.aquafarm.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {

    private static Clip clip;
    private static String location;
    static Long currentFrame;
    public static String fpath = "res/audio/Les Petits Poissons Dans l'Eau Instrumental.wav";
    public static String quitfpath = "res/audio/Poor Unfortunate Souls  Film Versions Instrumental 1.wav";

    private static FloatControl musicVolumeControl;

    private Music() {
        // Private constructor to prevent instantiation
    }

    private static Clip createClip(String location) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File audioFile = new File(location);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile)) {
            Clip clip = AudioSystem.getClip();
//            Music.clip = clip;
            clip.open(audioInputStream);
            return clip;
        }
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
        try {
            Music.location = location;
            Clip clip = createClip(location);
            musicVolumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            Music.clip = clip;
            startMusic(clip);
            System.out.println("Playing: " + location);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void startMusic(Clip clip){
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopMusic(){
        Music.getClip().stop();
        Music.getClip().close();
        System.out.println("Stopped playing: " + location);
    }

    public static String getLocation(){
        return Music.location;
    }

    public static Clip getClip(){
        return Music.clip;
    }

    public static void playsound(String location) {
        try {
            Clip clip = createClip(location);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }


        public static void setVolume(int percent) {
            if (musicVolumeControl != null) {

                float volumeValue = (float) (Math.log10(percent / 100.0) * 20);
                volumeValue = Math.max(volumeValue, -100);

                musicVolumeControl.setValue(volumeValue);
            }
        }
}
