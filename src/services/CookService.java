package services;

import entity.*;
import utils.MapUtil;
import utils.ReflectionUtil;

import java.lang.reflect.Field;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CookService {

    private Cook cook;

    private StorageService storageService;
    private DishService dishService;

    private final String EMOTION_PROPERTY_NAME = "emotionName";
    private final String EMOTION_CRITICAL_BORDER_PROPERTY = "emotionCriticalBorder";
    private final String FOOD_EFFECT_PROPERTY_NAME = "effect";

    public CookService(StorageService storageService, DishService dishService)
    {
        this.storageService = storageService;
        this.dishService = dishService;
    }

    public void start() {
        init();
    }

    public void init() {
        cook = new Cook();
        cook.init();
        cook.getEmotionProperties().setAnger(1000);
        cook.getEmotionProperties().setFear(1000);
    }

    public void checkReactions() {
        List<Integer> reactionsId = checkCriticalBorders(storageService.getReactions());
        if (!reactionsId.isEmpty()) {
            changeDishProperties(storageService.getReactionsById(reactionsId));
        }
    }

    public void changeCookProperties(Action action) {
        for (Field field : cook.getEmotionProperties().getClass().getDeclaredFields())
            ReflectionUtil.setFieldValueByNameAsInt(field.getName(), cook.getEmotionProperties(), ReflectionUtil.getFieldByNameAsInt(action.getEffect(), field.getName()));
    }





    private List<Integer> checkCriticalBorders(Map<Integer, Reaction> reactions) {
        List<Integer> reactionsId = new ArrayList<>();
        for (Map.Entry<Integer, Reaction> pair : reactions.entrySet()) {
            for (Field field : pair.getValue().getClass().getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (fieldName.equals(EMOTION_PROPERTY_NAME)) {
                    String emotionPropertyName = ReflectionUtil.getFieldValueAsString(pair.getValue(), field);
                    if (borderCheck(getCook().getEmotionProperties(), pair.getValue(), emotionPropertyName)) {
                        reactionsId.add(pair.getKey());
                    }
                    break;
                }
            }
        }
        return reactionsId;
    }

    private void changeDishProperties(List<Reaction> reactions) {
        for (Reaction reaction : reactions) {
            Field effectField = ReflectionUtil.getFieldByName(reaction, FOOD_EFFECT_PROPERTY_NAME);
            FoodProperties reactionEffect = ReflectionUtil.getFieldValueAsFoodProperties(reaction, effectField);
            if (reactionEffect != null) {
                for (Field field : reactionEffect.getClass().getDeclaredFields()) {
                    ReflectionUtil.setFieldValueByNameAsInt(ReflectionUtil.getFieldName(field), dishService.getDish().getFoodProperties(), ReflectionUtil.getFieldByNameAsInt(reactionEffect, ReflectionUtil.getFieldName(field)));
                }
            }
        }
    }

    private boolean borderCheck(Object obj1, Object obj2, String fieldName) {
        int cookEmotionProperties = ReflectionUtil.getFieldByNameAsInt(obj1, fieldName);
        int criticalBorder = ReflectionUtil.getFieldByNameAsInt(obj2, EMOTION_CRITICAL_BORDER_PROPERTY);
        return cookEmotionProperties >= criticalBorder;
    }



    public Cook getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }
}
