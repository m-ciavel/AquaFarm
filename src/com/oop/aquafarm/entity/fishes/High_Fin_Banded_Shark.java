package com.oop.aquafarm.entity.fishes;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

public class High_Fin_Banded_Shark extends Fish {
    public High_Fin_Banded_Shark(Vector2f origin, int initialX, int initialY) {
        super( origin, "HIghFinBandedShark");
//        updateFishImages("HIghFinBandedShark");
    }
        
    public void eatFood(Food food) {
        callEatingLogic(food);  // Call the eating logic from the Fish class
    }

}
