package main;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

public class MouseInput extends MouseMotionAdapter implements MouseListener {
    public int x, y;
    public final int size;
    public boolean mouseClicked;

    public MouseInput(int size) {
        this.size = size;
    }

    public void draw(Graphics2D g2) {
        g2.fillRect(x - size / 2, y - size / 2, size, size);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    	
        mouseClicked = true;
            
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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
        if (mouseClicked) {
            x = e.getX();
            y = e.getY();
        }
    }

}
