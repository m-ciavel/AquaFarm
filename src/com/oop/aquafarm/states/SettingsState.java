package com.oop.aquafarm.states;

import com.oop.aquafarm.audio.Music;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SettingsState extends GameState {
    public SettingsState(GameStateManager gsm) {
        super(gsm);
        System.out.println("Settings state");
    }

    @Override
    public void update(double time) {

    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {

    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage background  = null;
        background = SpriteSheet.paintbg(background);
        g.drawImage(background, 0, 0, null);
    }
}
