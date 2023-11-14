package com.oop.aquafarm.entity;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.Animation;
import com.oop.aquafarm.graphics.Sprite;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;


public abstract class Entity  {

    GamePanel game;
    public static MouseHandler mouseIn;
    public static Graphics2D g;
    public boolean xCol = false;
    public boolean yCol = false;
    public int x , y, initialX, initialY;
    public int dragX, dragY;
    public BufferedImage openhand, closehand, fish_left, fish_right, food_left, food_right ;
    public boolean clicked;
    public boolean dragged;
    public String direction;
    public String fish_type;
    public String add_fishes;

    public Entity() {
        super();

    }

    public void animate(){
        if(clicked){

            int imageX = mouseIn.getX() - GamePanel.tilesize / 2;
            int imageY = mouseIn.getY() - GamePanel.tilesize / 2;

            BufferedImage image = clicked ? closehand : openhand;

            if (image != null) {
                g.drawImage(image, imageX, imageY, null);
            }
        }
    }
    public void update(double time) {
//        animate();
    }

    public abstract void render(Graphics2D g);
    public void input(MouseHandler mouseIn, KeyHandler keyh){};


}
