package com.oop.aquafarm.states;


import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Finance;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.entity.Hand;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.graphics.SpriteSheet;
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

    private BufferedImage Img_mainmenu, Imgbuy_fish1, Imgbuy_fish2, Imgbuy_fish3, Imgbuy_fish4, Imgbuy_fish5, Imgbuy_fish6, Imgbuy_food, Imgsell_fish;

    private BufferedImage Hover_Img_mainmenu, Hover_Imgbuy_fish1, Hover_Imgbuy_fish2, Hover_Imgbuy_fish3, Hover_Imgbuy_fish4, Hover_Imgbuy_fish5, Hover_Imgbuy_fish6, Hover_Imgbuy_food, Hover_Imgsell_fish;

    private final Button btnmainmenu, btnFish1, btnFish2, btnFish3, btnFish4, btnFish5, btnFish6, btnBuyFood, btnSellFish;

    private int fishBtnSize = 128;
//    private int fishBtnNewSize = GamePanel.width / 10;
    private int smallFishBtnNewSize = GamePanel.width / 20;
//    private int btnHoverSize = smallFishBtnNewSize + 10;

    private int btnSpacing = smallFishBtnNewSize + 10;
    private int middleX = (GamePanel.width - (4 * fishBtnSize)) / 2;
    private int bottomY = GamePanel.height - btnSpacing;

    private boolean clicked = false;

    public static int fishCount = 0;
    private static final int MAX_FISH = 20;

    public static int unlock_fish2, unlock_fish3, unlock_fish4, unlock_fish5, unlock_fish6;
    public static int price;


    private boolean isBuyingFood = false;
    private boolean isSellingFish = false;
    private static boolean fishAdded = false;

    private void initbtnImage(){
        Img_mainmenu = GameStateManager.mainmenubtn.getSubimage(0, 0, 50, 50);
        Imgbuy_fish1 = GameStateManager.buybutton.getSubimage(0, 0, fishBtnSize, fishBtnSize);
        Imgbuy_fish2 = GameStateManager.buybutton.getSubimage(fishBtnSize, 0, fishBtnSize, fishBtnSize);
        Imgbuy_fish3 = GameStateManager.buybutton.getSubimage((fishBtnSize * 2), 0, fishBtnSize, fishBtnSize);
        Imgbuy_fish4 = GameStateManager.buybutton.getSubimage((fishBtnSize * 3), 0, fishBtnSize, fishBtnSize);
        Imgbuy_fish5 = GameStateManager.buybutton.getSubimage((fishBtnSize * 4), 0, fishBtnSize, fishBtnSize);
        Imgbuy_fish6 = GameStateManager.buybutton.getSubimage((fishBtnSize * 5), 0, fishBtnSize, fishBtnSize);
        Imgbuy_food = GameStateManager.buybutton.getSubimage((fishBtnSize * 6), 0, fishBtnSize, fishBtnSize);
        Imgsell_fish = GameStateManager.buybutton.getSubimage((fishBtnSize * 7), 0, fishBtnSize, fishBtnSize);

        Hover_Img_mainmenu = GameStateManager.mainmenubtn.getSubimage(0, 0, 50, 50);
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

        btnmainmenu = new Button(Img_mainmenu, new Vector2f(GamePanel.width - 32, 28), 30, 30);
        btnFish1 = new Button(Imgbuy_fish1, new Vector2f(middleX, bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnFish2 = new Button(Imgbuy_fish2, new Vector2f(middleX + btnSpacing, bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnFish3 = new Button(Imgbuy_fish3, new Vector2f(middleX + (btnSpacing*2), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnFish4 = new Button(Imgbuy_fish4, new Vector2f(middleX + (btnSpacing*3), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnFish5 = new Button(Imgbuy_fish5, new Vector2f(middleX + (btnSpacing*4), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnFish6 = new Button(Imgbuy_fish6, new Vector2f(middleX + (btnSpacing*5), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnBuyFood = new Button(Imgbuy_food, new Vector2f(middleX + (btnSpacing*6), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);
        btnSellFish = new Button(Imgsell_fish, new Vector2f(middleX + (btnSpacing*7), bottomY), smallFishBtnNewSize, smallFishBtnNewSize);

        btnmainmenu.addHoverImage(btnFish1.createButton(Hover_Img_mainmenu, 45 , 45));
        btnFish1.addHoverImage(btnFish1.createButton(Hover_Imgbuy_fish1, smallFishBtnNewSize , smallFishBtnNewSize));
        btnFish2.addHoverImage(btnFish2.createButton(Hover_Imgbuy_fish2, smallFishBtnNewSize, smallFishBtnNewSize));
        btnFish3.addHoverImage(btnFish3.createButton(Hover_Imgbuy_fish3, smallFishBtnNewSize, smallFishBtnNewSize));
        btnFish4.addHoverImage(btnFish4.createButton(Hover_Imgbuy_fish4, smallFishBtnNewSize, smallFishBtnNewSize));
        btnFish5.addHoverImage(btnFish5.createButton(Hover_Imgbuy_fish5, smallFishBtnNewSize, smallFishBtnNewSize));
        btnFish6.addHoverImage(btnFish6.createButton(Hover_Imgbuy_fish6, smallFishBtnNewSize, smallFishBtnNewSize));
        btnBuyFood.addHoverImage(btnBuyFood.createButton(Hover_Imgbuy_food, smallFishBtnNewSize, smallFishBtnNewSize));
        btnSellFish.addHoverImage(btnSellFish.createButton(Hover_Imgsell_fish, smallFishBtnNewSize, smallFishBtnNewSize));


        btnmainmenu.addEvent(e -> {
            if(gsm.isStateActive(GameStateManager.PAUSE)) {
                GameStateManager.pop(GameStateManager.PAUSE);
            } else {
                gsm.add(GameStateManager.PAUSE);
            }
        });

        btnFish1.addEvent(e -> {
            Fish fish = new Fish(origin, "AtlanticBass", null, null, 0);
            CRUD.addFish(con1, fish);
            CRUD.getfPrice(con1, fish);
            if (fishAdded) {
                unlock_fish2++;
                fishAdded = false;
                if (unlock_fish2 == 15) {
                    btnFish2.enabled = true;
                }
            }

        });

        btnFish2.addEvent(e -> {
            Fish fish = new Fish(origin, "BlueGill", null, null, 0);
            CRUD.addFish(con1, fish);
            CRUD.getfPrice(con1, fish);

            if (fishAdded) {
                unlock_fish3++;
                fishAdded = false;
                if (unlock_fish3 == 15) {
                    btnFish3.enabled = true;
                }
            }
        });

        btnFish3.addEvent(e -> {
            Fish fish = new Fish(origin, "Clownfish", null, null, 0);
            CRUD.addFish(con1, fish);
            CRUD.getfPrice(con1, fish);
            if (fishAdded) {
                unlock_fish4++;
                fishAdded = false;
                if (unlock_fish4 == 15) {
                    btnFish3.enabled = true;
                }
            }
        });

        btnFish4.addEvent(e -> {
            Fish fish = new Fish(origin, "GoldenTench", null, null, 0);
            CRUD.addFish(con1, fish);
            CRUD.getfPrice(con1, fish);
            if (fishAdded) {
                unlock_fish5++;
                fishAdded = false;
                if (unlock_fish5 == 15) {
                    btnFish3.enabled = true;
                }
            }
        });

        btnFish5.addEvent(e -> {
            Fish fish = new Fish(origin, "Guppy", null, null, 0);
            CRUD.addFish(con1, fish);
            CRUD.getfPrice(con1, fish);
            if (fishAdded) {
                unlock_fish6++;
                fishAdded = false;
                if (unlock_fish6 == 15) {
                    btnFish3.enabled = true;
                }
            }
        });


        btnFish6.addEvent(e -> {
            Fish fish = new Fish(origin, "HIghFinBandedShark", null, null, 0);
            CRUD.addFish(con1, fish);
            CRUD.getfPrice(con1, fish);
        });

        btnBuyFood.addEvent(e -> {
            isBuyingFood = !isBuyingFood;
            btnSellFish.enabled = !btnSellFish.enabled;
        });


        btnSellFish.addEvent(e -> {
            isSellingFish = !isSellingFish;
            btnBuyFood.enabled = !btnBuyFood.enabled;
        });

        btnFish2.enabled = false;
        btnFish3.enabled = false;
        btnFish4.enabled = false;
        btnFish5.enabled = false;
        btnFish6.enabled = false;

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
        if(mouseIn.getButton() == 1 && !clicked) {
            clicked = true;
        } else if(mouseIn.getButton() == -1) {
            clicked = false;
        }

        if (isBuyingFood) {
            food.input(mouseIn);
        }

        for (Fish fish : fishes) {
            if (fish != null) {
                fish.input(mouseIn);
                try {
                    CRUD.updateFish(PlayState.con1, fish);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        btnmainmenu.input(mouseIn, keyh);
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
                                Finance.money =  Finance.money + price/2 ;
                                break;
                            case 1:
                                Finance.money = Finance.money + price + 20;
                                break;
                            case 2:
                                Finance.money =  Finance.money + price + 40;
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
        if (Finance.money > price) {
            for (int i = 0; i < fishes.length; i++) {
                if (fishes[i] == null) {
                    fishes[i] = fish;
                    System.out.println(fishes[i]);
                    fishCount++;
                    Finance.money = Finance.money - price;
                    fishAdded = true;
                    break;
                }
            }
        }
    }
    public static void removeFishFromArray(Fish fishToRemove) {
        for (int i = 0; i < fishes.length; i++) {
            if (fishes[i] == fishToRemove) {
                fishes[i] = null;
                fishCount--;
                break;
            }
        }

    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage background  = null;
        background = SpriteSheet.paintbg(background);
        g.drawImage(background, 0, 0, null);
        g.setColor(new Color(1, 19, 50));
        g.fillRect(0,0, GamePanel.width, 50);

        CFont user = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, 32, 35);
        user.drawString(g, uname);

        CFont fps = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, GamePanel.width - 100, GamePanel.height - 32);
        fps.drawString(g, GamePanel.oldFrameCount +" FPS");

        CFont fishCountFont = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, 600, 35);
        fishCountFont.drawString(g, fishCount + "/" + MAX_FISH);

        if (!btnFish2.isEnabled()) {
            CFont unlockFish2 = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, middleX + btnSpacing - 25, bottomY - 50);
            unlockFish2.drawString(g, unlock_fish2 + "/15");
        }
        if (!btnFish3.isEnabled()) {
            CFont unlockFish3 = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, middleX + (btnSpacing * 2) - 25, bottomY - 50);
            unlockFish3.drawString(g, unlock_fish3 + "/15");
        }
        if (!btnFish4.isEnabled()) {
            CFont unlockFish4 = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, middleX + (btnSpacing * 3) - 25, bottomY - 50);
            unlockFish4.drawString(g, unlock_fish4 + "/15");

        }

        if (!btnFish5.isEnabled()) {
            CFont unlockFish5 = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, middleX + (btnSpacing * 4) - 25, bottomY - 50);
            unlockFish5.drawString(g, unlock_fish5 + "/15");
        }

        if (!btnFish6.isEnabled()) {
            CFont unlockFish6 = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, middleX + (btnSpacing * 5) - 25, bottomY - 50);
            unlockFish6.drawString(g,  unlock_fish6 + "/15");
        }

        CFont money = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, 1100, 35);
        int userMoney;
        try {
            userMoney = CRUD.getMoney(con1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        money.drawString(g, "$" + userMoney);

        food.render(g);

        btnmainmenu.render(g);
        btnFish1.render(g);
        btnFish2.render(g);
        btnFish3.render(g);
        btnFish4.render(g);
        btnFish5.render(g);
        btnFish6.render(g);
        btnBuyFood.render(g);
        btnSellFish.render(g);

        for (Fish fish : fishes) {
            if (fish != null) {
                fish.render(g);
            }
        }

        if (isSellingFish) {
            sells.render(g);
        } else {
            hands.render(g);
        }

    }
}