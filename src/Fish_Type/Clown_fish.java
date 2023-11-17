package Fish_Type;

import Entity.Fish;
import Entity.Food;
import main.GamePanel;

public class Clown_fish extends Fish {
    public Clown_fish(int initialX, int initialY, GamePanel gp) {
        super(initialX, initialY, gp);

        updateFishImages();
    }

    private void updateFishImages() {
        fish_left = setup("ClownFish_left");
        fish_right = setup("ClownFish_right");
    }



}

