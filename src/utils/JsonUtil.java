package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

public class JsonUtil {

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

