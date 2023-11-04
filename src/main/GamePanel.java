package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.Fish; // Import the Fish class
import Entity.GodHand;
import Fish_Type.Clown_fish;

public class GamePanel extends JPanel implements ActionListener {

    final int originalTileSize = 16;
    final int scale = 3;
    public final int tilesize = originalTileSize * scale;

    int FPS = 480;
    MouseInput mouseIn = new MouseInput(tilesize);
    KeyHandler keyH = new KeyHandler();

    GodHand hands;
    Fish fish;
    Clown_fish clownfish;// Declare a Fish instance

    public GamePanel() {
        setPreferredSizeToScreenSize();
        setBackground(Color.BLUE);
        initializeGodHand();
        initializeFish(); 
        addMouseListeners();
        startGameTimer();
    }

    private void setPreferredSizeToScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(screenSize);
    }

    private void initializeGodHand() {
        hands = new GodHand(mouseIn, this);
    }

    private void initializeFish() {
    	clownfish = new Clown_fish(100, 100, this);
    }

    private void addMouseListeners() {
        addMouseMotionListener(mouseIn);
        addMouseListener(mouseIn);
    }

    private void startGameTimer() {
        Timer gameTimer = new Timer(1000 / FPS, this);
        gameTimer.start();
    }

    public void update() {
        hands.update();
        clownfish.move();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        hands.draw(g2);
        clownfish.draw(g2); // Display the fish
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
}
