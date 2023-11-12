package com.oop.aquafarm.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class load {

    public static final String PLAY_BUTTON = "playbub.png";

    public static BufferedImage getButtonAtlas(String filename){
        BufferedImage img = null;
        InputStream is = load.class.getResourceAsStream("res/" + filename);
        try{
            img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }

}
