package services;

import com.google.gson.reflect.TypeToken;
import entity.Recipe;
import utils.DirectoryUtil;
import utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuService {

    private List<Recipe> menu;

    private DataService dataService;

    public MenuService(DataService dataService) {
        this.dataService = dataService;
    }

    public void init() {
        menu = new ArrayList<>();
    }

    public void loadMenu() {
        if (menu != null) {
            menu = JsonUtil.loadGenericList(new TypeToken<List<Recipe>>() {
            }.getType(), DirectoryUtil.DICTIONARY_PATH);
            menu = DirectoryUtil.getExistingRecipes(this.menu, DirectoryUtil.RECIPES_PATH);
        }
    }

    public boolean loadRecipes() {
        if (!menu.isEmpty()) {
            for (Recipe recipe : menu) {
                recipe.setStages(JsonUtil.loadGenericMap(new TypeToken<Map<Integer, List<Integer>>>() {
                }.getType(), DirectoryUtil.RECIPES_PATH + recipe.getFileName()));
            }
            return true;
        }
        return false;
    }

    public boolean setCookingTime() {
        if (menu.get(0).getStages() != null) {
            for (Recipe recipe : menu) {
                int cookingTime = 0;
                for (int stepId : recipe.getAllStepsIdInStages()) {
                    cookingTime += dataService.getStepById(stepId).getTime();
                }
                recipe.setCookingTime(cookingTime);
            }
            return true;
        }
        return false;
    }

    public List<Recipe> getMenu() {
        if (menu != null)
            return new ArrayList<>(menu);
        return null;
    }

    public Recipe getRecipeInMenuById(int id) {
        for (Recipe recipe : menu)
            if (recipe.getId() == id)
                return recipe;
        return null;
    }

    public static List<String> getFileNamesInMenu(List<Recipe> recipes) {
        List<String> fileNames = new ArrayList<>();
        for (Recipe recipe : recipes)
            fileNames.add(recipe.getFileName());
        return fileNames;
    }
}
