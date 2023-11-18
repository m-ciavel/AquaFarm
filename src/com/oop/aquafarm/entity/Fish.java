package com.oop.aquafarm.entity;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.Fish_movement;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Fish extends Entity {
    private final Fish_movement fishMovement;

    private int offsetX;
    private int offsetY;

    public int eatCounter;
    private int sizeIncreaseCounter;

    public boolean isDragging;
    private boolean isMoving;
    private boolean isSpeedIncreased;

    public boolean isHungry;

    private int mouseX;
    private int mouseY;
    private int mouseB;

    private final int originalSpeed;
    private final int increasedSpeed;
    private final Timer speedIncreaseTimer;

    private Timer hungerTime;

    public Fish(Vector2f origin, int initialX, int initialY, String fish_type) {
        super(origin);

        fishMovement = new Fish_movement(1280, 720);
        fishMovement.setInitialPosition(initialX, initialY);
        getFishImages(); // Load fish images
        updateFishImages(fish_type);

        // Initialize dragging variables
        offsetX = 0;
        offsetY = 0;

        originalSpeed = 2; // Set the original speed
        increasedSpeed = 15; // Set the increased speed

        eatCounter = 0;
        sizeIncreaseCounter = 0;

        isDragging = false;
        this.isMoving = true;
        isSpeedIncreased = false;
        isHungry = false;

        speedIncreaseTimer = new Timer();
        hungerTime = new Timer();

        hungerTime.schedule(new TimerTask() {
            @Override
            public void run() {
                isHungry = true;

            }
        }, 60000);

    }


    public void seek_food(java.util.List<Food.FoodLocation> foodLocations) {

        fishMovement.move();
        if (isHungry) {
            fishMovement.gotoFood(foodLocations);
            resetHungerTimer();

        }
    }

    private void resetHungerTimer() {

        hungerTime.cancel();

        hungerTime = new Timer();
        hungerTime.schedule(new TimerTask() {
            @Override
            public void run() {
                isHungry = true;
            }
        }, 60000);
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


    public void increaseSize() {

        if (isHungry && sizeIncreaseCounter < 2 && eatCounter >= 10) {
            int newWidth = (int) (fish_right.getWidth() * 1.125);
            int newHeight = (int) (fish_left.getHeight() * 1.125);

            // Resize the fish images
            fish_left = ScaledImage.scaledImage(fish_left, newWidth, newHeight);
            fish_right = ScaledImage.scaledImage(fish_right, newWidth, newHeight);


            sizeIncreaseCounter++;
            eatCounter = 0;
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
