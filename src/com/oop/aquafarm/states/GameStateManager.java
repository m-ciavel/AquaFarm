package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import java.awt.Graphics2D;



public class GameStateManager {

    public static GameState[] states;

    public static Vector2f map;

    public static SpriteSheet button , buybutton;

    public static final int TITLE = 0;
    public static final int ACCOUNT = 1;
    public static final int PLAY = 2;
    public static final int SETTINGS = 3;
    public static final int QUIT = 4;

    public static Graphics2D g;

//    public int onTopState = 0;

    public GameStateManager(Graphics2D g){
        GameStateManager.g = g;

        map = new Vector2f(GamePanel.width, GamePanel.height);
        Vector2f.setWorldVar(map.x, map.y);

        states = new GameState[5];

        button = new SpriteSheet("menubutton/buttons.png", 144, 42);

        buybutton = new SpriteSheet("menubutton/buy_button.png", 48,48);




        states[TITLE] = new TitleState(this);
    }

    public boolean isStateActive(int state){
        return states[state] != null;
    }

    public static GameState getState(int state){
        return states[state];
    }

    public static void pop(int state){

        states[state] = null;
    }

    public void add(int state){
        if(states[state] != null){
            return;
        }
        if(state == TITLE){
            states[TITLE] = new TitleState(this);
        }
        else if(state == ACCOUNT){
            states[ACCOUNT] = new AccountState(this);
        }
        else if(state == PLAY){
            states[PLAY] = new PlayState(this);
        }
        else if(state == SETTINGS){
            states[SETTINGS] = new SettingsState(this);
        }
        else if(state == QUIT){
            states[QUIT] = new QuitState(this);
        }
    }

//    public void addandpop(int state, int remove){
//        addandpop(state, 0);
//    }
//
//    public void addandpop(int state){
//        pop(state);
//        add(state);
//    }

    public  void update(double time){
        for (GameState state : states) {
            if (state != null) {
                state.update(time);
            }
        }
    }
    public  void input(MouseHandler mouseIn, KeyHandler keyh){
        for (GameState state : states) {
            if (state != null) {
                state.input(mouseIn, keyh);
            }
        }
    }
    public  void render(Graphics2D g){
        for (GameState state : states) {
            if (state != null) {
                state.render(g);
            }
        }
    }


}