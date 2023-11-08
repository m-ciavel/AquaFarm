package Fish_Type;

import Entity.Fish;
import main.GamePanel;

public class Atlantic_Bass extends Fish {
    public Atlantic_Bass(int initialX, int initialY, GamePanel gp) {
        super(initialX, initialY, gp);
        updateFishImages(); 
    }

    private void updateFishImages() {
        fish_left = setup("AtlanticBass_left"); 
        fish_right = setup("AtlanticBass_right"); 
    }
}