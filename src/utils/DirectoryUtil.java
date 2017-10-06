package utils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class DirectoryUtil {

    public static final String RECIPES_PATH = "./resources/recipes";
    public static final String DICTIONARY_PATH = "./resources/dictionary.json";
    private static final String JSON_EXTENSION = "json";

    public static List<String> getRecipeFileNames(String path) {
        List<String> recipes = null;
        File directory = new File(path);
        if (directory.isDirectory() && !Objects.equals(directory.listFiles(), null)) {
            recipes = new LinkedList<>();
            for (File item : directory.listFiles()) {
                String fileName = item.getName();
                if (getFileExtension(fileName).equals(JSON_EXTENSION)) {
                    recipes.add(fileName);
                }
            }
        }
        return recipes;
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }
}
