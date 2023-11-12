package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.Graphics2D;
import java.awt.Color;

public class PlayState extends GameState{

    public PlayState(GameStateManager gsm){
        super(gsm);

    }

    public void update(){
//        eme eme.update();
    }

    public void input(MouseHandler mouseIn, KeyHandler keyh){
        keyh.escape.tick();
        if(keyh.escape.clicked){
            if (gsm.getState(GameStateManager.TITLE)){
                gsm.add(GameStateManager.TITLE);
            }

        }
    }

    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(100, 100, 200, 200);
        CFont fps = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, 32, 32);
        fps.drawString(g, GamePanel.oldFrameCount +" FPS");
    }
}
