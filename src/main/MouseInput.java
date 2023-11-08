package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

public class MouseInput extends MouseMotionAdapter implements MouseListener {
    public int x, y ,getmouseX, getmouseY;
    public final int size;
    public boolean mousePressed;
    public boolean mouseClicked;

    public MouseInput(int size) {
        this.size = size;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;

    }


    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
        getmouseX = e.getX();
        getmouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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
        if (mousePressed) {
            x = e.getX();
            y = e.getY();
        }
    }


}
