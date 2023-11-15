package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.entity.Hand;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionListener;

public class PlayState extends GameState{
    GamePanel game;
    public static Vector2f map;
    public static Vector2f origin;
    Hand hands;
    Fish atlanticbass, clownfish, bluegill, goldentench, guppy, highfinbandedshark;
//    Clown_fish clownfish;
//    Blue_Gill bluegill;
//    Atlantic_Bass atlanticbass;
//    Golden_Tench goldentench;
//    Guppy guppy;
//    High_Fin_Banded_Shark highfinbandedshark;
    Food food;

    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);
        origin = new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2);
        hands = new Hand(new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2));
        food = new Food(origin);

//        clownfish = new Clown_fish(origin, 100, 100);
//        atlanticbass = new Atlantic_Bass(origin, 100, 100);
//        bluegill = new Blue_Gill(origin, 100, 100);
//        goldentench = new Golden_Tench(origin, 100, 100);
//        guppy = new Guppy(origin, 100, 100);
//        highfinbandedshark = new High_Fin_Banded_Shark(origin, 100, 100);

        clownfish = new Fish(origin, 100, 100, "ClownFish");
        atlanticbass = new Fish(origin, 100, 100, "AtlanticBass");
        bluegill = new Fish(origin, 100, 100, "BlueGill");
        goldentench = new Fish(origin, 100, 100, "GoldenTench");
        guppy = new Fish(origin, 100, 100, "Guppy");
        highfinbandedshark = new Fish(origin, 100, 100, "HIghFinBandedShark");

    }


    private void startGameTimer() {
        Timer gameTimer = new Timer((int) (1000 / GamePanel.FPS), (ActionListener) game);
        gameTimer.start();
    }

    @Override
    public void update(double time){
//        System.out.println("gamestate");
        Vector2f.setWorldVar(map.x, map.y);
        startGameTimer();
        hands.update(time);
        food.update(time);

        // Call the move method for all fish entities

        clownfish.move();
        bluegill.move();
        atlanticbass.move();
        goldentench.move();
        guppy.move();
        highfinbandedshark.move();

//        clownfish.eatFood(food);
//        bluegill.eatFood(food);
//        atlanticbass.eatFood(food);
//        goldentench.eatFood(food);
//        guppy.eatFood(food);
//        highfinbandedshark.eatFood(food);
//
        clownfish.callEatingLogic(food);
        bluegill.callEatingLogic(food);
        atlanticbass.callEatingLogic(food);
        goldentench.callEatingLogic(food);
        guppy.callEatingLogic(food);
        highfinbandedshark.callEatingLogic(food);

        // Use while loop to continuously check and seek food for all fish entities
        if (!food.foodLocations.isEmpty()) {
            clownfish.seek_food(food.foodLocations);
            bluegill.seek_food(food.foodLocations);
            atlanticbass.seek_food(food.foodLocations);
            goldentench.seek_food(food.foodLocations);
            guppy.seek_food(food.foodLocations);
            highfinbandedshark.seek_food(food.foodLocations);
        }
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh){
        food.input(mouseIn);

        clownfish.input(mouseIn);
        bluegill.input(mouseIn);
        atlanticbass.input(mouseIn);
        goldentench.input(mouseIn);
        guppy.input(mouseIn);
        highfinbandedshark.input(mouseIn);

        hands.input(mouseIn);

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


        clownfish.render(g);
        bluegill.render(g);
        atlanticbass.render(g);
        goldentench.render(g);
        guppy.render(g);
        highfinbandedshark.render(g);
        food.render(g);
        hands.render(g);

    }
}
