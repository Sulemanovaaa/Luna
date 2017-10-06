package services;

import entity.Recipe;
import utils.DirectoryUtil;
import utils.JsonUtil;
import utils.MapUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RecipeService {

    Map<String, String> dictionary;

    public RecipeService(Object dictionary) {
        this.dictionary = (Map<String, String>) dictionary;
    }

    public List<String> getMenu(List<String> recipeFileNames) {
        List<String> recipeNames = new LinkedList<>();
        for (String recipeFileName : recipeFileNames) {
            if (dictionary.containsKey(recipeFileName))
                recipeNames.add(dictionary.get(recipeFileName));
        }
        return recipeNames;
    }

    public Recipe getRecipe(Class recipe, String recipeName) {
        if (dictionary != null && dictionary.containsValue(recipeName)) {
            return (Recipe) JsonUtil.jsonToObject(recipe, DirectoryUtil.RECIPES_PATH + MapUtil.getKeyByValue(dictionary, recipeName)); //BAD SOLUTION
        }
        return null;
    }
}
