package Fish_Type;

import Entity.Fish;
import Entity.Food;
import main.GamePanel;

public class High_Fin_Banded_Shark extends Fish {
    public High_Fin_Banded_Shark(int initialX, int initialY, GamePanel gp) {
        super(initialX, initialY, gp);
        updateFishImages();
    }

    private void updateFishImages() {
        fish_left = setup("HIghFinBandedShark_left"); 
        fish_right = setup("HIghFinBandedShark_right"); 
    }
}