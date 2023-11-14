package com.oop.aquafarm.entity;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;;

public class Hand extends Entity{

    private int imageX, imageY;
    public Hand(Vector2f origin) {
        super(origin);
        setDefaultValues();
        loadGodHandImages();

    }
    public void setDefaultValues() {
        clicked = false;
    }
    private void loadGodHandImages() {
        String foldername = "hand";
        openhand = SpriteSheet.setup(foldername, foldername + ".open");
        closehand = SpriteSheet.setup(foldername,foldername + ".close");
    }
    public void update(double time){
    }
    @Override
    public void render(Graphics2D g) {
        BufferedImage image;
        if(!clicked){
            image = openhand;
        }else {
            image = closehand;
        }
        if (image != null) {
            g.drawImage(image, imageX, imageY, null);
        }
    }

    @Override
    public void input(MouseHandler mouseIn) {
        if(mouseIn.getButton() == 1){
            this.imageX = mouseIn.getX() - GamePanel.tilesize / 2;
            this.imageY = mouseIn.getY() - GamePanel.tilesize / 2;
            clicked = true;
        }else if (mouseIn.getButton() == -1){
            clicked = false;
        }


    }


}
