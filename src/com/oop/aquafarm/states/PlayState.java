package com.oop.aquafarm.states;

import com.oop.aquafarm.GameLauncher;
import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Finance;
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
    Fish[] fishes;
    Food food;

    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);
        origin = new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2);
        hands = new Hand(new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2));
        food = new Food(origin);
        fishes = new Fish[100000];
        System.out.println();


//        fishes[0] = new Fish(origin, 100, 100, "ClownFish");
//        fishes[1] = new Fish(origin, 100, 100, "AtlanticBass");
//        fishes[2]= new Fish(origin, 100, 100, "BlueGill");
//        fishes[3] = new Fish(origin, 100, 100, "GoldenTench");
//        fishes[4] = new Fish(origin, 100, 100, "Guppy");
//        fishes[5] = new Fish(origin, 100, 100, "HIghFinBandedShark");

    }


    private void startGameTimer() {
        Timer gameTimer = new Timer((int) (1000 / GamePanel.FPS), (ActionListener) game);
        gameTimer.start();
    }

    @Override
    public void update(double time){
        Vector2f.setWorldVar(map.x, map.y);
        startGameTimer();
        hands.update(time);
        food.update(time);

        for (Fish fish : fishes) {

            if (fish != null) {
                fish.callEatingLogic(food);
                fish.seek_food(food.foodLocations);
            }

        }

    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh){
        food.input(mouseIn);

        for (Fish fish : fishes) {

            if (fish != null) {
                fish.input(mouseIn);
            }

        }

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

        keyh.key1.tick();
        keyh.key2.tick();
        keyh.key3.tick();
        keyh.key4.tick();
        keyh.key5.tick();
        keyh.key6.tick();

        if (Finance.money > 10) {

            if (keyh.key1.clicked) {
                Fish AtlanticBass = new Fish(origin, "AtlanticBass", null , null);
                addFishToArray(AtlanticBass);
            }
            if (keyh.key2.clicked) {

                Fish BlueGill = new Fish(origin, "BlueGill", null , null);
                addFishToArray(BlueGill);
            }
            if (keyh.key3.clicked) {
                Fish ClownFish = new Fish(origin, "ClownFish", null , null);
                addFishToArray(ClownFish);
            }
            if (keyh.key4.clicked) {
                Fish GoldenTench = new Fish(origin, "GoldenTench", null , null);
                addFishToArray(GoldenTench);
            }
            if (keyh.key5.clicked) {
                Fish Guppy = new Fish(origin, "Guppy", null , null);
                addFishToArray(Guppy);
            }
            if (keyh.key6.clicked) {
                Fish HIghFinBandedShark = new Fish(origin, "HIghFinBandedShark", null , null);
                addFishToArray(HIghFinBandedShark);
            }

        }


    }

    public void addFishToArray(Fish fish) {
        for (int i = 0; i < fishes.length; i++) {
            if (fishes[i] == null) {
                fishes[i] = fish;
                System.out.println(fishes[i]);
                Finance.money = Finance.money- 10;
                break;
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

        CFont money = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, 1100, 32);
        money.drawString(g, "$" + Finance.money );



        for (Fish fish : fishes) {

            if (fish != null) {
                fish.render(g);
                String gender = fish.getGender();
                if (gender != null) {
                    CFont fishGender = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 12, fish.getFishX(), fish.getFishY() - 10);
                    fishGender.drawString(g, gender);
                }

            }

        }

        food.render(g);
        hands.render(g);

    }
}
