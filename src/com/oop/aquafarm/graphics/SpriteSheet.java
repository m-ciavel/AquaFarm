package com.oop.aquafarm.graphics;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.util.ScaledImage;
import com.oop.aquafarm.util.Vector2f;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SpriteSheet {

    private Sprite SPRITESHEET = null;
    private Sprite[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    private int wSprite;
    private int hSprite;
    private String foldername, imagename;

    public SpriteSheet(String foldername, String imagename) {
        this.foldername = foldername;
        this.imagename = imagename;
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading: " + imagename + "...");
        SPRITESHEET = new Sprite(setup(foldername, imagename));

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

    public SpriteSheet(String foldername, String imagename, int w, int h) {
        this.w = w;
        this.h = h;
        this.foldername = foldername;
        this.imagename = imagename;

        System.out.println("Loading: " + foldername + "/" +imagename + "...");
        SPRITESHEET = new Sprite(setup(foldername, imagename));

        wSprite = SPRITESHEET.image.getWidth() / w;
        hSprite = SPRITESHEET.image.getHeight() / h;
        loadSpriteArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        w = i;
        wSprite = SPRITESHEET.image.getWidth() / w;
    }

    public void setHeight(int i) {
        h = i;
        hSprite = SPRITESHEET.image.getHeight() / h;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public String getImagename() {
        return imagename;
    }

    public static BufferedImage setup(String folderName, String imageName) {
        ScaledImage uTool = new ScaledImage();
        BufferedImage simg = null;

        try {

            simg = ImageIO.read(Objects.requireNonNull(SpriteSheet.class.getResourceAsStream("/"+ folderName + "/" + imageName + ".png")));
            simg = uTool.scaledImage(simg, GamePanel.tilesize, GamePanel.tilesize);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: could not load file: " + imageName);
        }
        return simg;
    }

    public void loadSpriteArray() {
        spriteArray = new Sprite[hSprite][wSprite];

        for (int y = 0; y < hSprite; y++) {
            for (int x = 0; x < wSprite; x++) {
                spriteArray[y][x] = getSprite(x, y);
            }
        }
    }

    public Sprite getSpriteSheet() {
        return SPRITESHEET;
    }

    public Sprite getSprite(int x, int y) {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

    public Sprite getNewSprite(int x, int y) {
        return SPRITESHEET.getNewSubimage(x * w, y * h, w, h);
    }

    public Sprite getSprite(int x, int y, int w, int h) {
        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
    }

    public BufferedImage getSubimage(int x, int y, int w, int h) {
        return SPRITESHEET.image.getSubimage(x, y, w, h);
    }

    public Sprite[] getSpriteArray(int i) {
        return spriteArray[i];
    }

    public Sprite[][] getSpriteArray2() {
        return spriteArray;
    }

    public static void drawArray(Graphics2D g, ArrayList<Sprite> img, Vector2f pos, int width, int height, int xOffset, int yOffset) {
        float x = pos.x;
        float y = pos.y;

        for (int i = 0; i < img.size(); i++) {
            if (img.get(i) != null) {
                g.drawImage(img.get(i).image, (int) x, (int) y, width, height, null);
            }

            x += xOffset;
            y += yOffset;
        }
    }


}
