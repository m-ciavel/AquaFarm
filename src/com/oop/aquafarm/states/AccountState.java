package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AccountState extends GameState {
    public AccountState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update(double time) {
        System.out.println("login/signup");
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {

        keyh.p.tick();

        if(keyh.p.clicked){
            if (gsm.isStateActive(GameStateManager.PLAY)){
                gsm.pop(GameStateManager.PLAY);
            }else{
                gsm.add(GameStateManager.PLAY);
                gsm.pop(GameStateManager.ACCOUNT);
            }

        }

    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage background  = null;
        background = SpriteSheet.paintbg(background);
        g.drawImage(background, 0, 0, null);

    }
}
