package com.oop.aquafarm.entity;


import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.ScaledImage;
import com.oop.aquafarm.util.Vector2f;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Food extends Entity {
//    private final MouseHandler mouseIn;
    private BufferedImage foodImage;
    public final List<SummonedFood> ExistingFoods = new ArrayList<>();
    public final List<FoodLocation> foodLocations = new ArrayList<>();
    private static final int foodSize = 20;
    private Timer foodSpawnTimer;
    private boolean canSpawnFood = true;

    private double shakeAngle = 0; // Angle for shaking effect

    public Food(Vector2f origin) {
        super(origin);
        setDefaultValues();
        loadFoodImage();
        initFoodSpawnTimer();
    }

    @Override
    public void update(double time) {
        if (clicked && canSpawnFood && ExistingFoods.size() < 10) {
            int x = mouseIn.getX()- foodSize / 2;
            int y = mouseIn.getY() - foodSize / 2;

            ExistingFoods.add(new SummonedFood(x, y, x, y));
            canSpawnFood = false;
            foodSpawnTimer.start();
        }

        // Calculate the shake angle
        double shakeAmplitude = 3;
        shakeAngle += 0.1;

        // Update the positions of summoned foods to make them move down
        Iterator<SummonedFood> iterator = ExistingFoods.iterator();
        while (iterator.hasNext()) {
            SummonedFood food = iterator.next();

            // Apply a sinusoidal left-to-right shake effect
            food.foodX += (int) (shakeAmplitude * Math.cos(food.shakeAngle));
            food.shakeAngle = shakeAngle; // Store the shake angle in the food

            // Move the food down
            food.foodY += 1; // Adjust the value to control the speed of descent

            // Check for collision with the fish (you need to implement this logic)

            // Check if the food is off the screen and remove it
            if (food.foodY > game.getHeight()) {
                iterator.remove();
            }
        }

        // Update food locations
        updateFoodLocations();

        // Print food locations
        printFoodLocations();
    }

    public void setDefaultValues() {
        clicked = false;
    }

    private void loadFoodImage() {
        foodImage = SpriteSheet.setup("food", "pellet_left");
    }


    private void initFoodSpawnTimer() {
        foodSpawnTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canSpawnFood = true;
                foodSpawnTimer.stop();
            }
        });
    }


    private void updateFoodLocations() {
        // Clear existing locations
        foodLocations.clear();

        // Update locations based on current food positions
        for (SummonedFood food : ExistingFoods) {
            foodLocations.add(new FoodLocation(food.foodX, food.foodY));
        }
    }

    public void printFoodLocations() {
        System.out.println("Food Locations:");
        for (FoodLocation location : foodLocations) {
            System.out.println("X: " + location.getLocationX() + ", Y: " + location.getLocationY());
        }
    }

    public void render(Graphics2D g2) {
        for (SummonedFood food : ExistingFoods) {
            g2.drawImage(foodImage, food.foodX, food.foodY, null);
        }
    }

    @Override
    public void input(MouseHandler mouseIn) {
        clicked = mouseIn.mousePressed;
    }

    public static class SummonedFood {
        private int foodX;
        private int foodY;
        private double shakeAngle;

        public SummonedFood(int x, int y, int foodX, int foodY) {
            this.foodX = foodX;
            this.foodY = foodY;
            this.shakeAngle = 0;
        }

        public int getFoodX() {
            return foodX;
        }

        public int getFoodY() {
            return foodY;
        }

        public int getFoodSize() {
            return foodSize;
        }
    }

    public static class FoodLocation {
        private final int locationX;
        private final int locationY;

        public FoodLocation(int x, int y) {
            this.locationX = x;
            this.locationY = y;
        }

        public int getLocationX() {
            return locationX;
        }

        public int getLocationY() {
            return locationY;
        }
    }
}
