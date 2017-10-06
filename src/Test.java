import entity.Recipe;
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

        RecipeService recipeService = new RecipeService(JsonUtil.jsonToObject(LinkedHashMap.class, DirectoryUtil.DICTIONARY_PATH));

        List<String> recipeNames = recipeService.getMenu(DirectoryUtil.getRecipeFileNames(DirectoryUtil.RECIPES_PATH));

        //ВЫБОР РЕЦЕПТА

        Recipe recipe = recipeService.getRecipe(Recipe.class, "Шаурма"); // <-- Здесь текст нажатой кнопки
    }
}