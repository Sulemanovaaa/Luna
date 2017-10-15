import entity.Action;
import entity.Step;
import services.RecipeService;

import java.util.List;

public class Test {

    public static void main(String[] args) {

        InitNewRecipe();
    }

    private static void InitNewRecipe() {

        RecipeService recipeService = new RecipeService();
        recipeService.init();
        recipeService.loadData();

        List<String> recipeNames = recipeService.getMenu(); // ПОЛУЧИТЬ МЕНЮ

        //ВЫБОР РЕЦЕПТА

        recipeService.loadRecipe("Шаурма"); // <-- Здесь текст нажатой кнопки

        for (Step step : recipeService.getAllStepsInRecipe()) { // ПОЛУЧИТЬ ШАГИ БЛЮДА (ЗАОДНО СЧИТАЕТСЯ ВРЕМЯ ПРИГОТОВЛЕНИЯ)
            int cookingTime = recipeService.getCookingTime();
            List<Action> actionsInStep = recipeService.getAllActionsInStep(step); // ПОЛУЧИТЬ ДЕЙСТВИЯ НА ОПРЕДЕЛЕННОМ ЭТАПЕ
            System.out.println();
        }
    }
}
