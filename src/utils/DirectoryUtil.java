package utils;

import entity.Recipe;
import services.MenuService;

import java.io.File;
import java.util.*;

public class DirectoryUtil {

    public static final String RECIPES_PATH = "./resources/recipes/";
    public static final String DICTIONARY_PATH = "./resources/menu.json";
    public static final String STEPS_PATH = "./resources/steps.json";
    public static final String ACTIONS_PATH = "./resources/actions.json";
    public static final String REACTIONS_PATH = "./resources/reactions.json";
    public static final String EMOTION_TO_DISH = "./resources/emotiontodish.json";
    private static final String JSON_EXTENSION = "json";

    public static boolean FileExists(String path) {
        return new File(path).isFile();
    }

    public static List<Recipe> getExistingRecipes(List<Recipe> recipes, String path) {
        Collection<String> names = new ArrayList<>(MenuService.getFileNamesInMenu(recipes));
        Collection<String> fileNames = new ArrayList<>(getFileNames(path));

        names.removeAll(fileNames);
        if (!names.isEmpty()) {
            for (int index = 0; index < recipes.size(); index++) {
                for (String name : names) { //BAD SOLUTION
                    if (recipes.get(index).getFileName().equals(name)) {
                        recipes.remove(index);
                    }
                }
            }
        }
        return recipes;
    }

    private static List<String> getFileNames(String path) {
        List<String> recipes = null;
        File directory = new File(path);
        if (directory.isDirectory() && !Objects.equals(directory.listFiles(), null)) {
            recipes = new ArrayList<>();
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
