package Fish_Type;

import Entity.Fish;
import Entity.Food;
import main.GamePanel;

public class Guppy extends Fish {
    public Guppy(int initialX, int initialY, GamePanel gp) {
        super(initialX, initialY, gp);
        updateFishImages();
    }

    private void updateFishImages() {
        fish_left = setup("Guppy_left"); 
        fish_right = setup("Guppy_right"); 
    }
    public void eatFood(Food food) {
        callEatingLogic(food);  // Call the eating logic from the Fish class
    }
}