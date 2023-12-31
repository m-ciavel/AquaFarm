package com.oop.aquafarm.entity;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.graphics.SpriteSheet;
import com.oop.aquafarm.states.PlayState;
import com.oop.aquafarm.util.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Food extends Entity {
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

            if (food.foodY > GamePanel.height) {
                iterator.remove();


            }
        }

        // Update food locations
        updateFoodLocations();


    }

    public void setDefaultValues() {
        clicked = false;
    }

    private void loadFoodImage() {
        foodImage = SpriteSheet.setup("food", "pellet_left");
        foodImage = ScaledImage.scaledImage(foodImage, foodSize, foodSize);
    }

    private void initFoodSpawnTimer() {
        foodSpawnTimer = new Timer(1, new ActionListener() {
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

    @Override
    public void render(Graphics2D g2) {
        for (SummonedFood food : ExistingFoods) {
            g2.drawImage(foodImage, food.foodX, food.foodY, null);
        }
    }

    @Override
    public void input(MouseHandler mouseIn) {
        int mouseB = mouseIn.getButton();

        if (mouseB == 1) {
            clicked = true;
        } else if (mouseB == -1) {
            clicked = false;
        }

        // Check for collision with the fish (you need to implement this logic)
        if (clicked && canSpawnFood && ExistingFoods.size() < 10 && Finance.money > 0) {
            int x = mouseIn.getX() - foodSize / 2;
            int y = mouseIn.getY() - foodSize / 2;
            Finance.money = Finance.money- 1;
            try {
                CRUD.spendMoney(PlayState.con1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ExistingFoods.add(new SummonedFood(x, y));
            canSpawnFood = false;


            if (!foodSpawnTimer.isRunning()) {
                foodSpawnTimer.start();
            }
        }
    }


    public static class SummonedFood {
        private int foodX;
        private int foodY;
        private double shakeAngle;

        public SummonedFood(int foodX, int foodY) {
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
