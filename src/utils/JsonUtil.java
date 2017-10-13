package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import entity.Recipe;
import entity.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    public static Object jsonToObject(Class template, String path) {
        try {
            return new Gson().fromJson(new JsonReader(new FileReader(path)), template);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

