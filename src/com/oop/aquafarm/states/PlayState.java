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

    @Override
    public void update(double time){
        System.out.println("gamestate");
//        eme eme.update();
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh){
        keyh.escape.tick();
        if(keyh.escape.clicked){
            if (gsm.isStateActive(GameStateManager.TITLE)){
                gsm.pop(GameStateManager.TITLE);
            }else{
                gsm.add(GameStateManager.TITLE);
                gsm.pop(GameStateManager.PLAY);
            }

        }
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(14, 135, 204));
        g.fillRect(0,0, GamePanel.width, GamePanel.height);
        CFont fps = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, 32, 32);
        fps.drawString(g, GamePanel.oldFrameCount +" FPS");
        CFont tps = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, 32, 32*2);
        tps.drawString(g,GamePanel.oldTickCount + " TPS");
    }
}
