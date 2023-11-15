package com.oop.aquafarm.entity.fishes;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

public class Golden_Tench extends Fish {
    public Golden_Tench(Vector2f origin, int initialX, int initialY) {
        super( origin, initialX, initialY, "GoldenTench");
//        updateFishImages("GoldenTench");
    }

    public void eatFood(Food food) {
        callEatingLogic(food);  // Call the eating logic from the Fish class
    }
}