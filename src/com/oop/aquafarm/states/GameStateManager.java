package com.oop.aquafarm.states;

import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> states;

    public static final int TITLE = 0;
    public static final int ACCOUNT = 1;
    public static final int PLAY = 2;

    public GameStateManager(){

        states = new ArrayList<GameState>();

        states.add(new PlayState(this));
    }

    public void pop(int state){
        states.remove(state);
    }

    public void add(int state){
        if(state == 0){
            states.add(new TitleState(this));
        }
        if(state == 1){
            states.add(new AccountState(this));
        }
        if(state == 2){
            states.add(new PlayState(this));
        }
    }

    public void addAndpop(int state){
        states.remove(0);
        add(state);
    }

    public  void update(){
        for(int i = 0; i < states.size(); i++){
            states .get(i).update();
        }
    }
    public  void input(MouseHandler mouseIn, KeyHandler keyh){
        for(int i = 0; i < states.size(); i++){
            states .get(i).input(mouseIn, keyh);
        }
    }
    public  void render(Graphics2D g){
        for(int i = 0; i < states.size(); i++){
            states .get(i).render(g);
        }
    }

}
