package com.oop.aquafarm.states;

import com.oop.aquafarm.ui.TitleButton;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class GameState {

    protected GameStateManager gsm;

    public GameState(GameStateManager gsm){
        this.gsm = gsm;
    }

    public abstract void update(double time);
    public abstract void input(MouseHandler mouseIn, KeyHandler keyh);
    public abstract void render(Graphics2D g);

//    public boolean isIn(MouseEvent e, TitleButton tb){
//        return tb.getBounds().contains(e.getX(), e.getY());
//    }


}
