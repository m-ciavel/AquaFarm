package com.oop.aquafarm.entity.fishes;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.util.MouseHandler;
import com.oop.aquafarm.util.Vector2f;

public class Clown_fish extends Fish {
    public Clown_fish(Vector2f origin, int initialX, int initialY) {
        super( origin, initialX, initialY);

        updateFishImages("ClownFish_left");
    }

    public void eatFood(Food food) {
        callEatingLogic(food);
    }
}