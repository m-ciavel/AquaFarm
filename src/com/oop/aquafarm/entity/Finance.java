package com.oop.aquafarm.entity;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Finance extends Entity {
    public static int money = 100000;


    private int imageX, imageY;
    public Finance(Vector2f origin) {
        super(origin);
        setDefaultValues();
        loadSellImages();

    }
    public void setDefaultValues() {
        clicked = false;
    }

    private void loadSellImages() {
        String foldername = "hand";
        sell = SpriteSheet.setup(foldername, "sell_fish");
    }


    @Override
    public void update(double time) {

    }

    @Override
    public void render(Graphics2D g) {

        BufferedImage image;
        if(!clicked){
            image = sell;
        }else {
            image = sell;
        }
        if (image != null) {
            g.drawImage(image, imageX, imageY, null);
        }

    }

    @Override
    public void input(MouseHandler mouseIn) {
        this.imageX = mouseIn.getX() - GamePanel.tilesize / 2;
        this.imageY = mouseIn.getY() - GamePanel.tilesize / 2;
        if(mouseIn.getButton() == 1){
            clicked = true;
        }else if (mouseIn.getButton() == -1){
            clicked = false;
        }

    }
}
