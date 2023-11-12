package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class TitleState extends GameState {

    public TitleState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {
        System.out.println("titlestate");
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        keyh.p.tick();

        if(keyh.p.clicked){
            gsm.pop(GameStateManager.PLAY);
        }else {
            gsm.add(GameStateManager.TITLE);
        }
    }

    @Override
    public void render(Graphics2D g) {
//        BufferedImage backgroundgif = new BufferedImage(10,10, BufferedImage.TYPE_INT_ARGB);
//        backgroundgif.createGraphics();
//        g.setColor(Color.YELLOW);/*from   ww  w . j a v a  2s.com*/
//        g.fillRect(0, 0, GamePanel.width, GamePanel.height);
//        g.setColor(Color.BLACK);
//        g.drawRect(1, 1, 6, 6);
        BufferedImage backgroundgif = null;
        try {
            backgroundgif = ImageIO.read(new File( "res/background/background.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(backgroundgif,0,0,null);



        CFont fps = new CFont(Color.WHITE, "res/font/pixelated.ttf", "pixelated", 24, GamePanel.width / 2, 32);
        fps.drawString(g, "title");
    }
}
