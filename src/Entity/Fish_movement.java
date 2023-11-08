package Entity;

import main.GamePanel;

import java.util.Random;

public class Fish_movement extends Entity {
    private final Random random = new Random();
    private int fishX;
    private int fishY;
    private int destinationX;
    private int destinationY;
    private double speed;
    public int maxX;
    public int maxY;
    private String currentDirection;
    private Food food;  // Reference to the Food entity

    public Fish_movement(int maxX, int maxY, GamePanel gp) {
        super(gp);
        this.maxX = maxX;
        this.maxY = maxY;
        this.food = food;
        setRandomDestination();
        speed = 3.5;
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

        double changeThreshold = 30.0;
        if (distance < changeThreshold) {
            setRandomDestination();
        } else {
            double ratio = speed / distance;
            int deltaX = (int) ((destinationX - fishX) * ratio);
            int deltaY = (int) ((destinationY - fishY) * ratio);

            if (fishX + deltaX < 0 || fishX + deltaX > maxX || fishY + deltaY < 0 || fishY + deltaY > maxY) {
                // Handle boundary conditions here, such as wrapping around the screen
                fishX = (fishX + deltaX) % maxX;
                fishY = (fishY + deltaY) % maxY;
            } else {
                int waddleOffsetX = (int) (2 * Math.sin(System.currentTimeMillis() * 0.005));
                int waddleOffsetY = (int) (2 * Math.cos(System.currentTimeMillis() * 0.005));
                if (deltaX != 0) {
                    fishX += deltaX + waddleOffsetY;
                } else {
                    fishX += deltaX + waddleOffsetX;
                }
                if (deltaY != 0) {
                    fishY += deltaY + waddleOffsetX;
                } else {
                    fishY += deltaY + waddleOffsetY;
                }
            }

            updateDirection();
        }
    }

    public void gotoFood() {
        destinationX = Food.SummonedFood.foodX;
        destinationY = Food.SummonedFood.foodY;
        updateDirection();
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
}
