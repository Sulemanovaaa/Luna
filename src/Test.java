import entity.Recipe;
import services.RecipeService;
import utils.DirectoryUtil;
import utils.JsonUtil;

import java.util.List;
import java.util.Map;

public class Test {

    //recipeNames - список для текста кнопок

    public static void main(String[] args) {

        RecipeService recipeService = new RecipeService();
        List<String> recipeNames = recipeService.getMenu(JsonUtil.jsonToDictionary(DirectoryUtil.DICTIONARY_PATH), DirectoryUtil.getRecipeFileNames(DirectoryUtil.RECIPES_PATH));
    }
}
