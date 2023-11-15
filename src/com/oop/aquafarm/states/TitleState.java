package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.ScaledImage;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TitleState extends GameState {

    private int imagewidth;
    private int newWidth;
    private int imageheight;

    public TitleState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update(double time) {
        System.out.println("titlestate");
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
        ScaledImage uTool = new ScaledImage();
        BufferedImage background = null;

//        File f = new File("./res/background/bg.png");


//        background = SpriteSheet.setup("background", "bg");
//        System.out.println("ERROR: could not load file: background");


        File f = new File("./res/background/background.gif");
        try {
            background = ImageIO.read(f);
            background = uTool.scaledImage(background, GamePanel.width, GamePanel.height);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: could not load file: " + f);
        }

        g.drawImage(background, GamePanel.width, GamePanel.height, null);

//        g.setColor(Color.BLACK);
//        g.fillRect(0,0, GamePanel.width, GamePanel.height);

        BufferedImage logo = null;
        g.drawImage(painttitle(logo),  (GamePanel.width - newWidth )/ 2, 64, null);

    }

    private BufferedImage painttitle(BufferedImage title){
        ScaledImage uTool = new ScaledImage();
        File titlelogo = new File("res/background/logo.png");
        try {
            title = ImageIO.read(titlelogo);
            this.imagewidth = title.getWidth();
            this.imageheight = title.getHeight();
            this.newWidth = GamePanel.width/2;
            title = uTool.scaledImage(title, newWidth, (GamePanel.width/2) * imageheight / imagewidth);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: could not load file: " + titlelogo);
        }
        return title;
    }

}
