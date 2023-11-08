package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.MouseInput;
import main.UtilityTool;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.Timer;

public class Food extends Entity {
    private final MouseInput mouseIn;
    private BufferedImage foodImage;
    private final List<SummonedFood> ExistingFoods = new ArrayList<>();
    private final int foodSize = 20;
    private Timer foodSpawnTimer;
    private boolean canSpawnFood = true;

    private double shakeAngle = 0; // Angle for shaking effect

    public Food(MouseInput mouseIn, GamePanel gp) {
        super(gp);
        this.mouseIn = mouseIn;
        this.gp = gp;
        setDefaultValues();
        loadFoodImage();
        initFoodSpawnTimer();
    }

    public void setDefaultValues() {
        clicked = false;
    }

    private void loadFoodImage() {
        foodImage = setup("pellet_left");
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/food/" + imageName + ".png")));
            image = uTool.scaledImage(image, foodSize, foodSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
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

    public void update() {
        clicked = mouseIn.mousePressed;

        if (clicked && canSpawnFood && ExistingFoods.size() < 10) {
            int x = mouseIn.getmouseX - foodSize / 2;
            int y = mouseIn.getmouseY - foodSize / 2;
            ExistingFoods.add(new SummonedFood(x, y, x, y)); // Pass x and y as foodX and foodY
            canSpawnFood = false;
            foodSpawnTimer.start();
        }

        // Calculate the shake angle
        double shakeAmplitude = 3;
        shakeAngle += 0.1;
        int foodSpeed = 0;

        // Update the positions of summoned foods to make them move down
        Iterator<SummonedFood> foodIterator = ExistingFoods.iterator();
        while (foodIterator.hasNext()) {
            SummonedFood food = foodIterator.next();

            // Apply a sinusoidal left-to-right shake effect
            // Adjust the amplitude of the shake
            SummonedFood.foodX += (int) (shakeAmplitude * Math.sin(food.shakeAngle));
            food.shakeAngle = shakeAngle; // Store the shake angle in the food

            // Adjust the speed as needed
            SummonedFood.foodY += foodSpeed;

            // Remove the food if it goes off the screen
            if (SummonedFood.foodY > gp.getHeight()) {
                foodIterator.remove();
            }
        }
    }



    public void draw(Graphics2D g2) {
        for (SummonedFood food : ExistingFoods) {
            g2.drawImage(foodImage, SummonedFood.foodX, SummonedFood.foodY, null);
        }
    }

    static class SummonedFood {
        public static int foodX;
        public static int foodY;
        private double shakeAngle; // Shake angle for each food

        public SummonedFood(int x, int y, int foodX, int foodY) {

            SummonedFood.foodX = foodX;
            SummonedFood.foodY = foodY;
            this.shakeAngle = 0;
        }
    }
}