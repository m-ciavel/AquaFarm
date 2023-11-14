package com.oop.aquafarm.entity;

/*

basis of src/com/oop/aquafarm/entity/Hand.java

*/

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
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
        openhand = SpriteSheet.setup("hand.open");
        closehand = SpriteSheet.setup("hand.close");
    }


    public void update() {
        clicked = mouseIn.mousePressed;
    }

    public void render(Graphics2D g2) {
        int imageX = mouseIn.getX() - game.tilesize / 2;
        int imageY = mouseIn.getY() - game.tilesize / 2;

        BufferedImage image = clicked ? closehand : openhand;

        if (image != null) {
            g2.drawImage(image, imageX, imageY, null);
        }
    }

}
