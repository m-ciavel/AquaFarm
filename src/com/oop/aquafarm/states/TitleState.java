package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.Graphics2D;
import java.awt.Color;


public class TitleState extends GameState {

    public TitleState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update(double time) {
        System.out.println("titlestate");
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        keyh.p.tick();

        if(keyh.p.clicked){
            if (gsm.isStateActive(GameStateManager.PLAY)){
                gsm.pop(GameStateManager.PLAY);
            }else{
                gsm.add(GameStateManager.PLAY);
                gsm.pop(GameStateManager.TITLE);
            }

        }
    }

    @Override
    public void render(Graphics2D g) {

        g.setColor(Color.BLACK);
        g.fillRect(0,0, GamePanel.width, GamePanel.height);

        CFont title = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, GamePanel.width / 2, 32);
        title.drawString(g, "title");
    }
}
