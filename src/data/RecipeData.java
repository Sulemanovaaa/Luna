package data;

import entity.Recipe;

public class RecipeData {
    private StageData stages = new StageData();
    Recipe shaurma = new Recipe("Шаурма", 60);

    public RecipeData() {
        shaurma.addStage(stages.cutVegetables);
    }


}
