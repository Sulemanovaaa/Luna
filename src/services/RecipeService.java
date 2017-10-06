package services;

import utils.DirectoryUtil;
import utils.JsonUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static utils.JsonUtil.jsonToDictionary;

public class RecipeService {

    public List<String> getMenu(Map<String, String> dictionary, List<String> recipeFileNames) {
        List<String> recipeNames = new LinkedList<>();
        for (String recipeFileName : recipeFileNames) {
            if (dictionary.containsKey(recipeFileName))
                recipeNames.add(dictionary.get(recipeFileName));
        }
        return recipeNames;
    }
}
