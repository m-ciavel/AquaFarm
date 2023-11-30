package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.ui.Button;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PauseState extends GameState {

    private BufferedImage imgButtonResume, imgButtonMenu;
    private BufferedImage imgHoverResume, imgHoverMenu;
    private final Button btnResume, btnMenu;
    private final int btnWidth = 144;
    private final int btnHeight = 42;
    private int newbtnWidth = GamePanel.width/5;
    private int newbtnHeight = newbtnWidth * btnHeight/btnWidth;

    public PauseState(GameStateManager gsm) {
        super(gsm);

        imgButtonMenu = GameStateManager.button.getSubimage(btnWidth*5, 0, btnWidth, btnHeight);
        imgButtonResume = GameStateManager.button.getSubimage(btnWidth*6, 0, btnWidth, btnHeight);

        imgHoverMenu = GameStateManager.button.getSubimage(btnWidth*5, btnHeight, btnWidth, btnHeight);
        imgHoverResume = GameStateManager.button.getSubimage(btnWidth*6, btnHeight, btnWidth, btnHeight);

        btnMenu = new Button(imgButtonMenu, new Vector2f((float) (GamePanel.width - newbtnWidth)/2 -5, (float) GamePanel.height /2 ), newbtnWidth, newbtnHeight);
        btnResume = new Button(imgButtonResume, new Vector2f((float)(GamePanel.width + newbtnWidth) /2 + 5, (float) GamePanel.height /2), newbtnWidth, newbtnHeight);

        btnMenu.addHoverImage(btnMenu.createButton(imgHoverMenu, newbtnWidth, newbtnHeight));
        btnResume.addHoverImage(btnResume.createButton(imgHoverResume, newbtnWidth, newbtnHeight));

        btnMenu.addEvent(e -> {
            GameStateManager.pop(GameStateManager.PAUSE);
            if (gsm.isStateActive(GameStateManager.TITLE)){
                GameStateManager.pop(GameStateManager.TITLE);
            }else{
                gsm.add(GameStateManager.TITLE);
                GameStateManager.pop(GameStateManager.PLAY);
            }
        });

        btnResume.addEvent(e -> {
            GameStateManager.pop(GameStateManager.PAUSE);
        });

    }

    @Override
    public void update(double time) {

    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {

        btnMenu.input(mouseIn, keyh);
        btnResume.input(mouseIn, keyh);
    }

    @Override
    public void render(Graphics2D g) {
        btnMenu.render(g);
        btnResume.render(g);
    }
}
