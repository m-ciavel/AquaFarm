package com.oop.aquafarm.states;


import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Finance;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.entity.Hand;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.ui.Button;
import com.oop.aquafarm.util.*;

import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

public class PlayState extends GameState{

    GamePanel game;
    public static dbConnection con1 = new dbConnection();
    public static Vector2f map, origin;
    Hand hands;
    static Fish[] fishes;
    Food food;

    Finance sells;
    private String uname;

    private BufferedImage Imgbuy_fish1, Imgbuy_fish2, Imgbuy_fish3, Imgbuy_fish4, Imgbuy_fish5, Imgbuy_fish6, Imgbuy_food, Imgsell_fish;

    private BufferedImage Hover_Imgbuy_fish1, Hover_Imgbuy_fish2, Hover_Imgbuy_fish3, Hover_Imgbuy_fish4, Hover_Imgbuy_fish5, Hover_Imgbuy_fish6, Hover_Imgbuy_food, Hover_Imgsell_fish;

    private final Button btnFish1, btnFish2, btnFish3, btnFish4, btnFish5, btnFish6, btnBuyFood, btnSellFish;

    private int fishBtnSize = 128;
//    private int fishBtnNewSize = GamePanel.width / 10;
    private int smallFishBtnNewSize = GamePanel.width / 20;
//    private int btnHoverSize = smallFishBtnNewSize + 10;

    private int btnSpacing = smallFishBtnNewSize + 10;
    private int middleX = (GamePanel.width - (4 * fishBtnSize)) / 2;
    private int bottomY = GamePanel.height - btnSpacing;



    private boolean isBuyingFood = false;
    private boolean isSellingFish = false;

    private void initbtnImage(){
        Imgbuy_fish1 = GameStateManager.buybutton.getSubimage(0, 0, fishBtnSize, fishBtnSize);
        Imgbuy_fish2 = GameStateManager.buybutton.getSubimage(fishBtnSize, 0, fishBtnSize, fishBtnSize);
        Imgbuy_fish3 = GameStateManager.buybutton.getSubimage((fishBtnSize * 2), 0, fishBtnSize, fishBtnSize);
        Imgbuy_fish4 = GameStateManager.buybutton.getSubimage((fishBtnSize * 3), 0, fishBtnSize, fishBtnSize);
        Imgbuy_fish5 = GameStateManager.buybutton.getSubimage((fishBtnSize * 4), 0, fishBtnSize, fishBtnSize);
        Imgbuy_fish6 = GameStateManager.buybutton.getSubimage((fishBtnSize * 5), 0, fishBtnSize, fishBtnSize);
        Imgbuy_food = GameStateManager.buybutton.getSubimage((fishBtnSize * 6), 0, fishBtnSize, fishBtnSize);
        Imgsell_fish = GameStateManager.buybutton.getSubimage((fishBtnSize * 7), 0, fishBtnSize, fishBtnSize);

        Hover_Imgbuy_fish1 = GameStateManager.buybutton.getSubimage(0, fishBtnSize, fishBtnSize, fishBtnSize);
        Hover_Imgbuy_fish2 = GameStateManager.buybutton.getSubimage(fishBtnSize, fishBtnSize, fishBtnSize, fishBtnSize);
        Hover_Imgbuy_fish3 = GameStateManager.buybutton.getSubimage(fishBtnSize * 2, fishBtnSize, fishBtnSize, fishBtnSize);
        Hover_Imgbuy_fish4 = GameStateManager.buybutton.getSubimage(fishBtnSize * 3, fishBtnSize, fishBtnSize, fishBtnSize);
        Hover_Imgbuy_fish5 = GameStateManager.buybutton.getSubimage(fishBtnSize * 4, fishBtnSize, fishBtnSize, fishBtnSize);
        Hover_Imgbuy_fish6 = GameStateManager.buybutton.getSubimage(fishBtnSize * 5, fishBtnSize, fishBtnSize, fishBtnSize);
        Hover_Imgbuy_food = GameStateManager.buybutton.getSubimage(fishBtnSize * 6, fishBtnSize, fishBtnSize, fishBtnSize);
        Hover_Imgsell_fish = GameStateManager.buybutton.getSubimage(fishBtnSize * 7, fishBtnSize, fishBtnSize, fishBtnSize);
    }

    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);
        origin = new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2);
        hands = new Hand(new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2), "hand");

        uname = Login.getUname();


        sells = new Finance(new Vector2f((float) GamePanel.width / 2, (float) GamePanel.height / 2));
        food = new Food(origin);
        fishes = new Fish[50];

        try {
            CRUD.getFish();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initbtnImage();

        btnFish1 = new Button(Imgbuy_fish1, new Vector2f(middleX, bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnFish2 = new Button(Imgbuy_fish2, new Vector2f(middleX + btnSpacing, bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnFish3 = new Button(Imgbuy_fish3, new Vector2f(middleX + (btnSpacing*2), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnFish4 = new Button(Imgbuy_fish4, new Vector2f(middleX + (btnSpacing*3), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnFish5 = new Button(Imgbuy_fish5, new Vector2f(middleX + (btnSpacing*4), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnFish6 = new Button(Imgbuy_fish6, new Vector2f(middleX + (btnSpacing*5), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnBuyFood = new Button(Imgbuy_food, new Vector2f(middleX + (btnSpacing*6), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnSellFish = new Button(Imgsell_fish, new Vector2f(middleX + (btnSpacing*7), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);

        btnFish1.addHoverImage(btnFish1.createButton(Hover_Imgbuy_fish1, smallFishBtnNewSize , smallFishBtnNewSize));
        btnFish2.addHoverImage(btnFish2.createButton(Hover_Imgbuy_fish2, smallFishBtnNewSize, smallFishBtnNewSize));
        btnFish3.addHoverImage(btnFish3.createButton(Hover_Imgbuy_fish3, smallFishBtnNewSize, smallFishBtnNewSize));
        btnFish4.addHoverImage(btnFish4.createButton(Hover_Imgbuy_fish4, smallFishBtnNewSize, smallFishBtnNewSize));
        btnFish5.addHoverImage(btnFish5.createButton(Hover_Imgbuy_fish5, smallFishBtnNewSize, smallFishBtnNewSize));
        btnFish6.addHoverImage(btnFish6.createButton(Hover_Imgbuy_fish6, smallFishBtnNewSize, smallFishBtnNewSize));
        btnBuyFood.addHoverImage(btnBuyFood.createButton(Hover_Imgbuy_food, smallFishBtnNewSize, smallFishBtnNewSize));
        btnSellFish.addHoverImage(btnSellFish.createButton(Hover_Imgsell_fish, smallFishBtnNewSize, smallFishBtnNewSize));


        btnFish1.addEvent(e -> {
            Fish fish = new Fish(origin, "AtlanticBass", null, null,0);
            CRUD.addFish(con1, fish);
//            addFishToArray(fish);
        });


        btnFish2.addEvent(e -> {
            Fish fish = new Fish(origin, "BlueGill", null, null, 0);
            CRUD.addFish(con1, fish);
//            addFishToArray(fish);
        });
        btnFish3.addEvent(e -> {
            Fish fish = new Fish(origin, "Clownfish", null, null, 0);
            CRUD.addFish(con1, fish);
//            addFishToArray(fish);
        });

        btnFish4.addEvent(e -> {
            Fish fish = new Fish(origin, "GoldenTench", null, null, 0);
            CRUD.addFish(con1, fish);
//            addFishToArray(fish);
        });

        btnFish5.addEvent(e -> {
            Fish fish = new Fish(origin, "Guppy", null, null, 0);
            CRUD.addFish(con1, fish);
//            addFishToArray(fish);
        });


        btnFish6.addEvent(e -> {
            Fish fish = new Fish(origin, "HIghFinBandedShark", null, null, 0);
            CRUD.addFish(con1, fish);
        });

        btnBuyFood.addEvent(e -> {
            isBuyingFood = !isBuyingFood;
        });


        btnSellFish.addEvent(e -> {
            isSellingFish = !isSellingFish;
        });



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
        sells.update(time);

        for (Fish fish : fishes) {

            if (fish != null) {
                fish.callEatingLogic(food);
                fish.seek_food(food.foodLocations);
            }

        }

    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        keyh.escape.tick();
        if(keyh.escape.clicked){
            if (gsm.isStateActive(GameStateManager.TITLE)){
                GameStateManager.pop(GameStateManager.TITLE);
            }else{
                gsm.add(GameStateManager.TITLE);
                GameStateManager.pop(GameStateManager.PLAY);
            }
            System.out.println(Login.loggedIn);
        }

        if (isBuyingFood) {
            food.input(mouseIn);
        }

        for (Fish fish : fishes) {
            if (fish != null) {
                fish.input(mouseIn);
            }
        }

        btnFish1.input(mouseIn, keyh);
        btnFish2.input(mouseIn, keyh);
        btnFish3.input(mouseIn, keyh);
        btnFish4.input(mouseIn, keyh);
        btnFish5.input(mouseIn, keyh);
        btnFish6.input(mouseIn, keyh);
        btnBuyFood.input(mouseIn, keyh);
        btnSellFish.input(mouseIn, keyh);

        hands.input(mouseIn);
        sells.input(mouseIn);

        if (isSellingFish && mouseIn.getButton() == 1) {

            for (Fish fish : fishes) {
                if (fish != null) {

                    if (mouseIn.getX() >= fish.getFishX() &&
                            mouseIn.getX() <= fish.getFishX() + fish.getFishWidth() &&
                            mouseIn.getY() >= fish.getFishY() &&
                            mouseIn.getY() <= fish.getFishY() + fish.getFishHeight()) {
                        switch (fish.getFishsize()){
                            case 0:
                                Finance.money +=5;
                                break;
                            case 1:
                                Finance.money +=10;
                                break;
                            case 2:
                                Finance.money += 15;
                                break;
                        }
                        try {
                            CRUD.removeFish(con1, fish);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                }
            }
        }
    }



    public static void addFishToArray(Fish fish) {
        for (int i = 0; i < fishes.length; i++) {
            if (fishes[i] == null) {
                fishes[i] = fish;
                System.out.println(fishes[i]);
                Finance.money = Finance.money - 10;
                break;
            }
        }
    }
    public static void removeFishFromArray(Fish fishToRemove) {
        for (int i = 0; i < fishes.length; i++) {
            if (fishes[i] == fishToRemove) {
                fishes[i] = null;
                break;
            }
        }

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(new Color(14, 135, 204));
        g.fillRect(0,0, GamePanel.width, GamePanel.height);

        CFont user = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, 32, 32);
        user.drawString(g, uname);

        CFont fps = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, GamePanel.width - 100, GamePanel.height - 32);
        fps.drawString(g, GamePanel.oldFrameCount +" FPS");

//        CFont tps = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, 32, GamePanel.height - 32);
//        tps.drawString(g,GamePanel.oldTickCount + " TPS");

        CFont money = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, 1100, 32);
        int userMoney;
        try {
            userMoney = CRUD.getMoney(con1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        money.drawString(g, "$" + userMoney);

        food.render(g);

        btnFish1.render(g);
        btnFish2.render(g);
        btnFish3.render(g);
        btnFish4.render(g);
        btnFish5.render(g);
        btnFish6.render(g);
        btnBuyFood.render(g);
        btnSellFish.render(g);

        if (isSellingFish) {
            sells.render(g);
        } else {
            hands.render(g);
        }

        for (Fish fish : fishes) {
            if (fish != null) {
                fish.render(g);
            }
        }

    }
}