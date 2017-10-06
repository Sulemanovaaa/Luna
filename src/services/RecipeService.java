package services;

import entity.Recipe;
import utils.DirectoryUtil;
import utils.JsonUtil;
import utils.MapUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static utils.JsonUtil.jsonToDictionary;

public class RecipeService {

    Map<String, String> dictionary;

    public RecipeService(Map<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> getMenu(List<String> recipeFileNames) {
        List<String> recipeNames = new LinkedList<>();
        for (String recipeFileName : recipeFileNames) {
            if (dictionary.containsKey(recipeFileName))
                recipeNames.add(dictionary.get(recipeFileName));
        }
        return recipeNames;
    }

    public Recipe getRecipe(String recipeName) {
        if (dictionary != null && dictionary.containsValue(recipeName)) {
            return JsonUtil.jsonToRecipe(DirectoryUtil.RECIPES_PATH + MapUtil.getKeyByValue(dictionary, recipeName));
        }
        return null;
    }
}
