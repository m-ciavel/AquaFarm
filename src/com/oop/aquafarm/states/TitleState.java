package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.ui.Button;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.ScaledImage;
import com.oop.aquafarm.util.Vector2f;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TitleState extends GameState {

    private BufferedImage imgButton;
    private BufferedImage imgHover;
    private int newWidth;
    private Button btnPlay;
    private Button btnSettings;
    private Button btnExit;


    public TitleState(GameStateManager gsm) {
        super(gsm);
//        imgButton = GameStateManager.button.getSubimage(0, 0, 144, 42);
//        imgHover = GameStateManager.button.getSubimage(0, 43, 144, 42);

//        btnPlay = new Button(imgButton, new Vector2f(GamePanel.width/2, GamePanel.height/2 - 48),32, 16);
//        btnSettings = new Button(imgHover, new Vector2f(GamePanel.width/2, GamePanel.height/2 + 48), 32, 16);
//        btnSettings = new Button(imgHover, new Vector2f(GamePanel.width/2, GamePanel.height/2 + 96), 32, 16);
//
//        btnPlay.addHoverImage(btnPlay.createButton(imgHover, 32, 20));
//        btnSettings.addHoverImage(btnSettings.createButton(imgHover, 32, 20));
//
//        btnPlay.addEvent(e -> {
//            gsm.add(GameStateManager.PLAY);
//            gsm.pop(GameStateManager.TITLE);
//        });
//
//        btnSettings.addEvent(e -> {
//            gsm.add(GameStateManager.SETTINGS);
//            gsm.pop(GameStateManager.TITLE);
//        });
//
//        btnExit.addEvent(e -> {
//            System.exit(0);
//        });
    }

    @Override
    public void update(double time) {
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        keyh.p.tick();

        if(keyh.p.clicked){
            if (gsm.isStateActive(GameStateManager.PLAY)){
                gsm.pop(GameStateManager.PLAY);
            }else{
                gsm.add(GameStateManager.PLAY);
                gsm.pop(GameStateManager.TITLE);
            }

        }

    }

    @Override
    public void render(Graphics2D g) {

        BufferedImage background  = null;
        g.drawImage(paintbg(background), 0, 0, null);

        BufferedImage logo = null;
        g.drawImage(painttitle(logo),  (GamePanel.width - newWidth )/ 2, 115, null);


    }

    private BufferedImage painttitle(BufferedImage title){
        ScaledImage uTool = new ScaledImage();
        File titlelogo = new File("res/background/logo.png");
        try {
            title = ImageIO.read(titlelogo);
            this.newWidth = GamePanel.width - (GamePanel.width/5);
            title = uTool.scaledImage(title, newWidth, GamePanel.height/4);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: could not load file: " + titlelogo);
        }
        return title;
    }
    private BufferedImage paintbg(BufferedImage background){
        ScaledImage uTool = new ScaledImage();
        File f = new File("res/background/bg.jpg");
        try {
            background = ImageIO.read(f);
            this.newWidth = GamePanel.width;
            background = uTool.scaledImage(background, newWidth, GamePanel.height);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: could not load file: " + f);
        }
        return background;
    }



}
