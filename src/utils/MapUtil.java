package utils;

import java.util.Map;

public class MapUtil {

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) { //BAD SOLUTION
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
