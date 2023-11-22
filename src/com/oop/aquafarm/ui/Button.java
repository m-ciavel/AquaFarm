package com.oop.aquafarm.ui;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.util.AABB;
import com.oop.aquafarm.util.KeyHandler;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Button {

    private BufferedImage image;
    private BufferedImage hoverImage;
    private BufferedImage pressedImage;

    private Vector2f iPos;
    private AABB bounds;

    private boolean hovering = false;
    private int hoverSize;

    private ArrayList<ClickedEvent> events;
    private ArrayList<SlotEvent> slotevents;

    private boolean clicked = false;
    private boolean pressed = false;
    private boolean canHover = true;

    private float pressedtime;
    private Slots slot; // temp fix

    // ******************************************** ICON CUSTOM POS *******************************************

//    public Button(BufferedImage icon, BufferedImage image, Vector2f pos, int width, int height, int iconsize) {
//        this.image = createIconButton(icon, image, width + iconsize, height + iconsize, iconsize);
//        this.iPos = pos;
//        this.bounds = new AABB(iPos, this.image.getWidth(), this.image.getHeight());
//
//        events = new ArrayList<ClickedEvent>();
//        slotevents = new ArrayList<SlotEvent>();
//        this.canHover = false;
//    }

//    private BufferedImage createIconButton(BufferedImage icon, BufferedImage image, int width, int height, int iconsize) {
//        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//
//        if (image.getWidth() != width || image.getHeight() != height) {
//            image = resizeImage(image, width, height);
//        }
//
//        if (icon.getWidth() != width - iconsize || icon.getHeight() != height - iconsize) {
//            icon = resizeImage(icon, width - iconsize, height - iconsize);
//        }
//
//        Graphics g = result.getGraphics();
//        g.drawImage(image, 0, 0, width, height, null);
//
//        g.drawImage(icon,
//                image.getWidth() / 2 - icon.getWidth() / 2,
//                image.getHeight() / 2 - icon.getHeight() / 2,
//                icon.getWidth(), icon.getHeight(), null);
//
//        g.dispose();
//
//        return result;
//    }

    // ******************************************** LABEL TTF CUSTOM MIDDLE POS *******************************************

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

    // ******************************************** LABEL PNG GAMEPANEL POS *******************************************
//
//    public Button(BufferedImage icon, BufferedImage image, int iWidth, int iHeight, Vector2f offset) {
//        this(icon, image, iWidth, iHeight);
//
//        iPos = new Vector2f(((float) GamePanel.width / 2 - (float) iWidth / 2 + offset.x) , ((float) GamePanel.height / 2 - (float) iHeight / 2 + offset.y));
//
//        this.bounds = new AABB(iPos, iWidth, iHeight);
//    }
//
//    public Button(BufferedImage image, int iWidth, int iHeight) {
//        this.image = image;
//        this.hoverSize = 20;
//
//        iPos = new Vector2f(((float) GamePanel.width / 2 - (float) iWidth / 2) , ((float) GamePanel.height / 2 - (float) iHeight / 2));
//
//        this.bounds = new AABB(iPos, iWidth, iHeight);
//
//        events = new ArrayList<ClickedEvent>();
//    }
//
//    // ******************************************** LABEL PNG CUSTOM POS *******************************************
//
//    public Button(BufferedImage image, Vector2f iPos, int iWidth, int iHeight) {
//        this(image, iPos, iWidth, iHeight);
//    }
//
//    public Button(BufferedImage image, Vector2f iPos, int iWidth, int iHeight) {
//        this(image, iWidth, iHeight);
//        this.image = image;
//
//        this.iPos = iPos;
//
//        this.bounds = new AABB(iPos, iWidth, iHeight);
//    }


    // ******************************************** END ************************************************************

    public void addHoverImage(BufferedImage image) {
        this.hoverImage = image;
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

    public void addSlotEvent(SlotEvent e) {
        slotevents.add(e);
    }

    public void setSlot(Slots slot) {
        this.slot = slot;
    } // temp fix

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

    public void input(MouseHandler mouseIn, KeyHandler keyh) {
        if (bounds.inside(mouseIn.getX(), mouseIn.getY())) {

            if (canHover && !hovering) {
                hover(hoverSize);
            }
            if (mouseIn.getButton() == 1 && !clicked) {
                clicked = true;
                pressed = true;

                pressedtime = (float) System.nanoTime() / 1000000;

                for (int i = 0; i < events.size(); i++) {
                    events.get(i).action(1);
                }
                if (slotevents == null) return;
                for (int i = 0; i < slotevents.size(); i++) {
                    slotevents.get(i).action(slot);
                }
            } else if (mouseIn.getButton() == -1) {
                clicked = false;
            }
        } else if (canHover && hovering) {
            hover(-hoverSize);
            hovering = false;
        }


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
        void action(int mouseButton);
    }

    public interface SlotEvent {
        void action(Slots slot);
    }

    public static void buy_fish(Graphics2D g) {
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonMargin = 10;
        int totalWidth = 8 * buttonWidth + 7 * buttonMargin;
        int buttonY = GamePanel.height - buttonHeight - 10;

        int startX = (GamePanel.width - totalWidth) / 2;

        // Button 1
        g.setColor(Color.GRAY);
        g.fillRect(startX, buttonY, buttonWidth, buttonHeight);
        g.setColor(Color.BLACK);
        g.drawString("Button 1", startX + 10, buttonY + buttonHeight - 10);

        // Button 2
        g.setColor(Color.GRAY);
        g.fillRect(startX + buttonWidth + buttonMargin, buttonY, buttonWidth, buttonHeight);
        g.setColor(Color.BLACK);
        g.drawString("Button 2", startX + buttonWidth + buttonMargin + 10, buttonY + buttonHeight - 10);

        // Button 3
        g.setColor(Color.GRAY);
        g.fillRect(startX + 2 * (buttonWidth + buttonMargin), buttonY, buttonWidth, buttonHeight);
        g.setColor(Color.BLACK);
        g.drawString("Button 3", startX + 2 * (buttonWidth + buttonMargin) + 10, buttonY + buttonHeight - 10);

        // Button 4
        g.setColor(Color.GRAY);
        g.fillRect(startX + 3 * (buttonWidth + buttonMargin), buttonY, buttonWidth, buttonHeight);
        g.setColor(Color.BLACK);
        g.drawString("Button 4", startX + 3 * (buttonWidth + buttonMargin) + 10, buttonY + buttonHeight - 10);

        // Button 5
        g.setColor(Color.GRAY);
        g.fillRect(startX + 4 * (buttonWidth + buttonMargin), buttonY, buttonWidth, buttonHeight);
        g.setColor(Color.BLACK);
        g.drawString("Button 5", startX + 4 * (buttonWidth + buttonMargin) + 10, buttonY + buttonHeight - 10);

        // Button 6
        g.setColor(Color.GRAY);
        g.fillRect(startX + 5 * (buttonWidth + buttonMargin), buttonY, buttonWidth, buttonHeight);
        g.setColor(Color.BLACK);
        g.drawString("Button 6", startX + 5 * (buttonWidth + buttonMargin) + 10, buttonY + buttonHeight - 10);

        // Button 7
        g.setColor(Color.GRAY);
        g.fillRect(startX + 6 * (buttonWidth + buttonMargin), buttonY, buttonWidth, buttonHeight);
        g.setColor(Color.BLACK);
        g.drawString("Button 7", startX + 6 * (buttonWidth + buttonMargin) + 10, buttonY + buttonHeight - 10);

        // Button 8
        g.setColor(Color.GRAY);
        g.fillRect(startX + 7 * (buttonWidth + buttonMargin), buttonY, buttonWidth, buttonHeight);
        g.setColor(Color.BLACK);
        g.drawString("Button 8", startX + 7 * (buttonWidth + buttonMargin) + 10, buttonY + buttonHeight - 10);
    }



}
