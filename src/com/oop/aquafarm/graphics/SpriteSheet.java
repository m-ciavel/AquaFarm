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
