package com.oop.aquafarm.util;

import com.oop.aquafarm.entity.Fish;
import com.oop.aquafarm.entity.Food;

import java.util.Iterator;

public class Eating_logic {
    public void eatFood(Food food, Fish fish) {
        Iterator<Food.SummonedFood> iterator = food.ExistingFoods.iterator();
        Iterator<Food.FoodLocation> locationIterator = food.foodLocations.iterator();

        while (iterator.hasNext()) {
            Food.SummonedFood summonedFood = iterator.next();
            Food.FoodLocation foodLocation = locationIterator.next();

            if (isColliding(fish, summonedFood)) {
                iterator.remove();
                locationIterator.remove();
                return;
            }
        }
    }

    private boolean isColliding(Fish fish, Food.SummonedFood food) {
        int fishX = fish.getFishX();
        int fishY = fish.getFishY();
        int foodX = food.getFoodX();
        int foodY = food.getFoodY();

        return fishX < foodX + food.getFoodSize() &&
                fishX + fish.getFishWidth() > foodX &&
                fishY < foodY + food.getFoodSize() &&
                fishY + fish.getFishHeight() > foodY;
    }
}