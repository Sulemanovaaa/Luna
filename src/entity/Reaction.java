package entity;

public class Reaction {

    private String name;
    private String emotionName;
    private int emotionCriticalBorder;
    private FoodProperties effect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmotionName() {
        return emotionName;
    }

    public void setEmotionName(String emotionName) {
        this.emotionName = emotionName;
    }

    public int getEmotionCriticalBorder() {
        return emotionCriticalBorder;
    }

    public void setEmotionCriticalBorder(int emotionCriticalBorder) {
        this.emotionCriticalBorder = emotionCriticalBorder;
    }

    public FoodProperties getEffect() {
        return effect;
    }

    public void setEffect(FoodProperties effect) {
        this.effect = effect;
    }
}
