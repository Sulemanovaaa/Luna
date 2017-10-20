package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    public static <T> List<T> loadList(Class template, String path) {
        return (List<T>) JsonUtil.jsonToObject(template, path);
    }

    public static <T> List<T> loadGenericList(Type type, String path) {
        return (List<T>) JsonUtil.jsonToGenericObject(type, path);
    }

    public static <T1, T2> Map<T1, T2> loadMap(Class template, String path) {
        return (Map<T1, T2>) JsonUtil.jsonToObject(template, path);
    }

    public static <T1, T2> Map<T1, T2> loadGenericMap(Type type, String path) {
        return (Map<T1, T2>) JsonUtil.jsonToGenericObject(type, path);
    }

    public static Object jsonToObject(Class template, String path) {
        if (DirectoryUtil.FileExists(path)) {
            try {
                return new Gson().fromJson(new JsonReader(new FileReader(path)), template);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return null;
    }

    public static Object jsonToGenericObject(Type type, String path) {
        if (DirectoryUtil.FileExists(path)) {
            try {
                return new Gson().fromJson(new JsonReader(new FileReader(path)), type);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return null;
    }
}

