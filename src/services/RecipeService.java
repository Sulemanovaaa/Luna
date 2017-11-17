package services;

import entity.*;
import java.util.*;

public class RecipeService {

    private Recipe recipe;

    private StorageService storageService;
    private MenuService menuService;

    private Iterator iterator;
    private Map.Entry iteratorPair;


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

    public void initIterator() {
        iterator = recipe.getStages().entrySet().iterator();
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


    public void iteratorNext() {
        if (iterator.hasNext())
            iteratorPair = (Map.Entry) iterator.next();
    }

    public Step getCurrentStepInRecipe() {
        return getStepInRecipe((Integer) iteratorPair.getKey());
    }

    public List<Action> getAllActionsInCurrentStep() {
        return getAllActionsInStep((Integer) iteratorPair.getKey());
    }
}
