package com.oop.aquafarm.entity;

import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;


public abstract class Entity  {

//    GamePanel game;
    static MouseHandler mouseIn;
    public static Graphics2D g;
    protected Vector2f pos;
    public boolean xCol = false;
    public boolean yCol = false;
//    public int x , y, initialX, initialY;
    public int dragX, dragY;
    public BufferedImage openhand, closehand, fish_left, fish_right, food_left, food_right ;
    public boolean clicked;
    public boolean dragged;
    public String direction;
    public String fish_type;
    public String add_fishes;

    public Entity(Vector2f origin) {
        this.pos = origin;
    }


    public abstract void update(double time);

    public abstract void render(Graphics2D g);
    public abstract void input(MouseHandler mouseIn);


}
