package services;

import entity.Action;
import entity.Recipe;
import entity.Step;

import java.util.*;

public class RecipeService {

    private Recipe recipe;

    private StorageService storageService;
    private MenuService menuService;

    public RecipeService(StorageService storageService, MenuService menuService) {
        this.storageService = storageService;
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

    public Step getStepInRecipe(int stepId) {
        return storageService.getSteps().get(stepId);
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
            actionsInStep.add(storageService.getActions().get(actionId));
        return actionsInStep;
    }
}
