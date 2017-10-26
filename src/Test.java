import entity.Action;
import entity.Recipe;
import entity.Step;
import services.StorageService;
import services.MenuService;
import services.RecipeService;

import java.util.List;

public class Test {

    public static void main(String[] args) {

        InitNewRecipe();
    }

    private static void InitNewRecipe() {

        StorageService storageService = new StorageService();
        storageService.init();
        storageService.loadData();

        MenuService menuService = new MenuService(storageService);
        menuService.init();
        menuService.loadMenu();
        menuService.loadRecipes();
        menuService.setCookingTime();

        List<Recipe> recipes = menuService.getMenu(); // ПОЛУЧИТЬ МЕНЮ (СПИСОК РЕЦЕПТОВ)

        // ЗДЕСЬ ПРОИСХОДИТ ВЫБОР РЕЦЕПТА С ВОЗВРАТОМ ID ВЫБРАННОГО РЕЦЕПТА:
        // int id = ...

        RecipeService recipeService = new RecipeService(storageService, menuService);
        recipeService.init();
        recipeService.setRecipe(1); // ЗДЕСЬ ID, КОТОРЫЙ ПОЛУЧЕН ВЫШЕ
        recipeService.initIterator();
        Recipe recipe = recipeService.getRecipe(); // ПОЛУЧИТЬ ВЫБРАННЫЙ РЕЦЕПТ

        // 1 ВАРИАНТ ПОЛУЧЕНИЯ РЕЦЕПТА И ЕГО ДАННЫХ
        Step step1 = recipeService.getStepInRecipeViaIterator();
        List<Action> actions1 = recipeService.getAllActionsInStepViaIterator();
        Step step2 = recipeService.getStepInRecipeViaIterator();
        List<Action> actions2 = recipeService.getAllActionsInStepViaIterator();
        // И Т.Д.

        // 2 ВАРИАНТ ПОЛУЧЕНИЯ РЕЦЕПТА И ЕГО ДАННЫХ
        for (int stepId : recipeService.getAllStepsIdInRecipe()) { // ПОЛУЧИТЬ ШАГИ БЛЮДА
            Step step = recipeService.getStepInRecipe(stepId); //ПОЛУЧИТЬ ШАГ
            List<Action> actionsInStep = recipeService.getAllActionsInStep(stepId); // ПОЛУЧИТЬ ДЕЙСТВИЯ НА ШАГЕ
            System.out.println(); //ДЛЯ ОТЛАДКИ
        }
    }
}
