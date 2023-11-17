package Fish_Type;

import Entity.Fish;
import Entity.Food;
import main.GamePanel;

public class Golden_Tench extends Fish {
    public Golden_Tench(int initialX, int initialY, GamePanel gp) {
        super(initialX, initialY, gp);
        updateFishImages();
    }

    private void updateFishImages() {
        fish_left = setup("GoldenTench_left"); 
        fish_right = setup("GoldenTench_right"); 
    }

}