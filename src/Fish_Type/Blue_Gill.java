package Fish_Type;

import Entity.Fish;
import Entity.Food;
import main.GamePanel;

public class Blue_Gill extends Fish {
    public Blue_Gill(int initialX, int initialY, GamePanel gp) {
        super(initialX, initialY, gp);
        updateFishImages();
    }

    private void updateFishImages() {
        fish_left = setup("BlueGill_left");
        fish_right = setup("BlueGill_right");
    }
}