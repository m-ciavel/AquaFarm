package com.oop.aquafarm.entity;

import com.oop.aquafarm.graphics.Fish_movement;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;
import com.oop.aquafarm.util.Eating_logic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Fish extends Entity {
    private final Fish_movement fishMovement;

    private int offsetX;
    private int offsetY;
    public boolean isDragging;
    private boolean isMoving;
    private boolean isSpeedIncreased;

    private int mouseX;
    private int mouseY;
    private int mouseB;

    private final int originalSpeed;
    private final int increasedSpeed;
    private final Timer speedIncreaseTimer;

    public Fish(Vector2f origin, int initialX, int initialY, String fish_type) {
        super(origin);

        fishMovement = new Fish_movement(1920, 1080);
        fishMovement.setInitialPosition(initialX, initialY);
        getFishImages(); // Load fish images
        updateFishImages(fish_type);

        // Initialize dragging variables
        offsetX = 0;
        offsetY = 0;
        isDragging = false;
        isMoving = true;
        isSpeedIncreased = false;
        originalSpeed = 2; // Set the original speed
        increasedSpeed = 15; // Set the increased speed
        speedIncreaseTimer = new Timer();

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

    @Override
    public void update(double time) {
//        update(time);

    }

    public void render(Graphics2D g2) {
//        dragAnimation();
        BufferedImage fishImage = null;

        String currentDirection = fishMovement.getCurrentDirection();

        if (currentDirection.equals("left")) {
            fishImage = fish_left;
        } else if (currentDirection.equals("right")) {
            fishImage = fish_right;
        }

        g2.drawImage(fishImage, fishMovement.getFishX(), fishMovement.getFishY(), null);
    }

    @Override
    public void input(MouseHandler mouseIn) {
        this.mouseX = mouseIn.getX();
        this.mouseY = mouseIn.getY();
        this.mouseB = mouseIn.getButton();

        // Check if the mouse click is on the fish
        if(mouseB == 1){
            if (mouseX >= fishMovement.getFishX() && mouseX <= fishMovement.getFishX() + fish_left.getWidth() &&
                    mouseY >= fishMovement.getFishY() && mouseY <= fishMovement.getFishY() + fish_left.getHeight()) {
                offsetX = mouseX - fishMovement.getFishX();
                offsetY = mouseY - fishMovement.getFishY();
                isDragging = true;
                isMoving = false; // Pause fish movement while dragging

            }
        }
        else if(mouseB == -1){
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
