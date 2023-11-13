package com.oop.aquafarm.util;

import com.oop.aquafarm.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

public class MouseHandler extends MouseMotionAdapter implements MouseListener, MouseMotionListener {

    private static int mouseX;
    private static int mouseY;
    private static int mouseB;
    public static boolean mousePressed;
    public static boolean mouseClicked;
    public static boolean mouseReleased;
    public static boolean mouseDragged;

    private int offsetX;
    private int offsetY;

    private boolean isSpeedIncreased;

    public MouseHandler(GamePanel game){
        game.addMouseListener(this);
    }

    public int getX(){
        return mouseX;
    }

    public int getY(){
        return mouseY;
    }

    public int getButton(){
        return mouseB;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseB = e.getButton();
        mousePressed = true;
        mouseX = e.getX();
        mouseY = e.getY();

        // Check if the mouse click is on the fish

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseB = -1;
        mouseReleased = true;
        mousePressed = false;
        mouseClicked = false;


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        if(e.getButton()){
//            mouseX = e.getX();
//            mouseY = e.getY();
//        }
        mouseDragged = true;

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
