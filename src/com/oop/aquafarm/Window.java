package com.oop.aquafarm;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.SwingUtilities;
import java.awt.image.BufferedImage;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame{

    public Window(){
        setTitle("AquaFarm");
        setLayout(new FlowLayout());
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

