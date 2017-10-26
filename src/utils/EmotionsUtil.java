package utils;

import entity.EmotionProperties;

import java.lang.reflect.Field;

public class EmotionsUtil {

    public final int CRITICAL_BORDER = 100;

    public int checkCriticalBorder(EmotionProperties cookEmotions, EmotionProperties conditions) throws IllegalAccessException, NoSuchFieldException {
        for (Field field : cookEmotions.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if ((int) field.get(cookEmotions) >= CRITICAL_BORDER) {
                //...
            }
        }
        return 0;
    }
}
