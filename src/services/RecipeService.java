package services;

import com.google.gson.reflect.TypeToken;
import entity.Action;
import entity.Reaction;
import entity.Recipe;
import entity.Step;
import utils.DirectoryUtil;
import utils.JsonUtil;
import utils.MapUtil;

import java.lang.reflect.Type;
import java.util.*;

public class RecipeService {

    private Map<String, String> dictionary;
    private Map<Integer, Step> steps;
    private Map<Integer, Action> actions;
    private Map<Integer, Reaction> reactions;
    private Recipe recipe;

    public void init() {
        dictionary = new HashMap<>();
        steps = new HashMap<>();
        actions = new HashMap<>();
        reactions = new HashMap<>();
        recipe = new Recipe();
    }

    public void loadData() {
        if (dictionary != null) {
            dictionary = loadGenericMap(new TypeToken<Map<String, String>>(){}.getType(), DirectoryUtil.DICTIONARY_PATH);
            dictionary = DirectoryUtil.getExistingRecipes(dictionary, DirectoryUtil.RECIPES_PATH);
        }
        if (steps != null)
            steps = loadGenericMap(new TypeToken<Map<Integer, Step>>(){}.getType(), DirectoryUtil.STEPS_PATH);
        if (actions != null)
            actions = loadGenericMap(new TypeToken<Map<Integer, Action>>(){}.getType(), DirectoryUtil.ACTIONS_PATH);
        if (reactions != null)
            reactions = loadGenericMap(new TypeToken<Map<Integer, Reaction>>(){}.getType(), DirectoryUtil.REACTIONS_PATH);
    }

    public void loadRecipe(String recipeName) {
        if (dictionary.containsValue(recipeName)) {
            recipe = (Recipe) JsonUtil.jsonToObject(recipe.getClass(), DirectoryUtil.RECIPES_PATH + MapUtil.getKeyByValue(dictionary, recipeName)); // BAD SOLUTION
        }
    }

    public List<Step> getAllStepsInRecipe() {
        if (recipe != null) {
            List<Step> stepsInRecipe = new ArrayList<>();
            int cookingTime = 0;
            for (int stepId : recipe.getAllStepsInStages()) {
                Step step = steps.get(stepId);
                stepsInRecipe.add(step);
                cookingTime += step.getTime();
            }
            recipe.setTime(cookingTime);
            return stepsInRecipe;
        }
        return null;
    }

    public List<Action> getAllActionsInStep(Step step) {
        List<Action> actionsInStep = new ArrayList<>();
        for (Integer actionId : recipe.getAllActionsInStep(MapUtil.getKeyByValue(steps, step))) // BAD SOLUTION
            actionsInStep.add(actions.get(actionId));
        return actionsInStep;
    }

    public List<String> getMenu() {
        if (dictionary != null)
            return new ArrayList<>(dictionary.values());
        return null;
    }

    public int getCookingTime() {
        return recipe.getTime();
    }

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
