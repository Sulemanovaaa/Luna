import com.sun.org.apache.regexp.internal.RE;
import services.DataService;
import services.MenuService;
import services.RecipeService;

import javax.xml.crypto.Data;

public class DataInitializer {
    private DataService dataService;
    private MenuService menuService;


    public DataService initDataService() {
        DataService dataService = new DataService();
        dataService.init();
        dataService.loadData();
        this.dataService = dataService;
        return dataService;
    }

    public MenuService initMenuService() {
        MenuService menuService = new MenuService(dataService);
        menuService.init();
        menuService.loadMenu();
        menuService.loadRecipes();
        menuService.setCookingTime();
        this.menuService = menuService;
        return menuService;
    }

    public RecipeService initRecipeService() {
        RecipeService recipeService = new RecipeService(dataService, menuService);
        recipeService.init();
        return recipeService;
    }

}
