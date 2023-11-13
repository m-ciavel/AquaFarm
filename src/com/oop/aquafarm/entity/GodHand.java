package com.oop.aquafarm.entity;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.ScaledImage;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GodHand extends Entity{
    public GodHand(MouseHandler mouseIn, GamePanel game){
        super(game);
        this.mouseIn = mouseIn;
        this.game = game;
        setDefaultValues();
        loadGodHandImages();
    }

    public void setDefaultValues() {
        clicked = false;
    }

    private void loadGodHandImages() {
        hand1 = setup("hand.open");
        hand2 = setup("hand.close");
    }

    public BufferedImage setup(String imageName) {
        ScaledImage uTool = new ScaledImage();
        BufferedImage HandImage = null;

        try {

            HandImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/hand/" + imageName + ".png")));
            HandImage = uTool.scaledImage(HandImage, game.tilesize, game.tilesize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HandImage;
    }

    public void update() {
        clicked = mouseIn.mousePressed;
    }

    public void draw(Graphics2D g2) {
        int imageX = mouseIn.getX() - game.tilesize / 2;
        int imageY = mouseIn.getY() - game.tilesize / 2;

        BufferedImage image = clicked ? hand2 : hand1;

        if (image != null) {
            g2.drawImage(image, imageX, imageY, null);
        }
    }

}
