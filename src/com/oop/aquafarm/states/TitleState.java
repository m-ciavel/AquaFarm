package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.Window;
import com.oop.aquafarm.graphics.SpriteSheet;
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

    private BufferedImage imgButtonPlay;
    private BufferedImage imgButtonSettings;
    private BufferedImage imgButtonExit;
    private BufferedImage imgHoverPlay;
    private BufferedImage imgHoverSettings;
    private BufferedImage imgHoverExit;
    private int newWidth;
    private int btnWidth = 144;
    private int btnHeight = 42;
    private int newbtnWidth = (int) (GamePanel.width/3.5);
    private int newbtnHeight = newbtnWidth * btnHeight/btnWidth;
    private Button btnPlay;
    private Button btnSettings;
    private Button btnExit;


    public TitleState(GameStateManager gsm) {
        super(gsm);

        imgButtonPlay = GameStateManager.button.getSubimage(0, 0, btnWidth, btnHeight);
        imgButtonSettings = GameStateManager.button.getSubimage(btnWidth,0, btnWidth, btnHeight);
        imgButtonExit = GameStateManager.button.getSubimage(btnWidth*2, 0, btnWidth, btnHeight);

        imgHoverPlay = GameStateManager.button.getSubimage(0, btnHeight, btnWidth, btnHeight);
        imgHoverSettings = GameStateManager.button.getSubimage(btnWidth, btnHeight, btnWidth, btnHeight);
        imgHoverExit = GameStateManager.button.getSubimage(btnWidth*2, btnHeight, btnWidth, btnHeight);

        btnPlay = new Button(imgButtonPlay, new Vector2f(GamePanel.width/2, GamePanel.height/2 ), newbtnWidth, newbtnHeight);
        btnSettings = new Button(imgButtonSettings, new Vector2f(GamePanel.width/2, GamePanel.height/2 + newbtnHeight), newbtnWidth, newbtnHeight);
        btnExit = new Button(imgButtonExit, new Vector2f(GamePanel.width/2, GamePanel.height/2 + newbtnHeight * 2), newbtnWidth, newbtnHeight);

        btnPlay.addHoverImage(btnPlay.createButton(imgHoverPlay, newbtnWidth, newbtnHeight));
        btnSettings.addHoverImage(btnSettings.createButton(imgHoverSettings, newbtnWidth, newbtnHeight));
        btnExit.addHoverImage(btnSettings.createButton(imgHoverExit, newbtnWidth, newbtnHeight));

        btnPlay.addEvent(e -> {
//                gsm.add(GameStateManager.ACCOUNT);
            new Signup().setVisible(true);
//            gsm.pop(GameStateManager.TITLE);
            Window.window.setVisible(false);
        });

        btnSettings.addEvent(e -> {
            gsm.add(GameStateManager.SETTINGS);
            gsm.pop(GameStateManager.TITLE);
        });

        btnExit.addEvent(e -> {
            System.exit(0);
        });
    }

    @Override
    public void update(double time) {
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {

        btnPlay.input(mouseIn, keyh);
        btnSettings.input(mouseIn, keyh);
        btnExit.input(mouseIn, keyh);

    }

    @Override
    public void render(Graphics2D g) {

        BufferedImage background  = null;
        background = SpriteSheet.paintbg(background);
        g.drawImage(background, 0, 0, null);

        BufferedImage logo = null;
        g.drawImage(painttitle(logo),  (GamePanel.width - newWidth )/ 2, 115, null);

        btnPlay.render(g);
        btnSettings.render(g);
        btnExit.render(g);

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


}
