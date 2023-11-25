package com.oop.aquafarm.graphics;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.util.ScaledImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteSheet {
    public static final String TITLE_BUTTONS = "buttons";

    private Sprite SPRITESHEET = null;
    private Sprite[][] spriteArray;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;
    private String file;

//    public SpriteSheet(String file) {
//        this.file = file;
//        w = GamePanel.tilesize;
//        h = GamePanel.tilesize;
//
//        System.out.println("Loading: " + file + "...");
//        SPRITESHEET = new Sprite(loadSprite(file));
//
//        wSprite = SPRITESHEET.image.getWidth() / w;
//        hSprite = SPRITESHEET.image.getHeight() / h;
//        loadSpriteArray();
//    }

//    public SpriteSheet(Sprite sprite, String name, int w, int h) {
//        this.w = w;
//        this.h = h;
//
//        System.out.println("Loading: " + name + "...");
//        SPRITESHEET = sprite;
//
//        wSprite = SPRITESHEET.image.getWidth() / w;
//        hSprite = SPRITESHEET.image.getHeight() / h;
//        loadSpriteArray();
//
//    }

    public SpriteSheet(String file, int w, int h) {
        this.w = w;
        this.h = h;
        this.file = file;

        System.out.println("Loading: " + file + "...");
        SPRITESHEET = new Sprite(loadSprite(file));
        System.out.println(SPRITESHEET);

        wSprite = SPRITESHEET.image.getWidth() / w;
        hSprite = SPRITESHEET.image.getHeight() / h;
        loadSpriteArray();
    }

//    public void setSize(int width, int height) {
//        setWidth(width);
//        setHeight(height);
//    }
//
//    public void setWidth(int i) {
//        w = i;
//        wSprite = SPRITESHEET.image.getWidth() / w;
//    }
//
//    public void setHeight(int i) {
//        h = i;
//        hSprite = SPRITESHEET.image.getHeight() / h;
//    }
//
//    public int getWidth() { return w; }
//    public int getHeight() { return h; }
//    public int getRows() { return hSprite; }
//    public int getCols() { return wSprite; }
//    public int getTotalTiles() { return wSprite * hSprite; }
//    public String getFilename() { return file; }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            file = "./res/"+ file;
            File f = new File(file);
            sprite = ImageIO.read(f);
            System.out.println("Loading: " + file);
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }
        return sprite;
    }

    public void loadSpriteArray() {
        spriteArray = new Sprite[hSprite][wSprite];

        for (int y = 0; y < hSprite; y++) {
            for (int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }

//    public Sprite getSpriteSheet() {
//        return SPRITESHEET;
//    }

    public Sprite getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

//    public Sprite getNewSprite(int x, int y) {
//        return SPRITESHEET.getNewSubimage(x * w, y * h, w, h);
//    }

//    public Sprite getSprite(int x, int y, int w, int h) {
//        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
//    }

    public BufferedImage getSubimage(int x, int y, int w, int h) {
        return SPRITESHEET.image.getSubimage(x, y, w, h);
    }

//    public Sprite[] getSpriteArray(int i) {
//        return spriteArray[i];
//    }

//    public Sprite[][] getSpriteArray2() {
//        return spriteArray;
//    }

    public static BufferedImage setup(String folderName, String imageName) {
        ScaledImage uTool = new ScaledImage();
        BufferedImage simg = null;

        try {
//            simg = ImageIO.read(Objects.requireNonNull(SpriteSheet.class.getResourceAsStream("/res/"+ folderName + "/" + imageName + ".png")));
            File f = new File("./res/"+ folderName + "/" + imageName + ".png");
            simg = ImageIO.read(f);
            simg = uTool.scaledImage(simg, GamePanel.tilesize, GamePanel.tilesize);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: could not load file: " + imageName);
        }
        return simg;
    }

    public static BufferedImage paintbg(BufferedImage background){
        ScaledImage uTool = new ScaledImage();
        File f = new File("res/background/bg.jpg");
        try {
            background = ImageIO.read(f);
            background = uTool.scaledImage(background, GamePanel.width, GamePanel.height);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: could not load file: " + f);
        }
        return background;
    }

    public static class ImagePanel extends JComponent {
        private Image image;
        public ImagePanel(Image image) {
            this.image = image;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }

    }

}
