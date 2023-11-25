package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.ui.Button;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class QuitState extends GameState {

    private BufferedImage imgButtonExit;
    private BufferedImage imgHoverExit;
    private int btnWidth = 144;
    private int btnHeight = 42;
    private int newbtnWidth = (int) (GamePanel.width/3.5);
    private int newbtnHeight = newbtnWidth * btnHeight/btnWidth;
    private Button btnExit;

    public QuitState(GameStateManager gsm) {
        super(gsm);

        imgButtonExit = GameStateManager.button.getSubimage(btnWidth*2, 0, btnWidth, btnHeight);
        imgHoverExit = GameStateManager.button.getSubimage(btnWidth*2, btnHeight, btnWidth, btnHeight);
        btnExit = new Button(imgButtonExit, new Vector2f(GamePanel.width/2, GamePanel.height/2 + newbtnHeight), newbtnWidth, newbtnHeight);
        btnExit.addHoverImage(btnExit.createButton(imgHoverExit, newbtnWidth, newbtnHeight));
        btnExit.addEvent(e -> {
            System.exit(0);
        });
    }

    @Override
    public void update(double time) {

    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        btnExit.input(mouseIn, keyh);
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage background  = null;
        background = SpriteSheet.paintbg(background);
        g.drawImage(background, 0, 0, null);

        btnExit.render(g);
    }
}
