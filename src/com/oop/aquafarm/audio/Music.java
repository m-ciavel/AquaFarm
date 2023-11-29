package com.oop.aquafarm.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class Music {

    private static Clip clip;

    public static String fpath = "res/audio/Les Petits Poissons Dans l'Eau Instrumental.wav";

    private static FloatControl musicVolumeControl;

    private Music() {
        // Private constructor to prevent instantiation
    }

    private static Clip createClip(String location) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File audioFile = new File(location);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile)) {
            Clip clip = AudioSystem.getClip();
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
            Clip clip = createClip(location);
            musicVolumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
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
