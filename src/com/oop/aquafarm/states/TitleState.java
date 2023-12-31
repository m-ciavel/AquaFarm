package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.Window;
import com.oop.aquafarm.audio.Music;
import com.oop.aquafarm.entity.Hand;
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

import static com.oop.aquafarm.audio.Music.playMusic;


public class TitleState extends GameState {

    Hand hands;

    private final BufferedImage imgButtonPlay, imgButtonSettings, imgButtonExit;
    private final BufferedImage imgHoverPlay, imgHoverSettings, imgHoverExit;
    private int newWidth;
    private final int btnWidth = 144;
    private final int btnHeight = 42;
    private int newbtnWidth = (int) (GamePanel.width/3.5);
    private int newbtnHeight = newbtnWidth * btnHeight/btnWidth;
    private final Button btnPlay, btnSettings, btnExit;
    private boolean clicked = false;




    public TitleState(GameStateManager gsm) {

        super(gsm);
        hands = new Hand(new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2), "cursor");


        imgButtonPlay = GameStateManager.button.getSubimage(0, 0, btnWidth, btnHeight);
        imgButtonSettings = GameStateManager.button.getSubimage(btnWidth,0, btnWidth, btnHeight);
        imgButtonExit = GameStateManager.button.getSubimage(btnWidth*2, 0, btnWidth, btnHeight);

        imgHoverPlay = GameStateManager.button.getSubimage(0, btnHeight, btnWidth, btnHeight);
        imgHoverSettings = GameStateManager.button.getSubimage(btnWidth, btnHeight, btnWidth, btnHeight);
        imgHoverExit = GameStateManager.button.getSubimage(btnWidth*2, btnHeight, btnWidth, btnHeight);

        btnPlay = new Button(imgButtonPlay, new Vector2f((float) GamePanel.width /2, (float) GamePanel.height /2 ), newbtnWidth, newbtnHeight);
        btnSettings = new Button(imgButtonSettings, new Vector2f((float) GamePanel.width /2, (float) GamePanel.height /2 + newbtnHeight), newbtnWidth, newbtnHeight);
        btnExit = new Button(imgButtonExit, new Vector2f((float) GamePanel.width /2, (float) GamePanel.height /2 + newbtnHeight * 2), newbtnWidth, newbtnHeight);

        btnPlay.addHoverImage(btnPlay.createButton(imgHoverPlay, newbtnWidth, newbtnHeight));
        btnSettings.addHoverImage(btnSettings.createButton(imgHoverSettings, newbtnWidth, newbtnHeight));
        btnExit.addHoverImage(btnExit.createButton(imgHoverExit, newbtnWidth, newbtnHeight));

        btnPlay.addEvent(e -> {
            GameStateManager.pop(GameStateManager.TITLE);
            if(Login.loggedIn){
                if (gsm.isStateActive(GameStateManager.PLAY)){
                    GameStateManager.pop(GameStateManager.PLAY);
                }else{
                    gsm.add(GameStateManager.PLAY);
                    GameStateManager.pop(GameStateManager.TITLE);
                }
            }else{
                Window.window.setVisible(false);
                new Login(gsm).setVisible(true);
            }

        });

        btnSettings.addEvent(e -> {
            if (gsm.isStateActive(GameStateManager.SETTINGS)){
                GameStateManager.pop(GameStateManager.SETTINGS);
            }else{
                gsm.add(GameStateManager.SETTINGS);
                GameStateManager.pop(GameStateManager.TITLE);
            }
        });

        btnExit.addEvent(e -> {
            if (gsm.isStateActive(GameStateManager.QUIT)){
                GameStateManager.pop(GameStateManager.QUIT);
            }else{
                gsm.add(GameStateManager.QUIT);
                GameStateManager.pop(GameStateManager.TITLE);
            }
        });

        if(Music.isPlaying){
            //do nothing
        }else {
            Music.playMusic(Music.fpath);
        }



    }

    @Override
    public void update(double time) {
        hands.update(time);
//        System.out.println("clicked:" + Button.clicked);
//        System.out.println("pressed:" + Button.pressed);

    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        if(mouseIn.getButton() == 1 && !clicked) {
            clicked = true;
        } else if(mouseIn.getButton() == -1) {
            clicked = false;
        }
        hands.input(mouseIn);
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
        hands.render(g);

    }

    private BufferedImage painttitle(BufferedImage title){
        File titlelogo = new File("res/background/logo.png");
        try {
            title = ImageIO.read(titlelogo);
            this.newWidth = GamePanel.width - (GamePanel.width/5);
            title = ScaledImage.scaledImage(title, newWidth, GamePanel.height/4);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: could not load file: " + titlelogo);
        }
        return title;
    }


}
