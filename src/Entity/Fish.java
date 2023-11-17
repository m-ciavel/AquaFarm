package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

import Fish_Type.*;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Fish extends Entity {
    private final FishMovement fishMovement;
    private int offsetX;
    private int offsetY;
    int eatCounter;
    private int sizeIncreaseCounter;

    public boolean isDragging;
    private boolean isMoving;
    private boolean isSpeedIncreased;
    public boolean isHungry;

    private final int originalSpeed;
    private final int increasedSpeed;


    private final Timer speedIncreaseTimer;
    private Timer hungerTime;


    public Fish(int initialX, int initialY, GamePanel gp) {
        super(gp);


        fishMovement = new FishMovement(1920, 1080, this);
        fishMovement.setInitialPosition(initialX, initialY);
        this.gp = gp;
        getFishImages();

        offsetX = 0;
        offsetY = 0;
        originalSpeed = 2;
        increasedSpeed = 15;
        eatCounter = 0;
        sizeIncreaseCounter = 0;

        isDragging = false;
        isMoving = true;
        isSpeedIncreased = false;
        isHungry = false;

        speedIncreaseTimer = new Timer();
        hungerTime = new Timer();


        gp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                if (mouseX >= fishMovement.getFishX() && mouseX <= fishMovement.getFishX() +
                        fish_left.getWidth() &&
                        mouseY >= fishMovement.getFishY() && mouseY <= fishMovement.getFishY() + fish_left.getHeight()) {
                    offsetX = mouseX - fishMovement.getFishX();
                    offsetY = mouseY - fishMovement.getFishY();
                    isDragging = true;
                    isMoving = false; // Pause fish movement while dragging
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (isDragging) {
                    isDragging = false;
                    isMoving = true; // Resume fish movement when released

                    if (!isSpeedIncreased) {
                        isSpeedIncreased = true;
                        fishMovement.setSpeed(increasedSpeed);

                        // Schedule a timer to revert the speed back to the original value after 3 seconds
                        speedIncreaseTimer.schedule(new java.util.TimerTask() {
                            @Override
                            public void run() {
                                fishMovement.setSpeed(originalSpeed);
                                isSpeedIncreased = false;
                            }
                        }, 3000);
                    }
                }
            }
        });

        gp.addMouseMotionListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (isDragging) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();
                    fishMovement.setInitialPosition(mouseX - offsetX, mouseY - offsetY);
                }
            }
        });

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


    public void callEatingLogic(Food food) {
        EatingLogic eatinglogic = new EatingLogic();
        eatinglogic.eatFood(food, this);

    }


    public void increaseSize() {

        if (isHungry && sizeIncreaseCounter < 2 && eatCounter >= 10) {
            int newWidth = (int) (fish_right.getWidth() * 1.125);
            int newHeight = (int) (fish_left.getHeight() * 1.125);

            // Resize the fish images
            fish_left = UtilityTool.scaledImage(fish_left, newWidth, newHeight);
            fish_right = UtilityTool.scaledImage(fish_right, newWidth, newHeight);


            sizeIncreaseCounter++;
            eatCounter = 0;
        }

    }


    private void getFishImages() {
        fish_left = null;
        fish_right = null;
    }

    public void add_fish() {
//        keyHandler.p.tick();
//        keyHandler.key2.tick();
//        keyHandler.key3.tick();
//        keyHandler.key4.tick();
//        keyHandler.key5.tick();
//        keyHandler.key6.tick();

//       if (keyHandler.p.clicked) {
//            Atlantic_Bass fish1 = new Atlantic_Bass(100, 100, gp);
//            addFishToArray(fish1);
//        }
//        if (keyHandler.key2.clicked) {

//            Blue_Gill fish2 = new Blue_Gill(100, 100, gp);
//            addFishToArray(fish2);
//        }
//        if (keyHandler.key3.clicked) {
//            Clown_fish fish3 = new Clown_fish(100, 100, gp);
//            addFishToArray(fish3);
//        }
//        if (keyHandler.key4.clicked) {
//            Golden_Tench fish4 = new Golden_Tench(100, 100, gp);
//            addFishToArray(fish4);
//        }
//        if (keyHandler.key5.clicked) {
//            Guppy fish5 = new Guppy(100, 100, gp);
//            addFishToArray(fish5);
//        }
//        if (keyHandler.key6.clicked) {
//            High_Fin_Banded_Shark fish6 = new High_Fin_Banded_Shark(100, 100, gp);
//            addFishToArray(fish6);
//        }
    }


    public void addFishToArray(Fish fish) {
        for (int i = 0; i < gp.fishes.length; i++) {
            if (gp.fishes[i] == null) {
                gp.fishes[i] = fish;
                System.out.println(gp.fishes[i]);
                break;
            }
        }
    }







    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage fishImage = null;

        try {
            fishImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/fish/" + imageName + ".png")));
            fishImage = uTool.scaledImage(fishImage, gp.tilesize, gp.tilesize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fishImage;
    }


    public void draw(Graphics2D g2) {
        BufferedImage fishImage = null;

        String currentDirection = fishMovement.getCurrentDirection();

        if (currentDirection.equals("left")) {
            fishImage = fish_left;
        } else if (currentDirection.equals("right")) {
            fishImage = fish_right;
        }

        g2.drawImage(fishImage, fishMovement.getFishX(), fishMovement.getFishY(), null);
    }

    public int getFishWidth() {
        return fish_right.getWidth();
    }
    public int getFishHeight() {
        return fish_left.getHeight();
    }
    public int getFishX() {
        return fishMovement.getFishX();
    }
    public int getFishY() {
        return fishMovement.getFishY();
    }

}
