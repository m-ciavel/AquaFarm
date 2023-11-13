package com.oop.aquafarm.entity.fishes;

import com.oop.aquafarm.GamePanel;
import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;
import com.oop.aquafarm.util.MouseHandler;

public class Clown_fish extends Fish {
    public Clown_fish(int initialX, int initialY, GamePanel game, MouseHandler mouseIn) {
        super(initialX, initialY, game, mouseIn);

        updateFishImages("ClownFish_left");
    }

    public void eatFood(Food food) {
        callEatingLogic(food);
    }
}