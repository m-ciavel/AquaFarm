package main;

import javax.swing.JFrame;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("AquaFarm");

        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);

        window.pack();


        window.setLocationRelativeTo(null);


        BufferedImage cursorImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new java.awt.Point(0, 0), "blank cursor");
        window.setCursor(blankCursor);

        window.setVisible(true);
    }
}
