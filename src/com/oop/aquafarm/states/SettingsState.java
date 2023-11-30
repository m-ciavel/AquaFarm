package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.Window;
import com.oop.aquafarm.audio.Music;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.entity.Hand;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.ui.Button;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SettingsState extends GameState {

    private int soundLevel = 50;
    private CFont font, notif;
    private BufferedImage imgButtonSecurity, imgButtonBack;
    private BufferedImage imgHoverSecurity, imgHoverBack;
    private final Button btnSecurity, btnBack;
    private final int btnWidth = 144;
    private final int btnHeight = 42;
    private int newbtnWidth = GamePanel.width/4;
    private int newbtnHeight = newbtnWidth * btnHeight/btnWidth;

    Hand hands;


    public SettingsState(GameStateManager gsm) {
        super(gsm);
        hands = new Hand(new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2), "cursor");

        // Optimize font creation
        font = new CFont(Color.BLACK, "res/font/pixelated.ttf", "pixelated", 70, 200, 150);
        notif = new CFont(Color.red, "res/font/pixelated.ttf", "pixelated", 50, 400 + newbtnWidth, GamePanel.height - newbtnHeight - 175);

        imgButtonSecurity = GameStateManager.button.getSubimage(btnWidth*7, 0, btnWidth, btnHeight);
        imgButtonBack = GameStateManager.arrowbutton.getSubimage(0, 0, 50, 50);

        imgHoverSecurity = GameStateManager.button.getSubimage(btnWidth*7, btnHeight, btnWidth, btnHeight);
        imgHoverBack = GameStateManager.arrowbutton.getSubimage(0, 0, 50, 50);

        btnSecurity = new Button(imgButtonSecurity, new Vector2f(350, (float) GamePanel.height - newbtnHeight - 200 ), newbtnWidth, newbtnHeight);
        btnBack = new Button(imgButtonBack, new Vector2f(GamePanel.width - 50, GamePanel.height - 50), 50, 50);

        btnSecurity.addHoverImage(btnSecurity.createButton(imgHoverSecurity, newbtnWidth, newbtnHeight));
        btnBack.addHoverImage(btnBack.createButton(imgHoverBack, newbtnWidth, newbtnHeight));

        btnSecurity.addEvent(e -> {

            if(Login.loggedIn){
                GameStateManager.pop(GameStateManager.SETTINGS);
                Window.window.setVisible(false);
                new Account(gsm).setVisible(true);
            }
        });

        btnBack.addEvent(e -> {
            if (gsm.isStateActive(GameStateManager.TITLE)){
                GameStateManager.pop(GameStateManager.TITLE);
            }else{
                gsm.add(GameStateManager.TITLE);
                GameStateManager.pop(GameStateManager.SETTINGS);
            }
        });


    }

    @Override
    public void update(double time) {
        hands.update(time);
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        if (MouseHandler.mouseClicked) {
            handleMouseClick(mouseIn.getX(), mouseIn.getY());
            MouseHandler.mouseClicked = false;
        }

        btnSecurity.input(mouseIn,keyh);
        btnBack.input(mouseIn, keyh);

        hands.input(mouseIn);
    }


    @Override
    public void render(Graphics2D g) {
        // Optimize method calls
        BufferedImage background = SpriteSheet.paintbg(null);
        g.drawImage(background, 0, 0, null);

        int rectx = 200;
        int recty = 200;

        drawRectWithBorder(g, rectx, recty, 900, 50, 5, new Color(0x3E363f));

        int soundIndicatorWidth = (int) (soundLevel / 100.0 * 900);
        g.setColor(new Color(0x3E363f));
        g.fillRect(rectx, recty, soundIndicatorWidth, 50);

        // Optimize font rendering
        font.drawString(g, "Music:");

        if(!Login.loggedIn && btnSecurity.getHovering()){
            notif.drawString(g, "Please login first");
        }

        btnBack.render(g);
        btnSecurity.render(g);

        hands.render(g);
    }


    private void handleMouseClick(int mouseX, int mouseY) {
        int clickableAreaX = 200;
        int clickableAreaWidth = 900;

        if (mouseX >= clickableAreaX && mouseX <= clickableAreaX + clickableAreaWidth && mouseY >= 100 && mouseY <= 250) {
            int relativeX = mouseX - clickableAreaX;

            soundLevel = (int) ((float) relativeX / clickableAreaWidth * 100);

            soundLevel = Math.max(0, Math.min(100, soundLevel));

            if (soundLevel > 1 && soundLevel < 99) {
                Music.setVolume(null, soundLevel);
            }
        }
    }

    private void drawRectWithBorder(Graphics2D g, int x, int y, int width, int height, int borderWidth, Color borderColor) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g.fillRect(x, y, width, height);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        // Draw the border
        g.setColor(borderColor);
        for (int i = 0; i < borderWidth; i++) {
            g.drawRect(x - i, y - i, width + 2 * i, height + 2 * i);
        }
    }
}