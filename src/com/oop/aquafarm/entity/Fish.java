package com.oop.aquafarm.entity;

import com.oop.aquafarm.graphics.Fish_movement;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.states.PlayState;
import com.oop.aquafarm.util.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.oop.aquafarm.states.PlayState.origin;

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

    public boolean isBreedable;

    private int mouseX;
    private int mouseY;
    private int mouseB;

    private final int originalSpeed;
    private final int increasedSpeed;
    private final Timer speedIncreaseTimer;

    private Timer hungerTime;

    private String gender;

    public Fish(Vector2f origin, String fish_type, String fish_name, String gender) {
        super(origin);


        fishMovement = new Fish_movement(1280, 700);
        fishMovement.setInitialPosition(origin);
        getFishImages(); // Load fish images
        updateFishImages(fish_type);

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
        isBreedable = true;

        speedIncreaseTimer = new Timer();
        hungerTime = new Timer();


        hungerTime.schedule(new TimerTask() {
            @Override
            public void run() {
                isHungry = true;

            }
        }, 10000);

        addGender(gender);

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
        }, 10000);
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
        // Render fish image
        BufferedImage fishImage = null;
        String currentDirection = fishMovement.getCurrentDirection();

        if (currentDirection.equals("left")) {
            fishImage = fish_left;
        } else if (currentDirection.equals("right")) {
            fishImage = fish_right;
        }


        g2.drawImage(fishImage, fishMovement.getFishX(), fishMovement.getFishY(), null);


        if (isHungry) {
            int dotX = fishMovement.getFishX() + fishImage.getWidth() / 2 - 5; // Adjust the position of the dot
            int dotY = fishMovement.getFishY() - 10; // Adjust the position of the dot above the fish

            g2.setColor(Color.RED);
            g2.fillOval(dotX, dotY, 10, 10);
        }

        if (isBreedable) {
            int dotX = fishMovement.getFishX() + fishImage.getWidth() / 2 - 10; // Adjust the position of the dot
            int dotY = fishMovement.getFishY() - 10; // Adjust the position of the dot above the fish

            g2.setColor(Color.GREEN);
            g2.fillOval(dotX, dotY, 10, 10);
        }
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
                fishMovement.setInitialPosition(new Vector2f(mouseX - offsetX, mouseY - offsetY));
            }
        }

    }


    public void increaseSize() {

        if (isHungry && sizeIncreaseCounter < 2 && eatCounter >= 2) {
            int newWidth = (int) (fish_right.getWidth() * 1.125);
            int newHeight = (int) (fish_left.getHeight() * 1.125);

            // Resize the fish images
            fish_left = ScaledImage.scaledImage(fish_left, newWidth, newHeight);
            fish_right = ScaledImage.scaledImage(fish_right, newWidth, newHeight);

            sizeIncreaseCounter++;
            eatCounter = 0;
            if(sizeIncreaseCounter == 2){
                isBreedable = true;
            }
        }


    }

    public void callEatingLogic(Food food) {
        Eating_logic eatingLogic = new Eating_logic();
        eatingLogic.eatFood(food, this);
    }

    public void addGender(String gender) {
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;

        switch (randomNumber) {
            case 1:
                this.gender = "male";
                break;
            case 2:
                this.gender = "female";
                break;
            case 3:
                this.gender = "they";
                break;
            default:
                this.gender = "Unknown";
                break;
        }
    }

    public String getGender() {
        return gender;
    }



    public int getFishWidth() {
        return fish_right.getWidth();
    }
    public int getFishHeight() {
        return fish_left.getHeight();
    }

}
