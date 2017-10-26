package entity;

public class Reaction {

    private String name;
    private EmotionProperties conditions;
    private FoodProperties effect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmotionProperties getConditions() {
        return conditions;
    }

    public void setConditions(EmotionProperties conditions) {
        this.conditions = conditions;
    }

    public FoodProperties getEffect() {
        return effect;
    }

    public void setEffect(FoodProperties effect) {
        this.effect = effect;
    }
}
