package services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import entity.Action;
import entity.Reaction;
import entity.Recipe;
import entity.Stage;
import utils.DirectoryUtil;
import utils.JsonUtil;
import utils.MapUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;

public class RecipeService {

    private Map<String, String> dictionary;
    private Map<Integer, Action> actions;
    private Map<Integer, Reaction> reactions;
    private Recipe recipe;

    public void init() {
        dictionary = new HashMap<>();
        actions = new HashMap<>();
        reactions = new HashMap<>();
        recipe = new Recipe();
    }

    public void loadData() {
        if (dictionary != null) {
            dictionary = loadGenericMap(new TypeToken<Map<String, String>>(){}.getType(), DirectoryUtil.DICTIONARY_PATH);
            dictionary = DirectoryUtil.getExistingRecipes(dictionary, DirectoryUtil.RECIPES_PATH);
        }
        if (actions != null)
            actions = loadGenericMap(new TypeToken<Map<Integer, Action>>(){}.getType(), DirectoryUtil.ACTIONS_PATH);
        if (reactions != null)
            reactions = loadGenericMap(new TypeToken<Map<Integer, Reaction>>(){}.getType(), DirectoryUtil.REACTIONS_PATH);
    }

    public void loadRecipe(String recipeName) {
        if (dictionary.containsValue(recipeName)) {
            recipe = (Recipe) JsonUtil.jsonToObject(recipe.getClass(), DirectoryUtil.RECIPES_PATH + MapUtil.getKeyByValue(dictionary, recipeName));
        }
    }

    public List<String> getMenu() {
        if (dictionary != null)
            return new LinkedList<>(dictionary.values());
        return null;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public List<Action> getAllActionsInStage(Stage stage) {
        List<Action> actionsInStage = new ArrayList<>();
        for (Integer actionId : stage.getActions())
            actionsInStage.add(actions.get(actionId));
        return actionsInStage;
    }

    /*
        public Recipe getRecipe(Class recipe, String recipeName) {
            if (dictionary != null && dictionary.containsValue(recipeName)) {
                return (Recipe) JsonUtil.jsonToObject(recipe, DirectoryUtil.RECIPES_PATH + MapUtil.getKeyByValue(dictionary, recipeName)); //BAD SOLUTION
            }
            return null;
        }
    */

    private <T> List<T> loadList(Class template, String path) {
        return (List<T>) JsonUtil.jsonToObject(template, path);
    }

    private <T> List<T> loadGenericList(Type type, String path) {
        return (List<T>) JsonUtil.jsonToGenericObject(type, path);
    }

    private <T1, T2> Map<T1, T2> loadMap(Class template, String path) {
        return (Map<T1, T2>) JsonUtil.jsonToObject(template, path);
    }

    private <T1, T2> Map<T1, T2> loadGenericMap(Type type, String path) {
        return (Map<T1, T2>) JsonUtil.jsonToGenericObject(type, path);
    }

}
