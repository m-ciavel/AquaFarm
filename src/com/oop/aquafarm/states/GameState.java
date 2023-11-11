package com.oop.aquafarm.states;

import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.Graphics2D;

public abstract class GameState {

    private GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouseIn, KeyHandler keyh);
    public abstract void render(Graphics2D g);


}
