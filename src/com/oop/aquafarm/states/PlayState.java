package com.oop.aquafarm.states;

import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.*;

public class PlayState extends GameState{

    public PlayState(GameStateManager gsm){
        super(gsm);

    }

    public void update(){

    }

    public void input(MouseHandler mouseIn, KeyHandler keyh){

    }

    public void render(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(100, 100, 200, 200);
    }
}
