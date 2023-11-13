package com.oop.aquafarm;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Window extends JFrame{

    public Window(){
        setTitle("AquaFarm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280, 720));
        pack();
        setLocationRelativeTo(null);
//        BufferedImage cursorImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
//        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new java.awt.Point(0, 0), "blank cursor");
//        setCursor(blankCursor);
        setVisible(true);
    }
}
