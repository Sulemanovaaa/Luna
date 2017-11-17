package entity;

public class Action {

    private String name;
    private EmotionProperties effect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmotionProperties getEffect() {
        return effect;
    }

    public void setEffect(EmotionProperties effect) {
        this.effect = effect;
    }
}
