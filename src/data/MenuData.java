package data;

import entity.Menu;
import entity.Recipe;

public class MenuData {

    private Menu mainMenu = new Menu();

    public MenuData() {
        RecipeData recipes = new RecipeData();
        mainMenu.addRecipe(recipes.shaurma);
    }

    public Menu getMenu() {
        return mainMenu;
    }
}
