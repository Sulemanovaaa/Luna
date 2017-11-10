import services.*;

public class DataInitializer {

    private StorageService storageService;
    private MenuService menuService;
    private DishService dishService;

    public StorageService initDataService() {
        storageService = new StorageService();
        storageService.start();
        return storageService;
    }

    public MenuService initMenuService() {
        menuService = new MenuService(storageService);
        menuService.start();
        return menuService;
    }

    public DishService initDishService() {
        dishService = new DishService();
        dishService.start();
        return dishService;
    }

    public CookService initCookService() {
        CookService cookService = new CookService(storageService, dishService);
        cookService.start();
        return cookService;
    }

    public RecipeService initRecipeService() {
        RecipeService recipeService = new RecipeService(storageService, menuService);
        recipeService.start();
        return recipeService;
    }
}
