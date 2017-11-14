package services;

import entity.Dish;

public class DishService {

    private Dish dish;

    public void start() {
        init();

    }

    public void init() {
        dish = new Dish();
        dish.init();
        dish.getFoodProperties().setQuality(50);
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
