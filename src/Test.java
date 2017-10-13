import entity.Action;
import entity.Recipe;
import entity.Stage;
import services.RecipeService;
import utils.DirectoryUtil;
import utils.JsonUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Test {

    //recipeNames - список для текста кнопок

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
        Recipe recipe = recipeService.getRecipe();

        for (Stage stage : recipe.getStages()) {
            List<Action> actionsInStage = recipeService.getAllActionsInStage(stage); // ПОЛУЧИТЬ ДЕЙСТВИЯ НА ОПРЕДЕЛЕННОМ ЭТАПЕ
            System.out.println();
        }
    }
}
