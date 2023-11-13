package Entity;

import java.util.List;
import java.util.Random;

public class Fish_movement {
    private Random random = new Random();
    private int fishX;
    private int fishY;
    private int destinationX;
    private int destinationY;
    private int speed;
    public int maxX;
    public int maxY;
    private String currentDirection;

    public Fish_movement(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        setRandomDestination();
        speed = 2; // Default speed
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

    public void setInitialPosition(int x, int y) {
        fishX = x;
        fishY = y;
        setRandomDestination();
    }

    public void move() {
        double distance = Math.sqrt((destinationX - fishX) * (destinationX - fishX) +
                (destinationY - fishY) * (destinationY - fishY));

        if (distance < 1.0) {
            // Fish is very close to the destination, set new random destination
            setRandomDestination();
        } else {
            double ratio = speed / distance;
            int deltaX = (int) ((destinationX - fishX) * ratio);
            int deltaY = (int) ((destinationY - fishY) * ratio);

            if (fishX + deltaX < 0 || fishX + deltaX > maxX || fishY + deltaY < 0 || fishY + deltaY > maxY) {
                // Handle boundary conditions if needed
            } else {
                int waddleOffsetX = (int) (2 * Math.sin(System.currentTimeMillis() * 0.005));
                int waddleOffsetY = (int) (2 * Math.cos(System.currentTimeMillis() * 0.005));

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
        } else if (destinationY < fishY) {
            currentDirection = "up";
        } else if (destinationY > fishY) {
            currentDirection = "down";
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
        } else {
            // No food available, set random destination
            setRandomDestination();
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
