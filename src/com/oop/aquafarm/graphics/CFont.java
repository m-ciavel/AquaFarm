package com.oop.aquafarm.graphics;


import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.io.File;

public class CFont {

    private Color color;
    private String file;
    private String font_name;
    private int font_size;
    private int x;
    private int y;

    public  CFont(Color color, String file, String font_name, int font_size, int x, int y){
        this.color = color;
        this.file = file;
        this.font_name = font_name;
        this.font_size = font_size;
        this.x = x;
        this.y = y;
    }

    public void drawString(Graphics2D g, String text){
        g.setColor(color);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font f = null;
        try{
            f = Font.createFont(Font.TRUETYPE_FONT, new File(file));
        }catch (Exception e){
            e.printStackTrace();
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(f);
        Font font = new Font(font_name, Font.PLAIN, font_size);
        g.setFont(font);

        g.drawString(text, x, y);

    }


}
