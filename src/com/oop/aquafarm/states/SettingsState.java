package com.oop.aquafarm.states;

import com.oop.aquafarm.audio.Music;
import com.oop.aquafarm.graphics.CFont;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SettingsState extends GameState {

    private int soundLevel = 50;
    private CFont font;

    public SettingsState(GameStateManager gsm) {
        super(gsm);
        System.out.println("Settings state");

        // Optimize font creation
        font = new CFont(Color.BLACK, "res/font/pixelated.ttf", "pixelated", 70, 10, 150);
    }

    @Override
    public void update(double time) {
        // Update logic, if any
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        if (MouseHandler.mouseClicked) {
            handleMouseClick(mouseIn.getX(), mouseIn.getY());
            MouseHandler.mouseClicked = false;
        }
    }

    private void handleMouseClick(int mouseX, int mouseY) {
        int clickableAreaX = 200;
        int clickableAreaWidth = 900;

        if (mouseX >= clickableAreaX && mouseX <= clickableAreaX + clickableAreaWidth && mouseY >= 100 && mouseY <= 150) {
            int relativeX = mouseX - clickableAreaX;

            soundLevel = (int) ((float) relativeX / clickableAreaWidth * 100);

            soundLevel = Math.max(0, Math.min(100, soundLevel));

            if (soundLevel > 1 && soundLevel < 99) {
                Music.setVolume(null, soundLevel);
            }
        }
    }


    @Override
    public void render(Graphics2D g) {
        // Optimize method calls
        BufferedImage background = SpriteSheet.paintbg(null);
        g.drawImage(background, 0, 0, null);

        drawRectWithBorder(g, 200, 100, 900, 50, 5, new Color(0x3E363f));

        int soundIndicatorWidth = (int) (soundLevel / 100.0 * 900);
        g.setColor(new Color(0x3E363f));
        g.fillRect(200, 100, soundIndicatorWidth, 50);

        // Optimize font rendering
        font.drawString(g, "Music:");
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