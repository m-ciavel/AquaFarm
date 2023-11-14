package com.oop.aquafarm.entity;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.Sprite;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;;

public class Hand extends Entity{


    public Hand(Sprite sprite, Vector2f origin) {
//        super(sprite, origin);

        setDefaultValues();
        loadGodHandImages();
    }
    public void setDefaultValues() {
        clicked = false;
    }
    private void loadGodHandImages() {
        String foldername = "hand";
        openhand = SpriteSheet.setup(foldername, "hand.open");
        closehand = SpriteSheet.setup(foldername,"hand.close");
    }
    public void update(double time){
        clicked = mouseIn.mousePressed;
    }
    @Override
    public void render(Graphics2D g) {
        int imageX = mouseIn.getX() - GamePanel.tilesize / 2;
        int imageY = mouseIn.getY() - GamePanel.tilesize / 2;

        BufferedImage image = clicked ? closehand : openhand;

        if (image != null) {
            g.drawImage(image, imageX, imageY, null);
        }
    }

    public void input(MouseHandler mouseIn){
        if(clicked){

            int imageX = mouseIn.getX() - GamePanel.tilesize / 2;
            int imageY = mouseIn.getY() - GamePanel.tilesize / 2;

            BufferedImage image = clicked ? closehand : openhand;

            if (image != null) {
                g.drawImage(image, imageX, imageY, null);
            }
        }
    }
}
