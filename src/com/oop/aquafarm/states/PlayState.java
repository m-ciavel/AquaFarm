package com.oop.aquafarm.states;

import com.oop.aquafarm.GameLauncher;
import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Finance;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.entity.Hand;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.ui.Button;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class PlayState extends GameState{
    GamePanel game;
    public static Vector2f map;
    public static Vector2f origin;
    Hand hands;
    Fish[] fishes;
    Food food;

    Finance sells;

    private BufferedImage Imgbuy_fish1;
    private BufferedImage Imgbuy_fish2;
    private BufferedImage Imgbuy_fish3;
    private BufferedImage Imgbuy_fish4;
    private BufferedImage Imgbuy_fish5;
    private BufferedImage Imgbuy_fish6;
    private BufferedImage Imgbuy_food;
    private BufferedImage Imgsell_fish;
    private BufferedImage Hover_Imgbuy_fish1;
    private BufferedImage Hover_Imgbuy_fish2;
    private BufferedImage Hover_Imgbuy_fish3;
    private BufferedImage Hover_Imgbuy_fish4;
    private BufferedImage Hover_Imgbuy_fish5;
    private BufferedImage Hover_Imgbuy_fish6;
    private BufferedImage Hover_Imgbuy_food;
    private BufferedImage Hover_Imgsell_fish;

    private Button btnFish1;
    private Button btnFish2;
    private Button btnFish3;
    private Button btnFish4;
    private Button btnFish5;
    private Button btnFish6;

    private Button btnBuyFood;
    private Button btnSellFish;

    private int fishBtnWidth = 128;
    private int fishBtnHeight = 128;
    private int fishBtnNewWidth = (int) (GamePanel.width / 10);
    private int fishBtnNewHeight = fishBtnNewWidth * fishBtnHeight / fishBtnWidth;

    private boolean isBuyingFood = false;


    private boolean isSellingFish = false;




    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);
        origin = new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2);
        hands = new Hand(new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2));
        sells = new Finance(new Vector2f(((float) GamePanel.width+100), (float) GamePanel.height+100));
        food = new Food(origin);
        fishes = new Fish[100000];
        System.out.println();


//        fishes[0] = new Fish(origin, 100, 100, "ClownFish");
//        fishes[1] = new Fish(origin, 100, 100, "AtlanticBass");
//        fishes[2]= new Fish(origin, 100, 100, "BlueGill");
//        fishes[3] = new Fish(origin, 100, 100, "GoldenTench");
//        fishes[4] = new Fish(origin, 100, 100, "Guppy");
//        fishes[5] = new Fish(origin, 100, 100, "HIghFinBandedShark");


        Imgbuy_fish1 = GameStateManager.buybutton.getSubimage(0, 0, fishBtnWidth, fishBtnHeight);
        Imgbuy_fish2 = GameStateManager.buybutton.getSubimage(fishBtnWidth, 0, fishBtnWidth, fishBtnHeight);
        Imgbuy_fish3 = GameStateManager.buybutton.getSubimage(fishBtnWidth * 2, 0, fishBtnWidth, fishBtnHeight);
        Imgbuy_fish4 = GameStateManager.buybutton.getSubimage(fishBtnWidth * 3, 0, fishBtnWidth, fishBtnHeight);
        Imgbuy_fish5 = GameStateManager.buybutton.getSubimage(fishBtnWidth * 4, 0, fishBtnWidth, fishBtnHeight);
        Imgbuy_fish6 = GameStateManager.buybutton.getSubimage(fishBtnWidth * 5, 0, fishBtnWidth, fishBtnHeight);
        Imgbuy_food = GameStateManager.buybutton.getSubimage(fishBtnWidth * 6, 0, fishBtnWidth, fishBtnHeight);
        Imgsell_fish = GameStateManager.buybutton.getSubimage(fishBtnWidth * 7, 0, fishBtnWidth, fishBtnHeight);

        Hover_Imgbuy_fish1 = GameStateManager.buybutton.getSubimage(0, 0, fishBtnWidth, fishBtnHeight);


        Hover_Imgbuy_fish2 = GameStateManager.buybutton.getSubimage(fishBtnWidth, 0, fishBtnWidth, fishBtnHeight);
        Hover_Imgbuy_fish3 = GameStateManager.buybutton.getSubimage(fishBtnWidth * 2, 0, fishBtnWidth, fishBtnHeight);
        Hover_Imgbuy_fish4 = GameStateManager.buybutton.getSubimage(fishBtnWidth * 3, 0, fishBtnWidth, fishBtnHeight);
        Hover_Imgbuy_fish5 = GameStateManager.buybutton.getSubimage(fishBtnWidth * 4, 0, fishBtnWidth, fishBtnHeight);
        Hover_Imgbuy_fish6 = GameStateManager.buybutton.getSubimage(fishBtnWidth * 5, 0, fishBtnWidth, fishBtnHeight);
        Hover_Imgbuy_food = GameStateManager.buybutton.getSubimage(fishBtnWidth * 6, 0, fishBtnWidth, fishBtnHeight);
        Hover_Imgsell_fish = GameStateManager.buybutton.getSubimage(fishBtnWidth * 7, 0, fishBtnWidth, fishBtnHeight);




        int buttonSpacing = 10;

        int smallFishBtnNewWidth = fishBtnNewWidth -50;
        int smallFishBtnNewHeight = fishBtnNewHeight -50;

        int middleX = (GamePanel.width - (7 * (smallFishBtnNewWidth + buttonSpacing))) / 2;
        int bottomY = GamePanel.height - smallFishBtnNewHeight + 10;
        int hoverIncrease = 1000;



        btnFish1 = new Button(Imgbuy_fish1, new Vector2f(middleX, bottomY), smallFishBtnNewWidth, smallFishBtnNewHeight);
        btnFish2 = new Button(Imgbuy_fish2, new Vector2f(middleX + (smallFishBtnNewWidth + buttonSpacing), bottomY), smallFishBtnNewWidth, smallFishBtnNewHeight);
        btnFish3 = new Button(Imgbuy_fish3, new Vector2f(middleX + 2 * (smallFishBtnNewWidth + buttonSpacing), bottomY), smallFishBtnNewWidth, smallFishBtnNewHeight);
        btnFish4 = new Button(Imgbuy_fish4, new Vector2f(middleX + 3 * (smallFishBtnNewWidth + buttonSpacing), bottomY), smallFishBtnNewWidth, smallFishBtnNewHeight);
        btnFish5 = new Button(Imgbuy_fish5, new Vector2f(middleX + 4 * (smallFishBtnNewWidth + buttonSpacing), bottomY), smallFishBtnNewWidth, smallFishBtnNewHeight);
        btnFish6 = new Button(Imgbuy_fish6, new Vector2f(middleX + 5 * (smallFishBtnNewWidth + buttonSpacing), bottomY), smallFishBtnNewWidth, smallFishBtnNewHeight);
        btnBuyFood = new Button(Imgbuy_food, new Vector2f(middleX + 6 * (smallFishBtnNewWidth + buttonSpacing), bottomY), smallFishBtnNewWidth, smallFishBtnNewHeight);
        btnSellFish = new Button(Imgsell_fish, new Vector2f(middleX + 7 * (smallFishBtnNewWidth + buttonSpacing), bottomY), smallFishBtnNewWidth, smallFishBtnNewHeight);

        btnFish1.addHoverImage(btnFish1.createButton(Hover_Imgbuy_fish1, smallFishBtnNewWidth + hoverIncrease, smallFishBtnNewHeight + hoverIncrease));
        btnFish2.addHoverImage(btnFish2.createButton(Hover_Imgbuy_fish2, smallFishBtnNewWidth + hoverIncrease, smallFishBtnNewHeight + hoverIncrease));
        btnFish3.addHoverImage(btnFish3.createButton(Hover_Imgbuy_fish3, smallFishBtnNewWidth + hoverIncrease, smallFishBtnNewHeight + hoverIncrease));
        btnFish4.addHoverImage(btnFish4.createButton(Hover_Imgbuy_fish4, smallFishBtnNewWidth + hoverIncrease, smallFishBtnNewHeight + hoverIncrease));
        btnFish5.addHoverImage(btnFish5.createButton(Hover_Imgbuy_fish5, smallFishBtnNewWidth + hoverIncrease, smallFishBtnNewHeight + hoverIncrease));
        btnFish6.addHoverImage(btnFish6.createButton(Hover_Imgbuy_fish6, smallFishBtnNewWidth + hoverIncrease, smallFishBtnNewHeight + hoverIncrease));
        btnBuyFood.addHoverImage(btnBuyFood.createButton(Hover_Imgbuy_food, smallFishBtnNewWidth + hoverIncrease, smallFishBtnNewHeight + hoverIncrease));
        btnSellFish.addHoverImage(btnSellFish.createButton(Hover_Imgsell_fish, smallFishBtnNewWidth + hoverIncrease, smallFishBtnNewHeight + hoverIncrease));



        btnFish1.addEvent(e -> {
            Fish fish = new Fish(origin, "AtlanticBass", null, null);
            addFishToArray(fish);
        });


        btnFish2.addEvent(e -> {
            Fish fish = new Fish(origin, "BlueGill", null, null);
            addFishToArray(fish);
        });
        btnFish3.addEvent(e -> {
            Fish fish = new Fish(origin, "Clownfish", null, null);
            addFishToArray(fish);
        });

        btnFish4.addEvent(e -> {
            Fish fish = new Fish(origin, "GoldenTench", null, null);
            addFishToArray(fish);
        });

        btnFish5.addEvent(e -> {
            Fish fish = new Fish(origin, "Guppy", null, null);
            addFishToArray(fish);
        });


        btnFish6.addEvent(e -> {
            Fish fish = new Fish(origin, "HIghFinBandedShark", null, null);
            addFishToArray(fish);
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
    public void input(MouseHandler mouseIn, KeyHandler keyh){

        if (isBuyingFood) {
            food.input(mouseIn);
        }

        for (Fish fish : fishes) {

            if (fish != null) {
                fish.input(mouseIn);
            }

        }
            hands.input(mouseIn);
            sells.input(mouseIn);



        btnFish1.input(mouseIn, keyh);
        btnFish2.input(mouseIn, keyh);
        btnFish3.input(mouseIn, keyh);
        btnFish4.input(mouseIn, keyh);
        btnFish5.input(mouseIn, keyh);
        btnFish6.input(mouseIn, keyh);
        btnBuyFood.input(mouseIn, keyh);
        btnSellFish.input(mouseIn, keyh);

        keyh.escape.tick();
        if(keyh.escape.clicked){
            if (gsm.isStateActive(GameStateManager.TITLE)){
                gsm.pop(GameStateManager.TITLE);
            }else{
                gsm.add(GameStateManager.TITLE);
                gsm.pop(GameStateManager.PLAY);
            }

        }

//        keyh.key1.tick();
//        keyh.key2.tick();
//        keyh.key3.tick();
//        keyh.key4.tick();
//        keyh.key5.tick();
//        keyh.key6.tick();
//
//        if (Finance.money > 10) {
//
//            if (keyh.key1.clicked) {
//                Fish AtlanticBass = new Fish(origin, "AtlanticBass", null , null);
//                addFishToArray(AtlanticBass);
//            }
//            if (keyh.key2.clicked) {
//
//                Fish BlueGill = new Fish(origin, "BlueGill", null , null);
//                addFishToArray(BlueGill);
//            }
//            if (keyh.key3.clicked) {
//                Fish ClownFish = new Fish(origin, "ClownFish", null , null);
//                addFishToArray(ClownFish);
//            }
//            if (keyh.key4.clicked) {
//                Fish GoldenTench = new Fish(origin, "GoldenTench", null , null);
//                addFishToArray(GoldenTench);
//            }
//            if (keyh.key5.clicked) {
//                Fish Guppy = new Fish(origin, "Guppy", null , null);
//                addFishToArray(Guppy);
//            }
//            if (keyh.key6.clicked) {
//                Fish HIghFinBandedShark = new Fish(origin, "HIghFinBandedShark", null , null);
//                addFishToArray(HIghFinBandedShark);
//            }
//
//        }


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

        food.render(g);

        if (isSellingFish) {
            sells.render(g);
        } else {
            hands.render(g);
        }
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

                String gender = fish.getGender();
                if (gender != null) {
                    BufferedImage genderImg = SpriteSheet.setup("genders", gender);

                    double scale = 0.4;

                    int scaledWidth = (int) (genderImg.getWidth() * scale);
                    int scaledHeight = (int) (genderImg.getHeight() * scale);

                    int genderX = fish.getFishX() + (fish.getFishWidth() - scaledWidth) / 2;
                    int genderY = fish.getFishY() - 10;

                    g.drawImage(genderImg, genderX + 30, genderY, scaledWidth, scaledHeight, null);
                }
            }
        }





    }
}