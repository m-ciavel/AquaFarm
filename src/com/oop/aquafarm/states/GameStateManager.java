package com.oop.aquafarm.states;

import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.Graphics2D;



public class GameStateManager {

    private GameState[] states;

    public static final int TITLE = 0;
    public static final int ACCOUNT = 1;
    public static final int PLAY = 2;

    public int onTopState = 0;

    public GameStateManager(){

        states = new GameState[3];

        states[PLAY] = new TitleState(this);
    }

    public boolean getState(int state){
        return states[state] != null;
    }

    public void pop(int state){

        states[state] = null;
    }

    public void add(int state){
        if(states[state] != null){
            return;
        }
        if(state == PLAY){
            states[TITLE] = new PlayState(this);
        }
        if(state == ACCOUNT){
            states[ACCOUNT] = new AccountState(this);
        }
        if(state == PLAY){
            states[PLAY] = new PlayState(this);
        }
    }

    public void addandpop(int state, int remove){
        addandpop(state, 0);
    }

    public void addandpop(int state){
        pop(state);
        add(state);
    }

    public  void update(){
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].update();
            }
        }
    }
    public  void input(MouseHandler mouseIn, KeyHandler keyh){
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].input(mouseIn, keyh);
            }
        }
    }
    public  void render(Graphics2D g){
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].render(g);
            }
        }
    }

}
