package com.oop.aquafarm.ui;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.audio.Music;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.AABB;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Button {
    public static Color borderdarkred = new Color(139, 0, 0);

    private BufferedImage image;
    private BufferedImage hoverImage;
    private BufferedImage pressedImage;

    private Vector2f iPos;
    private AABB bounds;

    private boolean hovering = false;
    private int hoverSize;

    private final ArrayList<ClickedEvent> events;

    public static boolean clicked = false;
    public static boolean pressed = false;
    private boolean canHover = true;

    private boolean hoverSoundPlayed = false;

    private float pressedtime;


    public Button(BufferedImage image, Vector2f pos, int buttonSize) {
        this(image, pos, buttonSize, -1);
    }

    public Button(BufferedImage image, Vector2f pos, int buttonWidth, int buttonHeight) {

        this.image = createButton(image, buttonWidth, buttonHeight);
        this.iPos = new Vector2f(pos.x - (float) this.image.getWidth() / 2, pos.y - (float) this.image.getHeight() / 2);
        this.bounds = new AABB(iPos, this.image.getWidth(), this.image.getHeight());


        events = new ArrayList<ClickedEvent>();
        this.canHover = false;
    }

    public BufferedImage createButton(BufferedImage image, int width, int height) {
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        if (image.getWidth() != width || image.getHeight() != height) {
            image = resizeImage(image, width, height);
        }

        Graphics g = result.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);

        g.dispose();

        return result;
    }

    private BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image temp = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();

        g.drawImage(temp, 0, 0, null);
        g.dispose();

        return result;
    }


    public void addHoverImage(BufferedImage hoverImage) {
        this.hoverImage = hoverImage;
        this.canHover = true;
    }


    public void addPressedImage(BufferedImage image) {
        this.pressedImage = image;
    }

    public void setHoverSize(int size) {
        this.hoverSize = size;
    }

    public boolean getHovering() {
        return hovering;
    }

    public void setHover(boolean b) {
        this.canHover = b;
    }

    public void addEvent(ClickedEvent e) {
        events.add(e);
    }


    public int getWidth() {
        return (int) bounds.getWidth();
    }

    public int getHeight() {
        return (int) bounds.getHeight();
    }

    public Vector2f getPos() {
        return bounds.getPos();
    }

    public void update(double time) {
        if (pressedImage != null && pressed && pressedtime + 300 < time / 1000000) {
            pressed = false;
        }
    }

    private void hover(int value) {
        if (hoverImage == null) {
            iPos.x -= value / 2;
            iPos.y -= value / 2;
            float iWidth = value + bounds.getWidth();
            float iHeight = value + bounds.getHeight();
            this.bounds = new AABB(iPos, (int) iWidth, (int) iHeight);

//            lbPos.x -= value / 2;
//            lbPos.y -= value / 2;
//            lbWidth += value / 3;
//            lbHeight += value / 3;

        }

        hovering = true;
    }



    public void render(Graphics2D g) {
        if (canHover && hoverImage != null && hovering) {
            g.drawImage(hoverImage, (int) iPos.x, (int) iPos.y, (int) bounds.getWidth(), (int) bounds.getHeight(), null);
        } else if (pressedImage != null && pressed) {
            g.drawImage(pressedImage, (int) iPos.x, (int) iPos.y, (int) bounds.getWidth(), (int) bounds.getHeight(), null);
        } else {
            g.drawImage(image, (int) iPos.x, (int) iPos.y, (int) bounds.getWidth(), (int) bounds.getHeight(), null);
        }
    }




    public interface ClickedEvent {
        void action(int mouseButton) throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException;
    }


    public void input(MouseHandler mouseIn, KeyHandler keyh) {


        if (bounds.inside(mouseIn.getX(), mouseIn.getY())) {

            if (canHover && !hovering) {
                hover(hoverSize);
                Music.playHoverSound();
            }
            if (mouseIn.getButton() == 1 && !clicked) {
                clicked = true;
                pressed = true;

                pressedtime = (float) System.nanoTime() / 1000000;

                for (int i = 0; i < events.size(); i++) {
                    try {
                        events.get(i).action(1);
                    } catch (SQLException | UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            } else if (mouseIn.getButton() == -1) {
                clicked = false;
                pressed = false;
            }
        } else if (canHover && hovering) {
            hover(-hoverSize);
            hovering = false;
        }



    }





}
