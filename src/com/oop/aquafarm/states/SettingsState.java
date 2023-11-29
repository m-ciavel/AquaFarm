package com.oop.aquafarm.states;

import com.oop.aquafarm.audio.Music;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class SettingsState extends GameState {
    private JSlider musicVolumeSlider;

    public SettingsState(GameStateManager gsm) {
        super(gsm);
        System.out.println("Settings state");

        // Initialize the slider with a range from 0 to 100
        musicVolumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        musicVolumeSlider.setMajorTickSpacing(10);
        musicVolumeSlider.setMinorTickSpacing(1);
        musicVolumeSlider.setPaintTicks(true);
        musicVolumeSlider.setPaintLabels(true);

        musicVolumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int volume = musicVolumeSlider.getValue();
                // Adjust the music volume based on the slider value
                Music.setVolume(volume);
            }
        });

        // Add the slider to the panel
        JPanel panel = new JPanel();
        panel.add(new JLabel("Music Volume:"));
        panel.add(musicVolumeSlider);

        // Display the panel with the slider
        JOptionPane.showMessageDialog(null, panel, "Settings", JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public void update(double time) {
        // Update logic, if needed
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        // Handle input, if needed
    }

    @Override
    public void render(Graphics2D g) {
        // Render background
        BufferedImage background = SpriteSheet.paintbg(null);
        g.drawImage(background, 0, 0, null);
    }
}
