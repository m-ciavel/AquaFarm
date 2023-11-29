package com.oop.aquafarm.states;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.audio.Music;
import com.oop.aquafarm.entity.Hand;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.ui.Button;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.ScaledImage;
import com.oop.aquafarm.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QuitState extends GameState {

    Hand hands;
    private final BufferedImage imgButtonExit;
    private final BufferedImage imgHoverExit;
    private final int btnWidth = 144;
    private final int btnHeight = 42;
    private final int newbtnWidth = (int) (GamePanel.width/3.5);
    private final int newbtnHeight = newbtnWidth * btnHeight/btnWidth;
    private final Button btnExit;

    public QuitState(GameStateManager gsm) {
        super(gsm);
        hands = new Hand(new Vector2f(((float) GamePanel.width /2), (float) GamePanel.height / 2), "cursor");

        Music.stopMusic(Music.getClip());

        imgButtonExit = GameStateManager.button.getSubimage(btnWidth*2, 0, btnWidth, btnHeight);
        imgHoverExit = GameStateManager.button.getSubimage(btnWidth*2, btnHeight, btnWidth, btnHeight);
        btnExit = new Button(imgButtonExit, new Vector2f((float) GamePanel.width /2, (float) GamePanel.height /2 + newbtnHeight), newbtnWidth, newbtnHeight);
        btnExit.addHoverImage(btnExit.createButton(imgHoverExit, newbtnWidth, newbtnHeight));
        btnExit.addEvent(e -> {
            System.exit(0);
        });
    }

    @Override
    public void update(double time) {
        hands.update(time);
    }

    @Override
    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        btnExit.input(mouseIn, keyh);
        hands.input(mouseIn);
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage background  = null;
        background = SpriteSheet.paintbg(background);
        g.drawImage(background, 0, 0, null);

//        BufferedImage octopus = SpriteSheet.setup("background", "octo");
//        octopus = ScaledImage.scaledImage(octopus, (GamePanel.width / 3) * 2, ((GamePanel.width/2) * (octopus.getHeight()/octopus.getWidth())));



        File octo = new File("res/background/octo.png");
        try {
            BufferedImage octopus = ImageIO.read(octo);
            int octwidth = (GamePanel.width / 3) * 2;
            int octheight = (GamePanel.width / 2);
            octopus = ScaledImage.scaledImage(octopus, octwidth, octheight);
            g.drawImage(octopus, GamePanel.width - octopus.getWidth(), GamePanel.height - octopus.getHeight(), null);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: could not load file: " + octo);
        }



        btnExit.render(g);
        hands.render(g);
    }
}
