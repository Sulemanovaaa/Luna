package entity;

public class Cook {

    private EmotionProperties emotionProperties;
    private CookStates state;

    public void init() {
        emotionProperties = new EmotionProperties();
        state = state.FREE;
    }

    public EmotionProperties getEmotionProperties() {
        return emotionProperties;
    }

    public void setEmotionProperties(EmotionProperties emotionProperties) {
        this.emotionProperties = emotionProperties;
    }
}
