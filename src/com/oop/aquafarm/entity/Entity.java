package com.oop.aquafarm.entity;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.util.MouseHandler;

import java.awt.image.BufferedImage;


public class Entity {

    GamePanel game;
    MouseHandler mouseIn;
    public int x , y, initialX, initialY;
    public int dragX, dragY;
    public BufferedImage hand1, hand2, fish_left, fish_right, food_left, food_right ;
    public boolean clicked;
    public boolean dragged;
    public String direction;
    public String fish_type;
    public String add_fishes;

    public Entity(GamePanel game) {
        this.game = game;
    }


}
