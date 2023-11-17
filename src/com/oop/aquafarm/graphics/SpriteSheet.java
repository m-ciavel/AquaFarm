package com.oop.aquafarm.graphics;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.util.ScaledImage;
import com.oop.aquafarm.util.Vector2f;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SpriteSheet {
    public static final String TITLE_BUTTONS = "buttons";

    private Sprite SPRITESHEET = null;
    private Sprite[][] spriteArray;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;
    private String file;

    public SpriteSheet(String file) {
        this.file = file;
        w = GamePanel.tilesize;
        h = GamePanel.tilesize;

        System.out.println("Loading: " + file + "...");
        SPRITESHEET = new Sprite(loadSprite(file));

        wSprite = SPRITESHEET.image.getWidth() / w;
        hSprite = SPRITESHEET.image.getHeight() / h;
        loadSpriteArray();
    }

    public SpriteSheet(Sprite sprite, String name, int w, int h) {
        this.w = w;
        this.h = h;

        System.out.println("Loading: " + name + "...");
        SPRITESHEET = sprite;

        wSprite = SPRITESHEET.image.getWidth() / w;
        hSprite = SPRITESHEET.image.getHeight() / h;
        loadSpriteArray();

    }

    public SpriteSheet(String file, int w, int h) {
        this.w = w;
        this.h = h;
        this.file = file;

        System.out.println("Loading: " + file + "...");
        SPRITESHEET = new Sprite(loadSprite(file));

        wSprite = SPRITESHEET.image.getWidth() / w;
        hSprite = SPRITESHEET.image.getHeight() / h;
        loadSpriteArray();
    }

    private BufferedImage loadSprite(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
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

    public Sprite getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getSubimage(int x, int y, int w, int h) {
        return SPRITESHEET.image.getSubimage(x, y, w, h);
    }

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




}
