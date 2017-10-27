package entity;

public class Cook {

    private EmotionProperties emotionProperties;

    public void init() {
        emotionProperties = new EmotionProperties();
    }

    public EmotionProperties getEmotionProperties() {
        return emotionProperties;
    }

    public void setEmotionProperties(EmotionProperties emotionProperties) {
        this.emotionProperties = emotionProperties;
    }
}
