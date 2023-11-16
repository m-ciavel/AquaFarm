package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.ui.TitleButton;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.ScaledImage;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TitleState extends GameState {

    private TitleButton[] buttons = new TitleButton[3];
    private String currentState = "TITLE";
    private int imagewidth;
    private int newWidth;
    private int imageheight;

    public TitleState(GameStateManager gsm) {
        super(gsm);
        loadButtons();
    }

    @Override
    public void update(double time) {
        for(TitleButton tb : buttons){
//            tb.update(time);
        }
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


//        if(mouseIn.getButton() == 1){
//            for(TitleButton tb: buttons){
//                if(isIn(MouseHandler.mousePressed, tb)){
//                    tb.setMousePressed(true);
//                }
//            }
//        }
//        if(mouseIn.getButton() == -1){
//            for(TitleButton tb: buttons){
//                if(isIn(MouseHandler.mousePressed, tb)){
//                    if(tb.isMousePressed()){
////                        tb.applyGamestate(tb, currentState);
//                    }
//                }
//            }
//        }

    }

    @Override
    public void render(Graphics2D g) {

        BufferedImage background  = null;


        g.drawImage(paintbg(background), 0, 0, null);

        BufferedImage logo = null;
        g.drawImage(painttitle(logo),  (GamePanel.width - newWidth )/ 2, 115, null);

//        for(TitleButton tb: buttons){
//            tb.render(g);
//        }

    }

    private BufferedImage painttitle(BufferedImage title){
        ScaledImage uTool = new ScaledImage();
        File titlelogo = new File("res/background/logo.png");
        try {
            title = ImageIO.read(titlelogo);
            this.imagewidth = title.getWidth();
            this.imageheight = title.getHeight();
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
            this.imagewidth = background.getWidth();
            this.imageheight = background.getHeight();
            this.newWidth = GamePanel.width;
            background = uTool.scaledImage(background, newWidth, GamePanel.height);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: could not load file: " + f);
        }
        return background;
    }


    private void loadButtons(){
//        buttons[0] = new TitleButton(GamePanel.width /2, (int) (150 * GamePanel.scale), 0, GameStateManager.PLAY);
//        buttons[1] = new TitleButton(GamePanel.width /2, (int) (150 * GamePanel.scale), 0, GameStateManager.SETTINGS);
//        buttons[2] = new TitleButton(GamePanel.width /2, (int) (150 * GamePanel.scale), 0, GameStateManager.QUIT);
//        buttons[0] = new TitleButton(GamePanel.width /2, (int) (150 * GamePanel.scale), 0);
//        buttons[1] = new TitleButton(GamePanel.width /2, (int) (150 * GamePanel.scale), 0);
//        buttons[2] = new TitleButton(GamePanel.width /2, (int) (150 * GamePanel.scale), 0);
    }


}
