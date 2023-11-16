package com.oop.aquafarm.ui;


import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.states.GameStateManager;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TitleButton {
    public static final int BTN_WIDTH_DEFAULT = 144;
    public static final int BTN_HEIGHT_DEFAULT = 42;
    public static final int BTN_WIDTH = (int) (BTN_WIDTH_DEFAULT * GamePanel.scale);
    public static final int BTN_HEIGHT = (int) (BTN_HEIGHT_DEFAULT * GamePanel.scale);

    private Vector2f pos;
    private float w;
    private float h;

    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = BTN_WIDTH / 2;
    private GameStateManager gsm;
    private int state;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;



    public TitleButton(int xPos, int yPos, int rowIndex, int state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
//        this.gsm = gsm;
        this.state = state;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, BTN_WIDTH, BTN_HEIGHT);

    }

    private void loadImgs(){
        imgs = new BufferedImage[3];
        BufferedImage temp = SpriteSheet.setup("menubutton", SpriteSheet.TITLE_BUTTONS);
        for (int i = 0; i < imgs.length; i++){
            imgs[i] = temp.getSubimage(i * BTN_WIDTH_DEFAULT, rowIndex * BTN_HEIGHT_DEFAULT, BTN_WIDTH_DEFAULT, BTN_HEIGHT_DEFAULT);
        }
    }

    public void render(Graphics2D g){
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, BTN_WIDTH, BTN_HEIGHT, null);
    }


    public void update(double time){
        index = 0;
        if(mouseOver){
            index = 1;
        } else if(mousePressed){
            index = 2;
        }
    }

    public void input(MouseHandler mouseIn){
//        if(bounds.inside(mouseIn.getX(), mouseIn.getY())){
//
//        }

        if(mouseIn.getButton() == 1){
            mousePressed = true;
        }else if (mouseIn.getButton() == -1){
            mousePressed = false;
        }
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGamestate() {

    }


    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }

//    public boolean inside(int xp, int yp) {
//        if(xp == -1 || yp == - 1) return false;
//
//        int wTemp = (int) this.w;
//        int hTemp = (int) this.h;
//        int x = (int) this.pos.x;
//        int y = (int) this.pos.y;
//
//        if(xp < x || yp < y) {
//            return false;
//        }
//
//        wTemp += x;
//        hTemp += y;
//        return ((wTemp < x || wTemp > xp) && (hTemp < y || hTemp > yp));
//    }

}
