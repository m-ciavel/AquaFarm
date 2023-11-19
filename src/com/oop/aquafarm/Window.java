package com.oop.aquafarm;


import javax.swing.JFrame;
import java.awt.image.BufferStrategy;
import java.awt.FlowLayout;

public class Window extends JFrame{

    private BufferStrategy bs;
    private GamePanel gp;
    public static Window window;
    public Window(){
        window = this;
        setTitle("AquaFarm");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setContentPane(new GamePanel(1280, 720));
        setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
//        BufferedImage cursorImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
//        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new java.awt.Point(0, 0), "blank cursor");
//        setCursor(blankCursor);
        setResizable(false);
        setVisible(true);
    }

    public void addNotify() {
        super.addNotify();

        createBufferStrategy(2);
        bs = getBufferStrategy();

        gp = new GamePanel(bs, 1280, 720);
        //add(gp);
        setContentPane(gp);

    }
}

