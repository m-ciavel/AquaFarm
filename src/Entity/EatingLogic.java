package Entity;

import java.util.Iterator;

public class EatingLogic {

    public void eatFood(Food food, Fish fish) {
        if (fish.isHungry) {
            Iterator<Food.SummonedFood> foodIterator = food.ExistingFoods.iterator();
            Iterator<Food.FoodLocation> locationIterator = food.foodLocations.iterator();

            while (foodIterator.hasNext()) {
                Food.SummonedFood summonedFood = foodIterator.next();
                Food.FoodLocation foodLocation = locationIterator.next();

                if (isColliding(fish, summonedFood)) {
                    foodIterator.remove();
                    locationIterator.remove();
                    fish.eatCounter++;
                    fish.increaseSize();
                    fish.isHungry = false;

                    return;
                }
            }
        }
    }

    private boolean isColliding(Fish fish, Food.SummonedFood food) {
        int fishX = fish.getFishX();
        int fishY = fish.getFishY();
        int foodX = food.getFoodX();
        int foodY = food.getFoodY();
        int foodSize = food.getFoodSize();

        return fishX < foodX + foodSize &&
                fishX + fish.getFishWidth() > foodX &&
                fishY < foodY + foodSize &&
                fishY + fish.getFishHeight() > foodY;
    }


    public void breeding(){


    }
}
