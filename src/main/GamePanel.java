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

import Entity.Food;
import Entity.Fish;
import Entity.GodHand;

public class GamePanel extends JPanel implements ActionListener {

    final int originalTileSize = 48;
    public int tilesize = originalTileSize;

    private static final int FPS = 480;
    private final MouseInput mouseIn = new MouseInput(tilesize);
    private KeyHandler keyh;

    private GodHand hands;
    public Fish[] fishes;
    private Food food;

    public GamePanel() {
        setPreferredSizeToScreenSize();
        setBackground(Color.BLUE);
        initializeGodHand();
        initializeFood();
        initializeFishes();
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
        food = new Food(mouseIn, this);
    }

    public void initializeFishes() {
        fishes = new Fish[10];
    //    Fish fish = new Fish(100, 100, this);
        //    fish.add_fish();



                //{
                //new Clown_fish(100, 100, this),
                //new Atlantic_Bass(100, 100, this),
                //new  Blue_Gill(100, 100, this),
                //new Golden_Tench(100, 100, this),
                //new Guppy(100, 100, this),
                //new High_Fin_Banded_Shark(100, 100, this)
                //};
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


        for (Fish fish : fishes) {

            if (fish != null) {
                fish.callEatingLogic(food);
                fish.seek_food(food.foodLocations);
            }

        }


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        hands.draw(g2);

        for (Fish fish : fishes)
            if (fish != null) {
            fish.draw(g2);
        }
        food.draw(g2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();

        repaint();
    }
}
