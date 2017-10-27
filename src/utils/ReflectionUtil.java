package utils;

import entity.FoodProperties;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static String getFieldName(Field field) {
        return field.getName();
    }

    public static Field getFieldByName(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static FoodProperties getFieldValueAsFoodProperties(Object obj, Field field) {
        try {
            return (FoodProperties) field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFieldValueAsString(Object obj, Field field) {
        try {
            return (String) field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getFieldByNameAsInt(Object obj, String fieldName) {
        try {
            Field field = getFieldByName(obj, fieldName);
            if (field != null) {
                return (int) field.get(obj);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getFieldValueByNameAsString(Object obj, String fieldName) {
        try {
            Field field = getFieldByName(obj, fieldName);
            if (field != null) {
                return (String) field.get(obj);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldValueByNameAsInt(String fieldName, Object obj, int delta) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            int currentValue = (int) field.get(obj);
            field.set(obj, currentValue + delta);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
