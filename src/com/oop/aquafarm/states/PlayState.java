package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.GodHand;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.entity.Hand;
import com.oop.aquafarm.entity.fishes.*;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayState extends GameState{

    MouseHandler mouseIn;
    GamePanel game;
    public static Vector2f map;
    Hand hands;
    Fish fish;
    Clown_fish clownfish;
    Blue_Gill bluegill;
    Atlantic_Bass atlanticbass;
    Golden_Tench goldentench;
    Guppy guppy;
    High_Fin_Banded_Shark highfinbandedshark;
    Food food;

    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);
//        hands = new Hand(mouseIn, game);
//        food = new Food(mouseIn, g);

//        clownfish = new Clown_fish(100, 100, game, mouseIn);
//        atlanticbass = new Atlantic_Bass(100, 100, game);
//        bluegill = new Blue_Gill(100, 100, game);
//        goldentench = new Golden_Tench(100, 100, game);
//        guppy = new Guppy(100, 100, game);
//        highfinbandedshark = new High_Fin_Banded_Shark(100, 100, game);
    }

    private void startGameTimer() {
        Timer gameTimer = new Timer((int) (1000 / GamePanel.FPS), (ActionListener) game);
        gameTimer.start();
    }

    @Override
    public void update(double time){
        System.out.println("gamestate");
        Vector2f.setWorldVar(map.x, map.y);
        startGameTimer();
//        hands.update(time);
//        food.update();

        // Call the move method for all fish entities
//        clownfish.move();
//        bluegill.move();
//        atlanticbass.move();
//        goldentench.move();
//        guppy.move();
//        highfinbandedshark.move();
//
//        clownfish.eatFood(food);
//        bluegill.eatFood(food);
//        atlanticbass.eatFood(food);
//        goldentench.eatFood(food);
//        guppy.eatFood(food);
//        highfinbandedshark.eatFood(food);
//
//        // Use while loop to continuously check and seek food for all fish entities
//        if (!food.foodLocations.isEmpty()) {
//            clownfish.seek_food(food.foodLocations);
//            bluegill.seek_food(food.foodLocations);
//            atlanticbass.seek_food(food.foodLocations);
//            goldentench.seek_food(food.foodLocations);
//            guppy.seek_food(food.foodLocations);
//            highfinbandedshark.seek_food(food.foodLocations);
//        }
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh){
//        game.addMouseMotionListener(mouseIn);
//        game.addMouseListener(mouseIn);
//        hands.input(mouseIn);

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

//        hands.render(g);
//        clownfish.render(g);
//        bluegill.render(g);
//        atlanticbass.render(g);
//        goldentench.render(g);
//        guppy.render(g);
//        highfinbandedshark.render(g);
//        food.render(g);

    }
}
