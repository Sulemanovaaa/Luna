package entity;

import utils.ReflectionUtil;

import java.lang.reflect.Field;

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

    @Override
    public String toString() {
        final String COLON = ":";
        StringBuilder output = new StringBuilder();
        for (Field field : emotionProperties.getClass().getDeclaredFields()) {
            output.append(ReflectionUtil.getFieldName(field));
            output.append(COLON);
            output.append(" ");
            output.append(ReflectionUtil.getFieldValueAsInt(this.emotionProperties, field));
            output.append("/n");
        }
        return output.toString();
    }
}
