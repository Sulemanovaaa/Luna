import com.sun.org.apache.regexp.internal.RE;
import services.StorageService;
import services.MenuService;
import services.RecipeService;
import services.StorageService;

import javax.xml.crypto.Data;

public class DataInitializer {
    private StorageService storageService;
    private MenuService menuService;


    public StorageService initDataService() {
        storageService = new StorageService();
        storageService.init();
        storageService.loadData();
        return storageService;
    }

    public MenuService initMenuService() {
        menuService = new MenuService(storageService);
        menuService.init();
        menuService.loadMenu();
        menuService.loadRecipes();
        menuService.setCookingTime();
        return menuService;
    }

    public RecipeService initRecipeService() {
        RecipeService recipeService = new RecipeService(storageService, menuService);
        recipeService.init();
        return recipeService;
    }

}
