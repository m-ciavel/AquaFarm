package com.oop.aquafarm.entity.fishes;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.util.MouseHandler;

public class Guppy extends Fish {
    public Guppy(int initialX, int initialY, GamePanel game, MouseHandler mouseIn) {
        super(initialX, initialY, game, mouseIn);
        updateFishImages("Guppy");
    }

    public void eatFood(Food food) {
        callEatingLogic(food);  // Call the eating logic from the Fish class
    }
}
