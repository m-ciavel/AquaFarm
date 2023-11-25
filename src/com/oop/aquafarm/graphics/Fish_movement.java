package com.oop.aquafarm.graphics;

import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.util.Vector2f;

import java.util.List;
import java.util.Random;

public class Fish_movement {
    private final Random random = new Random();
    private int fishX;
    private int fishY;

    private Vector2f origin;
    private int destinationX;
    private int destinationY;
    private int speed;
    public int maxX;
    public int maxY;
    private String currentDirection;

    public Fish_movement(int screenWidth, int screenHeight) {

        this.maxX = screenWidth;
        this.maxY = screenHeight;
        setRandomDestination();
        speed = 3; // Default speed
    }

    public void setRandomDestination() {
        int newX, newY;
        do {
            newX = random.nextInt(maxX);
            newY = random.nextInt(maxY);
        } while (newX == destinationX && newY == destinationY);

        destinationX = newX;
        destinationY = newY;

        updateDirection();
    }

    public void setInitialPosition(Vector2f origin) {
        this.origin = origin;
        setRandomDestination();
    }

    public void move() {
        double distance = Math.sqrt((destinationX - fishX) * (destinationX - fishX) +
                (destinationY - fishY) * (destinationY - fishY));

        if (distance < 3.0) {
            // Fish is very close to the destination, set new random destination
            setRandomDestination();
        } else {
            double ratio = speed / distance;
            int deltaX = (int) ((destinationX - fishX) * ratio);
            int deltaY = (int) ((destinationY - fishY) * ratio);

            if (fishX + deltaX < 0 || fishX + deltaX > maxX || fishY + deltaY < 0 || fishY + deltaY > maxY) {

                setRandomDestination();
                updateDirection();

            } else {
                int waddleOffsetX = (int) (2 * Math.sin(System.currentTimeMillis() * 0.002));
                int waddleOffsetY = (int) (2 * Math.cos(System.currentTimeMillis() * 0.002));

                fishX += deltaX + waddleOffsetX;
                fishY += deltaY + waddleOffsetY;
            }

            updateDirection();
        }
    }

    private void updateDirection() {
        if (destinationX < fishX) {
            currentDirection = "left";
        } else if (destinationX > fishX) {
            currentDirection = "right";
        }
    }

    public String getCurrentDirection() {
        return currentDirection;
    }

    public int getFishX() {
        return fishX;
    }

    public int getFishY() {
        return fishY;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void gotoFood(List<Food.FoodLocation> foodLocations) {
        if (!foodLocations.isEmpty()) {

            Food.FoodLocation nearestFood = findNearestFood(foodLocations);

            destinationX = nearestFood.getLocationX();
            destinationY = nearestFood.getLocationY();

            updateDirection();

        }
    }

    private Food.FoodLocation findNearestFood(List<Food.FoodLocation> foodLocations) {
        Food.FoodLocation nearestFood = foodLocations.get(0);
        double minDistance = distanceToFood(nearestFood);

        for (Food.FoodLocation foodLocation : foodLocations) {
            double distance = distanceToFood(foodLocation);
            if (distance < minDistance) {
                minDistance = distance;
                nearestFood = foodLocation;
            }
        }

        return nearestFood;
    }

    private double distanceToFood(Food.FoodLocation foodLocation) {
        return Math.sqrt((foodLocation.getLocationX() - fishX) * (foodLocation.getLocationX() - fishX) +
                (foodLocation.getLocationY() - fishY) * (foodLocation.getLocationY() - fishY));
    }
}