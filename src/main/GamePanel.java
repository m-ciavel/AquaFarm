package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import javax.swing.JPanel;
import javax.swing.Timer;

import Entity.GodHand;

public class GamePanel extends JPanel implements Runnable{
	
	//Screen Settings
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tilesize = originalTileSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tilesize * maxScreenCol;
	final int screenHeight = tilesize * maxScreenRow;
	
	int FPS = 480;
	
	MouseInput mouseIn = new MouseInput(tilesize);
	GodHand hands = new GodHand(mouseIn , this);
	
	Thread gameThread;
	
	

	public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        
        
        addMouseMotionListener(mouseIn);
        addMouseListener(mouseIn);
        
        Timer gameTimer = new Timer(1000 / FPS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        gameTimer.start();
    }

	
	
    public void update() {
    	
    	hands.update();

    	}


    
    

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        hands.draw(g2);
        g2.dispose();
    }



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}

