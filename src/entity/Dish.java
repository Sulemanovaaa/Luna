package entity;

public class Dish {

    private FoodProperties foodProperties;

    public void init() {
        foodProperties = new FoodProperties();
    }

    public FoodProperties getFoodProperties() {
        return foodProperties;
    }

    public void setFoodProperties(FoodProperties foodProperties) {
        this.foodProperties = foodProperties;
    }
}
