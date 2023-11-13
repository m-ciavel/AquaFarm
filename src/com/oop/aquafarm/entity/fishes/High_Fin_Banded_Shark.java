package com.oop.aquafarm.entity.fishes;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.util.MouseHandler;

public class High_Fin_Banded_Shark extends Fish {
    public High_Fin_Banded_Shark(int initialX, int initialY, GamePanel game, MouseHandler mouseIn) {
        super(initialX, initialY, game, mouseIn);
        updateFishImages("HIghFinBandedShark");
    }
        
    public void eatFood(Food food) {
        callEatingLogic(food);  // Call the eating logic from the Fish class
    }

}
