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

import Entity.Fish;
import Entity.Food;
import Entity.GodHand;
import Fish_Type.Atlantic_Bass;
import Fish_Type.Blue_Gill;
import Fish_Type.Clown_fish;
import Fish_Type.Golden_Tench;
import Fish_Type.Guppy;
import Fish_Type.High_Fin_Banded_Shark;


public class GamePanel extends JPanel implements ActionListener {

    final int originalTileSize = 16;
    final int scale = 3;
    public final int tilesize = originalTileSize * scale;

    int FPS = 480;
    MouseInput mouseIn = new MouseInput(tilesize);
    KeyHandler keyH = new KeyHandler();

    GodHand hands;
    Fish fish;
    Clown_fish clownfish;
    Blue_Gill bluegill;
    Atlantic_Bass atlanticbass;
    Golden_Tench goldentench;
    Guppy guppy;
    High_Fin_Banded_Shark highfinbandedshark;
    Food food;




    public GamePanel() {
        setPreferredSizeToScreenSize();
        setBackground(Color.BLUE);
        initializeGodHand();
        initializeFood();
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

    private void initializeFood() {

        food =new Food(mouseIn, this);
    }

    private void initializeFish() {
        clownfish = new Clown_fish(100, 100, this);
        atlanticbass = new Atlantic_Bass(100, 100, this);
        bluegill = new Blue_Gill(100, 100, this);
        goldentench = new Golden_Tench(100, 100, this);
        guppy = new Guppy(100, 100, this);
        highfinbandedshark = new High_Fin_Banded_Shark(100, 100, this);
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
        food.update();
        clownfish.move();
        bluegill.move();
        atlanticbass.move();
        goldentench.move();
        guppy.move();
        highfinbandedshark.move();


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        hands.draw(g2);
        clownfish.draw(g2);
        bluegill.draw(g2);
        atlanticbass.draw(g2);
        goldentench.draw(g2);
        guppy.draw(g2);
        highfinbandedshark.draw(g2);
        food.draw(g2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
}
