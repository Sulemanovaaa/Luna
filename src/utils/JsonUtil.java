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

/*
    public static void LoadFile(String path) {
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
*/

    public static Map<String, String> jsonToDictionary(String path) {
        try {
            return new Gson().fromJson(new JsonReader(new FileReader(path)), LinkedHashMap.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Recipe jsonToRecipe(String path) {
        try {
            return new Gson().fromJson(new JsonReader(new FileReader(path)), Recipe.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


//    private
//    List<String> recipesName
//    Map<String, String> dictionary = new LinkedHashMap<String, String>();
//        for (String )
}


