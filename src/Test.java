import entity.*;
import services.*;

import java.util.List;

public class Test {

    public static void main(String[] args) {

        InitNewRecipe();
    }

    private static void InitNewRecipe() {

        StorageService storageService = new StorageService();
        storageService.start();

        MenuService menuService = new MenuService(storageService);
        menuService.start();

        DishService dishService = new DishService(storageService);
        dishService.start();

        CookService cookService = new CookService(storageService, dishService);
        cookService.start();



        List<Recipe> recipes = menuService.getMenu(); // ПОЛУЧИТЬ МЕНЮ (СПИСОК РЕЦЕПТОВ)

        // ЗДЕСЬ ПРОИСХОДИТ ВЫБОР РЕЦЕПТА С ВОЗВРАТОМ ID ВЫБРАННОГО РЕЦЕПТА:
        // int id = ...

        RecipeService recipeService = new RecipeService(storageService, menuService);
        recipeService.init();
        recipeService.setRecipe(1); // ЗДЕСЬ ID, КОТОРЫЙ ПОЛУЧЕН ВЫШЕ
        recipeService.initIterator();
        Recipe recipe = recipeService.getRecipe(); // ПОЛУЧИТЬ ВЫБРАННЫЙ РЕЦЕПТ


        // 1 ВАРИАНТ ПОЛУЧЕНИЯ РЕЦЕПТА И ЕГО ДАННЫХ
        recipeService.iteratorNext();
        Step step1 = recipeService.getCurrentStepInRecipe();
        List<Action> actions1 = recipeService.getAllActionsInCurrentStep();
        recipeService.iteratorNext();
        Step step2 = recipeService.getCurrentStepInRecipe();
        List<Action> actions2 = recipeService.getAllActionsInCurrentStep();
        // И Т.Д.

        // 2 ВАРИАНТ ПОЛУЧЕНИЯ РЕЦЕПТА И ЕГО ДАННЫХ
        for (int stepId : recipeService.getAllStepsIdInRecipe()) { // ПОЛУЧИТЬ ШАГИ БЛЮДА
            Step step = recipeService.getStepInRecipe(stepId); //ПОЛУЧИТЬ ШАГ
            List<Action> actionsInStep = recipeService.getAllActionsInStep(stepId); // ПОЛУЧИТЬ ДЕЙСТВИЯ НА ШАГЕ
            List<Integer> doReactionsId = cookService.checkReactions(); // ПРОВЕРЯЕМ ЭМОЦИИ ПОВАРА ПОСЛЕ КАЖДОГО ДЕЙСТВИЯ
/*
            for (int reactionId : doReactionsId) {
                Reaction reaction = storageService.getReactionById(reactionId);
                // пишешь reaction.getName();
                if (cookService.checkHappenedReaction(reactionId)) {
                    return;
                }
            }
*/
            cookService.changeCookProperties(actionsInStep.get(1)); // ИЗМЕНЕНИЕ ЭМОЦИЙ ПОСЛЕ ДЕЙСТВИЯ
            String description = dishService.getDescriptionOfTheDish(); // ОПИСАНИЕ БЛЮДА
            System.out.println(); //ДЛЯ ОТЛАДКИ
        }
    }
}
