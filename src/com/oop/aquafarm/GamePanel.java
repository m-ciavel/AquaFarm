package com.oop.aquafarm;

import com.oop.aquafarm.entity.Entity;
import com.oop.aquafarm.states.GameStateManager;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;

import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;

public class GamePanel extends JPanel implements Runnable{

    public static int width;
    public static int height;
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tilesize = originalTileSize * scale;
    public static int oldFrameCount;
    public static int oldTickCount;
    public static int tickCount;
    public static double FPS = 60;

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    private MouseHandler mouseIn;
    private KeyHandler keyh;
    private Entity entity;

    private GameStateManager gsm;
    public GamePanel(int width, int height){
        GamePanel.width = width;
        GamePanel.height = height;
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify(){
        super.addNotify();

        if(thread == null){
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }


    public void initG(){
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void init(){
        running = true;

        initG();

        mouseIn = new MouseHandler(this);
        keyh = new KeyHandler(this);

        gsm = new GameStateManager(g);

        entity = new Entity(this);
    }

    public void run(){
        init();

        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ; //time before update

        final int MUBR = 3; //must update before render

        double lastUpdateT = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 60;
        final double TTBR = 1000000000 / TARGET_FPS; //total time before render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateT / 1000000000);
        int oldFrameCount = 0;

        tickCount = 0;
        oldTickCount = 0;

        while(running){

            double now = System.nanoTime();
            int updateCount = 0;
            while(((now = lastUpdateT)> TBU) && (updateCount < MUBR)){
                update(now);
                input(mouseIn, keyh);
                lastUpdateT += TBU;
                updateCount++;
                tickCount++;
            }

            if(now - lastUpdateT > TBU){
                lastUpdateT = now - TBU;
            }

            input(mouseIn, keyh);
            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateT / 1000000000);
            if(thisSecond > lastSecondTime){
                if(frameCount != oldFrameCount){
                    System.out.println("NEW SECOND "+ thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                if(tickCount != oldTickCount){
                    System.out.println("NEW SECOND(T) "+ thisSecond + " " + tickCount);
                    oldTickCount = tickCount;
                }
                frameCount = 0;
                tickCount = 0;
                lastSecondTime = thisSecond;
            }

            while(now - lastRenderTime < TTBR && now - lastUpdateT < TBU){
                Thread.yield();

                try {
                    Thread.sleep(1);
                }catch(Exception e){
                    System.out.println("Error: yielding thread");
                }

                now = System.nanoTime();
            }
        }

    }

    public void update(double time){
        gsm.update(time);
    }

    public void input(MouseHandler mouseIn, KeyHandler keyh){
        gsm.input(mouseIn, keyh);
    }

    public void render(){
        if(g != null){
//            g.setColor(new Color(14, 135, 204));
            g.setColor(Color.BLUE);
            g.fillRect(0,0,width, height);
            gsm.render(g);
        }
    }

    public void draw(){

        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0,0, width, height, null);
        g2.dispose();
    }

}
