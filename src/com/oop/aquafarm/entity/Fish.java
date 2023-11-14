package com.oop.aquafarm.entity;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.ScaledImage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Fish extends Entity {
    private final Fish_movement fishMovement;
    private int offsetX;
    private int offsetY;
    public boolean isDragging;
    private boolean isMoving;
    private boolean isSpeedIncreased;


    private final int originalSpeed;
    private final int increasedSpeed;
    private final Timer speedIncreaseTimer;

    public Fish(int initialX, int initialY, GamePanel game, MouseHandler mouseIn) {
        super(game);

        fishMovement = new Fish_movement(1920, 1080);
        fishMovement.setInitialPosition(initialX, initialY);
        this.game = game;
        this.mouseIn = mouseIn;
        getFishImages(); // Load fish images

        // Initialize dragging variables
        offsetX = 0;
        offsetY = 0;
        isDragging = false;
        isMoving = true;
        isSpeedIncreased = false;
        originalSpeed = 2; // Set the original speed
        increasedSpeed = 15; // Set the increased speed
        speedIncreaseTimer = new Timer();

        // Add a mouse listener to the game panel for dragging
//        game.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int mouseX = e.getX();
//                int mouseY = e.getY();
//
//                // Check if the mouse click is on the fish
//                if (mouseX >= fishMovement.getFishX() && mouseX <= fishMovement.getFishX() + fish_left.getWidth() &&
//                        mouseY >= fishMovement.getFishY() && mouseY <= fishMovement.getFishY() + fish_left.getHeight()) {
//                    offsetX = mouseX - fishMovement.getFishX();
//                    offsetY = mouseY - fishMovement.getFishY();
//                    isDragging = true;
//                    isMoving = false; // Pause fish movement while dragging
//                }
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                if (isDragging) {
//                    isDragging = false;
//                    isMoving = true; // Resume fish movement when released
//
//                    if (!isSpeedIncreased) {
//                        isSpeedIncreased = true;
//                        fishMovement.setSpeed(increasedSpeed);
//
//                        // Schedule a timer to revert the speed back to the original value after 3 seconds
//                        speedIncreaseTimer.schedule(new TimerTask() {
//                            @Override
//                            public void run() {
//                                fishMovement.setSpeed(originalSpeed);
//                                isSpeedIncreased = false;
//                            }
//                        }, 3000);
//                    }
//                }
//            }
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                if (isDragging) {
//                    int mouseX = e.getX();
//                    int mouseY = e.getY();
//                    fishMovement.setInitialPosition(mouseX - offsetX, mouseY - offsetY);
//                }
//            }
//
//        });

        int mouseX = mouseIn.getX();
        int mouseY = mouseIn.getY();
        // Check if the mouse click is on the fish
        if(MouseHandler.mousePressed){
            if (mouseX >= fishMovement.getFishX() && mouseX <= fishMovement.getFishX() + fish_left.getWidth() &&
                    mouseY >= fishMovement.getFishY() && mouseY <= fishMovement.getFishY() + fish_left.getHeight()) {
                offsetX = mouseX - fishMovement.getFishX();
                offsetY = mouseY - fishMovement.getFishY();
                isDragging = true;
                isMoving = false; // Pause fish movement while dragging
            }
        }
        if(MouseHandler.mouseReleased){
            if (isDragging) {
                isDragging = false;
                isMoving = true; // Resume fish movement when released

                if (!isSpeedIncreased) {
                    isSpeedIncreased = true;
                    fishMovement.setSpeed(increasedSpeed);

                    // Schedule a timer to revert the speed back to the original value after 3 seconds
                    speedIncreaseTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            fishMovement.setSpeed(originalSpeed);
                            isSpeedIncreased = false;
                        }
                    }, 3000);
                }
            }
        }
        if(MouseHandler.mouseDragged){
            if (isDragging) {
                fishMovement.setInitialPosition(mouseX - offsetX, mouseY - offsetY);
            }
        }

    }

    public void move() {
        if (isMoving) {
            fishMovement.move();
        }
    }
    public void seek_food(List<Food.FoodLocation> foodLocations) {
        fishMovement.gotoFood(foodLocations);
    }



    private void getFishImages() {
        fish_left = null;
        fish_right = null;
    }

    protected void updateFishImages(String fish_type) {
        fish_left = SpriteSheet.setup("fish", fish_type + "_left");
        fish_right = SpriteSheet.setup("fish",fish_type + "_right");
    }


    public int getFishX() {
        return fishMovement.getFishX();
    }

    public int getFishY() {
        return fishMovement.getFishY();
    }

    public void render(Graphics2D g2) {
        BufferedImage fishImage = null;

        String currentDirection = fishMovement.getCurrentDirection();

        if (currentDirection.equals("left")) {
            fishImage = fish_left;
        } else if (currentDirection.equals("right")) {
            fishImage = fish_right;
        }

        g2.drawImage(fishImage, fishMovement.getFishX(), fishMovement.getFishY(), null);
    }

    public void callEatingLogic(Food food) {
        Eating_logic eatingLogic = new Eating_logic();
        eatingLogic.eatFood(food, this);
    }


    public int getFishWidth() {
        return fish_right.getWidth();
    }
    public int getFishHeight() {
        return fish_left.getHeight();
    }



}
