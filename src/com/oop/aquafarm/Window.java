package com.oop.aquafarm;


import com.oop.aquafarm.states.Login;
import com.oop.aquafarm.util.CRUD;
import com.oop.aquafarm.util.dbConnection;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

public class Window extends JFrame implements WindowListener {

    private BufferStrategy bs;
    private GamePanel gp;
    public static Window window;
    dbConnection con1 = new dbConnection();

    public Window(){
        window = this;
        setTitle("AquaFarm");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setContentPane(new GamePanel(1280, 720));
        setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        addWindowListener(this);
        BufferedImage cursorImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new java.awt.Point(0, 0), "blank cursor");
        setCursor(blankCursor);
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            CRUD.logIn(con1, Login.getUname(), false);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
