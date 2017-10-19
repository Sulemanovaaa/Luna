package services;

import entity.Action;
import entity.Recipe;
import entity.Step;

import java.util.*;

public class RecipeService {

    private Recipe recipe;

    private DataService dataService;
    private MenuService menuService;

    public RecipeService(DataService dataService, MenuService menuService) {
        this.dataService = dataService;
        this.menuService = menuService;
    }

    public void init() {
        recipe = new Recipe();
    }

    public void setRecipe(int id) {
        recipe = menuService.getRecipeInMenuById(id);
    }

    public Recipe getRecipe() {
        return recipe;
    }

    //recipe = (Recipe) JsonUtil.jsonToObject(recipe.getClass(), DirectoryUtil.RECIPES_PATH + MapUtil.getKeyByValue(dictionary, recipeName)); // BAD SOLUTION

    public Step getStepInRecipe(int stepId) {
        return dataService.getSteps().get(stepId);
    }

    public List<Integer> getAllStepsIdInRecipe() {
        if (recipe != null) {
            return recipe.getAllStepsIdInStages();
        }
        return null;
    }

    public List<Action> getAllActionsInStep(int stepId) {
        List<Action> actionsInStep = new ArrayList<>();
        for (Integer actionId : recipe.getAllActionsIdInStep(stepId))
            actionsInStep.add(dataService.getActions().get(actionId));
        return actionsInStep;
    }
}
